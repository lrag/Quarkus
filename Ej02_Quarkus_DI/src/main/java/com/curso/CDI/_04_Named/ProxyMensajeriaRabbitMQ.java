package com.curso.CDI._04_Named;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("RabbitMQ")
@ApplicationScoped
public class ProxyMensajeriaRabbitMQ implements ProxyMensajeria{

	@Override
	public void enviarMensaje(String destino, Object mensaje) {
	}

}
