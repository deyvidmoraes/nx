package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.IngressoSituacaoDAO;
import br.com.nx.tickets.entidade.IngressoSituacao;

@Stateless
public class IngressoSituacaoServico extends BaseServico<IngressoSituacao> {

	private static final long serialVersionUID = 1L;
	@Inject
	private IngressoSituacaoDAO ingressoSituacaoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(ingressoSituacaoDao);
	}
}