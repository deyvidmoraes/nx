package br.com.nx.tickets.entidade.permissao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PaginaNivelPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "_pagina")
	private int pagina;

	@Column(name = "_nivel")
	private int nivel;

	public PaginaNivelPK(int pagina, int nivel) {
		this.pagina = pagina;
		this.nivel = nivel;
	}

	public PaginaNivelPK() {
	}

	public int getPagina() {
		return pagina;
	}

	public int getNivel() {
		return nivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pagina;
		result = prime * result + nivel;
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
		PaginaNivelPK other = (PaginaNivelPK) obj;
		if (pagina != other.pagina) {
			return false;
		}
		if (nivel != other.nivel) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PaginaNivelPK [pagina=" + pagina + ", nivel=" + nivel + "]";
	}
}
