package com.curso.endpoint;

import java.util.ArrayList;
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

import org.jboss.resteasy.reactive.RestStreamElementType;

import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.MensajeEndpoint;
import com.curso.endpoint.dto.PeliculaDto;
import com.curso.modelo.negocio.ServicioPeliculas;
import com.curso.modelo.repositorio.PeliculaRepositorio;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/peliculas")
public class PeliculasEndpoint {

	@Inject
	PeliculaRepositorio peliculaRepo;
	
	@Inject
	ServicioPeliculas servicioPeliculas;

	/////////////////////////////////////////
	// Cuando estamos hablando de un MULTI //
	// o de vários elementos               //
	/////////////////////////////////////////
	
	//Cuanto tenemos varios elementos y nos importa un pito si el cliente es reactivo o no
	//entonces hacemos esto:
	//-Devolver un Uni<List>
	//-Devolverlo como un JSON del tirón
	@GET
	@Path("/clienteNoReactivo")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<List<PeliculaDto>> listar_para_clientes_no_reactivos(){
		return peliculaRepo
			.listAll() //de aqui sale un Uni<List<Pelicula>>
			.map(peliculas -> {
				return peliculas.stream().map(p -> new PeliculaDto(p)).collect(Collectors.toList());
			}); //de aqui sale un Uni<List<PeliculaDto>>
	}
	
	//Cuando tenemos varios elementos y nos interesa un cliente reactivo
	//entonces hacemos esto:
	//-devolver un Multi<T>
	//-devolverlo como SERVER_SENT_EVENTS	
	@GET
	@Path("/clienteReactivo")
    @Produces(MediaType.SERVER_SENT_EVENTS)
	@RestStreamElementType(MediaType.APPLICATION_JSON)
	public Multi<PeliculaDto> listar_para_clientes_reactivos(){
		return peliculaRepo
				.streamAll() //De aqui sale un Multi<Pelicula>
				.map(p -> new PeliculaDto(p)); //Aqui llega una pelicula y sale un PeliculaDto
	}
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> listar_desastroso_no_hacer_en_la_vida(){
		//En este caso es otro el hilo que se subscribe (por que el multi es 'emit-on')
		//pero entonces el hilo del event loop sale del método con una lista vacía
		List<Pelicula> peliculas = new ArrayList<>();
		peliculaRepo
			.findAll()		
			.stream()
			.subscribe()
			.with(p -> peliculas.add(p));
		return peliculas;

		//Esto está mal porque el hilo del event loop se queda ejecutando la query!!!!
		return peliculaRepo.findAll().list().await().indefinitely();
	}
	*/
	
	@GET
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> buscar(Long idPelicula) {
		return peliculaRepo
			.findById(idPelicula) //Uni<Pelicula>
			.map(p -> { //Aqui llega una pelicula
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
		System.out.println(Thread.currentThread().getName()+":ReliculasEndpoint.insertar)");
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
