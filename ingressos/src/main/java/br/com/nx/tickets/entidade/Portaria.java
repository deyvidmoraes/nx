package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@Table(name = "portaria", schema = "public")
public class Portaria implements Serializable, SituacaoAlteravel, Paginavel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "portariaSeq", sequenceName = "portaria_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "portariaSeq")
	private Integer id;

	@NotNull
	@Column(name = "nome", nullable = false, length = SistemaConstantes.DUZENTOS_CINQUENTA)
	private String nome;

	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;
	
	@NotNull
	@XmlElement(name = "local")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_local", foreignKey = @ForeignKey(name = "fk_local"), nullable = false)
	private Local local;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;
	
	public Portaria() {

	}

	public Portaria(String caminhoArquivo) {
		this.situacao = ESituacao.ATIVO;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void alterarSituacao() {
		this.situacao = Util.alteraSituacao(this.situacao);
	}

	@Override
	public ESituacao getSituacao() {
		return situacao;
	}

	public Integer getId() {
		return id;
	}

	public Local getLocal() {
		return local;
	}
	
	public void setLocal(Local local) {
		this.local = local;
	}
	
	@PrePersist
	protected void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return "Portaria [id=" + id + ", nome=" + nome + "]";
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
		Portaria other = (Portaria) obj;
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
	public String getSqlSelect() {
		return "SELECT distinct(p) FROM Portaria p ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct p.id) FROM Portaria p ";
	}

	@Override
	public String getObjetoRetorno() {
		return "p";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH p.local lc";
	}
}