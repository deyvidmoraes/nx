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

import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.Identificavel;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@Table(name = "arquivo_tipo", schema = "public")
public class ArquivoTipo implements SituacaoAlteravel, Descritivel,
		Identificavel {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "arquivoTipoSeq", sequenceName = "arquivo_tipo_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "arquivoTipoSeq")
	private Integer id;

	@NotNull
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false, unique = true)
	private String descricao;

	@NotNull
	@Column(name = "diretorio", length = SistemaConstantes.DESCRICAO, nullable = false)
	private String diretorio;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;

	public ArquivoTipo() {

	}

	public ArquivoTipo(String descricao, String diretorio) {
		this.descricao = descricao;
		this.diretorio = diretorio;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDiretorio() {
		return diretorio;
	}

	@Override
	public ESituacao getSituacao() {
		return situacao;
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
		ArquivoTipo other = (ArquivoTipo) obj;
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
