package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClientePontoVendaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "_cliente")
	private int cliente;

	@Column(name = "_ponto_venda")
	private int pontoVenda;

	public ClientePontoVendaPK() {
	}

	public ClientePontoVendaPK(int cliente, int pontoVenda) {
		this.cliente = cliente;
		this.pontoVenda = pontoVenda;
	}

	public int getUsuario() {
		return this.cliente;
	}

	public int getPontoVenda() {
		return pontoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pontoVenda;
		result = prime * result + cliente;
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
		ClientePontoVendaPK other = (ClientePontoVendaPK) obj;
		if (pontoVenda != other.pontoVenda) {
			return false;
		}
		if (cliente != other.cliente) {
			return false;
		}
		return true;
	}
}