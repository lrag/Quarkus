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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.curso.endpoint.dto.ClienteDto;
import com.curso.endpoint.dto.ErrorEndpoint;
import com.curso.endpoint.dto.MensajeEndpoint;
import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.ServicioClientes;

@Path("/clientes")
public class ClientesEndpoint {
	
	@Inject
	ServicioClientes servicioClientes;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClienteDto> listar(){
		return Cliente
				.streamAll()
				.map(c -> new ClienteDto((Cliente) c))
				.collect(Collectors.toList());
	}
	
	@GET
	@Path("/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("idCliente") Integer idCliente) {
		Cliente cliente = Cliente.findById(idCliente);
		if(cliente==null) {
			return Response
				.status(404)
				.entity(new ErrorEndpoint("404","El cliente no existe"))
				.build();			
		}		
		return Response.ok(new ClienteDto(cliente)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertar(@Valid ClienteDto clienteDto) {
		Cliente clienteInsertada = servicioClientes.insertar(clienteDto.asCliente());
		return Response
			.created(null)
			.entity(new ClienteDto(clienteInsertada))
			.build();
	}

	@PUT
	@Path("/{idCliente}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modificar(@Valid ClienteDto clienteDto, @PathParam("idCliente") Integer idCliente) {
		clienteDto.setId(idCliente);
		Cliente clienteInsertada = servicioClientes.modificar(clienteDto.asCliente());
		return Response
			.created(null)
			.entity(new ClienteDto(clienteInsertada))
			.build();
	}	

	@DELETE
	@Path("/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response borrar(@PathParam("idCliente")Integer idCliente) {
		boolean eliminado = servicioClientes.borrar(idCliente);
		if(eliminado) {
			return Response
					.ok()
					.entity(new MensajeEndpoint("200","El cliente se eliminó"))
					.build();
		}
		return Response
				.status(404)
				.entity(new MensajeEndpoint("404","El cliente no existe"))
				.build();
	}	
	
}
