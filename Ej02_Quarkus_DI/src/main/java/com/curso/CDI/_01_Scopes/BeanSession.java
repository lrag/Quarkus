package com.curso.CDI._01_Scopes;

import javax.enterprise.context.SessionScoped;

//Este ámbito sólo está presente si contamos con la extensión 'undertow'
@SessionScoped
public class BeanSession {
	
	public BeanSession() {
		super();
		System.out.println("Instanciando "+this);
	}

	public String saludar() {
		System.out.println("Hola. Soy:"+this);
		return "Hola. Soy:"+this; 
	}	
	
}
