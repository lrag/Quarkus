package com.curso.CDI;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.curso.CDI._04_Named.ProxyMensajeria;
import com.curso.CDI._04_Named.ProxyMensajeriaRabbitMQ;
import com.curso.CDI._05_Producer.ClienteDao;

@Path("/producer")
public class EndpointProducer {

	@Inject	
	ClienteDao clienteDao;
	
	public EndpointProducer() {
		super();
		System.out.println("Instanciando "+this);
	}

	@GET
	@Path("/uno")
	@Produces(MediaType.APPLICATION_JSON)
	public String uno() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		return clienteDao.toString();
	}
	
}
