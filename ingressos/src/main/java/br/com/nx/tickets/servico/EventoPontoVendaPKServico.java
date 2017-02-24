package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.EventoPontoVendaPKDAO;
import br.com.nx.tickets.entidade.EventoPontoVendaPK;

@Stateless
public class EventoPontoVendaPKServico extends BaseServico<EventoPontoVendaPK> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoPontoVendaPKDAO eventoPontoVendaPKDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(eventoPontoVendaPKDao);
	}
}