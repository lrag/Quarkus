package com.curso.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.MensajeEndpoint;
import com.curso.endpoint.dto.PeliculaDto;

@Path("/peliculas")
public class PeliculasEndpoint {

	private static List<PeliculaDto> peliculas;
	
	static {
		peliculas = new ArrayList<>();
		peliculas.add(new PeliculaDto(1,"2001","Stanley Kubrik","Ci-Fi",1968));
		peliculas.add(new PeliculaDto(2,"Alien","Ridley Scott","Ci-Fi",1979));
		peliculas.add(new PeliculaDto(3,"Die Hard","John McTiernan","Accion",1988));
		peliculas.add(new PeliculaDto(4,"Young Frankenstein","Mel Brooks","Comedia",1974));
		peliculas.add(new PeliculaDto(5,"Moon","Duncan Jones","Ci-Fi",2009));
		peliculas.add(new PeliculaDto(6,"El bueno, el feo y el malo","Sergio Leone","Western",1968));		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PeliculaDto> listar(){
		return peliculas;
	}
	
	@GET
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("idPelicula") Integer idPelicula) {
		return peliculas
			.stream()
			.filter(p -> p.getId().equals(idPelicula))
			.map(p -> Response.ok().entity(p).build())
			.findFirst()
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
		System.out.println(peliculaDto);
		peliculaDto.setId((int) System.currentTimeMillis());
		peliculas.add(peliculaDto);
		return Response
			.created(null)
			.entity(peliculaDto)
			.build();
	}

	@PUT
	@Path("/{idPelicula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificar(@Valid PeliculaDto peliculaDto, @PathParam("idPelicula") Integer idPelicula) {
		System.out.println(peliculaDto);
		peliculaDto.setId(idPelicula);
		for(int a=0; a<peliculas.size(); a++) {
			if(peliculas.get(a).getId().equals(idPelicula)) {
				peliculas.set(a, peliculaDto);
			}
		}
		return Response
				.ok()
				.entity(peliculaDto)
				.build();
	}

	@DELETE
	@Path("/{idPelicula}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response borrar(@PathParam("idPelicula") Integer idPelicula) {
		boolean eliminado = peliculas.removeIf(p -> p.getId().equals(idPelicula));
		if(eliminado) {
			return Response
					.ok()
					.entity(new MensajeEndpoint("200","La pelicula se eliminó"))
					.build();
		}
		return Response
				.status(404)
				.entity(new ErrorEndpoint("404","La pelicula no existe"))
				.build();
	}	
	
}
