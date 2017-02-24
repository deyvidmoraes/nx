package br.com.nx.tickets.componente;

import br.com.nx.tickets.entidade.Usuario;

public class FiltroUsuarioPedido extends FiltroPermissao {

	private static final long serialVersionUID = 1L;

	private Integer idEventoSelecionado;

	public FiltroUsuarioPedido(Usuario usuarioLogado) {
		super(usuarioLogado);
	}

	public Integer getIdEventoSelecionado() {
		return idEventoSelecionado;
	}

	public void setIdEventoSelecionado(Integer idEventoSelecionado) {
		this.idEventoSelecionado = idEventoSelecionado;
	}
}
