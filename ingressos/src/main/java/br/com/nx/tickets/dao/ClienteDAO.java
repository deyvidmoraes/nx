package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Cliente;
import br.com.nx.tickets.entidade.ClientePontoVenda;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class ClienteDAO extends BaseDAO<Cliente> {

	private static final long serialVersionUID = 1L;

	public ClienteDAO() {
		super(Cliente.class);
	}

	@Override
	public void verificarDuplicidade(Cliente t) throws ViolacaoDeConstraintException {
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(Cliente.class, getEm(), filtravel)
									.setupPaginador(paginador)
									.filterSimpleBy("cl.nome")
									.orderBy("cl.nome")
									.sortedBy(ESortedBy.ASC).build()
									.dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}
	
	public List<PontoVenda> consultarPontosVendas(Cliente cliente) {
		try {
			return getEm().createQuery("SELECT pdv.pontoVenda FROM Cliente cl JOIN cl.clientesPontosVendas pdv "
					+ "WHERE cl =:_cliente ORDER BY pdv.pontoVenda.nome ", PontoVenda.class)
						  .setParameter("_cliente", cliente)
						  .getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public void atualizarPontosVendas(Cliente cliente, List<PontoVenda> pontosVendas) {
		try {
			removerPontoVendaPorCliente(cliente);
			for (PontoVenda pontoVenda : pontosVendas) {
				getEm().persist(new ClientePontoVenda(cliente, pontoVenda));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removerPontoVendaPorCliente(Cliente cliente) {
		try {
			getEm().createNativeQuery("DELETE FROM cliente_ponto_venda where _cliente =:_cliente ")
			.setParameter("_cliente", cliente)
			.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}