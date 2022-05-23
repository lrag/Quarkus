package com.curso.mutiny_2_generadores;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class Multis {
	
	public Multi<Long> getNumerosAleatorioFinito(){
		AtomicInteger contador = new AtomicInteger(0);		
		//El emitter recibe una única llamada y se encarga de generar todos los elementos
		return Multi.createFrom().emitter(
				emitter -> {					
					while(contador.incrementAndGet() < 10) {
						System.out.println(Thread.currentThread().getName()+"-Generando número aleatorio...");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
						
						emitter.emit(Math.round(Math.random()*10_000));
					}
					System.out.println(Thread.currentThread().getName()+"-Complete");
					emitter.complete();
				}
			);
	}
	
	public Multi<String> multiConEstado(){
		return Multi.createFrom().generator(
			//State supplier
			() -> 1,
			//Generator
			(state, emitter) -> {

				emitter.emit("Mensaje nº:"+state);
				state++;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(state == 11) {
					emitter.complete();
				}				
				return state;
			}
		);		
	}	
	
}
