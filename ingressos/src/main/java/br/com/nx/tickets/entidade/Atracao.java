package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.NotAudited;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@Table(name = "atracao", schema = "public")
public class Atracao implements Serializable, SituacaoAlteravel, Paginavel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "atracaoSeq", sequenceName = "atracao_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "atracaoSeq")
	private Integer id;

	@NotNull
	@Column(name = "nome", nullable = false, length = SistemaConstantes.DUZENTOS_CINQUENTA)
	private String nome;

	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;
	
	@XmlElement(name = "arquivo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_arquivo", foreignKey = @ForeignKey(name = "fk_arquivo"))
	private Arquivo arquivo;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;
	
	@XmlTransient
	@NotAudited
	@OneToMany(mappedBy = "atracao", fetch = FetchType.LAZY)
	private List<EventoAtracao> eventosAtracoes;


	public Atracao() {

	}

	public Atracao(String caminhoArquivo) {
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

	public Arquivo getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
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
		return "Atracao [id=" + id + ", nome=" + nome + "]";
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
		Atracao other = (Atracao) obj;
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
		return "SELECT distinct(at) FROM Atracao at ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct at.id) FROM Atracao at ";
	}

	@Override
	public String getObjetoRetorno() {
		return "at";
	}

	@Override
	public String getJoin() {
		return "";
	}
}