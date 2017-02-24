package br.com.nx.tickets.entidade.permissao;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.nx.tickets.entidade.Nivel;

@Entity
@Table(name = "paginal_nivel", schema = "permissao")
public class PaginaNivel implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaginaNivelPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_pagina", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_pagina"))
	private Pagina pagina;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_nivel", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_nivel"))
	private Nivel nivel;

	public PaginaNivel() {
	}

	public PaginaNivel(Pagina pagina, Nivel nivel) {
		this.id = new PaginaNivelPK(pagina.getId(), nivel.getId());
		this.pagina = pagina;
		this.nivel = nivel;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public Nivel getNivel() {
		return nivel;
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
		PaginaNivel other = (PaginaNivel) obj;
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
