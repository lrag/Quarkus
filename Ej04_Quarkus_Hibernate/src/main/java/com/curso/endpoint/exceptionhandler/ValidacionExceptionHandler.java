package com.curso.endpoint.exceptionhandler;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.curso.endpoint.dto.ErrorEndpoint;

@Provider
public class ValidacionExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Map<String, String> errores = exception
			.getConstraintViolations()
			.stream()
			.collect(Collectors.toMap(cvex -> cvex.getPropertyPath().toString(), 
									  cvex -> cvex.getMessage()));
		ErrorEndpoint error = new ErrorEndpoint("400","Datos invalidos", errores);
		return Response
			.status(400)
			.entity(error)
			.build();
	}

}
