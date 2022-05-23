package com.curso;
import javax.inject.Inject;

import com.curso.modelo.repositorio.PeliculaRepositorio;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

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
    	PeliculaRepositorio peliculaRepo;
    	
        @Override
        public int run(String... args) throws Exception {
        	return 0;
        }
        
    }    
}