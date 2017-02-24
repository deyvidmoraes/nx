package br.com.nx.tickets.dao;

public class ViolacaoDeConstraintException extends Exception {

	private static final long serialVersionUID = 1L;

	public ViolacaoDeConstraintException(String mensagem) {
		super(mensagem);
	}
}
