package com.curso.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class LoggerFactory {

	//@Inject
	@ConfigProperty(name="ficheroLog")
	String ficheroLog;	

	@ConfigProperty(name="ficheroLogCronometro")
	String ficheroLogCronometro;	
	
	@Produces
	@Named("logger")
	@ApplicationScoped
	Logger logger() {
		Logger logger = new Logger(ficheroLog);
		return logger;
	}

	@Produces
	@Named("loggerCronometro")
	@ApplicationScoped
	Logger logCronometro() {
		Logger logger = new Logger(ficheroLogCronometro);
		return logger;
	}
	
}
