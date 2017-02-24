package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.ConfiguracaoImpressora;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class ConfiguracaoImpressoraDAO extends BaseDAO<ConfiguracaoImpressora> {

	private static final long serialVersionUID = 1L;
	
	public ConfiguracaoImpressoraDAO() {
		super(ConfiguracaoImpressora.class);
	}

	@Override
	public void verificarDuplicidade(ConfiguracaoImpressora t)
			throws ViolacaoDeConstraintException {
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(
			Paginador<Paginavel> paginador, Filtravel filtravel) {
		return null;
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}
}
