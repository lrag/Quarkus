package com.curso.CDI._04_Named;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("Kafka")
@ApplicationScoped
public class ProxyMensajeriaKafka implements ProxyMensajeria{

	@Override
	public void enviarMensaje(String destino, Object mensaje) {
	}

}
