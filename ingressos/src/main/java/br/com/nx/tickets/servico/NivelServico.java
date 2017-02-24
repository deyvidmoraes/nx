package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.NivelDAO;
import br.com.nx.tickets.entidade.Nivel;

@Stateless
public class NivelServico extends BaseServico<Nivel> {

	private static final long serialVersionUID = 1L;
	@Inject
	private NivelDAO nivelDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(nivelDao);
	}
}