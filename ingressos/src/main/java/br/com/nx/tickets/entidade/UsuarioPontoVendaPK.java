package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioPontoVendaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "_usuario")
	private int usuario;

	@Column(name = "_ponto_venda")
	private int pontoVenda;

	public UsuarioPontoVendaPK() {
	}

	public UsuarioPontoVendaPK(int usuario, int pontoVenda) {
		super();
		this.usuario = usuario;
		this.pontoVenda = pontoVenda;
	}

	public int getUsuario() {
		return this.usuario;
	}

	public int getPontoVenda() {
		return pontoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pontoVenda;
		result = prime * result + usuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UsuarioPontoVendaPK other = (UsuarioPontoVendaPK) obj;
		if (pontoVenda != other.pontoVenda) {
			return false;
		}
		if (usuario != other.usuario) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioPontoVendaPK [usuario=" + usuario + ", pontoVenda=" + pontoVenda + "]";
	}
}