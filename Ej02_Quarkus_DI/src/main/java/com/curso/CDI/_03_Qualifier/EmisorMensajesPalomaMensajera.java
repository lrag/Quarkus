package com.curso.CDI._03_Qualifier;

import javax.enterprise.context.ApplicationScoped;

@QEmisorMensajesPalomaMensajera
@ApplicationScoped
public class EmisorMensajesPalomaMensajera implements EmisorMensajes {

	@Override
	public void enviar(String destinatario, String mensaje) {
		System.out.println("Enviando SMS '"+mensaje+"' a "+destinatario);		
	}

}
