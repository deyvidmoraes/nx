package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.UsuarioTelefone;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class UsuarioTelefoneDAO extends BaseDAO<UsuarioTelefone> {

	private static final long serialVersionUID = 1L;

	public UsuarioTelefoneDAO() {
		super(UsuarioTelefone.class);
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public void verificarDuplicidade(UsuarioTelefone t) throws ViolacaoDeConstraintException {
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		return null;
	}
}
