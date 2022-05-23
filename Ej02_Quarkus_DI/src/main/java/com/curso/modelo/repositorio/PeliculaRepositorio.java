package com.curso.modelo.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.curso.modelo.entidad.Pelicula;

@ApplicationScoped
public class PeliculaRepositorio {

	private static List<Pelicula> peliculas;
	
	static {
		peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1,"2001","Stanley Kubrik","Ci-Fi",1968));
		peliculas.add(new Pelicula(2,"Alien","Ridley Scott","Ci-Fi",1979));
		peliculas.add(new Pelicula(3,"Die Hard","John McTiernan","Accion",1988));
		peliculas.add(new Pelicula(4,"Young Frankenstein","Mel Brooks","Comedia",1974));
		peliculas.add(new Pelicula(5,"Moon","Duncan Jones","Ci-Fi",2009));
		peliculas.add(new Pelicula(6,"El bueno, el feo y el malo","Sergio Leone","Western",1968));		
	}	
	
	public List<Pelicula> listar(){
		return peliculas;
	}
	
	public Optional<Pelicula> buscar(Integer idPelicula) {
		return peliculas
			.stream()
			.filter(p -> p.getId().equals(idPelicula))
			.findFirst();
	}
	
	public Pelicula insertar(Pelicula pelicula) {
		System.out.println(pelicula);
		pelicula.setId((int) System.currentTimeMillis());
		peliculas.add(pelicula);
		return pelicula;
	}

	public Pelicula modificar(Pelicula pelicula) {
		for(int a=0; a<peliculas.size(); a++) {
			if(peliculas.get(a).getId().equals(pelicula.getId())) {
				peliculas.set(a, pelicula);
			}
		}
		return pelicula;
	}

	public boolean borrar(Integer idPelicula) {
		return peliculas.removeIf(p -> p.getId().equals(idPelicula));
	}		
	
}
