package com.curso.CDI._05_Producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.curso.modelo.entidad.Cliente;

public class ClienteDaoMysql implements ClienteDao {
	
	public Cliente insertar(Cliente cliente) {
		System.out.println("Insertando el cliente en Mysql");
		return null;
	}
	
	public Cliente modificar(Cliente cliente) {
		System.out.println("Modificando el cliente en Mysql");
		return null;
	}
	
	public Boolean borrar(Integer idCliente) {
		System.out.println("Borrando el cliente en Mysql");
		return null;
	}
	
	public Cliente buscar(Integer idCliente) {
		System.out.println("Buscando el cliente en Mysql");
		return null;
	}
	
	public List<Cliente> listar(){
		System.out.println("Listando los clientes en Mysql");
		return null;
	}
	
}
