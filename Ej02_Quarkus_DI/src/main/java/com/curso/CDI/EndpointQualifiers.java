package com.curso.CDI;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.curso.CDI._03_Qualifier.EmisorMensajes;
import com.curso.CDI._03_Qualifier.EmisorMensajesSMS;
import com.curso.CDI._03_Qualifier.QEmisorMensajesCorreoE;
import com.curso.CDI._03_Qualifier.QEmisorMensajesPalomaMensajera;
import com.curso.CDI._03_Qualifier.QEmisorMensajesSMS;

@Path("/qualifiers")
public class EndpointQualifiers {

	//@Inject 
	@QEmisorMensajesSMS     
	EmisorMensajesSMS emisorSMS; 
	@QEmisorMensajesCorreoE 
	EmisorMensajes emisorCorreoE; 
	@QEmisorMensajesPalomaMensajera 
	EmisorMensajes emisorPalomaMensajera; 
	
	public EndpointQualifiers() {
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
		mapa.put("sms", emisorSMS.toString());
		mapa.put("correoE", emisorCorreoE.toString());
		mapa.put("palomaMensajera", emisorPalomaMensajera.toString());
		return mapa;
	}
	
}
