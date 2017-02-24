
package br.com.nx.tickets.entidade;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.entidade.util.EFormatoData;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "pedido", schema = "public")
public class Pedido implements Paginavel {

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "pedidoSeq", sequenceName = "pedido_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pedidoSeq")
	@Column(unique = true, nullable = false)
	private Long id;

	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataCadastro;

	@Transient
	@XmlElement(name = "data_cadastro")
	private String dataCadastroIntegracao;

	@NotNull
	@XmlElement(name = "valor_total")
	@Column(name = "valor_total", precision = SistemaConstantes.DEZESSETE, scale = SistemaConstantes.DOIS, nullable = false)
	private BigDecimal valorTotal = new BigDecimal(0);
	
	@NotNull
	@XmlElement(name = "quantidade_ingresso")
	@Column(name = "quantidade_ingresso", nullable = false)
	private Integer quantidadeIngresso;

	@NotNull
	@XmlElement(name = "evento")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_evento", foreignKey = @ForeignKey(name = "fk_evento"))
	private Evento evento;

	@XmlElement(name = "ingressos")
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<Ingresso> ingressos;

	@XmlTransient
	@NotNull
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "_usuario", referencedColumnName = "_usuario"),
			@JoinColumn(name = "_ponto_venda", referencedColumnName = "_ponto_venda") })
	private UsuarioPontoVenda usuarioPontoVenda;

	public Pedido() {
	}

	public Pedido(UsuarioPontoVenda usuarioPontoVenda) {
		this.usuarioPontoVenda = usuarioPontoVenda;
	}

	public Long getId() {
		return id;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public UsuarioPontoVenda getUsuarioPontoVenda() {
		return usuarioPontoVenda;
	}
	
	public void setUsuarioPontoVenda(UsuarioPontoVenda usuarioPontoVenda) {
		this.usuarioPontoVenda = usuarioPontoVenda;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	
	public Integer getQuantidadeIngresso() {
		return quantidadeIngresso;
	}
	
	public void setQuantidadeIngresso(Integer quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}
	
	@Override
	public String getSqlSelect() {
		return "SELECT distinct(pd) FROM Pedido pd ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct pd.id) FROM Pedido pd ";
	}

	@Override
	public String getObjetoRetorno() {
		return "pd";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH pd.usuarioPontoVenda upv ";
	}

	public String getDataCadastroIntegracao() {
		return DataUtil.formatarData(dataCadastro, EFormatoData.AMERICANO_HORA);
	}
	
	public void setDataCadastroIntegracao(String dataCadastroIntegracao) {
		this.dataCadastroIntegracao = dataCadastroIntegracao;
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
		Pedido other = (Pedido) obj;
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
		return "Pedido [id=" + id + ", dataCadastro=" + dataCadastro + ", valorTotal=" + valorTotal
				+ ", quantidadeIngresso=" + quantidadeIngresso + "]";
	}

//	@Override
//	public String toString() {
//		return "Pedido [id=" + id + ", dataCadastro=" + dataCadastro + ", valorTotal=" + valorTotal
//				+ ", quantidadeIngresso=" + quantidadeIngresso + ", usuarioPontoVenda=" + usuarioPontoVenda + "]";
//	}
}