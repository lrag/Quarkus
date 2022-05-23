package com.curso.mutiny_3_concurrencia;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@ApplicationScoped
public class Multis {
	
	//Un flujo que emite un elemento cada cierto tiempo
	public Multi<Long> multiInterval(){
		return Multi.createFrom().ticks().every(Duration.ofSeconds(1));
	}	
	
	public Multi<Long> getNumerosAleatorioIninito(){		
		//El emitter recibe una única llamada y se encarga de generar todos los elementos
		return Multi.createFrom().emitter(
				emitter -> {					
					while(true) {
						System.out.println(Thread.currentThread().getName()+"-Generando número aleatorio...");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}							
						emitter.emit(Math.round(Math.random()*10_000));
					}
				}
			);
	}	
	
	public Multi<List<String>> monitorizarDirectorio() throws IOException{				
		return Multi.createFrom().emitter(
			emitter -> {				
				while(true) {
					try {
						WatchKey key = null;
						final Path path = Paths.get("directorio_monitorizado");
						final WatchService watchService = FileSystems.getDefault().newWatchService();
						path.register(
								watchService, 
								StandardWatchEventKinds.ENTRY_CREATE, 
								StandardWatchEventKinds.ENTRY_DELETE, 
								StandardWatchEventKinds.ENTRY_MODIFY
							);		
	
						System.out.println("Esperando a una accion en el directorio para publicar el siguiente mensaje...");
						key = watchService.take();
						List<String> mensaje = new ArrayList<>();
						//el método 'pollEvents' bloquea al hijo que lo invoca hasta que haya un nuevo evento
						for (WatchEvent<?> event : key.pollEvents()) {
							mensaje.add(event.kind()+":"+event.context());
						}
						emitter.emit(mensaje);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			} 
		);
	}

	/*
	public Flux<String> flujoPalabras() {		
		String[] palabras = {"UNO","DOS","TRES","CUATRO","CINCO","SEIS","SIETE","OCHO","NUEVE","DIEZ","ONCE","DOCE","TRECE","CATORCE"};		
		return Flux.generate(
				//State supplier
				() -> 0,
				//Generator
				(state,sink) -> {					
					sink.next(palabras[state]);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
					
					state++;
					if(state == palabras.length) {
						sink.complete();
					}					
					return state;
				}				
			);
	}	
	*/
	
	//Multi que indica que el consumidor procesará los elementos utilizando otro hilo
	public Multi<String> multiEmitOn() {
		return Multi.createFrom().items("siete","caballos","vienen","de","bonanza")
			.emitOn(Infrastructure.getDefaultWorkerPool());
	}
	
	
}
