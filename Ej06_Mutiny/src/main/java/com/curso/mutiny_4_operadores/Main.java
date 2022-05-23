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
    		
    		System.out.println("======================================");
    		peliculaRepo
    			.findAllById(1,2,3)
    			.subscribe().with(peliculas -> peliculas.forEach(p -> System.out.println(p)));
    		
    		///////////
    		// MERGE //
    		///////////
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
    			.subscribe(p -> System.out.println(p));	
    		
    	
    		System.exit(0);
    		*/
    		
    		/////////
    		// MAP //
    		/////////
    		/*
    		System.out.println("======================================");
    		flujos
    			.flujoPalabras()
    			.subscribe( palabra -> System.out.println(palabra));
    		
    		System.out.println("======================================");
    		flujos
    			.flujoPalabras()
    			//Llega un string, sale otro
    			.map( p -> p.toUpperCase() )
    			.subscribe(palabra -> System.out.println(palabra));

    		System.out.println("======================================");
    		flujos
    			.flujoPalabras()
    			//Llega la palabra, sale su longitud...
    			.map( p -> p.length() )
    			.subscribe(palabra -> System.out.println(palabra));
    		*/
    		/*
    		System.out.println("======================================");
    		flujos
    			.flujoPalabras() //De aqui salen cadenas de texto
    			.map( palabra -> palabra.toUpperCase() ) //De aqui salen pasadas a mayusculas
    			.map( palabra -> palabra.length()+"-"+palabra) //De aqui salen con longitud por delante
    			.subscribe( palabra -> System.out.println(palabra));		
    		
    		System.out.println("======================================");
    		//Lo mismo pero sin 'map'
    		flujos
    			.flujoPalabras()
    			.subscribe( palabra -> System.out.println(palabra.length()+":"+palabra.toUpperCase()));		
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
    		flujos
    			.leerFichero("imagen.jpg")
    			.subscribeOn(Schedulers.boundedElastic())
    			.subscribe( contenido -> {
    				System.out.println("Contenido:"+contenido);
    				flujos
    					.convertirImagen(contenido)
    					.subscribeOn(Schedulers.boundedElastic())
    					.subscribe(nuevoFormato -> {
    						System.out.println("Nuevo formato:"+nuevoFormato);
    						flujos.escribirFichero(nuevoFormato, contenido)
    							.subscribeOn(Schedulers.boundedElastic())
    							.subscribe( c -> {
    								System.out.println("Nueva imagen creada.");
    							});
    					});
    			});	
    		Thread.sleep(10_000);
    		 */
    		
    		/*
    		flujos
    			.leerFichero("imagen.jgp") //De aqui sale un mono<string>
    			.flatMap( contenido -> {
    				System.out.println("Contenido:"+contenido);
    				return flujos.convertirImagen(contenido); //Esto devuelve un Mono<String> 
    			})
    			.flatMap( nuevoFormato -> {
    				System.out.println("Nuevo formato:"+nuevoFormato);
    				return flujos.escribirFichero("imagenConvertida.jpg", nuevoFormato); //De aqui sale otro Mono!
    			})
    			.doOnSuccess( x -> System.out.println("IMAGEN CONVERTIDA") ) //Como el ultimo mono es Mono<Void> 'x' es null
    			.subscribe();	
    		*/

    		//Lo mismo pero con un hilo para cada tarea que implique I/O 
    		/*
    		flujos
    			.leerFichero("imagen.jgp") //De aqui sale un mono
    			.subscribeOn(Schedulers.boundedElastic())
    			.flatMap( contenido -> {
    				System.out.println(Thread.currentThread().getName()+"-Contenido:"+contenido);
    				return flujos
    					.convertirImagen(contenido)
    					.subscribeOn(Schedulers.boundedElastic()); //Esto devuelve un Mono<String> 
    			})
    			.flatMap( nuevoFormato -> {
    				System.out.println(Thread.currentThread().getName()+"-Nuevo formato:"+nuevoFormato);
    				return flujos
    					.escribirFichero("", "")
    					.subscribeOn(Schedulers.boundedElastic());
    			})
    			.doOnSuccess( x-> System.out.println(Thread.currentThread().getName()+"-IMAGEN CONVERTIDA") )
    			.subscribe();
    		
    		Thread.sleep(10_000);
    		*/
    		
    		///////////////////
    		// FLAT MAP MANY //
    		///////////////////	
        	/*
    		System.out.println("======================================");		
    		
    		Integer idPelicula = 3;
    		peliculaRepo
    			.findById(idPelicula) //De aqui sale un Mono en patines
    			.flatMap( p -> { //Aqui llega la pelicula
    				Mono<List<Premio>> monoPremios = premioRepo.findAllByIdPelicula(p.getId()).collectList();
    				Mono<Pelicula> peliculaMono = Mono.just(p);
    				return Mono.zip(peliculaMono, monoPremios);
    				//return Mono.just(p).zipWith(premioRepo.findAllByIdPelicula(p.getId()).collectList()); 
    			})
    			.map( tupla -> {
    				Pelicula p = tupla.getT1();
    				p.setPremios(tupla.getT2());
    				return p;
    			})
    			.subscribe(pelicula -> System.out.println(pelicula));			
        	*/
    		
        	/////////////
    		// COLLECT //
    		/////////////
    		/*
    		System.out.println("======================================");		
    		flujos
    			.flujoPalabras()
    			.collect(Collectors.toList())
    			.subscribe(lista -> System.out.println(lista));
    		*/
    		/*
    		peliculaRepo
    			.findAll()
    			.collect(Collectors.groupingBy( pelicula -> pelicula.getGenero()))
    			.subscribe( mapa -> {
    				mapa.forEach( (clave,valor) -> System.out.println(clave+":"+valor) );
    			});*/
    		
    		/*
    		.defaultIfEmpty(valor por defecto);
    		.switchIfEmpty(Mono.error(new Exception("EL AÑO NO PUEDE SER NEGATIVO!")));
    		*/    		
    		
    		System.out.println("FIN del hilo main");	
        	        	
    		System.exit(42);
        	return 42;
        }
        
    }    
}