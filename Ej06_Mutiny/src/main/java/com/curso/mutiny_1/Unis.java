package com.curso.mutiny_1;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class Unis {

	public String saludar(){
		return "Hola!";
	}
	
	public Uni<String> saludarReactivo(){
		return Uni.createFrom().item("Hola uni!");
	}
	
}
