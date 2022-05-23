package com.curso;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Empleado;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.GestorEmpleados;
import com.curso.util.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
class OyenteInicio {

	@Inject GestorClientes gestorClientes;
	@Inject GestorEmpleados gestorEmpleados;
	
	@Inject
	@Named("logger")
	Logger logger;	
	@Inject
	@Named("loggerCronometro")
	Logger loggerCronometro;	
	
	void startup(@Observes StartupEvent event) {
		
		System.out.println("=========================================");
		System.out.println("EVENTO INICIALIZACION");
		/*
		Cliente cliente = new Cliente("Bartolo");
		gestorClientes.insertar(cliente);
		gestorClientes.borrar(cliente);
		
		Empleado empleado = new Empleado("Bartola");
		gestorEmpleados.insertar(empleado);
		gestorEmpleados.borrar(empleado);
		*/
		
	}
	
	void shutDown(@Observes ShutdownEvent event) {
		logger.preDestroy();
		loggerCronometro.preDestroy();
		System.out.println("=========================================");
		System.out.println("ADIOS MUNDO CRUEL");
	}
	
}