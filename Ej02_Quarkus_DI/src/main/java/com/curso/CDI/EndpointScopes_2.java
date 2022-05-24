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
import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.PeliculaDto;

@Path("/scopes2")
public class EndpointScopes_2 {

	@Inject BeanApplication beanApplication;
	@Inject BeanSingleton beanSingleton;
	@Inject BeanRequest beanRequest;
	@Inject BeanDependent beanDependent;
	
	public EndpointScopes_2() {
		super();
		System.out.println("Instanciando "+this);
	}

	@GET
	@Path("/application")
	@Produces(MediaType.TEXT_PLAIN)
	public String application() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		return beanApplication.saludar();
	}

	@GET
	@Path("/singleton")
	@Produces(MediaType.TEXT_PLAIN)
	public String singleton() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		return beanSingleton.saludar();
	}
	
	@GET
	@Path("/request")
	@Produces(MediaType.TEXT_PLAIN)
	public String request() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		return beanRequest.saludar()+":"+beanRequest.saludar();
	}
	
	@GET
	@Path("/dependent")
	@Produces(MediaType.TEXT_PLAIN)
	public String dependent() {
		System.out.println("=============================");
		System.out.println("Hola. Soy:"+this);
		return beanDependent.saludar();
	}	
	
}
