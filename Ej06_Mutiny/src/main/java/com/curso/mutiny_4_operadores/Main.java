package com.curso.mutiny_4_operadores;
import java.util.Arrays;

import javax.inject.Inject;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

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
    	PeliculaRepository peliculaRepo;
    	
        @Override
        public int run(String... args) throws Exception {

    		////////////
    		// CONCAT //
    		////////////
    		/*
        	System.out.println("======================================");
    		Multi
    			.createBy()
    			.concatenating()
    			.streams(multis.numerosPares(), multis.numerosImpares())
    			.subscribe()
    			.with(n -> System.out.println(Thread.currentThread().getName()+":"+n));
    		*/
        	
        	/*
    		System.out.println("======================================");
    		//Tambien podemos concatenar varios monos para obtener un flujo
    		Uni.join()
    			.all(
    				peliculaRepo.findById(1), 
    				peliculaRepo.findById(2), 
    				peliculaRepo.findById(3)
    			)
    			.andCollectFailures()
    			.subscribe().with(peliculas -> peliculas.forEach(p -> System.out.println(p)));
    		*/
    		
        	/*
    		System.out.println("======================================");
    		peliculaRepo
    			.findAllById(1,2,3)
    			.subscribe().with(peliculas -> peliculas.forEach(p -> System.out.println(p)));
    		*/
        	
    		///////////
    		// MERGE //
    		///////////
        	/*
    		System.out.println("======================================");
    		Multi
				.createBy()
				.merging()
				.streams(
					multis.numerosPares().runSubscriptionOn(Infrastructure.getDefaultWorkerPool()), 
					multis.numerosImpares().runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
				)
				.subscribe()
				.with(n -> System.out.println(Thread.currentThread().getName()+":::"+n));

    		Thread.sleep(10_000);

    		System.exit(0);
    		*/
        	
    		/////////
    		// ZIP //
    		/////////
    		/*
    		System.out.println("======================================");		
    		Flux.zip(
    				flujos.numerosPares().subscribeOn(Schedulers.boundedElastic()).collect(Collectors.toList()), 
    				flujos.numerosImpares().subscribeOn(Schedulers.boundedElastic()).collect(Collectors.toList())
    			)
    			.subscribe( tupla -> {
    				System.out.println(tupla.getT1());
    				System.out.println(tupla.getT2());
    			});		

    		Thread.sleep(15_000);
    		System.exit(0);
    		*/
    		
    		////////////
    		// FILTER //
    		////////////

        	/*
    		System.out.println("======================================");		
    		peliculaRepo
    			.findAll()
    			.filter(p -> p.getGenero().equals("Ci-fi"))
    			//Se pueden concatenar más filtros
    			.filter(p -> p.getTitulo().length()>4)
    			.subscribe().with(p -> System.out.println(p));	
    		
    		/////////
    		// MAP //
    		/////////
    		
    		System.out.println("======================================");
    		multis
    			.flujoPalabras()
    			.subscribe().with(palabra -> System.out.println(palabra));
    		
    		
    		
    		System.out.println("======================================");
    		multis
    			.flujoPalabras()
    			//Llega un string, sale otro
    			//.onItem().transform(p -> p.toUpperCase())
    			.map( p -> p.toUpperCase() )
    			.subscribe().with(palabra -> System.out.println(palabra));


    		System.out.println("======================================");
    		multis
    			.flujoPalabras()
    			//Llega la palabra, sale su longitud...
    			.map( p -> p.length() )
    			.subscribe().with(longitud -> System.out.println(longitud));


    		System.out.println("======================================");
    		multis
    			.flujoPalabras() //De aqui salen cadenas de texto
    			.map( palabra -> palabra.toUpperCase() ) //De aqui salen pasadas a mayusculas
    			.map( palabra -> palabra.length()+"-"+palabra) //De aqui salen con longitud por delante
    			.subscribe().with( palabra -> System.out.println(palabra));		
    		
    		System.out.println("======================================");
    		//Lo mismo pero sin 'map'
    		multis
    			.flujoPalabras()
    			.subscribe().with( palabra -> System.out.println(palabra.length()+":"+palabra.toUpperCase()));		
			*/

    		//////////////
    		// FLAT MAP //
    		//////////////
    		
    		//System.out.println("======================================");
    		//leer un fichero imagen
    		//transformar el contendio en otro formato
    		//crear un nuevo fichero con la imagen resultante
    		
    		//Con callback hell el código es un infierno
    		//Además, como nos subscribimos a los flujos/monos no podemos devolvelos
    		//Y tampoco podemos devolver el resultado si el código está en un método
    		//Y para más INRI no podremos avisar de que ha habido un fallo
    		
        	/*
    		multis
    			.leerFichero("imagen.jpg")
    			.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    			.subscribe().with( contenido -> {
    				System.out.println();
    				System.out.println("Contenido:"+contenido);
    				multis
    					.convertirImagen(contenido)
    					.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    					.subscribe().with(nuevoFormato -> {
    						System.out.println("Nuevo formato:"+nuevoFormato);
    						multis.escribirFichero("imagen2.png", nuevoFormato)
    							.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    							.subscribe().with( c -> {
    								System.out.println("Nueva imagen creada.");
    							});
    					});
    			});	
    		
    		
    		Thread.sleep(10_000);
    		*/
    		
        	/*
    		multis
    			.leerFichero("imagen.jgp") //De aqui sale un Uni<string>
    			.flatMap( contenido -> {
    				System.out.println("Contenido:"+contenido);
    				return multis.convertirImagen(contenido); //Esto devuelve un Uni<String> 
    			})
    			.flatMap( nuevoFormato -> {
    				System.out.println("Nuevo formato:"+nuevoFormato);
    				return multis.escribirFichero("imagenConvertida.jpg", nuevoFormato); //De aqui sale otro Uni!
    			})
    			.subscribe().with(x -> System.out.println("IMAGEN CONVERTIDA"));	
			*/
			
    		//Lo mismo pero con un hilo para cada tarea que implique I/O 
    		System.out.println();
        	multis
    			.leerFichero("imagen.jgp") //De aqui sale un mono
    			.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
    			.flatMap( contenido -> {
    				System.out.println(Thread.currentThread().getName()+"-Contenido:"+contenido);
    				return multis.convertirImagen(contenido)
    						.runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    			})
    			.flatMap( nuevoFormato -> {
    				System.out.println(Thread.currentThread().getName()+"-Nuevo formato:"+nuevoFormato);
    				return multis
    					.escribirFichero("", "")
    					.runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    			})
    			.subscribe().with( x-> System.out.println(Thread.currentThread().getName()+"-IMAGEN CONVERTIDA"));
    		
    		Thread.sleep(10_000);
    		
    		
        	/////////////
    		// COLLECT //
    		/////////////
    		
    		System.out.println("======================================");		
    		multis
    			.flujoPalabras()
    			.collect().asList()
    			.subscribe().with(lista -> System.out.println(lista));
    		 		
    		
    		System.out.println("FIN del hilo main");	
        	        	
    		System.exit(42);
        	return 42;
        }
        
    }    
}