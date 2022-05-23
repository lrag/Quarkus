package com.curso.endpoint.dto;

public class ErrorEndpoint {

	private String codigo;
	private String mensaje;
	private Object data;

	public ErrorEndpoint() {
		super();
	}

	public ErrorEndpoint(String codigo, String mensaje, Object data) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.data = data;
	}

	public ErrorEndpoint(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getData() {
		return data;
	}

	public void Object(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "EndpointError [codigo=" + codigo + ", mensaje=" + mensaje + ", data=" + data + "]";
	}

}
