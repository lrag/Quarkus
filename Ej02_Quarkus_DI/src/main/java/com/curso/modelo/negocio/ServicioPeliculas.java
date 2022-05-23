package com.curso.modelo.negocio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.repositorio.PeliculaRepositorio;

@ApplicationScoped
public class ServicioPeliculas {

	@Inject
	PeliculaRepositorio peliculaRepo;
	
	public Pelicula insertar(Pelicula pelicula) {
		//LN...
		return peliculaRepo.insertar(pelicula);
	}
	
	public Pelicula modificar(Pelicula pelicula) {
		//LN...
		return peliculaRepo.modificar(pelicula);
	}
	
	public boolean borrar(Integer idPelicula) {
		//LN...
		return peliculaRepo.borrar(idPelicula);
	}
	
}
