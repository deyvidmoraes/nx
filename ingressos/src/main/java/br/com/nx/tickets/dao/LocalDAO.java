package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Local;
import br.com.nx.tickets.entidade.Portaria;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class LocalDAO extends BaseDAO<Local> {

	private static final long serialVersionUID = 1L;

	public LocalDAO() {
		super(Local.class);
	}

	@Override
	public void verificarDuplicidade(Local t) throws ViolacaoDeConstraintException {
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(Local.class, getEm(), filtravel)
									.setupPaginador(paginador)
									.filterSimpleBy("lc.descricao")
									.orderBy("lc.id")
									.sortedBy(ESortedBy.ASC).build()
									.dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Portaria> consultarPortariasPorLocal(Local local) {
		try {
			return getEm()
					.createQuery("SELECT p FROM Portaria p "
							   + "WHERE p.local =:_local "
							   + "ORDER BY p.id ", Portaria.class)
					.setParameter("_local", local)
					.getResultList();
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}
}