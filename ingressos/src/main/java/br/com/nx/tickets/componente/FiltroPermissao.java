package br.com.nx.tickets.componente;

import br.com.nx.tickets.entidade.ENivel;
import br.com.nx.tickets.entidade.Usuario;

public abstract class FiltroPermissao implements Filtravel {

	private static final long serialVersionUID = 1L;
	private String entradaFiltro;
	private String situacaoSelecionada;
	private ENivel nivel;
	
	private Integer usuarioLogado;
	
	public FiltroPermissao(Usuario usuarioLogado) {
		if (usuarioLogado != null) {
			this.usuarioLogado = usuarioLogado.getId();
			this.nivel = ENivel.ADMINISTRADOR;
			this.entradaFiltro = null;
		}
	}

	public Integer getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Integer usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getEntradaFiltro() {
		return entradaFiltro;
	}
	
	public void setEntradaFiltro(String entradaFiltro) {
		this.entradaFiltro = entradaFiltro;
	}
	
	public String getSituacaoSelecionada() {
		return situacaoSelecionada;
	}
	
	public void setSituacaoSelecionada(String situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}
	
	public ENivel getNivel() {
		return nivel;
	}
	
	public void setNivel(ENivel nivel) {
		this.nivel = nivel;
	}
}
