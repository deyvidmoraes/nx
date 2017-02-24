package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.OperadoraTelefonia;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class OperadoraTelefoniaDAO extends BaseDAO<OperadoraTelefonia> {

	private static final long serialVersionUID = 1L;

	public OperadoraTelefoniaDAO() {
		super(OperadoraTelefonia.class);
	}

	@Override
	public void verificarDuplicidade(OperadoraTelefonia t) throws ViolacaoDeConstraintException {
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