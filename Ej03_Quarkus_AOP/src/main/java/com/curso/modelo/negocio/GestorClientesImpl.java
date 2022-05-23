package com.curso.modelo.negocio;

import javax.enterprise.context.ApplicationScoped;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.aop.ICronometro;
import com.curso.modelo.negocio.aop.ILog;

@ILog
@ICronometro
@ApplicationScoped
public class GestorClientesImpl implements GestorClientes {

	@Override
	public void insertar(Cliente cliente){		
		System.out.println("Insertando en GestorClientesImpl:"+cliente);
		//LN
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void borrar(Cliente cliente){
		System.out.println("Borrando en GestorClientesImpl:"+cliente);
		//LN
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
