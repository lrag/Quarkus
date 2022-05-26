package com.curso.mutiny_3_concurrencia;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;


public class Main2 {
    
    public static void main(String... args) {
        
        Multis multis = new Multis();
    	
    	Multi<Long> multiInfinito = multis.getNumerosAleatorioIninito();
    	
		System.out.println("=====================================");
		System.out.println(Thread.currentThread().getName()+"-Antes de subscribirse");
		multiInfinito
			.runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
			.subscribe().with( numero -> System.out.println(Thread.currentThread().getName()+"-"+numero));
		System.out.println(Thread.currentThread().getName()+"-Despues de subscribirse");
		

		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

		System.out.println("/////////////////////");
		System.out.println("//FIN DEL HILO MAIN//");
		System.out.println("/////////////////////");
        
    }
   
}