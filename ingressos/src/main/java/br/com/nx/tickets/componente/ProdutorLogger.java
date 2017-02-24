package br.com.nx.tickets.componente;

import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

import br.com.nx.tickets.util.SistemaConstantes;

@Singleton
public class ProdutorLogger {

	 @Produces
	 @AccessTimeout(value = SistemaConstantes.SESSENTA, unit = TimeUnit.SECONDS)
     public Logger produceLog(InjectionPoint injectionPoint) {
         return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
     }
}
