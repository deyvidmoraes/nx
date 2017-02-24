package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioTelefoneDAO;
import br.com.nx.tickets.entidade.UsuarioTelefone;

@Stateless
public class UsuarioTelefoneServico extends BaseServico<UsuarioTelefone> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioTelefoneDAO usuarioTelefoneDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioTelefoneDao);
	}
}
