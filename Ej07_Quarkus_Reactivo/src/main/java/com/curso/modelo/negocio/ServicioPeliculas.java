package com.curso.modelo.negocio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.repositorio.PeliculaRepositorio;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ServicioPeliculas {

	@Inject
	PeliculaRepositorio peliculaRepo;
	
	public Uni<Pelicula> insertar(Pelicula pelicula) {
		//LN...
		return Panache.withTransaction(
				() -> peliculaRepo.persist(pelicula)
			);
	}
	
	public Uni<Pelicula> modificar(Pelicula pelicula) {
		//LN...
		return Panache.withTransaction(
				() -> peliculaRepo.persist(pelicula)
			);
	}
	
	public Uni<Boolean> borrar(Integer idPelicula) {
		//LN...
		return Panache.withTransaction(() -> peliculaRepo.deleteById(idPelicula.longValue()));
	}
	
}
