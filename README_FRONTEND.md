# Cómo usar el frontend agregado

Se agregó una carpeta nueva llamada `frontend` con una interfaz en React para gestionar estudiantes.

## 1. Ejecutar backend

Desde la carpeta principal del backend:

```bash
.\mvnw.cmd spring-boot:run
```

El backend debe quedar activo en:

```text
http://localhost:8080
```

## 2. Ejecutar frontend

Abre otra terminal y entra a la carpeta frontend:

```bash
cd frontend
npm install
npm run dev
```

Luego entra en el navegador a:

```text
http://localhost:5173
```

## 3. Qué permite hacer

- Registrar estudiantes.
- Listar estudiantes.
- Buscar estudiantes.
- Editar estudiantes.
- Eliminar estudiantes.

## 4. Archivo importante agregado al backend

También se agregó:

```text
src/main/java/co/edu/uco/ucoparking/infrastructure/config/CorsConfig.java
```

Ese archivo permite que React, que corre en `localhost:5173`, pueda consumir el backend de Spring Boot, que corre en `localhost:8080`.
