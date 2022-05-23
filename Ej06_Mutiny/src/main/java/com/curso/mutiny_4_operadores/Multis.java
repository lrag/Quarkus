package com.curso.mutiny_4_operadores;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@ApplicationScoped
public class Multis {
	
	public Multi<Integer> numerosPares(){
		return Multi.createFrom().generator(
				//State supplier
				() -> 0,
				//Generator
				(state,emitter) -> {					
					emitter.emit(state);					
					long fin = System.currentTimeMillis()+1000;
					while(System.currentTimeMillis()<fin) {
					}
					state +=2;
					if(state > 12) {
						emitter.complete();
					}					
					return state;
				}				
			);
	}

	public Multi<Integer> numerosImpares(){
		return Multi.createFrom().generator(
				//State supplier
				() -> 1,
				//Generator
				(state,emitter) -> {					
					emitter.emit(state);					
					long fin = System.currentTimeMillis()+1000;
					while(System.currentTimeMillis()<fin) {
					}
					state +=2;
					if(state > 13) {
						emitter.complete();
					}					
					return state;
				}				
			);
	}	
	
	public Multi<String> flujoPalabras() {		
		String[] palabras = {"Dice","que…","la","parte","contratante","de","la","primera","parte","será","considerada","como","la","parte","contratante","de","la","primera","parte."};		
		return Multi.createFrom().generator(
				//State supplier
				() -> 0,
				//Generator
				(state,emitter) -> {					
					emitter.emit(palabras[state]);					
					try {
						Thread.sleep(palabras[state].length()*30);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}					
					state++;
					if(state == palabras.length) {
						emitter.complete();
					}					
					return state;
				}				
			);
	}	
	
	public Uni<String> leerFichero(String fichero){
		return Uni.createFrom().emitter( 
				emitter -> {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					emitter.complete("contenido del fichero");
				}
			);
	}
	
	public Uni<Void> escribirFichero(String fichero, String contenido){
		return Uni.createFrom().emitter(  
				emitter -> {
					System.out.println("Escribiendo el fichero...");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Escribiendo... Contenido:"+contenido+", fichero:"+fichero);
					emitter.complete(null);
				}
			);
	}	
	
	public Uni<String> convertirImagen(String imagen){
		return Uni.createFrom().emitter( 
				emitter -> {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					emitter.complete(imagen.toUpperCase());
				}
			);
	}
		
}
