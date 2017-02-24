package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "lote", schema = "public")
public class Lote implements Serializable, Paginavel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "loteSeq", sequenceName = "lote_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "loteSeq")
	@Column(unique = true, nullable = false)
	@XmlElement(name = "id")
	private Integer id;

	@NotNull
	@XmlElement(name = "numero")
	@Column(name = "numero")
	private Integer numero;

	@NotNull
	@XmlElement(name = "quantidade_ingressos")
	@Column(name = "quantidade_ingressos", nullable = false)
	private Integer quantidadeIngressos;

	@XmlElement(name = "valor")
	@Column(name = "valor", precision = SistemaConstantes.DEZESSETE, scale = SistemaConstantes.DOIS)
	private BigDecimal valor = new BigDecimal(0);

	@NotNull
	@Column(name = "data_inicio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataInicio;
	
	@Transient
	@XmlElement(name = "data_inicio")
	private String dataInicioIntegracao;

	@NotNull
	@Column(name = "data_fim", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataFim;
	
	@Transient
	@XmlElement(name = "data_fim")
	private String dataFimIntegracao;

	@NotNull
	@XmlTransient
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;
	
	@XmlElement(name = "taxa_administrativa")
	@Column(name = "taxa_administrativa", precision = SistemaConstantes.DEZESSETE, scale = SistemaConstantes.DOIS)
	private BigDecimal taxaAdministrativa = new BigDecimal(0);

	@NotNull
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_evento", foreignKey = @ForeignKey(name = "fk_evento"), nullable = false)
	private Evento evento;

	@NotNull
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario", foreignKey = @ForeignKey(name = "fk_usuario"), nullable = false)
	private Usuario usuario;

	@NotNull
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_lote_situacao", foreignKey = @ForeignKey(name = "fk_lote_situacao"), nullable = false)
	private LoteSituacao loteSituacao;

	@NotNull
	@XmlElement(name = "ingresso_tipo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_ingresso_tipo", foreignKey = @ForeignKey(name = "fk_ingresso_tipo"), nullable = false)
	private IngressoTipo ingressoTipo;
	
	public Lote() {

	}

	public Lote(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getQuantidadeIngressos() {
		return quantidadeIngressos;
	}

	public void setQuantidadeIngressos(Integer quantidadeIngressos) {
		this.quantidadeIngressos = quantidadeIngressos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}
	
	public Date getDataFimFormatado() {
		return dataFim.getTime();
	}
	
	
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	@PrePersist
	protected void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}
	
	public BigDecimal getTaxaAdministrativa() {
		return taxaAdministrativa;
	}
	
	public void setTaxaAdministrativa(BigDecimal taxaAdministrativa) {
		this.taxaAdministrativa = taxaAdministrativa;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LoteSituacao getLoteSituacao() {
		return loteSituacao;
	}

	public void setLoteSituacao(LoteSituacao loteSituacao) {
		this.loteSituacao = loteSituacao;
	}
	
	public IngressoTipo getIngressoTipo() {
		return ingressoTipo;
	}

	public void setIngressoTipo(IngressoTipo ingressoTipo) {
		this.ingressoTipo = ingressoTipo;
	}
	
	public String getDataInicioIntegracao() {
		return DataUtil.formatarData(dataInicio, EFormatoData.AMERICANO_HORA);
	}
	
	public String getDataFimIntegracao() {
		return DataUtil.formatarData(dataFim, EFormatoData.AMERICANO_HORA);
	}
	
	
	public String getDataInicioListar() {
		return DataUtil.formatarData(dataInicio, EFormatoData.BRASILEIRO_DATA);
	}
	
	public String getDataFimListar() {
		return DataUtil.formatarData(dataFim, EFormatoData.BRASILEIRO_DATA);
	}
	
	public String getDataCadastroListar() {
		return DataUtil.formatarData(dataCadastro, EFormatoData.BRASILEIRO_DATA_HORA);
	}
	
	@Override
	public String getSqlSelect() {
		return "SELECT distinct(lt) FROM Lote lt ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct lt) FROM Lote lt ";
	}

	@Override
	public String getObjetoRetorno() {
		return "lt";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH lt.evento ev " 
			 + "JOIN FETCH lt.usuario usu " 
			 + "JOIN FETCH lt.loteSituacao lts "
			 + "JOIN FETCH lt.ingressoTipo it ";
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lote other = (Lote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lote [id=" + id + ", numero=" + numero + ", quantidadeIngressos=" + quantidadeIngressos + ", valor="
				+ valor + ", taxaAdministrativa=" + taxaAdministrativa + ", ingressoTipo=" + ingressoTipo + "]";
	}
}