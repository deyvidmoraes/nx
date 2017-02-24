package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.EventoTipoDAO;
import br.com.nx.tickets.entidade.EventoTipo;

@Stateless
public class EventoTipoServico extends BaseServico<EventoTipo> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoTipoDAO eventoTipoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(eventoTipoDao);
	}
}