package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evento_atracao", schema = "public")
public class EventoAtracao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventoAtracaoPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_evento", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_evento"))
	private Evento evento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_atracao", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_atracao"))
	private Atracao atracao;

	public EventoAtracao() {
	}

	public EventoAtracao(Evento evento, Atracao atracao) {
		this.evento = evento;
		this.atracao = atracao;
		this.id = new EventoAtracaoPK(evento.getId(), atracao.getId());
	}

	public EventoAtracaoPK getId() {
		return this.id;
	}

	public Evento getEvento() {
		return evento;
	}

	public Atracao getAtracao() {
		return atracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EventoAtracao other = (EventoAtracao) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}