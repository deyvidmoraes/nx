package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioHistoricoDAO;
import br.com.nx.tickets.entidade.UsuarioHistorico;

@Stateless
public class UsuarioHistoricoServico extends BaseServico<UsuarioHistorico> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioHistoricoDAO usuarioHistoricoDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioHistoricoDao);
	}
}
