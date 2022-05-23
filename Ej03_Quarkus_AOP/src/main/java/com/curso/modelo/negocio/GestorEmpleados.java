package com.curso.modelo.negocio;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import com.curso.modelo.entidad.Empleado;
import com.curso.modelo.negocio.aop.ICronometro;
import com.curso.modelo.negocio.aop.ILog;

@ILog
@ICronometro
@ApplicationScoped
public class GestorEmpleados {

	@PreDestroy
	public void preDestroy() {
		System.out.println("---------------------------------");
		System.out.println("/PRE_DESTROY DE GESTOR_EMPLEADOS/");
		System.out.println("---------------------------------");
	}
	
	public void insertar(Empleado empleado){		
		System.out.println("Insertando el empleado "+empleado+" en GestorEmpleados");
		//LN		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
	public void borrar(Empleado empleado){
		System.out.println("Borrando el empleado "+empleado+" en GestorEmpleados");
		//LN
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
}
