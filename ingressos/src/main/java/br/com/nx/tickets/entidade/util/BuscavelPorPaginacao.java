package br.com.nx.tickets.entidade.util;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.servico.BaseServicoException;

@Local
public interface BuscavelPorPaginacao extends Serializable {
	
	Paginador<Paginavel> obterPorFiltro(Paginador<Paginavel> paginador,
			Filtravel filtravel) throws BaseServicoException;
}
