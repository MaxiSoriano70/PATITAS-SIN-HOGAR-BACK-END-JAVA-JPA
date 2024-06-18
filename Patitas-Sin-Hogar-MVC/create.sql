-- Elimina las tablas si ya existen

DROP TABLE IF EXISTS DOMICILIOS;
-- Crear la tabla DOMICILIOS
CREATE TABLE DOMICILIOS (
  idDomicilio INT AUTO_INCREMENT PRIMARY KEY,
  calle VARCHAR(50) NOT NULL,
  numero INT NOT NULL,
  localidad VARCHAR(50) NOT NULL,
  provincia VARCHAR(50) NOT NULL
);

-- Crear la tabla USUARIOS
DROP TABLE IF EXISTS USUARIOS;
CREATE TABLE USUARIOS (
  idUsuario INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  telefono VARCHAR(45) NOT NULL,
  fechaDeNacimiento DATE NOT NULL,
  contrasenia VARCHAR(255) NOT NULL,
  idDomicilio INT NOT NULL
);

-- Crear tabla MASCOTAS con el campo edad
DROP TABLE IF EXISTS MASCOTAS;
CREATE TABLE MASCOTAS (
  idMascota INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(45) NOT NULL,
  urlFoto VARCHAR(255) NOT NULL,
  fechaDeNacimiento DATE NOT NULL,
  especie VARCHAR(45) NOT NULL,
  colorDePelo VARCHAR(100) NOT NULL,
  pesoKg DOUBLE NOT NULL,
  isEsterilizado BOOLEAN NOT NULL,
  observaciones VARCHAR(250) NOT NULL,
  isAdoptado BOOLEAN NOT NULL DEFAULT FALSE,
  idUsuario INT NOT NULL
);

-- Crear la tabla ADOPCIONES
DROP TABLE IF EXISTS ADOPCIONES;
CREATE TABLE ADOPCIONES (
  idAdopcion INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario INT NOT NULL,
  idMascota INT NOT NULL,
  fechaDeAdopcion DATE NOT NULL
);

-- Insertar domicilios
INSERT INTO DOMICILIOS VALUES (DEFAULT, 'CALLE FALSA', 123, 'CAPITAL', 'SALTA');
INSERT INTO DOMICILIOS VALUES (DEFAULT, 'SIEMPRE VIVA', 321, 'CAPITAL', 'SALTA');

-- Insertar usuarios
INSERT INTO USUARIOS (idUsuario, email, nombre, apellido, telefono, fechaDeNacimiento, contrasenia, idDomicilio) VALUES
(DEFAULT,'karen@gmail.com', 'KAREN', 'GARCIA', '123456789', '1998-02-17', '1234', 1),
(DEFAULT,'emilia@gmail.com', 'EMILIA', 'CASTRO', '987654321', '1995-02-02', '1234', 2);

-- Insertar mascotas para Usuario1
INSERT INTO MASCOTAS (idMascota, nombre, urlFoto, fechaDeNacimiento, especie, colorDePelo, pesoKg, isEsterilizado, observaciones, isAdoptado, idUsuario) VALUES
(DEFAULT, 'MARGO', 'url1', '2010-01-01', 'CANINO', 'NEGRO', 10.5, TRUE, 'AMIGABLE CON NIÑOS Y GATOS', FALSE, 1),
(DEFAULT, 'ZEUS', 'url2', '2012-02-02', 'FELINO', 'BLANCO', 5.2, FALSE, 'PERDIO UN OJO', FALSE, 1);

-- Insertar mascotas para Usuario2
INSERT INTO MASCOTAS (idMascota, nombre, urlFoto, fechaDeNacimiento, especie, colorDePelo, pesoKg, isEsterilizado, observaciones, isAdoptado, idUsuario) VALUES
(DEFAULT, 'FIFI', 'url4', '2011-04-04', 'FELINO', 'GRIS', 7.3, FALSE, 'MUY AMIGABLE', FALSE, 2),
(DEFAULT, 'ROCO', 'url5', '2013-05-05', 'CANINO', 'BLANCO Y NEGRO', 12.1, TRUE, 'COLA CORTA', FALSE, 2);
