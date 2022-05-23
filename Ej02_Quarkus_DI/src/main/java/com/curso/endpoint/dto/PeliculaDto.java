package com.curso.endpoint.dto;

import javax.validation.constraints.NotNull;

import com.curso.modelo.entidad.Pelicula;

public class PeliculaDto {
	
	private Integer id;
	@NotNull
	private String titulo;
	@NotNull
	private String director;
	@NotNull
	private String genero;
	@NotNull
	private Integer year;

	public PeliculaDto() {
		super();
	}

	public PeliculaDto(Integer id, String titulo, String director, String genero, Integer year) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.genero = genero;
		this.year = year;
	}

	public PeliculaDto(Pelicula pelicula) {
		super();
		id = pelicula.getId();
		titulo = pelicula.getTitulo();
		director = pelicula.getDirector();
		genero = pelicula.getGenero();
		year = pelicula.getYear();
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "PeliculaDto [id=" + id + ", titulo=" + titulo + ", director=" + director + ", genero=" + genero + ", year="
				+ year + "]";
	}
	
	public Pelicula asPelicula() {
		return new Pelicula(id, titulo, director, genero, year);
	}
}

