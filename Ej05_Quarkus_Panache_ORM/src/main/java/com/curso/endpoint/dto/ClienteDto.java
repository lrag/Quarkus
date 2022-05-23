package com.curso.endpoint.dto;

import javax.validation.constraints.NotNull;

import com.curso.modelo.entidad.Cliente;

public class ClienteDto {

	public Integer id;
	@NotNull
	public String nombre;
	@NotNull
	public String direccion;
	@NotNull
	public String telefono;

	public ClienteDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDto(Integer id, @NotNull String nombre, @NotNull String direccion, @NotNull String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	public ClienteDto(Cliente cliente) {
		super();
		this.id = cliente.id;
		this.nombre = cliente.nombre;
		this.direccion = cliente.direccion;
		this.telefono = cliente.telefono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "ClienteDto [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ "]";
	}
	
	public Cliente asCliente() {
		return new Cliente(id, nombre, direccion, telefono); 
	}

}
