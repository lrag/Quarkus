package com.curso.CDI._01_Scopes;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;

@Dependent
public class BeanDependent {
	
	public BeanDependent() {
		super();
		System.out.println("Instanciando "+this);
	}

	public String saludar() {
		System.out.println("Hola. Soy:"+this);
		return "Hola. Soy:"+this; 
	}	
	
}
