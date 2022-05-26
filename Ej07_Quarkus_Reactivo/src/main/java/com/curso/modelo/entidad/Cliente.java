package com.curso.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.curso.modelo.repositorio.PeliculaRepositorio;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Entity
public class Cliente extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;	
	public String nombre;
	public String direccion;
	public String telefono;
	
	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nombre, String direccion, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
}

