package com.curso.CDI._01_Scopes;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BeanRequest {
	
	public BeanRequest() {
		super();
		System.out.println("Instanciando "+this);
	}

	public String saludar() {
		System.out.println("Hola. Soy:"+this);
		return "Hola. Soy:"+this; 
	}	
	
}
