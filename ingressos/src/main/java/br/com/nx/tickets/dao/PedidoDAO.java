package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.FiltroUsuarioPedido;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Pedido;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class PedidoDAO extends BaseDAO<Pedido> {

	private static final long serialVersionUID = 1L;

	public PedidoDAO() {
		super(Pedido.class);
	}

	@Override
	public void verificarDuplicidade(Pedido t) throws ViolacaoDeConstraintException {
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			FiltroUsuarioPedido filtroPedido = (FiltroUsuarioPedido) filtravel;
			getLog().info("EVENTO SELECIONADO: " + filtroPedido.getIdEventoSelecionado());
			return new SQLFilter.SQLFilterBuilder(Pedido.class, getEm(), filtravel)
									.filterBy("pd.evento.id", filtroPedido.getIdEventoSelecionado())
									.setupPaginador(paginador)
									.filterSimpleBy("pd.id")
									.orderBy("pd.id")
									.sortedBy(ESortedBy.ASC).build()
									.dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public Pedido consultarCompletoPorId(Long id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ped FROM Pedido ped ");
			sql.append("LEFT JOIN FETCH ped.evento ev ");
			sql.append("LEFT JOIN FETCH ev.cliente c ");
			sql.append("LEFT JOIN FETCH ev.eventoTipo et ");
			sql.append("LEFT JOIN FETCH ev.local lo ");
			sql.append("LEFT JOIN FETCH lo.endereco ed ");
			sql.append("LEFT JOIN FETCH ed.cidade cd ");
			sql.append("LEFT JOIN FETCH cd.estado est ");
			sql.append("WHERE ped.id =:_id");

			return getEm().createQuery(sql.toString(), Pedido.class)
					.setParameter("_id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}
}