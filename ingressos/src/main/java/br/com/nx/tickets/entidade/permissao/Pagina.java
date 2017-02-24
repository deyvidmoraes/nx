package br.com.nx.tickets.entidade.permissao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@Table(name = "pagina", schema = "permissao")
public class Pagina implements Serializable, SituacaoAlteravel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "paginaSeq", sequenceName = "pagina_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "paginaSeq")
	private Integer id;

	@NotNull
	@Column(name = "nome", nullable = false, length = SistemaConstantes.DUZENTOS_CINQUENTA)
	private String nome;

	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;
	
	@OneToMany(mappedBy = "pagina")
	private List<PaginaNivel> paginaNivel;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;

	public Pagina() {

	}

	public Pagina(String caminhoArquivo) {
		this.situacao = ESituacao.ATIVO;
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

	@PrePersist
	public void setDataCadastro() {
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
		return "Pagina [id=" + id + "]";
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
		Pagina other = (Pagina) obj;
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