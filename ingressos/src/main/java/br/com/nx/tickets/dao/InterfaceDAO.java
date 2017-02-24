package br.com.nx.tickets.dao;

import java.io.Serializable;
import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public interface InterfaceDAO<T> extends Serializable {

	T salvar(final T t);

	T alterar(final T t);

	void deletar(final T t, final Integer id);

	List<T> consultarTodos();

	List<T> consultarTodos(String nomeColuna);

	int consultarQuantidade();

	T consultarPorId(final int id);

	<K extends Descritivel> T consultarPorDescricao(final String descricao);

	List<? extends SituacaoAlteravel> consultarPorSituacao(final ESituacao situacao);

	void verificarDuplicidade(final T t) throws ViolacaoDeConstraintException;

	Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel);
}
