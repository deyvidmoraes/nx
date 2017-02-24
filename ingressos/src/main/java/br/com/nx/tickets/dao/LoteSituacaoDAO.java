package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel; 
import br.com.nx.tickets.entidade.LoteSituacao;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class LoteSituacaoDAO extends BaseDAO<LoteSituacao> {
	
	private static final long serialVersionUID = 1L;
	
	public LoteSituacaoDAO() {
		super(LoteSituacao.class);
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verificarDuplicidade(LoteSituacao t) throws ViolacaoDeConstraintException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		// TODO Auto-generated method stub
		return null;
	}

}
