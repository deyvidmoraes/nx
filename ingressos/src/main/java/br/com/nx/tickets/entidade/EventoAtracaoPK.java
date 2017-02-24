package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventoAtracaoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "_evento")
	private int evento;

	@Column(name = "_atracao")
	private int atracao;

	public EventoAtracaoPK() {
	}

	public EventoAtracaoPK(int evento, int atracao) {
		this.evento = evento;
		this.atracao = atracao;
	}

	public int getUsuario() {
		return this.evento;
	}

	public int getPontoVenda() {
		return atracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + atracao;
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
		EventoAtracaoPK other = (EventoAtracaoPK) obj;
		if (atracao != other.atracao) {
			return false;
		}
		if (evento != other.evento) {
			return false;
		}
		return true;
	}
}