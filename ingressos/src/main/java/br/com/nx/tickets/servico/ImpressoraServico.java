package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.ImpressoraDAO;
import br.com.nx.tickets.entidade.Impressora;

@Stateless
public class ImpressoraServico extends BaseServico<Impressora> {

	private static final long serialVersionUID = 1L;
	@Inject
	private ImpressoraDAO impressoraDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(impressoraDao);
	}
}