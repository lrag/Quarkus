package com.curso.restproxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestStreamElementType;

import com.curso.endpoint.dto.ClienteDto;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/clientes")
@RegisterRestClient
public interface ClientesRestProxy {
	
	@GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
	@RestStreamElementType(MediaType.APPLICATION_JSON)	
	public Multi<ClienteDto> listar();
	
	@GET
	@Path("/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<ClienteDto> buscar(Integer idCliente);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> insertar(ClienteDto clienteDto);

	@PUT
	@Path("/{idCliente}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> modificar(ClienteDto clienteDto, Integer idCliente);

	@DELETE
	@Path("/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Uni<Response> borrar(Integer idCliente);
	
}
