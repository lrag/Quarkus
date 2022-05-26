package com.curso;
import java.net.URI;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.curso.restproxy.ClientesRestProxy;

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
    
    	@RestClient
    	ClientesRestProxy clientesRestProxy;
    	
        @Override
        public int run(String... args) throws Exception {
        	
        	System.out.println();

        	System.out.println("=================================");       	
            RestClientBuilder
            	.newBuilder()
            	.baseUri(new URI("http://localhost:8081"))
            	.build(ClientesRestProxy.class)
            	.listar()
            	.subscribe()
        		.with(
        			c -> {        		
	        			System.out.println(c);
	        			
	        		}); 
            
            Thread.sleep(1000);

            	
            System.out.println("=================================");       	
        	clientesRestProxy
        		.listar()
        		.subscribe()
        		.with(
        			c -> {        		
	        			System.out.println(c);
	        		}  				
   				);
        	
        	Thread.sleep(10000);
        	System.exit(0);;
        	
        	Quarkus.waitForExit();
        	return 0;
        }
        
    }    
}