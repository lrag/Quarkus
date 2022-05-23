package com.curso.CDI._03_Qualifier;

import javax.enterprise.context.ApplicationScoped;

@QEmisorMensajesCorreoE
@ApplicationScoped
public class EmisorMensajesCorreoE implements EmisorMensajes {

	@Override
	public void enviar(String destinatario, String mensaje) {
		System.out.println("Enviando CORREO_E '"+mensaje+"' a "+destinatario);		
	}

}
