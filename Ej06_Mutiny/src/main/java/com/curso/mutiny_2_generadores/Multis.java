package com.curso.mutiny_2_generadores;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class Multis {
	
	public Multi<Long> getNumerosAleatoriosFinito(){
		//El emitter recibe una única llamada y se encarga de generar todos los elementos
		return Multi.createFrom().emitter(
				emitter -> {
					int smith = 0;
					while(smith < 10) {
						System.out.println(Thread.currentThread().getName()+"-Generando número aleatorio...");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
						
						emitter.emit(Math.round(Math.random()*10_000));
						smith++;
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

				String mensaje = "Mensaje nº:"+state;
				System.out.println(Thread.currentThread().getName()+"-Generator:"+mensaje);
				emitter.emit(mensaje);
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



