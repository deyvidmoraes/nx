package br.com.nx.tickets.entidade;

import java.io.Serializable;
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

import org.hibernate.envers.NotAudited;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.EFormatoData;
import br.com.nx.tickets.entidade.util.Identificavel;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "evento", schema = "public")
public class Evento implements Descritivel, Comparable<Evento>, Paginavel, Identificavel, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "eventoSeq", sequenceName = "evento_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "eventoSeq")
	@Column(unique = true, nullable = false)
	@XmlElement(name = "id")
	private Integer id;

	@NotNull
	@XmlElement(name = "descricao")
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false)
	private String descricao;

	@XmlElement(name = "taxa_admimistrativa")
	@Column(name = "taxa_admimistrativa", precision = SistemaConstantes.DEZESSETE, scale = SistemaConstantes.DOIS)
	private BigDecimal taxaAdministrativa = new BigDecimal(0);
	
	@NotNull
	@XmlElement(name = "classificacao_etaria")
	@Column(name = "classificacao_etaria")
	private Integer classificacaoEtaria;
	
	@NotNull
	@XmlTransient
	@Column(name = "capacidade_maxima")
	private Integer capacidadeMaxima;
	
	@NotNull
	@XmlElement(name = "evento_tipo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_evento_tipo", foreignKey = @ForeignKey(name = "fk_evento_tipo"), nullable = false)
	private EventoTipo eventoTipo;

	@NotNull
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_evento_situacao", foreignKey = @ForeignKey(name = "fk_evento_situacao"), nullable = false)
	private EventoSituacao eventoSituacao;
	
	@NotNull
	@XmlTransient
	@Column(name = "data_inicio_venda_ingresso", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//@XmlElement(name = "data_inicio_venda_ingresso")
	private Calendar dataInicioVendaIngresso;

	@NotNull
	@Column(name = "data_abertura_portao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataAberturaPortao;
	
	@Transient
	@XmlElement(name = "data_abertura_portao")
	private String dataAberturaPortaoIntegracao;
	
	@NotNull
	@Column(name = "data_evento", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataEvento;
	
	@Transient
	@XmlElement(name = "data_evento")
	private String dataEventoIntegracao;
	
	@NotNull
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//@XmlElement(name = "data_cadastro")
	@XmlTransient
	private Calendar dataCadastro;
	
	@XmlElement(name = "local")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_local", foreignKey = @ForeignKey(name = "fk_local"))
	private Local local;
	
	@XmlElement(name = "cliente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_cliente", foreignKey = @ForeignKey(name = "fk_cliente"))
	private Cliente cliente;
	
	@XmlTransient
	@OneToMany(mappedBy = "evento", fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
	
	@XmlTransient
	@NotAudited
	@OneToMany(mappedBy = "evento", fetch = FetchType.LAZY)
	private List<EventoPontoVenda> eventosPontosVendas;
	
	@XmlTransient
	@NotAudited
	@OneToMany(mappedBy = "evento", fetch = FetchType.LAZY)
	private List<EventoAtracao> eventosAtracoes;

	public Evento(Integer id) {
		this.id = id;
	}

	public Evento() {
	}

	public Evento(String descricao) {
		this.descricao = descricao;
	}

	public Evento(Integer id, String descricao) {
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
 
	public BigDecimal getTaxaAdministrativa() {
		return taxaAdministrativa;
	}
	
	public void setTaxaAdministrativa(BigDecimal taxaAdministrativa) {
		this.taxaAdministrativa = taxaAdministrativa;
	}

	public Integer getClassificacaoEtaria() {
		return classificacaoEtaria;
	}

	public void setClassificacaoEtaria(Integer classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}

	public Integer getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public void setCapacidadeMaxima(Integer capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public EventoTipo getEventoTipo() {
		return eventoTipo;
	}

	public void setEventoTipo(EventoTipo eventoTipo) {
		this.eventoTipo = eventoTipo;
	}

	public EventoSituacao getEventoSituacao() {
		return eventoSituacao;
	}

	public void setEventoSituacao(EventoSituacao eventoSituacao) {
		this.eventoSituacao = eventoSituacao;
	}

	public Calendar getDataInicioVendaIngresso() {
		return dataInicioVendaIngresso;
	}

	public void setDataInicioVendaIngresso(Calendar dataInicioVendaIngresso) {
		this.dataInicioVendaIngresso = dataInicioVendaIngresso;
	}

	public Calendar getDataAberturaPortao() {
		return dataAberturaPortao;
	}

	public void setDataAberturaPortao(Calendar dataAberturaPortao) {
		this.dataAberturaPortao = dataAberturaPortao;
	}

	public Calendar getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Calendar dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	
	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getDataAberturaPortaoIntegracao() {
		return DataUtil.formatarData(dataAberturaPortao, EFormatoData.AMERICANO_HORA);
	}

	public String getDataEventoIntegracao() {
		return DataUtil.formatarData(dataEvento, EFormatoData.AMERICANO_HORA);
	}
	
	public void setDataAberturaPortaoIntegracao(
			String dataAberturaPortaoIntegracao) {
		this.dataAberturaPortaoIntegracao = dataAberturaPortaoIntegracao;
	}
	
	public void setDataEventoIntegracao(String dataEventoIntegracao) {
		this.dataEventoIntegracao = dataEventoIntegracao;
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
		Evento other = (Evento) obj;
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
		return "Evento [id=" + id + ", descricao=" + descricao + ", taxaAdministrativa=" + taxaAdministrativa
				+ ", classificacaoEtaria=" + classificacaoEtaria + ", capacidadeMaxima=" + capacidadeMaxima + "]";
	}

	@Override
	public int compareTo(Evento n) {
		return this.descricao.compareTo(n.descricao);
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(ev) FROM Evento ev ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct ev.id) FROM Evento ev ";
	}

	@Override
	public String getObjetoRetorno() {
		return "ev";
	}

	@Override
	public String getJoin() {
		return " JOIN FETCH ev.cliente cl "
			 + "JOIN FETCH ev.eventoTipo et "
			 + "JOIN FETCH ev.eventoSituacao es "
			 + "JOIN FETCH ev.local lc ";
	}
}