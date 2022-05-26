package com.curso.restproxy;

import javax.inject.Inject;
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

import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.MensajeEndpoint;
import com.curso.endpoint.dto.PeliculaDto;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/peliculas")
@RegisterRestClient
public interface PeliculasRestProxy {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<PeliculaDto> listar();
	
	@GET
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> buscar(Long idPelicula);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> insertar(PeliculaDto peliculaDto);

	@PUT
	@Path("/{idPelicula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> modificar(PeliculaDto peliculaDto, Long idPelicula);

	@DELETE
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Uni<Response> borrar(Integer idPelicula);

}
