package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.PortariaDAO;
import br.com.nx.tickets.entidade.Portaria;

@Stateless
public class PortariaServico extends BaseServico<Portaria> {

	private static final long serialVersionUID = 1L;
	@Inject
	
	private PortariaDAO portariaDao;


	@Override
	@PostConstruct
	public void inicializar() {
		setDao(portariaDao);
	}
}