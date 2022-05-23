package com.curso.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Logger {

	private BufferedWriter bw;
	
	public Logger(String nombreFichero) {
		super();
		System.out.println("Instanciando Logger");
		try {
			System.out.println("NombreFichero:"+nombreFichero);
			FileWriter fw = new FileWriter(nombreFichero, StandardCharsets.UTF_8);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@PostConstruct
	public void postConstruct() throws Exception {
		System.out.println("PostConstruct de Logger");
	}	
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("PreDestroy de Logger");
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public synchronized void escribir(String texto) {
		try {
			bw.write(texto+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
