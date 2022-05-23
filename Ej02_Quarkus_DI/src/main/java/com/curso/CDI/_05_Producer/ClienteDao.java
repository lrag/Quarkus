package com.curso.CDI._05_Producer;

import java.util.List;
import com.curso.modelo.entidad.Cliente;

public interface ClienteDao {

	Cliente insertar(Cliente cliente);
	Cliente modificar(Cliente cliente);
	Boolean borrar(Integer idCliente);
	Cliente buscar(Integer idCliente);
	List<Cliente> listar();
	
}
