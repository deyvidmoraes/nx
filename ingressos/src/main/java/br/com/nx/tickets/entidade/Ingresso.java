package br.com.nx.tickets.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "ingresso", schema = "public")
public class Ingresso implements Paginavel  {

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "ingressoSeq", sequenceName = "ingresso_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ingressoSeq")
	@Column(unique = true, nullable = false)
	private Long id;

	@NotNull
	@XmlElement(name = "codigo")
	@Column(name = "codigo", nullable = false, updatable = false, unique = true)
	private String codigo;
	
	@NotNull
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_pedido", foreignKey = @ForeignKey(name = "fk_pedido"))
	private Pedido pedido;
	
	@NotNull
	@XmlElement(name = "lote")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_lote", foreignKey = @ForeignKey(name = "fk_lote"))
	private Lote lote;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@XmlElement(name = "ingresso_situacao")
	@JoinColumn(name = "_ingresso_situacao", foreignKey = @ForeignKey(name = "fk_ingresso_situacao"), nullable = false)
	private IngressoSituacao ingressoSituacao;
	
	public Ingresso() {
	}
	
	public Ingresso(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Lote getLote() {
		return lote;
	}
	
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	public IngressoSituacao getIngressoSituacao() {
		return ingressoSituacao;
	}
	
	public void setIngressoSituacao(IngressoSituacao ingressoSituacao) {
		this.ingressoSituacao = ingressoSituacao;
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
		Ingresso other = (Ingresso) obj;
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
		return "Ingresso [id=" + id + ", codigo=" + codigo + ", lote=" + lote + "]";
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(i) FROM Ingresso i ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct i.id) FROM Ingresso i ";
	}

	@Override
	public String getObjetoRetorno() {
		return "i";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH i.pedido p "
			 + "JOIN FETCH i.lote lt "
			 + "JOIN FETCH i.ingressoSituacao st ";
	}
}