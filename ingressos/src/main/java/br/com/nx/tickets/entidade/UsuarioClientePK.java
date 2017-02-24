package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioClientePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "_usuario")
	private int usuario;

	@Column(name = "_cliente")
	private int cliente;

	public UsuarioClientePK() {
	}

	public UsuarioClientePK(int usuario, int cliente) {
		super();
		this.usuario = usuario;
		this.cliente = cliente;
	}

	public int getUsuario() {
		return this.usuario;
	}

	public int getPontoVenda() {
		return cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cliente;
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
		UsuarioClientePK other = (UsuarioClientePK) obj;
		if (cliente != other.cliente) {
			return false;
		}
		if (usuario != other.usuario) {
			return false;
		}
		return true;
	}
}