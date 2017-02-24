package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Atracao;
import br.com.nx.tickets.entidade.Cliente;
import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.EventoAtracao;
import br.com.nx.tickets.entidade.EventoPontoVenda;
import br.com.nx.tickets.entidade.Ingresso;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class EventoDAO extends BaseDAO<Evento> {

	private static final long serialVersionUID = 1L;

	public EventoDAO() {
		super(Evento.class);
	}

	@Override
	public void verificarDuplicidade(Evento evento) throws ViolacaoDeConstraintException {
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(Evento.class, getEm(), filtravel).setupPaginador(paginador)
					.filterSimpleBy("ev.descricao").orderBy("ev.id").sortedBy(ESortedBy.ASC).build().dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	public List<Evento> consultarEventosPorCliente(Cliente cliente) {
		try {
			return getEm().createQuery(
					"SELECT ev FROM Evento ev " + "JOIN FETCH ev.eventoTipo et " + "JOIN FETCH ev.eventoSituacao es "
							+ "JOIN FETCH ev.local lc " + "WHERE ev.cliente =:_cliente " + "ORDER BY ev.id ",
					Evento.class).setParameter("_cliente", cliente).getResultList();
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());

		}
	}

	public void atualizarPontosVendas(Evento evento, List<PontoVenda> pontosVendas) {
		try {
			removerPontoVendaPorEvento(evento);
			for (PontoVenda pontoVenda : pontosVendas) {
				getEm().persist(new EventoPontoVenda(evento, pontoVenda));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removerPontoVendaPorEvento(Evento evento) {
		try {
			getEm().createNativeQuery("DELETE FROM evento_ponto_venda " + "WHERE _evento =:_evento ")
					.setParameter("_evento", evento).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PontoVenda> consultarPontosVendas(Evento evento) {
		try {
			return getEm()
					.createQuery("SELECT pdv.pontoVenda " + "FROM Evento ev " + "JOIN ev.eventosPontosVendas pdv "
							+ "WHERE ev =:_evento " + "ORDER BY pdv.pontoVenda.id ", PontoVenda.class)
					.setParameter("_evento", evento).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public void salvarPontosVendas(Evento evento, List<PontoVenda> pontosVendasAssociados) {
		try {
			for (PontoVenda pontoVenda : pontosVendasAssociados) {
				getEm().persist(new EventoPontoVenda(evento, pontoVenda));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Evento> consultarEventosPorPontoVenda(PontoVenda pontoVenda) {
		try {
			return getEm()
					.createQuery("SELECT pdv.evento " + "FROM PontoVenda pv " + "JOIN pv.eventosPontosVendas pdv "
							+ "WHERE pv =:_pontoVenda " + "ORDER BY pdv.evento.id ", Evento.class)
					.setParameter("_pontoVenda", pontoVenda).getResultList();
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}

	public void atualizarAtracoes(Evento evento, List<Atracao> atracoes) {
		try {
			removerAtracaoPorEvento(evento);
			for (Atracao atracao : atracoes) {
				getEm().persist(new EventoAtracao(evento, atracao));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removerAtracaoPorEvento(Evento evento) {
		try {
			getEm().createNativeQuery("DELETE FROM evento_atracao " + "WHERE _evento =:_evento ")
					.setParameter("_evento", evento).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Atracao> consultarAtracoes(Evento evento) {
		try {
			return getEm()
					.createQuery("SELECT at.atracao " + "FROM Evento ev " + "JOIN ev.eventosAtracoes at "
							+ "WHERE ev =:_evento " + "ORDER BY at.atracao.id ", Atracao.class)
					.setParameter("_evento", evento).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Ingresso> obterPedidosPorEvento(Evento evento) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT i FROM Ingresso i ");
			sql.append(" JOIN FETCH i.pedido p ");
			sql.append(" JOIN FETCH p.evento ev ");
			sql.append(" WHERE ev =:_evento ");
			sql.append(" GROUP BY i.lote ");

			return getEm().createQuery(sql.toString(), Ingresso.class).setParameter("_evento", evento).getResultList();
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}

}
