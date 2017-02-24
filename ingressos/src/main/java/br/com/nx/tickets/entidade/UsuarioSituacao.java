package br.com.nx.tickets.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.EBoolean;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@XmlRootElement
@Table(name = "usuario_situacao")
@XmlAccessorType(XmlAccessType.NONE)
public class UsuarioSituacao implements SituacaoAlteravel, Descritivel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuarioSituacaoSeq", sequenceName = "usuario_situacao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarioSituacaoSeq")
	@Column(unique = true, nullable = false)
	@XmlElement(name = "id")
	private Integer id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "ativo", length = SistemaConstantes.DEZ, nullable = false)
	private EBoolean ativo;

	@NotNull
	@NotEmpty
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false, unique = true)
	@XmlElement(name = "descricao")
	private String descricao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;

	@OneToMany(mappedBy = "ultimaSituacao")
	private List<Usuario> usuarios;

	@Column(name = "icone", length = SistemaConstantes.CINQUENTA, unique = true)
	private String icone;
	
	public UsuarioSituacao() {
	}

	public UsuarioSituacao(Integer id, String descricao, String icone) {
		this.id = id;
		this.descricao = descricao;
		this.icone = icone;
	}
	
	public UsuarioSituacao(String descricao) {
		this.descricao = descricao;
	}

	public UsuarioSituacao(String descricao, EBoolean ativo) {
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public Integer getId() {
		return this.id;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario adicionarUsuarios(Usuario u) {
		getUsuarios().add(u);
		return u;
	}

	public Usuario removerUsuarios(Usuario u) {
		getUsuarios().remove(u);
		return u;
	}

	@Override
	public void alterarSituacao() {
		this.situacao = Util.alteraSituacao(situacao);
	}

	public EBoolean getAtivo() {
		return ativo;
	}
	
	public String getIcone() {
		return icone;
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
		UsuarioSituacao other = (UsuarioSituacao) obj;
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
	public ESituacao getSituacao() {
		return situacao;
	}
}