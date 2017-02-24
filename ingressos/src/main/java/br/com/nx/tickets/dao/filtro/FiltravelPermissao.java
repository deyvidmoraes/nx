package br.com.nx.tickets.dao.filtro;

import java.util.Set;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.servico.BaseServicoException;

public interface FiltravelPermissao<T> {

	Set<T> consularPorFiltro(Filtravel filtravel) throws BaseServicoException;
}