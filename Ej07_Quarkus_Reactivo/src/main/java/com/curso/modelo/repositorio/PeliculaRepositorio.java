package com.curso.modelo.repositorio;

import javax.enterprise.context.ApplicationScoped;

import com.curso.modelo.entidad.Pelicula;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

@ApplicationScoped
public class PeliculaRepositorio implements PanacheRepository<Pelicula>{

}
