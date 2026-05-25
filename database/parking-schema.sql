USE uco_parking;
GO

IF OBJECT_ID('dbo.reservations', 'U') IS NOT NULL
    DROP TABLE dbo.reservations;
GO

IF OBJECT_ID('dbo.parking_spaces', 'U') IS NOT NULL
    DROP TABLE dbo.parking_spaces;
GO

CREATE TABLE dbo.parking_spaces (
    id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    status VARCHAR(30) NOT NULL
);
GO

CREATE TABLE dbo.reservations (
    id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    parking_space_id UNIQUEIDENTIFIER NOT NULL,
    vehicle_plate VARCHAR(20) NOT NULL,
    entry_time DATETIME2 NOT NULL,
    exit_time DATETIME2 NOT NULL,
    reservation_time DATETIME2 NOT NULL,
    expiration_time DATETIME2 NOT NULL,
    status VARCHAR(30) NOT NULL,
    CONSTRAINT fk_reservations_parking_spaces
        FOREIGN KEY (parking_space_id)
        REFERENCES dbo.parking_spaces(id)
);
GO

INSERT INTO dbo.parking_spaces (id, code, status) VALUES
(NEWID(), 'A01', 'AVAILABLE'),
(NEWID(), 'A02', 'AVAILABLE'),
(NEWID(), 'A03', 'AVAILABLE'),
(NEWID(), 'A04', 'AVAILABLE'),
(NEWID(), 'A05', 'AVAILABLE'),
(NEWID(), 'B01', 'AVAILABLE'),
(NEWID(), 'B02', 'AVAILABLE'),
(NEWID(), 'B03', 'AVAILABLE'),
(NEWID(), 'B04', 'AVAILABLE'),
(NEWID(), 'B05', 'AVAILABLE');
GO