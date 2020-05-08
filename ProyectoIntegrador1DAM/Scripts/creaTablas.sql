/*
-- Drop Indexes

  DROP INDEX codPelcodInt;
  DROP INDEX tituloIndex;



-- Drop Tables 

  DROP TABLE Actuacion;
  DROP TABLE Documental;
  DROP TABLE Pelicula;
  DROP TABLE EjemplarAudiovisual;
  DROP TABLE Director;
  DROP TABLE Interprete;
  DROP TABLE Participante;
  DROP TABLE Pais;
*/

-- Create Tables

-- Tabla de relación N:M entre Película e Interprete
CREATE TABLE Actuacion
(
	-- Código de Película
	codigoPelicula integer NOT NULL,
	-- Código de Participante
	codigoInterprete integer NOT NULL,
	-- Nombre de personaje del Interprete en la Película
	nombrePersonaje varchar(33) NOT NULL
);


-- Tabla perteneciente a la clase Director
CREATE TABLE Director
(
	-- Código de participante
	codigo integer NOT NULL CONSTRAINT director_codigo_pk_fk PRIMARY KEY,
	generoPreferido varchar(15)
);


-- Tabla perteneciente a la clase Documental
CREATE TABLE Documental
(
	-- Código de Ejemplar Audiovisual
	codigo integer NOT NULL,
	-- Género del Documental
	generoDocumental varchar(20) NOT NULL,
	PRIMARY KEY (codigo)
);


-- Tabla perteneciente a la clase abstracta EjemplarAudiovisual
CREATE TABLE EjemplarAudiovisual
(
	-- Código de Ejemplar Audiovisual
	codigo integer NOT NULL,
	-- Título del ejemplar audiovisual
	titulo varchar(33) NOT NULL CONSTRAINT ejemplaraudiovisual_titulo_uq UNIQUE,
	-- Año de creación del ejemplar audiovisual
	anyo integer NOT NULL,
	-- Código de Director
	director integer NOT NULL,
	-- Nacionalidad del ejemplar audiovisual
	nacionalidad integer,
	-- Sipnosis del ejemplar audiovisual
	sipnosis varchar(256),
	PRIMARY KEY (codigo)
);


-- Tabla perteneciente a la clase Participante
CREATE TABLE Interprete
(
	-- Código de Participante
	codigo integer NOT NULL,
	-- Caché del intérprete
	cache double,
	PRIMARY KEY (codigo)
);


-- Relación de países
CREATE TABLE Pais
(
	-- Código de país
	codigo integer NOT NULL,
	-- Descripción del país
	descripcion varchar(25) NOT NULL,
	PRIMARY KEY (codigo)
);


-- Tabla perteneciente a la clase abstracta Participante
CREATE TABLE Participante
(
	-- Código de participante
	codigo integer NOT NULL,
	-- Nombre del participante
	nombre varchar(33) NOT NULL,
	-- Fecha de nacimiento
	fechaNacimiento date NOT NULL,
	-- Sexo
	sexo varchar(10) NOT NULL,
	-- Código de país
	nacionalidad integer,
	PRIMARY KEY (codigo)
);


-- Tabla perteneciente a la clase Pelicula
CREATE TABLE Pelicula
(
	-- Código de Película
	codigo integer NOT NULL,
	-- Género de la Película
	generoPelicula varchar(20) NOT NULL,
	PRIMARY KEY (codigo)
);



/* Create Foreign Keys */

ALTER TABLE EjemplarAudiovisual
	ADD FOREIGN KEY (director)
	REFERENCES Director (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Documental
	ADD FOREIGN KEY (codigo)
	REFERENCES EjemplarAudiovisual (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Pelicula
	ADD FOREIGN KEY (codigo)
	REFERENCES EjemplarAudiovisual (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Actuacion
	ADD FOREIGN KEY (codigoInterprete)
	REFERENCES Interprete (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EjemplarAudiovisual
	ADD FOREIGN KEY (nacionalidad)
	REFERENCES Pais (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Participante
	ADD FOREIGN KEY (nacionalidad)
	REFERENCES Pais (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Director
	ADD FOREIGN KEY (codigo)
	REFERENCES Participante (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Interprete
	ADD FOREIGN KEY (codigo)
	REFERENCES Participante (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Actuacion
	ADD FOREIGN KEY (codigoPelicula)
	REFERENCES Pelicula (codigo)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Create Indexes */

CREATE UNIQUE INDEX codPelcodInt ON Actuacion (codigoPelicula, codigoInterprete);
CREATE INDEX tituloIndex ON EjemplarAudiovisual (titulo);



