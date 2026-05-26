import { useEffect, useMemo, useState } from 'react';
import { useAuth0 } from '@auth0/auth0-react';
import {
  AlertCircle,
  Car,
  CheckCircle2,
  Clock,
  LogOut,
  ParkingCircle,
  RefreshCw,
  ShieldCheck,
  UserPlus
} from 'lucide-react';

import {
  createParkingEventSource,
  getParkingSpaces,
  reserveParkingSpace
} from './services/parkingService.js';

import { createStudent, getStudents } from './services/studentService.js';

const emptyProfile = {
  identification: '',
  institutionalCode: '',
  name: '',
  lastName: '',
  mobileNumber: '',
  vehiclePlate: ''
};

function App() {
  const {
    isAuthenticated,
    isLoading,
    user,
    loginWithRedirect,
    logout,
    getAccessTokenSilently
  } = useAuth0();

  const [parkingSpaces, setParkingSpaces] = useState([]);
  const [selectedSpace, setSelectedSpace] = useState(null);
  const [vehiclePlate, setVehiclePlate] = useState(
    () => localStorage.getItem('ucoparking_plate') || ''
  );

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [modalError, setModalError] = useState('');

  // Student profile state
  const [studentProfile, setStudentProfile] = useState(null);
  const [showProfileForm, setShowProfileForm] = useState(false);
  const [profileForm, setProfileForm] = useState(emptyProfile);
  const [profileLoading, setProfileLoading] = useState(false);
  const [profileError, setProfileError] = useState('');

  const summary = useMemo(() => {
    const available = parkingSpaces.filter((s) => s.status === 'AVAILABLE').length;
    const reserved = parkingSpaces.filter((s) => s.status === 'RESERVED').length;
    const occupied = parkingSpaces.filter((s) => s.status === 'OCCUPIED').length;
    return { available, reserved, occupied, total: parkingSpaces.length };
  }, [parkingSpaces]);

  async function getToken() {
    return getAccessTokenSilently({
      authorizationParams: { audience: import.meta.env.VITE_AUTH0_AUDIENCE }
    });
  }

  async function loadParkingSpaces() {
    setLoading(true);
    setError('');
    try {
      const accessToken = isAuthenticated ? await getToken() : null;
      const data = await getParkingSpaces(accessToken);
      setParkingSpaces(data || []);
    } catch (err) {
      setError(err.message || 'No se pudieron cargar los espacios.');
    } finally {
      setLoading(false);
    }
  }

  async function checkStudentProfile() {
    try {
      const students = await getStudents();
      const found = (students || []).find(
        (s) => s.email?.toLowerCase() === user?.email?.toLowerCase()
      );
      if (found) {
        setStudentProfile(found);
        if (found.vehiclePlate) {
          setVehiclePlate(found.vehiclePlate);
          localStorage.setItem('ucoparking_plate', found.vehiclePlate);
        }
      } else {
        setShowProfileForm(true);
        setProfileForm({ ...emptyProfile, email: user?.email || '' });
      }
    } catch {
      setShowProfileForm(true);
    }
  }

  useEffect(() => {
    if (!isAuthenticated || !user) return;

    checkStudentProfile();
    loadParkingSpaces();

    const eventSource = createParkingEventSource();
    eventSource.addEventListener('SPACE_RESERVED', () => loadParkingSpaces());
    eventSource.addEventListener('SPACE_OCCUPIED', () => loadParkingSpaces());
    eventSource.onerror = () => eventSource.close();
    return () => eventSource.close();
  }, [isAuthenticated, user]);

  function handleProfileChange(e) {
    const { name, value } = e.target;
    setProfileForm((f) => ({ ...f, [name]: value }));
  }

  async function handleProfileSubmit(e) {
    e.preventDefault();
    setProfileError('');

    const required = ['identification', 'institutionalCode', 'name', 'lastName', 'mobileNumber'];
    const missing = required.filter((k) => !profileForm[k].trim());
    if (missing.length > 0) {
      setProfileError('Todos los campos son obligatorios.');
      return;
    }

    setProfileLoading(true);
    try {
      const studentData = {
        identification: profileForm.identification,
        institutionalCode: profileForm.institutionalCode,
        name: profileForm.name,
        lastName: profileForm.lastName,
        email: user.email,
        mobileNumber: profileForm.mobileNumber
      };

      const created = await createStudent(studentData);
      setStudentProfile(created);

      if (profileForm.vehiclePlate) {
        setVehiclePlate(profileForm.vehiclePlate);
        localStorage.setItem('ucoparking_plate', profileForm.vehiclePlate);
      }

      setShowProfileForm(false);
      setMessage(`Bienvenido/a, ${profileForm.name}. Tu perfil fue creado correctamente.`);
    } catch (err) {
      setProfileError(err.message || 'No se pudo crear el perfil.');
    } finally {
      setProfileLoading(false);
    }
  }

  function openReservationModal(space) {
    setMessage('');
    setError('');
    setModalError('');
    if (space.status !== 'AVAILABLE') {
      setError('Solo se pueden reservar espacios disponibles.');
      return;
    }
    setSelectedSpace(space);
  }

  function closeReservationModal() {
    setSelectedSpace(null);
    setModalError('');
  }

  async function handleReserve(e) {
    e.preventDefault();
    setModalError('');
    setMessage('');

    if (!selectedSpace) return;

    const plate = vehiclePlate.trim();
    if (!plate) {
      setModalError('Ingresa la placa del vehículo.');
      return;
    }

    const confirmed = window.confirm(
      `¿Deseas reservar el espacio ${selectedSpace.code}? Tendrás 30 minutos para ocuparlo.`
    );
    if (!confirmed) return;

    const now = new Date();
    const exitTime = new Date(now.getTime() + 30 * 60 * 1000);

    try {
      const accessToken = await getToken();
      await reserveParkingSpace(
        {
          parkingSpaceId: selectedSpace.id,
          vehiclePlate: plate,
          entryTime: now.toISOString(),
          exitTime: exitTime.toISOString()
        },
        accessToken
      );

      localStorage.setItem('ucoparking_plate', plate);
      setMessage(`Espacio ${selectedSpace.code} reservado. Tienes 30 minutos para llegar.`);
      closeReservationModal();
      await loadParkingSpaces();
    } catch (err) {
      setModalError(err.message || 'No se pudo realizar la reserva.');
    }
  }

  function getStatusLabel(status) {
    return { AVAILABLE: 'Disponible', RESERVED: 'Reservado', OCCUPIED: 'Ocupado' }[status] || status;
  }

  if (isLoading) {
    return (
      <main className="authPage">
        <section className="loginCard">
          <div className="loginBrand">
            <div className="logoCircle"><ParkingCircle size={38} /></div>
            <div>
              <span className="eyebrow">UCO Parking</span>
              <h1>Cargando...</h1>
              <p>Validando sesión...</p>
            </div>
          </div>
        </section>
      </main>
    );
  }

  if (!isAuthenticated) {
    return (
      <main className="authPage">
        <section className="loginCard">
          <div className="loginBrand">
            <div className="logoCircle"><ParkingCircle size={38} /></div>
            <div>
              <span className="eyebrow">UCO Parking</span>
              <h1>Iniciar sesión</h1>
              <p>Accede para reservar tu espacio de parqueadero en la Universidad Católica de Oriente.</p>
            </div>
          </div>

          <div className="loginForm">
            <button className="primaryButton" type="button" onClick={() => loginWithRedirect()}>
              <ShieldCheck size={18} /> Continuar con Auth0
            </button>
            <button className="auth0Button" type="button" onClick={() => loginWithRedirect({ authorizationParams: { screen_hint: 'signup' } })}>
              <UserPlus size={18} /> Registrarme
            </button>
          </div>

          <p className="registerText">El inicio de sesión se gestiona mediante Auth0.</p>
        </section>
      </main>
    );
  }

  if (showProfileForm) {
    return (
      <main className="authPage">
        <section className="loginCard" style={{ maxWidth: 480 }}>
          <div className="loginBrand">
            <div className="logoCircle"><ParkingCircle size={38} /></div>
            <div>
              <span className="eyebrow">UCO Parking</span>
              <h1>Completa tu perfil</h1>
              <p>Necesitamos algunos datos para activar tu cuenta de parqueadero.</p>
            </div>
          </div>

          {profileError && (
            <div className="alert error"><AlertCircle size={18} /> {profileError}</div>
          )}

          <form onSubmit={handleProfileSubmit} className="studentForm" style={{ marginTop: 16 }}>
            <label>
              Número de identificación
              <input name="identification" value={profileForm.identification} onChange={handleProfileChange} placeholder="1001234567" />
            </label>

            <label>
              Código institucional
              <input name="institutionalCode" value={profileForm.institutionalCode} onChange={handleProfileChange} placeholder="UCO2026001" />
            </label>

            <div className="twoColumns">
              <label>
                Nombre
                <input name="name" value={profileForm.name} onChange={handleProfileChange} placeholder="Daniel" />
              </label>
              <label>
                Apellido
                <input name="lastName" value={profileForm.lastName} onChange={handleProfileChange} placeholder="Henao" />
              </label>
            </div>

            <label>
              Celular
              <input name="mobileNumber" value={profileForm.mobileNumber} onChange={handleProfileChange} placeholder="3001234567" />
            </label>

            <label>
              Placa del vehículo <span style={{ color: '#999', fontWeight: 400 }}>(opcional)</span>
              <input name="vehiclePlate" value={profileForm.vehiclePlate} onChange={handleProfileChange} placeholder="ABC123" />
            </label>

            <button type="submit" className="primaryButton" disabled={profileLoading}>
              {profileLoading ? 'Guardando...' : 'Activar cuenta'}
            </button>
          </form>
        </section>
      </main>
    );
  }

  return (
    <main className="pageShell">
      <nav className="topBar">
        <div className="brandBlock">
          <div className="ucoLogo">UCO</div>
          <div>
            <strong>UCO Parking</strong>
            <span>Reserva de espacios de parqueadero</span>
          </div>
        </div>

        <div className="topActions">
          <button className="refreshButton" onClick={loadParkingSpaces} disabled={loading}>
            <RefreshCw size={18} />
            {loading ? 'Actualizando...' : 'Actualizar'}
          </button>
          <button className="logoutButton" onClick={() => logout({ logoutParams: { returnTo: window.location.origin } })}>
            <LogOut size={18} /> Salir
          </button>
        </div>
      </nav>

      <section className="hero">
        <div>
          <span className="eyebrow">Parqueadero UCO</span>
          <h1>Reserva tu espacio de parqueadero</h1>
          <p>Consulta la disponibilidad y reserva tu espacio en tiempo real.</p>
        </div>
        <div className="heroCard">
          <Car size={46} />
          <strong>{summary.total}</strong>
          <span>espacios registrados</span>
        </div>
      </section>

      {user && (
        <div className="alert success">
          <CheckCircle2 size={18} />
          Bienvenido/a, {studentProfile?.name || user.name || user.email}.
        </div>
      )}

      <section className="statsGrid">
        <article className="statCard available">
          <CheckCircle2 size={26} />
          <div><strong>{summary.available}</strong><span>Disponibles</span></div>
        </article>
        <article className="statCard reserved">
          <Clock size={26} />
          <div><strong>{summary.reserved}</strong><span>Reservados</span></div>
        </article>
        <article className="statCard occupied">
          <Car size={26} />
          <div><strong>{summary.occupied}</strong><span>Ocupados</span></div>
        </article>
      </section>

      {message && (
        <div className="alert success"><CheckCircle2 size={18} /> {message}</div>
      )}
      {error && (
        <div className="alert error"><AlertCircle size={18} /> {error}</div>
      )}

      <section className="panel">
        <div className="panelHeader">
          <div>
            <span className="sectionTag"><ParkingCircle size={16} /> Parqueadero</span>
            <h2>Espacios de parqueadero</h2>
            <p>Selecciona un espacio disponible para realizar la reserva.</p>
          </div>
        </div>

        <div className="legend">
          <span><i className="dot availableDot"></i> Disponible</span>
          <span><i className="dot reservedDot"></i> Reservado</span>
          <span><i className="dot occupiedDot"></i> Ocupado</span>
        </div>

        <div className="spacesGrid">
          {parkingSpaces.length === 0 ? (
            <div className="emptyState">
              <ParkingCircle size={42} />
              <h3>No hay espacios para mostrar</h3>
              <p>Verifica que el backend esté encendido.</p>
            </div>
          ) : (
            parkingSpaces.map((space) => (
              <article key={space.id} className={`spaceCard ${space.status.toLowerCase()}`}>
                <div className="spaceCode">{space.code}</div>
                <span className="spaceStatus">{getStatusLabel(space.status)}</span>
                <button
                  className="reserveButton"
                  disabled={space.status !== 'AVAILABLE'}
                  onClick={() => openReservationModal(space)}
                >
                  Reservar espacio
                </button>
              </article>
            ))
          )}
        </div>
      </section>

      {selectedSpace && (
        <section className="modalOverlay">
          <article className="modalCard">
            <div className="modalHeader">
              <div>
                <span className="sectionTag"><Car size={16} /> Reserva</span>
                <h2>Reservar espacio {selectedSpace.code}</h2>
                <p>Tendrás 30 minutos para llegar al parqueadero.</p>
              </div>
              <button className="closeButton" onClick={closeReservationModal}>×</button>
            </div>

            {modalError && (
              <div className="alert error" style={{ margin: '0 0 12px 0' }}>
                <AlertCircle size={18} /> {modalError}
              </div>
            )}

            <form className="reservationForm" onSubmit={handleReserve}>
              <label>
                Placa del vehículo
                <input
                  value={vehiclePlate}
                  onChange={(e) => setVehiclePlate(e.target.value)}
                  placeholder="ABC123"
                />
              </label>

              <div className="modalActions">
                <button type="button" className="secondaryButton" onClick={closeReservationModal}>
                  Cancelar
                </button>
                <button type="submit" className="primaryButton">
                  Confirmar reserva
                </button>
              </div>
            </form>
          </article>
        </section>
      )}
    </main>
  );
}

export default App;
