package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Ingresso;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class IngressoDAO extends BaseDAO<Ingresso> {

	private static final long serialVersionUID = 1L;

	public IngressoDAO() {
		super(Ingresso.class);
	}

	@Override
	public void verificarDuplicidade(Ingresso t) throws ViolacaoDeConstraintException {
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(Ingresso.class, getEm(), filtravel)
									.setupPaginador(paginador)
									.filterSimpleBy("i.id")
									.orderBy("i.id")
									.sortedBy(ESortedBy.ASC).build()
									.dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Ingresso> consultarPorIdPedido(Long idPedido) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ing FROM Ingresso ing  ");
			sql.append("JOIN FETCH ing.pedido p ");
			sql.append("JOIN FETCH ing.lote lt ");
			sql.append("JOIN FETCH lt.ingressoTipo it ");
			sql.append("JOIN FETCH ing.ingressoSituacao st ");
			sql.append("WHERE ing.pedido.id =:_id ");
			sql.append("ORDER BY ing.lote.id ");

			return getEm().createQuery(sql.toString(), Ingresso.class)
					.setParameter("_id", idPedido).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}
}