package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.SegmentoDAO;
import br.com.nx.tickets.entidade.Segmento;

@Stateless
public class SegmentoServico extends BaseServico<Segmento> {

	private static final long serialVersionUID = 1L;
	@Inject
	private SegmentoDAO segmentoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(segmentoDao);
	}

	public Segmento verificarDuplicidade(Segmento segmento) {
		try {
			segmentoDao.verificarDuplicidade(segmento);
			return segmento;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Paginador<Paginavel> obterPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel)
			throws BaseServicoException {
		try {
			return segmentoDao.consultarPorFiltro(paginador, filtravel);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	// public List<Segmento> obterPorFiltroHierarquia(FiltravelNovo filtravel)
	// throws BaseServicoException {
	// try {
	// return segmentoDao.consultarPorFiltroHierarquia(filtravel);
	// } catch (BaseDAOException e) {
	// throw new BaseServicoException(e.getMessage());
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// throw new BaseServicoException(ex.getMessage());
	// }
	// }
}