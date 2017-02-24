package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.nx.tickets.componente.Paginavel;

@Entity
@Table(name = "usuario_ponto_venda", schema = "public")
public class UsuarioPontoVenda implements Serializable, Paginavel {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioPontoVendaPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_usuario"))
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_ponto_venda", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_ponto_venda"))
	private PontoVenda pontoVenda;

	public UsuarioPontoVenda() {
	}

	public UsuarioPontoVenda(Usuario usuario, PontoVenda pontoVenda) {
		this.usuario = usuario;
		this.pontoVenda = pontoVenda;
		this.id = new UsuarioPontoVendaPK(usuario.getId(), pontoVenda.getId());
	}

	public UsuarioPontoVendaPK getId() {
		return this.id;
	}

	public Usuario getUsuario() {
		return this.usuario;
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
		UsuarioPontoVenda other = (UsuarioPontoVenda) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioPontoVenda [id=" + id + ", pontoVenda=" + pontoVenda + ", usuario=" + usuario + "]";
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(upv) FROM usuarioPontoVenda upv";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct upv.id) FROM usuarioPontoVenda upv";
	}

	@Override
	public String getObjetoRetorno() {
		return "upv";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH upv.pontoVenda pv "
			 + "JOIN FETCH upv.usuario usu ";
	}
}