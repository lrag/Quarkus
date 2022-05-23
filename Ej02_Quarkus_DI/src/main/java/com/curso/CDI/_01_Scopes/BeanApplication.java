package com.curso.CDI._01_Scopes;

import javax.enterprise.context.ApplicationScoped;

//Se reutiliza la instancia
//Instanciación perezosa: se inyecta un proxy
//Opción recomendada
@ApplicationScoped
public class BeanApplication {

	public BeanApplication() {
		super();
		System.out.println("Instanciando "+this);
	}

	public String saludar() {
		System.out.println("Hola. Soy:"+this);
		return "Hola. Soy:"+this; 
	}
	
}
