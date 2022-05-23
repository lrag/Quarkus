package com.curso.CDI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.curso.CDI._01_Scopes.BeanApplication;
import com.curso.CDI._01_Scopes.BeanDependent;
import com.curso.CDI._01_Scopes.BeanRequest;
import com.curso.CDI._01_Scopes.BeanSingleton;
import com.curso.CDI._02_CicloDeVida.BeanCicloDeVida;
import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.PeliculaDto;

@Path("/CDI")
public class EndpointCicloDeVida {

	@Inject BeanCicloDeVida beanCicloDeVida;
	
	public EndpointCicloDeVida() {
		super();
		System.out.println("Instanciando "+this);
	}

	@GET
	@Path("/ciclodevida")
	@Produces(MediaType.TEXT_PLAIN)
	public String application() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		return beanCicloDeVida.saludar();
	}
	
}
