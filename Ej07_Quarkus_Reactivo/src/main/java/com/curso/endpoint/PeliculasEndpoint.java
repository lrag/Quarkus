package com.curso.endpoint;

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

import com.curso.endpoint.dto.ClienteDto;
import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.MensajeEndpoint;
import com.curso.endpoint.dto.PeliculaDto;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.repositorio.PeliculaRepositorio;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/peliculas")
public class PeliculasEndpoint {

	@Inject
	PeliculaRepositorio peliculaRepo;
	
	@Inject
	ServicioPeliculas servicioPeliculas;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<PeliculaDto> listar(){
		return peliculaRepo.streamAll().map(p -> new PeliculaDto(p));
	}
	
	@GET
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> buscar(Long idPelicula) {
		return peliculaRepo
			.findById(idPelicula)
			.map(p -> {
				if(p == null) {
					return Response
						.status(404)			
						.entity(new ErrorEndpoint("404","La pelicula no existe"))
						.build();	
				}
				return Response
					.ok()			
					.entity(new PeliculaDto(p))
					.build(); 				
			});
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> insertar(@Valid PeliculaDto peliculaDto) {
		return servicioPeliculas
			.insertar(peliculaDto.asPelicula())
			.map(peliculaInsertada -> Response.status(201).entity(new PeliculaDto(peliculaInsertada)).build());
	}

	@PUT
	@Path("/{idPelicula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> modificar(@Valid PeliculaDto peliculaDto, Long idPelicula) {
		return servicioPeliculas
			.insertar(peliculaDto.asPelicula())
			.map(peliculaInsertada -> Response.status(201).entity(new PeliculaDto(peliculaInsertada)).build());
	}	

	@DELETE
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Uni<Response> borrar(Integer idPelicula) {
		return servicioPeliculas
			.borrar(idPelicula)
			.map(borrado -> {
				if(borrado) {
					return Response
							.ok()
							.entity(new MensajeEndpoint("200","La película se eliminó"))
							.build();
				} 
				return Response
						.status(404)
						.entity(new MensajeEndpoint("404","La película no existe"))
						.build();
			});
	}	
}
