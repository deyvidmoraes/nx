package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.PontoVendaDAO;
import br.com.nx.tickets.entidade.PontoVenda;

@Stateless
public class PontoVendaServico extends BaseServico<PontoVenda> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PontoVendaDAO pontoVendaDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(pontoVendaDao);
	}
	
	public PontoVenda obterPorUsuario(Integer idUsuario) throws BaseServicoException {
		try {
			return pontoVendaDao.consultarPorUsuario(idUsuario);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}
}