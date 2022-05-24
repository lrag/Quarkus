package com.curso.CDI._05_Producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

//Aqui no necesitamos nada
//@ApplicationScoped
public class FactoriaClienteDao {

	@Inject
	@ConfigProperty(name="bbdd")
	String bbdd;
	
	@Produces
	@RequestScoped
	public ClienteDao getClienteDao() {
		ClienteDao clienteDao = null;
		switch(bbdd) {
			case "Mysql" : 
				clienteDao = new ClienteDaoMysql();
				break;
			case "Oracle" : 
				clienteDao = new ClienteDaoOracle();
				break;
			default : throw new RuntimeException("Problema con la configuración de la base de datos");
		}		
		return clienteDao;
	}
	
}
