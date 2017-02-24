package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventoPontoVendaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "_evento")
	private int evento;

	@Column(name = "_ponto_venda")
	private int pontoVenda;

	public EventoPontoVendaPK() {
	}

	public EventoPontoVendaPK(int evento, int pontoVenda) {
		this.evento = evento;
		this.pontoVenda = pontoVenda;
	}

	public int getUsuario() {
		return this.evento;
	}

	public int getPontoVenda() {
		return pontoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pontoVenda;
		result = prime * result + evento;
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
		EventoPontoVendaPK other = (EventoPontoVendaPK) obj;
		if (pontoVenda != other.pontoVenda) {
			return false;
		}
		if (evento != other.evento) {
			return false;
		}
		return true;
	}
}