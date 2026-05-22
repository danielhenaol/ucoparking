# Frontend UCO Parking

Frontend hecho con React + Vite para consumir el backend de Spring Boot del proyecto UCO Parking.

## Diseño incluido

- Plantilla visual tipo institucional UCO.
- Paleta verde con estilo moderno.
- Panel principal tipo dashboard.
- Tarjetas de resumen.
- Formulario para registrar y actualizar estudiantes.
- Listado en tarjetas para consultar, buscar, editar y eliminar estudiantes.

## Endpoints usados

- GET `http://localhost:8080/api/v1/students`
- POST `http://localhost:8080/api/v1/students`
- PUT `http://localhost:8080/api/v1/students/{id}`
- DELETE `http://localhost:8080/api/v1/students/{id}`

## Cómo ejecutarlo

1. Primero ejecuta el backend desde la raíz del proyecto:

```bash
.\mvnw.cmd spring-boot:run
```

2. Luego abre otra terminal en la carpeta `frontend`.

```bash
cd frontend
npm install
npm run dev
```

3. Abre en el navegador la URL que muestra Vite, normalmente:

```text
http://localhost:5173
```

Antes de probar, asegúrate de tener el backend encendido en `http://localhost:8080`.
