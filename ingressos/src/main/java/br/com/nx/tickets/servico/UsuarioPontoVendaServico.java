package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioPontoVendaDAO;
import br.com.nx.tickets.entidade.UsuarioPontoVenda;

@Stateless
public class UsuarioPontoVendaServico extends BaseServico<UsuarioPontoVenda> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioPontoVendaDAO usuarioPontoVendaDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioPontoVendaDao);
	}
}
