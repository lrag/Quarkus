package com.curso.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.MensajeEndpoint;
import com.curso.endpoint.dto.PeliculaDto;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.repositorio.PeliculaRepositorio;

@Path("/peliculas")
public class PeliculasEndpoint {

	@Inject
	PeliculaRepositorio peliculaRepo;
	
	@Inject
	ServicioPeliculas servicioPeliculas;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PeliculaDto> listar(){
		return peliculaRepo
			.listar()
			.stream()
			.map(p -> new PeliculaDto(p))
			.collect(Collectors.toList());
	}
	
	@GET
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(Integer idPelicula) {
		return peliculaRepo
			.buscar(idPelicula)
			.map(p -> Response.ok(new PeliculaDto(p)).build())
			.orElse(
				Response
					.status(404)
					.entity(new ErrorEndpoint("404","La película no existe"))
					.build()
				);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertar(@Valid PeliculaDto peliculaDto) {
		Pelicula peliculaInsertada = servicioPeliculas.insertar(peliculaDto.asPelicula());
		return Response
			.created(null)
			.entity(new PeliculaDto(peliculaInsertada))
			.build();
	}

	@PUT
	@Path("/{idPelicula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificar(@Valid PeliculaDto peliculaDto, Integer idPelicula) {
		peliculaDto.setId(idPelicula);
		Pelicula peliculaInsertada = servicioPeliculas.modificar(peliculaDto.asPelicula());
		return Response
			.created(null)
			.entity(new PeliculaDto(peliculaInsertada))
			.build();
	}

	@DELETE
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response borrar(Integer idPelicula) {
		boolean eliminado = servicioPeliculas.borrar(idPelicula);
		if(eliminado) {
			return Response
					.ok()
					.entity(new MensajeEndpoint("200","La pelicula se eliminó"))
					.build();
		}
		return Response
				.status(404)
				.entity(new MensajeEndpoint("404","La pelicula no existe"))
				.build();
	}	
	
}
