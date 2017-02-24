package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Lote;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class LoteDAO extends BaseDAO<Lote> {

	private static final long serialVersionUID = 1L;

	public LoteDAO() {
		super(Lote.class);
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verificarDuplicidade(Lote t) throws ViolacaoDeConstraintException {
		// TODO Auto-generated method stub

	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(Lote.class, getEm(), filtravel).setupPaginador(paginador)
					.filterSimpleBy("lt.numero").orderBy("lt.id").sortedBy(ESortedBy.ASC).build().dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public Lote consultarPorId(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT lt FROM Lote lt Join fetch lt.evento e join fetch lt.ingressoTipo it join fetch lt.loteSituacao ls ");
			sql.append("WHERE lt.id =:_id");

			return getEm().createQuery(sql.toString(), Lote.class).setParameter("_id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

}
