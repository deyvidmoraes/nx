package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioHistoricoPKDAO;
import br.com.nx.tickets.entidade.UsuarioHistoricoPK;

@Stateless
public class UsuarioHistoricoPKServico extends BaseServico<UsuarioHistoricoPK> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioHistoricoPKDAO usuarioHistoricoPKDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioHistoricoPKDao);
	}
}
