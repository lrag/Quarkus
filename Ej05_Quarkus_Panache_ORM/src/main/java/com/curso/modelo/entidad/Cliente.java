package com.curso.modelo.entidad;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
//Patrón Active Record
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
	
	public Cliente buscarPorNombre(String nombre) {
		return (Cliente) getEntityManager()
				.createQuery("select c from Cliente c where c.nombre=:nombre")
				.setParameter("nombre", nombre)
				.getResultList()
				.get(0); //Esto es cutre :)
	}
	
}

