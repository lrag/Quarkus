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
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.ServicioClientes;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/clientes")
public class ClientesEndpoint {
	
	@Inject
	ServicioClientes servicioClientes;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<ClienteDto> listar(){
		return Cliente
			.streamAll()
			.map(c -> new ClienteDto((Cliente) c));
	}
	
	@GET
	@Path("/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> buscar(Integer idCliente) {
		System.out.println("==========================");
		System.out.println("Buscando:"+idCliente);
		return Cliente
			.findById(idCliente)
			.map( c -> {				
				if(c == null) {
					return Response
						.status(404)			
						.entity(new ErrorEndpoint("404","El cliente no existe"))
						.build();	
				}
				Cliente cli = (Cliente) c;
				return Response
					.ok()			
					.entity(new ClienteDto(cli))
					.build(); 
			});
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> insertar(@Valid ClienteDto clienteDto) {
		return servicioClientes
				.insertar(clienteDto.asCliente())
				.map(clienteInsertado -> Response.status(201).entity(new ClienteDto(clienteInsertado)).build());
	}

	@PUT
	@Path("/{idCliente}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> modificar(@Valid ClienteDto clienteDto, Integer idCliente) {
		return servicioClientes
				.modificar(clienteDto.asCliente())
				.map(clienteInsertado -> Response.status(201).entity(new ClienteDto(clienteInsertado)).build());
	}	

	@DELETE
	@Path("/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Uni<Response> borrar(Integer idCliente) {
		return servicioClientes
			.borrar(idCliente)
			.map(borrado -> {
				if(borrado) {
					return Response
							.ok()
							.entity(new MensajeEndpoint("200","El cliente se eliminó"))
							.build();
				} 
				return Response
						.status(404)
						.entity(new MensajeEndpoint("404","El cliente no existe"))
						.build();
			});
	}	
	
}
