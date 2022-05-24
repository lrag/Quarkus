package com.curso;
import javax.inject.Inject;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Empleado;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.GestorEmpleados;

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

    	@Inject GestorClientes gestorClientes;
    	@Inject GestorEmpleados gestorEmpleados;	
    	    	
        @Override
        public int run(String... args) throws Exception {

        	Cliente cliente = new Cliente("Bartolo");
    		gestorClientes.insertar(cliente);
    		gestorClientes.borrar(cliente);
    		
    		Empleado empleado = new Empleado("Bartola");
    		gestorEmpleados.insertar(empleado);
    		gestorEmpleados.borrar(empleado);               
            
            Quarkus.waitForExit();
            return 0;
        }        
    }    
}