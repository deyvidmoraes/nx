package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario_historico")
public class UsuarioHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioHistoricoPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario_situacao", insertable = false, updatable = false, 
	foreignKey = @ForeignKey(name = "fk_usuario_situacao"))
	private UsuarioSituacao usuarioSituacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario", insertable = false, updatable = false, 
	foreignKey = @ForeignKey(name = "fk_usuario"))
	private Usuario usuario;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario_alteracao", 
	foreignKey = @ForeignKey(name = "fk_usuario_alteracao"))
	private Usuario usuarioAlteracao;

	public UsuarioHistorico() {
	}

	public UsuarioHistoricoPK getId() {
		return this.id;
	}

	public void setId(UsuarioHistoricoPK id) {
		this.id = id;
	}

	public UsuarioSituacao getUsuarioSituacao() {
		return usuarioSituacao;
	}
	
	public void setUsuarioSituacao(UsuarioSituacao usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
}