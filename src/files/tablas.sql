DROP TABLE if exists usuario CASCADE;
CREATE TABLE usuario(
    codigo_usuario SERIAL,
    nombre_usuario VARCHAR(50) NOT NULL,
    apellido_usuario VARCHAR(50) NOT NULL,
    cedula_usuario VARCHAR(20) UNIQUE NOT NULL,
    fecha_nacimiento VARCHAR(20) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(20),
    e-mail VARCHAR(20) NOT NULL,
    cargo VARCHAR(20) NOT NULL,
    estado varchar(10) NOT NULL,
    codigo_sede VARCHAR(50) NOT NULL,
    password varchar(30) NOT NULL,
    PRIMARY KEY (codigo_usuario)
);