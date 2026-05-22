import { useEffect, useMemo, useState } from 'react';
import {
  BookOpen,
  Building2,
  Car,
  CheckCircle2,
  GraduationCap,
  Mail,
  Pencil,
  Phone,
  Plus,
  RefreshCw,
  Search,
  ShieldCheck,
  Trash2,
  UserRound,
  Users
} from 'lucide-react';
import {
  createStudent,
  deleteStudent,
  getStudents,
  updateStudent
} from './services/studentService.js';

const emptyForm = {
  identification: '',
  institutionalCode: '',
  name: '',
  lastName: '',
  email: '',
  mobileNumber: ''
};

function App() {
  const [students, setStudents] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const filteredStudents = useMemo(() => {
    const text = search.toLowerCase().trim();

    if (!text) {
      return students;
    }

    return students.filter((student) =>
      `${student.identification} ${student.institutionalCode} ${student.name} ${student.lastName} ${student.email} ${student.mobileNumber}`
        .toLowerCase()
        .includes(text)
    );
  }, [students, search]);

  async function loadStudents() {
    setLoading(true);
    setError('');

    try {
      const data = await getStudents();
      setStudents(data || []);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadStudents();
  }, []);

  function handleChange(event) {
    const { name, value } = event.target;

    setForm((currentForm) => ({
      ...currentForm,
      [name]: value
    }));
  }

  function validateForm() {
    return Object.values(form).every((value) => value.trim() !== '');
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setMessage('');
    setError('');

    if (!validateForm()) {
      setError('Todos los campos son obligatorios.');
      return;
    }

    try {
      if (editingId) {
        await updateStudent(editingId, form);
        setMessage('Estudiante actualizado correctamente.');
      } else {
        await createStudent(form);
        setMessage('Estudiante registrado correctamente.');
      }

      setForm(emptyForm);
      setEditingId(null);
      await loadStudents();
    } catch (err) {
      setError(err.message);
    }
  }

  function handleEdit(student) {
    setEditingId(student.id);
    setForm({
      identification: student.identification || '',
      institutionalCode: student.institutionalCode || '',
      name: student.name || '',
      lastName: student.lastName || '',
      email: student.email || '',
      mobileNumber: student.mobileNumber || ''
    });
    setMessage('Editando estudiante seleccionado.');
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async function handleDelete(id) {
    const confirmDelete = window.confirm('¿Seguro que deseas eliminar este estudiante?');

    if (!confirmDelete) {
      return;
    }

    setMessage('');
    setError('');

    try {
      await deleteStudent(id);
      setMessage('Estudiante eliminado correctamente.');
      await loadStudents();
    } catch (err) {
      setError(err.message);
    }
  }

  function cancelEdit() {
    setEditingId(null);
    setForm(emptyForm);
    setMessage('Edición cancelada.');
  }

  const totalStudents = students.length;
  const filteredTotal = filteredStudents.length;

  return (
    <main className="pageShell">
      <nav className="topBar">
        <div className="brandBlock">
          <div className="ucoLogo">UCO</div>
          <div>
            <strong>Universidad Católica de Oriente</strong>
            <span>Sistema académico de parqueadero</span>
          </div>
        </div>

        <div className="navPill">
          <ShieldCheck size={18} />
          Proyecto UCO Parking
        </div>
      </nav>

      <section className="hero">
        <div className="heroText">
          <span className="eyebrow">Frontend institucional</span>
          <h1>Gestión de estudiantes para UCO Parking</h1>
          <p>
            Panel moderno para registrar, consultar, actualizar y eliminar estudiantes conectados al backend de Spring Boot.
          </p>

          <div className="heroActions">
            <a href="#studentForm" className="primaryLink">
              <Plus size={18} />
              Registrar estudiante
            </a>
            <button className="ghostButton" onClick={loadStudents} disabled={loading}>
              <RefreshCw size={18} />
              {loading ? 'Actualizando...' : 'Actualizar lista'}
            </button>
          </div>
        </div>

        <div className="heroCard">
          <div className="heroCardIcon">
            <Car size={42} />
          </div>
          <p>Control de acceso vehicular</p>
          <strong>{totalStudents}</strong>
          <span>estudiantes registrados</span>
        </div>
      </section>

      <section className="statsGrid">
        <article className="statCard">
          <Users size={24} />
          <div>
            <strong>{totalStudents}</strong>
            <span>Total registros</span>
          </div>
        </article>

        <article className="statCard">
          <Search size={24} />
          <div>
            <strong>{filteredTotal}</strong>
            <span>Coincidencias visibles</span>
          </div>
        </article>

        <article className="statCard">
          <Building2 size={24} />
          <div>
            <strong>UCO</strong>
            <span>Plantilla institucional</span>
          </div>
        </article>
      </section>

      <section className="contentGrid">
        <article className="panel formPanel" id="studentForm">
          <div className="panelHeader">
            <div>
              <span className="sectionTag"><GraduationCap size={16} /> Estudiante</span>
              <h2>{editingId ? 'Actualizar estudiante' : 'Registrar nuevo estudiante'}</h2>
              <p>Completa la información para enviarla al servicio REST.</p>
            </div>
            <div className="softIcon"><BookOpen size={24} /></div>
          </div>

          {message && <div className="alert success"><CheckCircle2 size={18} /> {message}</div>}
          {error && <div className="alert error">{error}</div>}

          <form onSubmit={handleSubmit} className="studentForm">
            <label>
              Identificación
              <input
                name="identification"
                value={form.identification}
                onChange={handleChange}
                placeholder="1001234567"
              />
            </label>

            <label>
              Código institucional
              <input
                name="institutionalCode"
                value={form.institutionalCode}
                onChange={handleChange}
                placeholder="UCO2026001"
              />
            </label>

            <div className="twoColumns">
              <label>
                Nombre
                <input
                  name="name"
                  value={form.name}
                  onChange={handleChange}
                  placeholder="Kelly"
                />
              </label>

              <label>
                Apellido
                <input
                  name="lastName"
                  value={form.lastName}
                  onChange={handleChange}
                  placeholder="Giraldo"
                />
              </label>
            </div>

            <label>
              Correo institucional
              <input
                name="email"
                value={form.email}
                onChange={handleChange}
                placeholder="kelly.giraldo@uco.edu.co"
                type="email"
              />
            </label>

            <label>
              Celular
              <input
                name="mobileNumber"
                value={form.mobileNumber}
                onChange={handleChange}
                placeholder="3001234567"
              />
            </label>

            <div className="buttonGroup">
              <button type="submit" className="primaryButton">
                {editingId ? 'Guardar cambios' : 'Registrar estudiante'}
              </button>

              {editingId && (
                <button type="button" className="secondaryButton" onClick={cancelEdit}>
                  Cancelar edición
                </button>
              )}
            </div>
          </form>
        </article>

        <article className="panel studentsPanel">
          <div className="panelHeader listHeader">
            <div>
              <span className="sectionTag"><Users size={16} /> Registros</span>
              <h2>Listado de estudiantes</h2>
              <p>Busca, edita o elimina registros almacenados en la base de datos.</p>
            </div>
          </div>

          <div className="toolbar">
            <div className="searchBox">
              <Search size={18} />
              <input
                value={search}
                onChange={(event) => setSearch(event.target.value)}
                placeholder="Buscar por nombre, código, correo..."
              />
            </div>

            <button className="refreshButton" onClick={loadStudents} disabled={loading}>
              <RefreshCw size={18} />
              {loading ? 'Cargando...' : 'Actualizar'}
            </button>
          </div>

          <div className="studentCards">
            {filteredStudents.length === 0 ? (
              <div className="emptyState">
                <div className="emptyIcon"><UserRound size={34} /></div>
                <h3>No hay estudiantes para mostrar</h3>
                <p>Registra uno nuevo o verifica que el backend esté encendido.</p>
              </div>
            ) : (
              filteredStudents.map((student) => (
                <article className="studentCard" key={student.id}>
                  <div className="studentAvatar">
                    {(student.name || 'E').charAt(0)}{(student.lastName || '').charAt(0)}
                  </div>

                  <div className="studentInfo">
                    <div className="studentTitleRow">
                      <div>
                        <h3>{student.name} {student.lastName}</h3>
                        <span>{student.institutionalCode}</span>
                      </div>
                      <div className="cardActions">
                        <button className="smallButton" onClick={() => handleEdit(student)} title="Editar estudiante">
                          <Pencil size={16} />
                        </button>
                        <button className="smallButton danger" onClick={() => handleDelete(student.id)} title="Eliminar estudiante">
                          <Trash2 size={16} />
                        </button>
                      </div>
                    </div>

                    <div className="studentMeta">
                      <span><UserRound size={15} /> {student.identification}</span>
                      <span><Mail size={15} /> {student.email}</span>
                      <span><Phone size={15} /> {student.mobileNumber}</span>
                    </div>
                  </div>
                </article>
              ))
            )}
          </div>
        </article>
      </section>
    </main>
  );
}

export default App;
