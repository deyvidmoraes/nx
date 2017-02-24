package br.com.nx.tickets.dao.filtro;

import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;

public interface IPaginavelNovo {

	IFilterByNovo setupPaginador(Paginador<Paginavel> paginador);
	
}
