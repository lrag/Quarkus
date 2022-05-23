package com.curso.CDI._05_Producer;

import java.util.List;
import com.curso.modelo.entidad.Cliente;

public class ClienteDaoOracle implements ClienteDao {

	public Cliente insertar(Cliente cliente) {
		System.out.println("Insertando el cliente en Oracle");
		return null;
	}
	
	public Cliente modificar(Cliente cliente) {
		System.out.println("Modificando el cliente en Oracle");
		return null;
	}
	
	public Boolean borrar(Integer idCliente) {
		System.out.println("Borrando el cliente en Oracle");
		return null;
	}
	
	public Cliente buscar(Integer idCliente) {
		System.out.println("Buscando el cliente en Oracle");
		return null;
	}
	
	public List<Cliente> listar(){
		System.out.println("Listando los clientes en Oracle");
		return null;
	}
	
}
