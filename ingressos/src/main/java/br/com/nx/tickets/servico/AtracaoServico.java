package br.com.nx.tickets.servico;

import javax.ejb.Stateless;

import br.com.nx.tickets.entidade.Atracao;

@Stateless
public class AtracaoServico extends BaseServico<Atracao> {

	private static final long serialVersionUID = 1L;

	@Override
	public void inicializar() {
	}
}