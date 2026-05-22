# UCO Parking

Proyecto académico **UCO Parking** construido con **Spring Boot 3**, arquitectura por capas (Application · Domain · Infrastructure), SQL Server dockerizado y colección Bruno para pruebas REST.

---

## Arquitectura del proyecto

```
co.edu.uco.ucoparking
├── crosscutting/            ← helpers, excepciones, mensajes, specification
├── domain/model/            ← Modelos de negocio: Student · AcademicProgram · IdType · Institute
├── application/
│   ├── inputport/           ← Contratos de entrada + MapperDTO (DTO → dominio)
│   ├── usecase/             ← Contratos de caso de uso + Validator Pattern
│   └── outputport/          ← StudentOutputPort (contrato de salida)
├── features/student/registernewstudent/
│   ├── application/inputport/   DTO · Input · Mapper · Interactor
│   └── application/usecase/     UseCase · Validator · UseCaseImpl
└── infrastructure/
    ├── entrypoint/rest/     ← StudentController · GlobalExceptionHandler
    └── persistence/
        ├── entity/          ← Entidades de dominio de persistencia
        ├── mapper/          ← MapperJPA (dominio ↔ JPA Entity)
        └── repository/
            ├── *RepositoryPort        ← Contratos CRUD de infraestructura
            ├── sql/entity/            ← JPA Entities + Spring Data JPA Repos
            └── adapter/sql/jpa/       ← Implementaciones CRUD (OutputPort + RepositoryPort)
```

---

## Requisitos previos

| Herramienta | Versión mínima |
|-------------|----------------|
| Java JDK    | 17             |
| Docker + Docker Compose | 24+ |
| Bruno       | Última versión – https://www.usebruno.com/ |
| Git         | 2.x            |

---

## 1. Levantar la base de datos (Docker)

```bash
docker compose up -d
```

El servicio `db-init` espera al healthcheck de SQL Server y luego ejecuta automáticamente:
- `database/create-database.sql` — crea la base `uco_parking`
- `database/init-schema.sql` — crea las tablas con datos semilla

**Credenciales:**

| Campo  | Valor         |
|--------|---------------|
| Host   | localhost     |
| Puerto | 1433          |
| User   | sa            |
| Pass   | Admin12345*   |
| DB     | uco_parking   |

---

## 2. Ejecutar la aplicación

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

API disponible en: `http://localhost:8080`

---

## 3. Probar con Bruno

Abrir Bruno → *Open Collection* → carpeta `bruno/UCO-Parking`.

| # | Request           | Método | Endpoint                              |
|---|-------------------|--------|---------------------------------------|
| 1 | Create Student    | POST   | /api/v1/students                      |
| 2 | List Students     | GET    | /api/v1/students                      |
| 3 | Get Student By Id | GET    | /api/v1/students/{id}                 |
| 4 | Update Student    | PUT    | /api/v1/students/{id}                 |
| 5 | Delete Student    | DELETE | /api/v1/students/{id}                 |

**Body para crear estudiante:**

```json
{
  "identification": "1001234567",
  "institutionalCode": "UCO2026001",
  "name": "Kelly",
  "lastName": "Giraldo",
  "email": "kelly.giraldo@uco.edu.co",
  "phoneNumber": "3001234567"
}
```

---

## 4. Subir a GitHub

```bash
# 1. Inicializar repositorio (solo la primera vez)
git init

# 2. Agregar todos los archivos
git add .

# 3. Primer commit
git commit -m "feat: UCO Parking - arquitectura 3 capas, JPA, Docker y Bruno"

# 4. Renombrar rama principal
git branch -M main

# 5. Vincular con tu repositorio en GitHub (reemplaza la URL)
git remote add origin https://github.com/TU_USUARIO/ucoparking.git

# 6. Push inicial
git push -u origin main
```

> Crea el repositorio vacío en GitHub **antes** de ejecutar estos comandos.

---

## Estructura de archivos

```
ucoparking/
├── docker-compose.yml
├── database/
│   ├── create-database.sql
│   └── init-schema.sql
├── bruno/UCO-Parking/       (5 requests: Create, List, GetById, Update, Delete)
├── pom.xml
└── src/main/java/co/edu/uco/ucoparking/
    ├── crosscutting/
    ├── domain/model/
    ├── application/
    ├── features/student/
    └── infrastructure/
```
