package com.curso.modelo.negocio;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.curso.modelo.entidad.Cliente;

@ApplicationScoped
@Transactional
public class ServicioClientes {
	
	public Cliente insertar(Cliente cliente) {
		//LN...
		//Asociarle un comercial
		//Buscarle una sucursal
		//Enviarle un correoE 
		//...
		cliente.persist(); //Solo hace el insert
		return cliente;
	}
	
	public Cliente modificar(Cliente cliente) {
		//LN...
		cliente.persist();
		return cliente;
	}
	
	public boolean borrar(Integer idCliente) {
		//LN...
		return Cliente.deleteById(idCliente.longValue());
	}
	
}
