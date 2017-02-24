package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.EstadoDAO;
import br.com.nx.tickets.entidade.Estado;



@Stateless
public class EstadoServico extends BaseServico<Estado> {

	private static final long serialVersionUID = 1L;
	@Inject
	private EstadoDAO estadoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(estadoDao);
	}

	public Estado obterPorUF(String uf) throws BaseServicoException {
		try {
			return estadoDao.buscarPorUF(uf);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
}