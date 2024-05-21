-- Base de Datos Inmobiliaria
CREATE DATABASE inmobiliaria; 
USE inmobiliaria;


-- CIUDAD 
CREATE TABLE ciudad (
    id_ciudad INT NOT NULL AUTO_INCREMENT,
    nombre_ciudad VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_ciudad)
);



-- ZONA
CREATE TABLE zona (
    id_zona INT NOT NULL AUTO_INCREMENT,
    nombre_zona VARCHAR(50) NOT NULL,
    id_ciudad INT NOT NULL,
    PRIMARY KEY (id_zona),
    CONSTRAINT fk_id_ciudad FOREIGN KEY (id_ciudad) REFERENCES ciudad(id_ciudad)
);



-- OFICINA
CREATE TABLE oficina (
    id_oficina INT NOT NULL AUTO_INCREMENT,
    hay_llave BOOLEAN NOT NULL,
    id_zona INT NOT NULL,
    PRIMARY KEY (id_oficina),
    CONSTRAINT fk_id_zona FOREIGN KEY (id_zona) REFERENCES zona(id_zona)
);

-- TIPO INMUEBLE
CREATE TABLE tipo_inmueble (
    id_tipo_inmueble INT NOT NULL AUTO_INCREMENT,
    diafono BOOLEAN NOT NULL,
    acondicionado BOOLEAN NOT NULL,
    puertas_entrada INT NOT NULL,
    puerta_blindada BOOLEAN NOT NULL,
    gas_ciudad BOOLEAN NOT NULL,
    urbanizacion VARCHAR(100) NOT NULL,
    tamaño_parcela VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id_tipo_inmueble)
);

-- ESTANCIA
CREATE TABLE estancia (
    id_estancia INT NOT NULL AUTO_INCREMENT,
    nombre_estancia VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    id_tipo_inmueble INT NOT NULL,
    PRIMARY KEY (id_estancia),
    CONSTRAINT fk_id_tipo_inmueble FOREIGN KEY (id_tipo_inmueble) REFERENCES tipo_inmueble(id_tipo_inmueble)
);

-- INMUEBLE
CREATE TABLE inmueble (
    id_inmueble INT NOT NULL AUTO_INCREMENT,
    referencia VARCHAR(50) NOT NULL,
    precio FLOAT NOT NULL,
    superficie FLOAT NOT NULL,
    direccion VARCHAR(100),
    id_oficina INT NOT NULL,
    id_tipo_inmueble INT NOT NULL,
    PRIMARY KEY (id_inmueble),
    CONSTRAINT fk_inmueble_oficina FOREIGN KEY (id_oficina) REFERENCES oficina(id_oficina),
    CONSTRAINT fk_tipo_inmueble FOREIGN KEY (id_tipo_inmueble) REFERENCES tipo_inmueble(id_tipo_inmueble)
);

-- PROPIETARIO
CREATE TABLE propietario (
    id_propietario INT NOT NULL AUTO_INCREMENT,  
    nombre_propietario VARCHAR(50) NOT NULL,
    telefono INT NOT NULL,
    PRIMARY KEY (id_propietario)
);

-- TRANSACCION
CREATE TABLE transaccion (
    id_transaccion INT NOT NULL AUTO_INCREMENT,
    tipo_transaccion VARCHAR(100),
    id_inmueble INT NOT NULL,
    id_propietario INT NOT NULL,
    PRIMARY KEY (id_transaccion),
    CONSTRAINT fk_id_inmueble FOREIGN KEY (id_inmueble) REFERENCES inmueble(id_inmueble),
    CONSTRAINT fk_propietario FOREIGN KEY (id_propietario) REFERENCES propietario(id_propietario)
);

-- CLIENTE
CREATE TABLE cliente (
    id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre_cliente VARCHAR(50) NOT NULL,
    telefono varchar(15) NOT NULL,
    PRIMARY KEY (id_cliente)
);

-- VISITA
CREATE TABLE visita (
    id_visita INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    comentario VARCHAR(200) NOT NULL,
    id_inmueble INT NOT NULL,
    id_cliente INT NOT NULL,
    PRIMARY KEY (id_visita),
    CONSTRAINT fk_visita_inmueble FOREIGN KEY (id_inmueble) REFERENCES inmueble(id_inmueble),
    CONSTRAINT fk_visita_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);
-- Tablas de Gestión de Roles y Operaciones

-- ROL
CREATE TABLE rol (
    id_rol INT NOT NULL AUTO_INCREMENT,
    nombre_rol VARCHAR(50),
    PRIMARY KEY (id_rol)
);

-- USERS
CREATE TABLE users (
    id_users INT NOT NULL AUTO_INCREMENT,
    nombre_user VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    fecha DATE,
    id_rol INT NOT NULL,
    PRIMARY KEY (id_users),
    CONSTRAINT fk_id_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);


-- MODULO
CREATE TABLE modulo (
    id_modulo INT NOT NULL AUTO_INCREMENT,
    nombre_modulo VARCHAR(50),
    PRIMARY KEY (id_modulo)
);

-- OPERACIONES
CREATE TABLE operaciones (
    id_operaciones INT NOT NULL AUTO_INCREMENT,
    nombre_operaciones VARCHAR(50),
    id_modulo INT NOT NULL,
    PRIMARY KEY (id_operaciones),
    CONSTRAINT fk_id_mod FOREIGN KEY (id_modulo) REFERENCES modulo(id_modulo)
);

-- ROL_OPERACION
CREATE TABLE rol_operacion (
    id_rol_operacion INT NOT NULL AUTO_INCREMENT,
    id_rol INT NOT NULL,
    id_operaciones INT NOT NULL,
    PRIMARY KEY (id_rol_operacion),
    CONSTRAINT fk_idRol FOREIGN KEY (id_rol) REFERENCES rol(id_rol),
    CONSTRAINT fk_idOperaciones FOREIGN KEY (id_operaciones) REFERENCES operaciones(id_operaciones)
);

-- Usuarios y Privilegios
CREATE USER 'administrador'@'%' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON inmobiliaria.* TO 'administrador'@'%';
FLUSH PRIVILEGES;

CREATE USER 'agente_inmobiliario'@'%' IDENTIFIED BY 'agente';
GRANT INSERT, SELECT ON inmobiliaria.tipo_inmueble TO 'agente_inmobiliario'@'%';
GRANT INSERT, SELECT ON inmobiliaria.inmueble TO 'agente_inmobiliario'@'%';
GRANT INSERT, SELECT ON inmobiliaria.estancia TO 'agente_inmobiliario'@'%';
GRANT INSERT, SELECT ON inmobiliaria.propietario TO 'agente_inmobiliario'@'%';
GRANT INSERT, SELECT ON inmobiliaria.ciudad TO 'agente_inmobiliario'@'%';
GRANT INSERT, SELECT ON inmobiliaria.zona TO 'agente_inmobiliario'@'%';
FLUSH PRIVILEGES;

CREATE USER 'gerente'@'%' IDENTIFIED BY 'gerent';
GRANT INSERT, UPDATE, DELETE ON inmobiliaria.* TO 'gerente'@'%';
FLUSH PRIVILEGES;

CREATE USER 'backup'@'%' IDENTIFIED BY 'back';
GRANT SELECT, SHOW VIEW, LOCK TABLES, RELOAD, FILE ON *.* TO 'backup'@'%';
FLUSH PRIVILEGES;

CREATE USER 'restore'@'%' IDENTIFIED BY 'res';
GRANT SELECT, INSERT, DELETE, CREATE, DROP, FILE ON *.* TO 'restore'@'%';
FLUSH PRIVILEGES;
