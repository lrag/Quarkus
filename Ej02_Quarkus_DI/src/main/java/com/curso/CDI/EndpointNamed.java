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
import com.curso.CDI._04_Named.ProxyMensajeriaKafka;
import com.curso.CDI._04_Named.ProxyMensajeriaRabbitMQ;

@Path("/named")
public class EndpointNamed {

	@Inject	
	ProxyMensajeriaKafka proxyKafka_1;
	@Inject
	ProxyMensajeriaRabbitMQ proxyRabbitMQ_1;

	@Named("Kafka")
	ProxyMensajeria proxyKafka_2;
	@Named("RabbitMQ")
	ProxyMensajeria proxyRabbitMQ_2;
	
	public EndpointNamed() {
		super();
		System.out.println("Instanciando "+this);
	}

	@GET
	@Path("/uno")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> uno() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		Map<String,String> mapa = new HashMap<>();
		mapa.put("proxyKafka", proxyKafka_1.toString());
		mapa.put("proxyRabbitMQ", proxyRabbitMQ_1.toString());
		return mapa;
	}

	@GET
	@Path("/dos")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> dos() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		Map<String,String> mapa = new HashMap<>();
		mapa.put("proxyKafka", proxyKafka_2.toString());
		mapa.put("proxyRabbitMQ", proxyRabbitMQ_2.toString());
		return mapa;
	}
	
}
