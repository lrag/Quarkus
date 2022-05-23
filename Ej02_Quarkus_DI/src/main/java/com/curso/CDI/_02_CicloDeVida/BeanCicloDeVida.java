package com.curso.CDI._02_CicloDeVida;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BeanCicloDeVida {

	public BeanCicloDeVida() {
		super();
		System.out.println("Instanciando "+this);
	}
	
	@PostConstruct
	public void inicializar() {
		System.out.println("Inicializando "+this);
	}
	
	@PreDestroy
	public void finalizar() {
		System.out.println("Finalizando "+this);
	}	

	public String saludar() {
		System.out.println("Hola. Soy:"+this);
		return "Hola. Soy:"+this; 
	}
	
}
