package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.curso.util.Logger;

@ILog
@Interceptor
public class LogInterceptor {

	@Inject
	@Named("logger")
	Logger logger;
	
	@AroundInvoke
	public Object log(InvocationContext iCtx) throws Exception {
		
		Method metodo = iCtx.getMethod();
		Object target = iCtx.getTarget();
		Object[] parametros = iCtx.getParameters();
		
		String entradaLog = LocalDateTime.now()+"-Inicio de la llamada al método "+metodo.getName()+" de "+target.getClass();
		System.out.println(entradaLog);
		logger.escribir(entradaLog);
		
		//Nos encargamos aqui de que el target reciba la llamada 
		Object retorno = iCtx.proceed();
		
		entradaLog = LocalDateTime.now()+"-Fin de la llamada al método "+metodo.getName()+" de "+target.getClass();
		System.out.println(entradaLog);
		logger.escribir(entradaLog);
		
		return retorno;
	}
	
}
