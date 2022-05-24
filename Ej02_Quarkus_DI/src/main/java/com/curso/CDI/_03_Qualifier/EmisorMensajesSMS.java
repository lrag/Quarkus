package com.curso.CDI._03_Qualifier;

import javax.enterprise.context.ApplicationScoped;

@QEmisorMensajesSMS
@ApplicationScoped
public class EmisorMensajesSMS implements EmisorMensajes {

	@Override
	public void enviar(String destinatario, String mensaje) {
		System.out.println("Enviando SMS '"+mensaje+"' a "+destinatario);		
	}

}
