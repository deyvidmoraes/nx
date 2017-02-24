package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioSituacaoDAO;
import br.com.nx.tickets.entidade.UsuarioSituacao;

@Stateless
public class UsuarioSituacaoServico extends BaseServico<UsuarioSituacao> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioSituacaoDAO usuarioSituacaoDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioSituacaoDao);
	}
}
