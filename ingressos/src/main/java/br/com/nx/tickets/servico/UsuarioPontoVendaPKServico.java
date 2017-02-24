package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioPontoVendaPKDAO;
import br.com.nx.tickets.entidade.UsuarioPontoVendaPK;

@Stateless
public class UsuarioPontoVendaPKServico extends BaseServico<UsuarioPontoVendaPK> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioPontoVendaPKDAO usuarioPontoVendaPKDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioPontoVendaPKDao);
	}
}
