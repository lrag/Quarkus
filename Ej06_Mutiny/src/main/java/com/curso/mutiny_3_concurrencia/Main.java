package com.curso.mutiny_3_concurrencia;
import java.util.List;

import javax.inject.Inject;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.subscription.Cancellable;

//@QuarkusMain  
public class Main {
    
    public static void main(String... args) {
    	System.out.println("///////////////////////////////////////////");
    	System.out.println("// INICIANDO LA APLICACIÓN DESDE EL MAIN //");
    	System.out.println("///////////////////////////////////////////");
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {
    
    	@Inject
    	Multis multis;
    	
        @Override
        public int run(String... args) throws Exception {
        	        	
    		/////////////////////////
    		// RUN SUBSCRIPTION ON //
    		/////////////////////////
    		
    		Multi<Long> multiInfinito = multis.getNumerosAleatorioIninito();
    		
    		//Si nos subscribimos a un multi infinito y no tenemos cuidado con cuál hilo
    		//se va a ejecutar el código del consumer y el generador nos quedaremos bloqueados procesando
    		//los elementos del multi para siempre
    		//
    		/*
    		System.out.println("=====================================");
    		System.out.println(Thread.currentThread().getName()+"-Antes de subscribirse");
    		multiInfinito.subscribe().with(numero -> System.out.println(Thread.currentThread().getName()+"-"+numero));
    		System.out.println("Aqui ya no llega :( ");
    		*/
    		
    		/*
    		System.out.println("=====================================");
    		System.out.println(Thread.currentThread().getName()+"-Antes de subscribirse");
    		multiInfinito
    			.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    			.subscribe().with( numero -> System.out.println(Thread.currentThread().getName()+"-"+numero));
    		System.out.println(Thread.currentThread().getName()+"-Despues de subscribirse");
    		
    		//
    		//Sin este thread.sleep la aplicación finaliza. El hilo que se queda procesando los elementos del multi
    		//no tiene peso suficiente para mantenerla viva
    		Thread.sleep(10_000);
    		*/
    		
    		/////////////////
    		// CANCELLABLE //
    		/////////////////    		
    		/*
    		System.out.println("=====================================");
    		Cancellable cancellable = multiInfinito
				.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
				.subscribe().with( numero -> System.out.println(Thread.currentThread().getName()+"-"+numero));
    		
    		Thread.sleep(4_000);
    		System.out.println("Cancelando la subscripción");
    		cancellable.cancel();
    		Thread.sleep(4_000);
    		*/
    		
    		/*
    		System.out.println("=====================================");
    		//El multi entrega un elemento cada segundo, pero el consumidor que proporciona el subscriptor
    		//tarda dos en procesarlo.
    		//Vemos que no se pierde ningún elemento
    		Cancellable cancellable2 = multis
    			.multiInterval()
				//.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    			.subscribe().with( numero -> {
    				System.out.println(Thread.currentThread().getName()+":"+numero);
    				long smith = System.currentTimeMillis()+2000;
    				while(smith>System.currentTimeMillis()) {
    					//Procesando...
    				}
    			});	
    		Thread.sleep(4_000);

    		System.out.println("=====================================");
    		System.out.println("Ficheros (y procesando el flujo anterior en paralelo)");
    		Multi<List<String>> eventosDirectorio = multis.monitorizarDirectorio();
    		Cancellable cancellable3 = eventosDirectorio
    			.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    			.subscribe().with(mensaje -> System.out.println(mensaje));
    		
    		Thread.sleep(30_000);

    		cancellable2.cancel();		
    		cancellable3.cancel();
    		*/
    		
    		/////////////
    		// EMIT ON //
    		/////////////
	
    		System.out.println("=====================================");
    		//En este ejemplo es el flujo el que indica que el consumidor debe ser ejecutado por otro hilo distinto al que se subscribe
    		System.out.println("Antes de subscribirse a multiEmitOn");
    		multis.multiEmitOn()
    			.subscribe().with(s -> System.out.println(Thread.currentThread().getName()+":"+s));
    		System.out.println("Después de subscribirse a multiEmitOn");

    		Thread.sleep(5000);	
    		
    		System.out.println("FIN del hilo main");	
        	        	
    		System.exit(42);
        	return 42;
        }
        
    }    
}