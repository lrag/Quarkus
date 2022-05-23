package com.curso.modelo.negocio;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.curso.modelo.entidad.Cliente;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
@Transactional
public class ServicioClientes {
	
	public Uni<Cliente> insertar(Cliente cliente) {
		//LN...
		//Asociarle un comercial
		//Buscarle una sucursal
		//Enviarle un correoE 
		//...
		return Panache.withTransaction(() -> cliente.persist()); 
	}
	
	public Uni<Cliente> modificar(Cliente cliente) {
		//LN...
		return Panache.withTransaction(() -> cliente.persist()); 
	}
	
	public Uni<Boolean> borrar(Integer idCliente) {
		//LN...
		return Panache.withTransaction(() -> Cliente.deleteById(idCliente));
	}
	
}
