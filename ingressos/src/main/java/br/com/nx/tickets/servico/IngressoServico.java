package br.com.nx.tickets.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.IngressoDAO;
import br.com.nx.tickets.entidade.Ingresso;

@Stateless
public class IngressoServico extends BaseServico<Ingresso> {

	private static final long serialVersionUID = 1L;
	@Inject
	private IngressoDAO ingressoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(ingressoDao);
	}

	/**
	 * @param i
	 * @return
	 * @throws BaseServicoException 
	 */
	public List<Ingresso> obterPorIdPedido(Long id) throws BaseServicoException {
		try {
			return ingressoDao.consultarPorIdPedido(id);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}
}