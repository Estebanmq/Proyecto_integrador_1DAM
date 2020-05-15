CREATE VIEW ListaParticipante (codigo, nombre, nacionalidad) AS
	SELECT participante.codigo, participante.nombre, pais.descripcion FROM participante INNER JOIN pais on participante.nacionalidad = pais.codigo; 
