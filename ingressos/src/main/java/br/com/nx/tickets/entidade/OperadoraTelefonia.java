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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "operadora_telefonia", schema = "public")
public class OperadoraTelefonia implements SituacaoAlteravel, Descritivel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "operadoraTelefoniaSeq", sequenceName = "operadora_telefonia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "operadoraTelefoniaSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false, unique = true)
	private String descricao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;

	@XmlTransient
	@OneToMany(mappedBy = "operadoraTelefonia")
	private List<UsuarioTelefone> usuarioTelefones;

	public OperadoraTelefonia() {
	}

	public OperadoraTelefonia(String descricao) {
		this.descricao = descricao;
	}

	public OperadoraTelefonia(Integer id) {
		this.id = id;
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

	public List<UsuarioTelefone> getUsuarioTelefones() {
		return this.usuarioTelefones;
	}

	public void setUsuarioTelefones(List<UsuarioTelefone> usuarioTelefones) {
		this.usuarioTelefones = usuarioTelefones;
	}

	public UsuarioTelefone adicionarUsuarioTelefones(UsuarioTelefone fones) {
		getUsuarioTelefones().add(fones);
		fones.setOperadoraTelefonia(this);

		return fones;
	}

	public UsuarioTelefone removerUsuarioTelefones(UsuarioTelefone fones) {
		getUsuarioTelefones().remove(fones);
		fones.setOperadoraTelefonia(null);
		return fones;
	}

	@Override
	public void alterarSituacao() {
		this.situacao = Util.alteraSituacao(situacao);
	}

	@Override
	public ESituacao getSituacao() {
		return situacao;
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
		OperadoraTelefonia other = (OperadoraTelefonia) obj;
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