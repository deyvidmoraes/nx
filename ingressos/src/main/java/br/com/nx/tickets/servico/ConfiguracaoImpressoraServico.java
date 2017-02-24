package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.ConfiguracaoImpressoraDAO;
import br.com.nx.tickets.entidade.ConfiguracaoImpressora;

@Stateless
public class ConfiguracaoImpressoraServico extends BaseServico<ConfiguracaoImpressora> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ConfiguracaoImpressoraDAO configuracaoImpressoraDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(configuracaoImpressoraDao);
	}
}