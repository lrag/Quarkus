package com.curso;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Empleado;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.GestorEmpleados;

@Path("/hello")
public class GreetingResource {

	@Inject GestorClientes gestorClientes;
	@Inject GestorEmpleados gestorEmpleados;	
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	
		System.out.println("=========================================");
		Cliente cliente = new Cliente("Bartolo");
		gestorClientes.insertar(cliente);
		gestorClientes.borrar(cliente);
		
		Empleado empleado = new Empleado("Bartola");
		gestorEmpleados.insertar(empleado);
		gestorEmpleados.borrar(empleado);    	
    	
        return "Hello from RESTEasy Reactive";
    }
}