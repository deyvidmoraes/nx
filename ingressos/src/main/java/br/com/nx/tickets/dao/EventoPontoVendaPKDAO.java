package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.EventoPontoVendaPK;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class EventoPontoVendaPKDAO extends BaseDAO<EventoPontoVendaPK> {

	private static final long serialVersionUID = 1L;

	public EventoPontoVendaPKDAO() {
		super(EventoPontoVendaPK.class);
	}

	@Override
	public void verificarDuplicidade(EventoPontoVendaPK t) throws ViolacaoDeConstraintException {
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		return null;
	}
}