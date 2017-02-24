package br.com.nx.tickets.entidade;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.NotAudited;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.Identificavel;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "local", schema = "public")
public class Local implements SituacaoAlteravel, Descritivel, Paginavel, Identificavel  {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "localSeq", sequenceName = "local_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "localSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull
	@XmlElement(name = "descricao")
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false)
	private String descricao;

	@NotNull
	@XmlElement(name = "endereco")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_endereco", foreignKey = @ForeignKey(name = "fk_endereco"), nullable = false)
	private Endereco endereco;
	
	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataCadastro;
	
	@NotNull
	@XmlTransient
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;
	
	@NotAudited
	@OneToMany(mappedBy = "local", fetch = FetchType.LAZY)
	private List<Portaria> portarias;
	
	public Local(Integer id) {
		this.id = id;
	}

	public Local() {
	}

	public Local(String descricao) {
		this.descricao = descricao;
	}

	public Local(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public ESituacao getSituacao() {
		return this.situacao;
	}

	@Override
	public void alterarSituacao() {
		this.situacao = Util.alteraSituacao(this.situacao);
	}
	
	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Portaria> getPortarias() {
		return portarias;
	}
	
	public void setPortarias(List<Portaria> portarias) {
		this.portarias = portarias;
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
		Local other = (Local) obj;
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
		return "Local [id=" + id + ", descricao=" + descricao + "]";
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(lc) FROM Local lc ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct lc.id) FROM Local lc ";
	}

	@Override
	public String getObjetoRetorno() {
		return "lc";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH lc.endereco en";
	}
}