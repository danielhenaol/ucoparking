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

const initialReservationForm = {
  vehiclePlate: '',
  entryTime: '',
  exitTime: ''
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
  const [reservationForm, setReservationForm] = useState(initialReservationForm);

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const summary = useMemo(() => {
    const available = parkingSpaces.filter((space) => space.status === 'AVAILABLE').length;
    const reserved = parkingSpaces.filter((space) => space.status === 'RESERVED').length;
    const occupied = parkingSpaces.filter((space) => space.status === 'OCCUPIED').length;

    return {
      available,
      reserved,
      occupied,
      total: parkingSpaces.length
    };
  }, [parkingSpaces]);

  async function getToken() {
    return getAccessTokenSilently({
      authorizationParams: {
        audience: import.meta.env.VITE_AUTH0_AUDIENCE
      }
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

  useEffect(() => {
    if (!isAuthenticated) {
      return;
    }

    loadParkingSpaces();

    const eventSource = createParkingEventSource();

    eventSource.addEventListener('SPACE_RESERVED', () => {
      loadParkingSpaces();
    });

    eventSource.addEventListener('SPACE_OCCUPIED', () => {
      loadParkingSpaces();
    });

    eventSource.onerror = () => {
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, [isAuthenticated]);

  function handleReservationChange(event) {
    const { name, value } = event.target;

    setReservationForm((currentForm) => ({
      ...currentForm,
      [name]: value
    }));
  }

  function handleLoginWithAuth0() {
    loginWithRedirect();
  }

  function handleRegisterWithAuth0() {
    loginWithRedirect({
      authorizationParams: {
        screen_hint: 'signup'
      }
    });
  }

  function handleLogout() {
    logout({
      logoutParams: {
        returnTo: window.location.origin
      }
    });
  }

  function openReservationModal(space) {
    setMessage('');
    setError('');

    if (space.status !== 'AVAILABLE') {
      setError('Solo se pueden reservar espacios disponibles.');
      return;
    }

    setSelectedSpace(space);
    setReservationForm(initialReservationForm);
  }

  function closeReservationModal() {
    setSelectedSpace(null);
    setReservationForm(initialReservationForm);
  }

  function validateReservationForm() {
    if (!reservationForm.vehiclePlate.trim()) {
      setError('La placa del vehículo es obligatoria.');
      return false;
    }

    if (!reservationForm.entryTime) {
      setError('La hora de entrada es obligatoria.');
      return false;
    }

    if (!reservationForm.exitTime) {
      setError('La hora de salida es obligatoria.');
      return false;
    }

    if (new Date(reservationForm.exitTime) <= new Date(reservationForm.entryTime)) {
      setError('La hora de salida debe ser posterior a la hora de entrada.');
      return false;
    }

    return true;
  }

  async function handleReserve(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    if (!selectedSpace) {
      setError('Debes seleccionar un espacio.');
      return;
    }

    const confirmReservation = window.confirm(
      `¿Deseas reservar el espacio ${selectedSpace.code}? Tendrás 30 minutos para ocuparlo.`
    );

    if (!confirmReservation) {
      return;
    }

    if (!validateReservationForm()) {
      return;
    }

    try {
      const accessToken = await getToken();

      await reserveParkingSpace(
        {
          parkingSpaceId: selectedSpace.id,
          vehiclePlate: reservationForm.vehiclePlate,
          entryTime: reservationForm.entryTime,
          exitTime: reservationForm.exitTime
        },
        accessToken
      );

      setMessage(`Espacio ${selectedSpace.code} reservado correctamente. Tienes 30 minutos para ocuparlo.`);
      closeReservationModal();
      await loadParkingSpaces();
    } catch (err) {
      setError(err.message || 'No se pudo realizar la reserva.');
    }
  }

  function getStatusLabel(status) {
    const labels = {
      AVAILABLE: 'Disponible',
      RESERVED: 'Reservado',
      OCCUPIED: 'Ocupado'
    };

    return labels[status] || status;
  }

  if (isLoading) {
    return (
      <main className="authPage">
        <section className="loginCard">
          <div className="loginBrand">
            <div className="logoCircle">
              <ParkingCircle size={38} />
            </div>

            <div>
              <span className="eyebrow">UCO Parking</span>
              <h1>Cargando...</h1>
              <p>Estamos validando la sesión con Auth0.</p>
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
            <div className="logoCircle">
              <ParkingCircle size={38} />
            </div>

            <div>
              <span className="eyebrow">UCO Parking</span>
              <h1>Iniciar sesión</h1>
              <p>
                Accede con Auth0 para reservar espacios de parqueadero en la Universidad Católica de Oriente.
              </p>
            </div>
          </div>

          {error && (
            <div className="alert error">
              <AlertCircle size={18} />
              {error}
            </div>
          )}

          <div className="loginForm">
            <button className="primaryButton" type="button" onClick={handleLoginWithAuth0}>
              <ShieldCheck size={18} />
              Continuar con Auth0
            </button>

            <button className="auth0Button" type="button" onClick={handleRegisterWithAuth0}>
              <UserPlus size={18} />
              Registrarme con Auth0
            </button>
          </div>

          <p className="registerText">
            El inicio de sesión ahora se gestiona mediante Auth0 como Identity Provider.
          </p>
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

          <button className="logoutButton" onClick={handleLogout}>
            <LogOut size={18} />
            Salir
          </button>
        </div>
      </nav>

      <section className="hero">
        <div>
          <span className="eyebrow">Panel reactivo</span>
          <h1>Reserva tu espacio de parqueadero</h1>
          <p>
            Consulta espacios disponibles, reservados y ocupados. Cuando un espacio cambia de estado,
            el tablero se actualiza mediante SSE.
          </p>
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
          Bienvenido/a, {user.name || user.email}.
        </div>
      )}

      <section className="statsGrid">
        <article className="statCard available">
          <CheckCircle2 size={26} />
          <div>
            <strong>{summary.available}</strong>
            <span>Disponibles</span>
          </div>
        </article>

        <article className="statCard reserved">
          <Clock size={26} />
          <div>
            <strong>{summary.reserved}</strong>
            <span>Reservados</span>
          </div>
        </article>

        <article className="statCard occupied">
          <Car size={26} />
          <div>
            <strong>{summary.occupied}</strong>
            <span>Ocupados</span>
          </div>
        </article>
      </section>

      {message && (
        <div className="alert success">
          <CheckCircle2 size={18} />
          {message}
        </div>
      )}

      {error && (
        <div className="alert error">
          <AlertCircle size={18} />
          {error}
        </div>
      )}

      <section className="panel">
        <div className="panelHeader">
          <div>
            <span className="sectionTag">
              <ParkingCircle size={16} />
              Parqueadero
            </span>
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
              <p>Verifica que el backend esté encendido y que la tabla parking_spaces tenga datos.</p>
            </div>
          ) : (
            parkingSpaces.map((space) => (
              <article
                key={space.id}
                className={`spaceCard ${space.status.toLowerCase()}`}
              >
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
                <span className="sectionTag">
                  <Car size={16} />
                  Reserva
                </span>
                <h2>Reservar espacio {selectedSpace.code}</h2>
                <p>Una vez reservado, tendrás 30 minutos para ocuparlo.</p>
              </div>

              <button className="closeButton" onClick={closeReservationModal}>
                ×
              </button>
            </div>

            <form className="reservationForm" onSubmit={handleReserve}>
              <label>
                Placa del vehículo
                <input
                  name="vehiclePlate"
                  value={reservationForm.vehiclePlate}
                  onChange={handleReservationChange}
                  placeholder="ABC123"
                />
              </label>

              <label>
                Hora de entrada
                <input
                  type="datetime-local"
                  name="entryTime"
                  value={reservationForm.entryTime}
                  onChange={handleReservationChange}
                />
              </label>

              <label>
                Hora de salida
                <input
                  type="datetime-local"
                  name="exitTime"
                  value={reservationForm.exitTime}
                  onChange={handleReservationChange}
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