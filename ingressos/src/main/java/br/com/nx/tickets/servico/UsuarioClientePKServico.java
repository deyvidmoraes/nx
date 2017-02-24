package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.UsuarioClientePKDAO;
import br.com.nx.tickets.entidade.UsuarioClientePK;

@Stateless
public class UsuarioClientePKServico extends BaseServico<UsuarioClientePK> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioClientePKDAO usuarioClientePKDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioClientePKDao);
	}
}
