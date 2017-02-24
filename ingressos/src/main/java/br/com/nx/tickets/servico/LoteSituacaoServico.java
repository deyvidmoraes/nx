package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.LoteSituacaoDAO;
import br.com.nx.tickets.entidade.LoteSituacao;

@Stateless
public class LoteSituacaoServico extends BaseServico<LoteSituacao> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoteSituacaoDAO loteSituacaoDAO;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(loteSituacaoDAO);
	}

}
