package br.com.nx.tickets.entidade;

import java.util.Comparator;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "cidade", schema = "public")
public class Cidade implements SituacaoAlteravel, Paginavel, Comparable<Cidade> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cidadeSeq", sequenceName = "cidade_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cidadeSeq")
	@Column(unique = true, nullable = false)
	@XmlElement(name = "id")
	private Integer id;

	@NotNull
	@XmlElement(name = "nome")
	@Column(name = "nome", length = SistemaConstantes.CENTO_CINQUENTA, nullable = false)
	private String nome;

	@NotNull
	@XmlElement(name = "estado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_estado", nullable = false, updatable = false, 
	foreignKey = @ForeignKey(name = "fk_estado"))
	private Estado estado;
	
	@NotNull
	@XmlTransient
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	private ESituacao situacao = ESituacao.ATIVO;
	
	public Cidade() {
	}
	
	public Cidade(Integer id, String nome, String uf, String nomeEstado) {
		this.id = id;
		this.nome = nome;
		this.estado = new Estado(uf, nomeEstado);
	}
	
	public Cidade(String nome, String uf) {
		this.nome = nome;
	}

	public Cidade(Integer id) {
		this.id = id;
	}
	
	public Cidade(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getSqlSelect() {
		return  "SELECT distinct(ci) FROM Cidade ci ";
	}
	
	public String getSqlCount() {
		return "SELECT count(distinct ci.id) FROM Cidade ci ";
	}
	
	public String getObjetoRetorno() {
		return "ci";
	}

	public String toPrevisaoTempo() {
		return nome + "," + estado.getUf();
	}
	
	@Override
	public String getJoin() {
		return    " LEFT JOIN FETCH ci.subcluster sc "
				+ " LEFT JOIN sc.cluster cl "
				+ " LEFT JOIN cl.regional r "
				+ " JOIN FETCH ci.estado e "
				+ " LEFT JOIN sc.gestoesExecutivasSubclusters gesb "
				+ " LEFT JOIN gesb.gestaoExecutiva ge "
				+ " LEFT JOIN ci.gestoesExecutivasCidades gec "
				+ " LEFT JOIN ge.agentesAutorizadosGestoesExecutivasCidades aagec "
				+ " LEFT JOIN FETCH ci.agentesAutorizadosGestoesExecutivasCidades aagec2 "
				+ " LEFT JOIN aagec.agenteAutorizado aa "
				+ " LEFT JOIN aa.agentesAutorizadosCanaisVenda aacv "
				+ " LEFT JOIN aacv.coordenacoes co "
				+ " LEFT JOIN co.equipeVendas ev ";
	}
	
	@Override
	public int compareTo(Cidade c) {
		return this.nome.compareTo(c.nome);
	}
	
	public static final Comparator<Cidade> COMPARAR_POR_NOME = new Comparator<Cidade>() {

		@Override
		public int compare(Cidade o1, Cidade o2) {
			if (o1.getNome().compareTo(o2.getNome()) > 0) {
				return 1;
			} else if (o1.getNome().compareTo(o2.getNome()) < 0) {
				return -1;
			}
			return 0;
		}
	};

	@Override
	public void alterarSituacao() {
		// TODO Auto-generated method stub
		
	}
}