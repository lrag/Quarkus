package com.curso.modelo.negocio.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.curso.util.Logger;

@ICronometro
@Interceptor
//ApplicationScoped
public class CronometroInterceptor {

	@Inject
	@Named("loggerCronometro")
	Logger logger;
	
	@AroundInvoke
	public Object cronometrar(InvocationContext iCtx) throws Exception {
		
		Method metodo = iCtx.getMethod();
		Object target = iCtx.getTarget();
		Object[] parametros = iCtx.getParameters();
		
		long inicio = System.currentTimeMillis();
		
		Object retorno = iCtx.proceed();
		
		long fin = System.currentTimeMillis();
		
		String entradaLog = "Llamada al método "+metodo.getName()+" de "+target.getClass()+" procesada en "+(fin-inicio)+" milisegundos.";
		System.out.println(entradaLog);
		logger.escribir(entradaLog);
		
		return retorno;
	}
	
}
