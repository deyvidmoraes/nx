package br.com.nx.tickets.rest.retorno;

import java.util.Map;

public interface IngressoRetornavel {
	
	String getMensagem();

	String getCodigoRetorno();

	Map<String, Object> getExtras();
}
