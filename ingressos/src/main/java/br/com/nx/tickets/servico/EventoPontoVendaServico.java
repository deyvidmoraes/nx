package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.EventoPontoVendaDAO;
import br.com.nx.tickets.entidade.EventoPontoVenda;

@Stateless
public class EventoPontoVendaServico extends BaseServico<EventoPontoVenda> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoPontoVendaDAO eventoPontoVendaDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(eventoPontoVendaDao);
	}
}