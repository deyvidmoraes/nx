package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.EventoSituacaoDAO;
import br.com.nx.tickets.entidade.EventoSituacao;

@Stateless
public class EventoSituacaoServico extends BaseServico<EventoSituacao> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoSituacaoDAO eventoSituacaoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(eventoSituacaoDao);
	}
}