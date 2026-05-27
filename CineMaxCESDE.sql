CREATE DATABASE CineMaxCESDE;
use CineMaxCESDE;

CREATE TABLE tblPeliculas (
    PeliculaId  int primary key identity(1,1) not null,
    Titulo varchar(100)  not null,
    DuracionMinutos int not null,
    PrecioBase money not null,
    ClasificacionEdad char(5) not null,
    Genero varchar(30) not null
);

-- Se crea el ususario con permiso de lectura:
create login Cajero with Password = 'cesde2026';
create User Cajero for login Cajero;
grant SELECT on tblPeliculas to Cajero;
--

-- Integrar constraints: 
ALTER TABLE tblPeliculas
add constraint Unique_Titulo unique (Titulo);
--

-- crear tablas adicionales
CREATE TABLE tblSalas (
    SalaId int primary key identity(1,1) not null,
    NombreSala varchar(30) not null,
    Capacidad int not null
);

CREATE TABLE tblClientes (
    ClienteId int primary key identity(1,1) not null,
    Nombre varchar(100) not null,
    CorreoElectronico varchar(100) unique not null
);

-- Se crea la tabla funciones con los constraints para la fk
CREATE TABLE tblFunciones (
    FuncionId int primary key identity(1,1) not null,
    PeliculaId int not null,
    SalaId int not null,
    Horario datetime not null,
    constraint fk_Pelicula
    foreign key (PeliculaId)
    references tblPeliculas (PeliculaId),
    constraint fk_Sala 
    foreign key (SalaId)
    references tblSalas (SalaId)
);
--

-- será que creamos la tabla de clasificacionEdad? estamos pensando...




-- Insertar datos de prueba

INSERT INTO tblPeliculas (Titulo, DuracionMinutos, PrecioBase, ClasificacionEdad, Genero) 
VALUES
	('Avatar 3', 181, 15000.00, 'PG', 'Acción'),
	('Una Batalla Tras Otra', 169, 12000.00, 'R', 'Acción'),
	('El Conjuro', 112, 10000.00, 'R', 'Terror'),
	('Los Pecadores', 169, 14000.00, 'R', 'Acción'),
	('Toy Story 4', 100, 9000.00, 'G', 'Animación');

INSERT INTO tblSalas (NombreSala,Capacidad)
VALUES
	('Sala 1', 100),
	('Sala 2', 150),
	('Sala 3', 200),
	('Sala 4', 200);

INSERT INTO tblClientes (Nombre, CorreoElectronico)
VALUES
	('Juan Pérez', 'juanperez@hotmail.com'),
	('María López', 'marialopez@hotmail.com'),
	('Carlos Mendoza', 'carlosm@gmail.com'),
	('Ana Posada', 'posada23@gmail.com'),
	('Alexis Ateortua', 'ateortua@gmail.com');	


INSERT INTO tblFunciones (PeliculaId, SalaId, Horario)
VALUES
	(1, 1, '2026-10-20 18:00:00'),
	(2, 3, '2026-10-20 21:00:00'),
	(3, 4, '2026-10-21 22:30:00'), 
	(4, 1, '2026-10-21 15:00:00'),
	(5, 2, '2026-10-22 11:00:00');
------------------------------------------------------------

-- Reporte Básico (SELECT y Agregado):

SELECT 
	Genero,
	count(*) as totalGenero
FROM tblPeliculas
GROUP BY Genero;


SELECT 
sum(Capacidad) as CapacidadSalas
FROM tblSalas;

-- Momento 3 --

-- Crear tabla tickets

CREATE TABLE tblTickets (
    ticketId int primary key identity(1,1),
    ClienteId int,
    FuncionId int,
    CantidadTickets int not null, 
    PrecioTotalPagado money not null,
    constraint FKClienteId foreign key (ClienteId) references tblClientes (ClienteId),
    constraint FKFuncionId foreign key (FuncionId) references tblFunciones (FuncionId)
);
 

-- Manipulación de Datos: UPDATE: Actualizar la Capacidad de una sala (simulando que se vendió un ticket y hay menos asientos disponibles):

SELECT * FROM tblsalas;


UPDATE tblSalas 
SET Capacidad = capacidad - 1
WHERE SalaId = 2;

-- DELETE: Eliminar registros de la tabla Clientes que no hayan comprado un ticket en los últimos 3 meses (simulación de limpieza de datos):
SELECT * FROM tblClientes;
SELECT * FROM tblTickets;

-- La tabla tblTickets no tiene datos aún y tampoco tiene el atributo FechaCompra. Vamos a crearlos

ALTER TABLE tblTickets 
add 
FechaCompra datetime not null;


