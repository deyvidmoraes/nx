package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "_usuario")
	private int usuario;

	@Column(name = "_usuario_situacao")
	private int usuarioSituacao;

	@Column(name = "data_alteracao")
	private Date dataAlteracao;

	public UsuarioHistoricoPK() {
	}

	public int getUsuario() {
		return this.usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getUsuarioSituacao() {
		return usuarioSituacao;
	}
	
	public void setUsuarioSituacao(int usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + usuarioSituacao;
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
		UsuarioHistoricoPK other = (UsuarioHistoricoPK) obj;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null) {
				return false;
			}
		} else if (!dataAlteracao.equals(other.dataAlteracao)) {
			return false;
		}
		if (usuarioSituacao != other.usuarioSituacao) {
			return false;
		}
		if (usuario != other.usuario) {
			return false;
		}
		return true;
	}
}