package br.com.nx.tickets.servico;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BaseServicoException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseServicoException(String mensagem) {
		super(mensagem);
	}
}