INSERT INTO tblTickets
(ClienteId, FuncionId, CantidadTickets, PrecioTotalPagado, FechaCompra)
VALUES
(1,1,2,30000,'2026-01-05'),
(2,2,1,12000,'2026-01-10'),
(3,3,4,40000,'2026-01-12'),
(4,4,2,28000,'2026-01-15'),
(5,5,3,27000,'2026-01-18'),
(1,2,2,24000,'2026-02-01'),
(2,3,3,30000,'2026-02-05'),
(3,4,1,14000,'2026-02-08'),
(4,5,2,18000,'2026-02-11'),
(5,1,5,75000,'2026-02-15'),
(1,3,2,20000,'2026-03-02'),
(2,4,1,14000,'2026-03-07'),
(3,5,4,36000,'2026-03-10'),
(4,1,3,45000,'2026-03-14'),
(5,2,2,24000,'2026-03-18'),
(1,4,1,14000,'2026-04-03'),
(2,5,2,18000,'2026-04-08'),
(3,1,3,45000,'2026-04-11'),
(4,2,4,48000,'2026-04-15'),
(5,3,2,20000,'2026-04-19'),
(1,5,2,18000,'2026-05-03'),
(2,1,1,15000,'2026-05-08'),
(3,2,2,24000,'2026-05-11'),
(4,3,3,30000,'2026-05-14'),
(5,4,1,14000,'2026-05-18'),
(1,1,4,60000,'2026-06-02'),
(2,2,2,24000,'2026-06-06'),
(3,3,5,50000,'2026-06-10'),
(4,4,2,28000,'2026-06-15'),
(5,5,3,27000,'2026-06-20');


-- ahora si podemos Eliminar registros de la tabla Clientes que no hayan comprado un ticket en los últimos 3 meses (simulación de limpieza de datos).


-- ingresaremos nuevos registros de clientes que no compran hace mas de 3 meses para asegurar la borrada del cliente

INSERT INTO tblClientes
(Nombre, CorreoElectronico)
VALUES
('Alejo Santa', 'alejo@lamusica.fm'),
('Howar Castro', 'howar@gmail.com');


-- Verificar clientes que se van a borrar
SELECT *
FROM tblClientes
WHERE ClienteId NOT IN (
    SELECT ClienteId
    FROM tblTickets
    WHERE FechaCompra >= DATEADD(MONTH, -3, GETDATE())
);

-- Eliminar clientes sin compras en los últimos 3 meses
DELETE FROM tblClientes
WHERE ClienteId NOT IN (
    SELECT ClienteId
    FROM tblTickets
    WHERE FechaCompra >= DATEADD(MONTH, -3, GETDATE())
);


-- Reporte Avanzado (JOIN): Crear un SELECT que combine al menos tres tablas (Peliculas, Funciones, Salas) 
---para mostrar una lista detallada: "Título de la Película", "Horario", "Nombre de la Sala" y 
---"Capacidad de la Sala" (utilizando un INNER JOIN).

SELECT 
    P.Titulo AS 'Título de la Película',
    F.Horario AS 'Horario',
    S.NombreSala AS 'Nombre de la Sala',
    S.Capacidad AS 'Capacidad de la Sala'
FROM tblFunciones F
INNER JOIN tblPeliculas P
    ON F.PeliculaId = P.PeliculaId
INNER JOIN tblSalas S
    ON F.SalaId = S.SalaId;





-- Procedimiento Almacenado: Crear un Procedimiento Almacenado llamado usp_Registrar_Compra que reciba 
--los parámetros necesarios (Cliente, Función, Cantidad, Precio) e inserte automáticamente un registro 
--en la tabla Tickets

CREATE PROCEDURE usp_Registrar_Compra
    @ClienteId INT,
    @FuncionId INT,
    @CantidadTickets INT,
    @PrecioTotalPagado MONEY
AS
BEGIN

    INSERT INTO tblTickets
    (ClienteId, FuncionId, CantidadTickets, PrecioTotalPagado,FechaCompra)
    VALUES
    (@ClienteId, @FuncionId, @CantidadTickets, @PrecioTotalPagado, GETDATE()
    );
END;

---Ejecutar 


SELECT * FROM tblClientes;

EXEC usp_Registrar_Compra
    @ClienteId = 8,
    @FuncionId = 2,
    @CantidadTickets = 10,
    @PrecioTotalPagado = 150000;



----------------------- Campo de pruebas----------------------
SELECT * FROM tblTickets;

SELECT * FROM sys.tables;

SELECT * FROM tblsalas;
SELECT * FROM tblClientes;






------------------------------------------------------------------