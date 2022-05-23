package com.curso.mutiny_1;

import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Consumidor implements Consumer<Integer>{

	@Override
	public void accept(Integer numero) {
		System.out.println("Consumidor bean: "+numero);
	}

}
