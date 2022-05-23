package com.curso.mutiny_1;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.smallrye.mutiny.Multi;

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

    	@Inject
    	Unis unis;
    	
    	@Inject
    	Consumidor consumidor;
    	
        @Override
        public int run(String... args) throws Exception {
        	
        	////////////
        	// MULTIS //
        	////////////
        	
        	System.out.println("=================================");
        	List<Integer> listaNumeros = multis.getNumeros();
        	listaNumeros.forEach(n -> System.out.println(n));
        	
        	System.out.println("=================================");
    		//Multi y Uni heredan de Publisher de Java 9
    		
    		//Un multi no se 'recorre' como si fuera una colección
    		//Nos subscribimos a un multi para recibir los elementos
    		//Se proporciona un subscriptor, que recibirá los elementos que entrega el multi
    		//Es tarea del consumidor controlar el ritmo con el que procesan los elementos
    		//Es tarea del flujo controlar el ritmo con el que se entregan los elementos
    		
    		//
    		//Proporcionando un consumidor definido en el momento de la subscripción:
    		//En este caso el mismo hilo que se subscribe ejecuta el código del consumidor
    		//        	
        	Multi<Integer> multiNumeros = multis.getNumerosMulti();

    		System.out.println(Thread.currentThread().getName()+"-Antes de subscribirse");
    		multiNumeros
    			.subscribe().with( new Consumer<Integer>() {
    				public void accept(Integer n) {
    					System.out.println(Thread.currentThread().getName()+"-"+n);
    				}
    			});
    		System.out.println(Thread.currentThread().getName()+"-Despues de subscribirse");
    			
    		System.out.println("=====================================");
    		//Ídem con expresión lambda
    		//Vemos que podemos volver a subscribirnos a un multi
    		multiNumeros.subscribe().with( n -> System.out.println(Thread.currentThread().getName()+"-Lambda-"+n));
    		//AQUI NO TENEMOS LAS PALABRAS
        	
    		System.out.println("=====================================");		
    		//Utilizando un consumidor definido como una bean CDI
    		multiNumeros.subscribe().with(consumidor);        	
        	
    		
    		//////////
    		// UNIS //
    		//////////
    		
    		System.out.println("=====================================");
    		String saludo_1 = unis.saludar();
    		System.out.println(saludo_1);
    		
    		unis
    			.saludarReactivo()
    			.subscribe()
    			.with(saludo -> System.out.println(saludo));
    		//Aqui no tenemos el saludo
    		
    		System.out.println("=====================================");			
    		
    		//Podemos utilizar await en unis y nos devuelven el elemento emitido
    		//Usar solo en caso de extrema necesidad		
    		String saludo_2 = unis.saludarReactivo().await().indefinitely();
    		System.out.println("Despues del wait: "+saludo_2);

    		System.out.println("FIN del hilo main");    		
    		
    		System.exit(42);
        	return 42;
        }
        
    }    
}