package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.LoteDAO;
import br.com.nx.tickets.entidade.Lote;

@Stateless
public class LoteServico extends BaseServico<Lote> {

	private static final long serialVersionUID = 1L;
	@Inject
	private LoteDAO loteDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(loteDao);
	}

	public Lote obterPorId(Integer id) throws BaseServicoException {
		try {
			return loteDao.consultarPorId(id);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

}
