package com.curso.mutiny_1;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class Multis {

	public List<Integer> getNumeros(){
		return Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	}
	
	public Multi<Integer> getNumerosMulti(){
		return Multi.createFrom().items(1,2,3,4,5,6,7,8,9,10);
	}
	
}
