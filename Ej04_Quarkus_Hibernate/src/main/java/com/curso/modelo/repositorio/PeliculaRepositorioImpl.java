package com.curso.modelo.repositorio;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.curso.modelo.entidad.Pelicula;

@Singleton
public class PeliculaRepositorioImpl implements PeliculaRepositorio{
	
	@Inject
	protected EntityManager em;
	
	@Override
	public Pelicula insertar(Pelicula pelicula) {
		em.persist(pelicula);
		return pelicula;
	}
	
	@Override
	public Pelicula modificar(Pelicula pelicula) {
		return em.merge(pelicula);
	}
	
	@Override
	public boolean borrar(Integer id) {
		Pelicula entidad = em.find(Pelicula.class,  id);
		if(entidad == null) {
			return false;
		}
		em.remove(entidad);
		return true;
	}		
	
	@Override
	public Optional<Pelicula> buscar(Integer id) {
		Pelicula entidad = em.find(Pelicula.class,  id);
		return Optional.of(entidad);
	}
	
	@Override
	public List<Pelicula> listar() {
		return em.createQuery("select p from Pelicula p").getResultList();
	}	
	
}
