package com.curso.modelo.repositorio;

import java.util.List;
import java.util.Optional;

public interface InterfaceRepositorio<T, k> {

	//Aqui definimos los métodos comunes a todos los repositorios
	T insertar(T cliente);
	T modificar(T cliente);
	boolean borrar(k id);
	Optional<T> buscar(k idCliente);
	List<T> listar();
	
}
