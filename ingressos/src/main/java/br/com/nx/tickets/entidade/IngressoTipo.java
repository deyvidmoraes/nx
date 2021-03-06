package br.com.nx.tickets.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.Identificavel;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@XmlRootElement
@Table(name = "ingresso_tipo", schema = "public")
public class IngressoTipo implements SituacaoAlteravel, Descritivel, Comparable<IngressoTipo>, Paginavel, Identificavel  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ingressoTipoSeq", sequenceName = "ingresso_tipo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ingressoTipoSeq")
	@Column(unique = true, nullable = false)
	@XmlElement(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false, unique = true)
	private String descricao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;
	
	public IngressoTipo(Integer id) {
		this.id = id;
	}

	public IngressoTipo() {
	}

	public IngressoTipo(String descricao) {
		this.descricao = descricao;
	}

	public IngressoTipo(Integer id, String descricao) {
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
		IngressoTipo other = (IngressoTipo) obj;
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
		return "IngressoTipo [descricao=" + descricao + "]";
	}
	
	@Override
	public int compareTo(IngressoTipo n) {
		return this.descricao.compareTo(n.descricao);
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(it) FROM IngressoTipo it ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct it.id) FROM IngressoTipo it ";
	}

	@Override
	public String getObjetoRetorno() {
		return "it";
	}

	@Override
	public String getJoin() {
		return "";
	}
}