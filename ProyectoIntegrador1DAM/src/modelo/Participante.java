package modelo;

import java.util.Date;
/** 
 * Esta clase define los atributos y métodos comunes a Director e Interprete.
 * 
 * @author 
 * @see Sexo
 * @since 01/05/2020
 */
public abstract class Participante {

	/**
	 * Atributos que consideramos importantes para identificar un participante.
	 */
	
	/** 
	 * Código unico de cada persona
	 */
	private Integer codigo;
	/**
	 * Nombre del participante
	 */
	private String nombre;
	/**
	 * Fecha de nacimiento del participante
	 * @see java.util.Date
	 */
	private Date fechaNacimiento;
	/**
	 * Sexo del participante
	 * @see Sexo
	 */
	private Sexo sexo;
	/**
	 * Nacionalidad del participante
	 */
	private Pais nacionalidad;
	
	/**
	 * Método constructor de participante
	 * @param codigo
	 * @param nombre
	 * @param fechaNacimiento
	 * @param sexo
	 */
	public Participante(int codigo, String nombre, Date fechaNacimiento, Sexo sexo, Pais nacionalidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
	}

	// GETTERS & SETTERS
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Pais getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

}

