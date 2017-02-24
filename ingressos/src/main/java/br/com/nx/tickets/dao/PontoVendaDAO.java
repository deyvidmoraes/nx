package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class PontoVendaDAO extends BaseDAO<PontoVenda> {

	private static final long serialVersionUID = 1L;

	public PontoVendaDAO() {
		super(PontoVenda.class);
	}

	@Override
	public void verificarDuplicidade(PontoVenda t) throws ViolacaoDeConstraintException {
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(PontoVenda.class, getEm(), filtravel)
									.setupPaginador(paginador)
									.filterSimpleBy("pv.nome")
									.orderBy("pv.nome")
									.sortedBy(ESortedBy.ASC).build()
									.dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	/**
	 * @param idUsuario
	 * @return
	 */
	public PontoVenda consultarPorUsuario(Integer idUsuario) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT pdv FROM UsuarioPontoVenda updv  ");
			sql.append("JOIN updv.usuario us ");
			sql.append("JOIN updv.pontoVenda pdv ");
			sql.append("WHERE us.id =:_id");

			return getEm().createQuery(sql.toString(), PontoVenda.class)
					.setParameter("_id", idUsuario).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}
}