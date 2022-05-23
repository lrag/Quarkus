package com.curso.CDI._01_Scopes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;

//Se reutiliza la instancia
//Se instancia al arrancar
//No se inyecta proxy
@Singleton
public class BeanSingleton {
	
	public BeanSingleton() {
		super();
		System.out.println("Instanciando "+this);
	}

	public String saludar() {
		System.out.println("Hola. Soy:"+this);
		return "Hola. Soy:"+this; 
	}	
	
}
