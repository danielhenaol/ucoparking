IF DB_ID('uco_parking') IS NULL
BEGIN
    CREATE DATABASE uco_parking;
END
GO

USE uco_parking;
GO

IF OBJECT_ID('dbo.students', 'U') IS NOT NULL
BEGIN
    DROP TABLE dbo.students;
END
GO

IF OBJECT_ID('dbo.academic_programs', 'U') IS NOT NULL
BEGIN
    DROP TABLE dbo.academic_programs;
END
GO

IF OBJECT_ID('dbo.institutes', 'U') IS NOT NULL
BEGIN
    DROP TABLE dbo.institutes;
END
GO

IF OBJECT_ID('dbo.id_types', 'U') IS NOT NULL
BEGIN
    DROP TABLE dbo.id_types;
END
GO

CREATE TABLE dbo.id_types (
    id               UNIQUEIDENTIFIER NOT NULL DEFAULT NEWID() PRIMARY KEY,
    code             NVARCHAR(10)     NOT NULL UNIQUE,
    description      NVARCHAR(80)     NOT NULL
);
GO

INSERT INTO dbo.id_types (id, code, description) VALUES
    (NEWID(), 'CC', 'Cédula de ciudadanía'),
    (NEWID(), 'CE', 'Cédula de extranjería'),
    (NEWID(), 'TI', 'Tarjeta de identidad'),
    (NEWID(), 'PP', 'Pasaporte');
GO

CREATE TABLE dbo.institutes (
    id               UNIQUEIDENTIFIER NOT NULL DEFAULT NEWID() PRIMARY KEY,
    code             NVARCHAR(20)     NOT NULL UNIQUE,
    name             NVARCHAR(120)    NOT NULL
);
GO

INSERT INTO dbo.institutes (id, code, name) VALUES
    (NEWID(), 'ING',  'Facultad de Ingeniería'),
    (NEWID(), 'CIEN', 'Facultad de Ciencias'),
    (NEWID(), 'EDUC', 'Facultad de Educación');
GO

CREATE TABLE dbo.academic_programs (
    id               UNIQUEIDENTIFIER NOT NULL DEFAULT NEWID() PRIMARY KEY,
    code             NVARCHAR(20)     NOT NULL UNIQUE,
    name             NVARCHAR(120)    NOT NULL,
    institute_id     UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT fk_program_institute FOREIGN KEY (institute_id)
        REFERENCES dbo.institutes(id)
);
GO

INSERT INTO dbo.academic_programs (id, code, name, institute_id) VALUES
    (NEWID(), 'ISC', 'Ingeniería de Sistemas y Computación', (SELECT id FROM dbo.institutes WHERE code = 'ING')),
    (NEWID(), 'ICI', 'Ingeniería Civil', (SELECT id FROM dbo.institutes WHERE code = 'ING')),
    (NEWID(), 'IEL', 'Ingeniería Electrónica', (SELECT id FROM dbo.institutes WHERE code = 'ING'));
GO

CREATE TABLE dbo.students (
    id                  UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    identification      NVARCHAR(30)     NOT NULL UNIQUE,
    institutional_code  NVARCHAR(30)     NOT NULL,
    name                NVARCHAR(80)     NOT NULL,
    last_name           NVARCHAR(80)     NOT NULL,
    email               NVARCHAR(120)    NOT NULL,
    mobile_number       NVARCHAR(30)     NOT NULL
);
GO