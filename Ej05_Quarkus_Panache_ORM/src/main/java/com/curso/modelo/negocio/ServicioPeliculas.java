package com.curso.modelo.negocio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.repositorio.PeliculaRepositorio;

@ApplicationScoped
@Transactional
public class ServicioPeliculas {

	@Inject
	PeliculaRepositorio peliculaRepo;
	
	public Pelicula insertar(Pelicula pelicula) {
		//LN...
		peliculaRepo.persist(pelicula);
		return pelicula;
	}
	
	public Pelicula modificar(Pelicula pelicula) {
		//LN...
		peliculaRepo.persist(pelicula);
		return pelicula;
	}
	
	public boolean borrar(Integer idPelicula) {
		//LN...
		return peliculaRepo.deleteById(idPelicula.longValue());
	}
	
}
