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
@Table(name = "usuario_cliente", schema = "public")
public class UsuarioCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioClientePK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_usuario"))
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_cliente", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_cliente"))
	private Cliente cliente;

	public UsuarioCliente() {
	}

	public UsuarioCliente(Usuario usuario, Cliente cliente) {
		this.usuario = usuario;
		this.cliente = cliente;
		this.id = new UsuarioClientePK(usuario.getId(), cliente.getId());
	}

	public UsuarioClientePK getId() {
		return this.id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Cliente getCliente() {
		return cliente;
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
		UsuarioCliente other = (UsuarioCliente) obj;
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