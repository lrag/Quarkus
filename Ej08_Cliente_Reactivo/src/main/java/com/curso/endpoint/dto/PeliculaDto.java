package com.curso.endpoint.dto;

public class PeliculaDto {
	
	private Long id;
	private String titulo;
	private String director;
	private String genero;
	private Integer year;

	public PeliculaDto() {
		super();
	}

	public PeliculaDto(Long id, String titulo, String director, String genero, Integer year) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.genero = genero;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}

