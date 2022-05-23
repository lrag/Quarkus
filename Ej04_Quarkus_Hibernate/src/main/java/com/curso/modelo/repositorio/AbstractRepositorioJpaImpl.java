package com.curso.modelo.repositorio;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractRepositorioJpaImpl<T, k> implements InterfaceRepositorio<T, k>{

	@Inject
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	private Class<T> tipo = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass())
			.getActualTypeArguments()[0];
	
	
	@Override
	public T insertar(T obj) {
		em.persist(obj);
		return obj;
	}
	
	@Override
	public T modificar(T obj) {
		return em.merge(obj);
	}
	
	@Override
	public boolean borrar(k id) {
		T entidad = em.find(tipo,  id);
		if(entidad == null) {
			return false;
		}
		em.remove(entidad);
		return true;
	}		
	
	@Override
	public Optional<T> buscar(k id) {
		T entidad = em.find(tipo,  id);
		return Optional.of(entidad);
	}
	
	@Override
	public List<T> listar() {
		return em.createQuery("select o from "+tipo.getName()+" o").getResultList();
	}	
	
}
