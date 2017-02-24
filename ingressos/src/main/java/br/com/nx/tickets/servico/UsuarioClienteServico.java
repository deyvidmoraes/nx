package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioClienteDAO;
import br.com.nx.tickets.entidade.UsuarioCliente;

@Stateless
public class UsuarioClienteServico extends BaseServico<UsuarioCliente> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioClienteDAO usuarioClienteDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioClienteDao);
	}
}
