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
@Table(name = "evento_ponto_venda", schema = "public")
public class EventoPontoVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventoPontoVendaPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_evento", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_evento"))
	private Evento evento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_ponto_venda", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_ponto_venda"))
	private PontoVenda pontoVenda;

	public EventoPontoVenda() {
	}

	public EventoPontoVenda(Evento evento, PontoVenda pontoVenda) {
		this.evento = evento;
		this.pontoVenda = pontoVenda;
		this.id = new EventoPontoVendaPK(evento.getId(), pontoVenda.getId());
	}

	public EventoPontoVendaPK getId() {
		return this.id;
	}

	public Evento getEvento() {
		return evento;
	}

	public PontoVenda getPontoVenda() {
		return pontoVenda;
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
		EventoPontoVenda other = (EventoPontoVenda) obj;
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