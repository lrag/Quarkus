package com.curso.modelo.entidad;

public class Premio {

	Integer id;
	private String nombre;
	private String fecha;
	private Integer idPelicula;

	public Premio() {
		super();
	}

	public Premio(Integer id, String nombre, String fecha, Integer idPelicula) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.idPelicula = idPelicula;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public Integer getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Integer idPelicula) {
		this.idPelicula = idPelicula;
	}

	@Override
	public String toString() {
		return "Premio [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", idPelicula=" + idPelicula + "]";
	}

}
