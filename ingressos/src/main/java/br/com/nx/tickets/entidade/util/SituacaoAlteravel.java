package br.com.nx.tickets.entidade.util;

import java.io.Serializable;

public interface SituacaoAlteravel extends Serializable {

	void alterarSituacao();
	
	ESituacao getSituacao();
}