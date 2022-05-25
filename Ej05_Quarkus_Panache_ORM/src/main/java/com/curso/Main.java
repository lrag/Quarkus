package com.curso;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.repositorio.PeliculaRepositorio;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain  
public class Main {
    
    public static void main(String... args) {
    	System.out.println("///////////////////////////////////////////");
    	System.out.println("// INICIANDO LA APLICACIÓN DESDE EL MAIN //");
    	System.out.println("///////////////////////////////////////////");
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

    	@Inject PeliculaRepositorio peliculaRepo;
    	    	
        @Override
        public int run(String... args) throws Exception {

        	//QuarkusTransaction.begin();
        	
        	System.out.println("ANTES");
       	
        	QuarkusTransaction.run(() -> {
	        	peliculaRepo.deleteAll();	        	
	    		peliculaRepo.persist(new Pelicula(null,"2001","Stanley Kubrik","Ci-fi",2000));
	    		peliculaRepo.persist(new Pelicula(null,"Alien","Ridley Scott","Ci-fi",2000));
	    		peliculaRepo.persist(new Pelicula(null,"Die Hard","John McTiernan","Acción",2000));
	    		peliculaRepo.persist(new Pelicula(null,"Young Frankenstein","Mel Brooks","Comedia",2000));
	    		peliculaRepo.persist(new Pelicula(null,"Los violentos de Kelly","Brian G. Hutton","Bélica",2000));
	    		peliculaRepo.persist(new Pelicula(null,"La diligencia","John Ford","Western",2000));
	    		peliculaRepo.persist(new Pelicula(null,"Depredador","John McTiernan","Acción",2000));
	    		peliculaRepo.persist(new Pelicula(null,"rio Bravo","Howard Hawks","Western",2000));
        	});
        	System.out.println("DESPUES");
        	
        	
        	//QuarkusTransaction.commit();
        	//QuarkusTransaction.rollback();
        	
        	Quarkus.waitForExit();
            return 0;
        }
        
    }    
}
