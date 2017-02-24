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
@Table(name = "cliente_ponto_venda", schema = "public")
public class ClientePontoVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClientePontoVendaPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_cliente", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_cliente"))
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_ponto_venda", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_ponto_venda"))
	private PontoVenda pontoVenda;

	public ClientePontoVenda() {
	}

	public ClientePontoVenda(Cliente cliente, PontoVenda pontoVenda) {
		this.cliente = cliente;
		this.pontoVenda = pontoVenda;
		this.id = new ClientePontoVendaPK(cliente.getId(), pontoVenda.getId());
	}

	public ClientePontoVendaPK getId() {
		return this.id;
	}

	public Cliente getCliente() {
		return cliente;
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
		ClientePontoVenda other = (ClientePontoVenda) obj;
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