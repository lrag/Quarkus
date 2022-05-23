package com.curso.mutiny_2_generadores;
import java.util.List;

import javax.inject.Inject;

import com.curso.modelo.entidad.Pelicula;

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
    	PeliculaRepositorio peliculaRepo;

        @Override
        public int run(String... args) throws Exception {
        	
        	/*
    		System.out.println("=====================================");
    		Multi<Long> multiFinito = multis.getNumerosAleatorioFinito();
    		multiFinito.subscribe().with(numero -> {
    			System.out.println(Thread.currentThread().getName()+"-Consumidor:"+numero);
    		});
    		
    		System.out.println("=====================================");
    		Multi<String> multiEstado = multis.multiConEstado();
    		multiEstado.subscribe().with(mensaje -> System.out.println(mensaje));
    		*/    		 
    		 
    		System.out.println("=============================================");
    		List<Pelicula> peliculas = peliculaRepo.findAll();
    		for(Pelicula p: peliculas) {
    			System.out.println(p);
    		}
    		
    		Thread.sleep(1000);
    		System.out.println("FIN");

    		System.out.println("=============================================");
    		peliculaRepo.findAll_Reactivo().subscribe().with(p -> System.out.println(p));	
    		
    		System.out.println("FIN del hilo main");
        	        	
    		System.exit(42);
        	return 42;
        }
        
    }    
}