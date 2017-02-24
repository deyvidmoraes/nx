package br.com.nx.tickets.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.componente.Credencial;
import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.UsuarioDAO;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.Usuario;

@Stateless
public class UsuarioServico extends BaseServico<Usuario> {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioDAO usuarioDao;
	
	@Override
	@PostConstruct
	public void inicializar() {
		setDao(usuarioDao);
	}
	
	public Usuario logar(Credencial credencial) throws BaseServicoException {
		try {
			return usuarioDao.logar(credencial);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
	
	public void atualizarPontosVendas(Usuario usuario, List<PontoVenda> pontosVendas) throws BaseServicoException {
		try {
			usuarioDao.atualizarPontosVendas(usuario, pontosVendas);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
}
