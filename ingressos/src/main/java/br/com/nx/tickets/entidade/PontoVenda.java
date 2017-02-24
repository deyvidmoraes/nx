package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "ponto_venda", schema = "public")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class PontoVenda implements Serializable, Paginavel {

	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "pontoVendaSeq", sequenceName = "ponto_venda_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pontoVendaSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@XmlElement(name = "nome")
	@Column(name = "nome", length = SistemaConstantes.CENTO_CINQUENTA)
	private String nome;

	@XmlElement(name = "cpf_cnpj")
	@Column(name = "cpf_cnpj", length = SistemaConstantes.CPF_CNPJ)
	private String cpfCnpj;

	@XmlTransient
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fundacao")
	private Calendar dataFundacao;

	@XmlTransient
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	@XmlTransient
	@Column(name = "email", length = SistemaConstantes.EMAIL)
	private String email;

	@XmlTransient
	@Column(name = "emissor", length = SistemaConstantes.DEZ)
	private String emissor;

	@XmlElement(name = "rg_inscricao_estadual")
	@Column(name = "rg_inscricao_estadual", length = SistemaConstantes.VINTE)
	private String rgInscricaoEstadual;

	@XmlTransient
	@Column(name = "telefone_celular", length = SistemaConstantes.TELEFONE)
	private String telefoneCelular;

	@XmlTransient
	@Column(name = "telefone_comercial", length = SistemaConstantes.TELEFONE)
	private String telefoneComercial;

	@XmlTransient
	@Column(name = "telefone_residencial", length = SistemaConstantes.TELEFONE)
	private String telefoneResidencial;

	@XmlElement(name = "taxa_administrativa")
	@Column(name = "taxa_administrativa", precision = SistemaConstantes.DEZESSETE, scale = SistemaConstantes.DOIS)
	private BigDecimal taxaAdministrativa = new BigDecimal(0);

	@XmlTransient
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_segmento", foreignKey = @ForeignKey(name = "fk_segmento"), nullable = false)
	private Segmento segmento;

	@XmlTransient
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_endereco", foreignKey = @ForeignKey(name = "fk_endereco"))
	private Endereco endereco;

	@XmlElement(name = "limite_ingresso_por_pedido")
	@NotNull
	@Column(name = "limite_ingresso_por_pedido")
	private Integer limiteIngressoPorPedido;

	@NotNull
	@XmlTransient
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name = "data_cadastro")
	private Calendar dataCadastro;
	
	@NotAudited
	@XmlTransient
	@OneToMany(mappedBy = "pontoVenda", fetch = FetchType.LAZY)
	private List<EventoPontoVenda> eventosPontosVendas;
	
	@NotAudited
	@XmlTransient
	@OneToMany(mappedBy = "pontoVenda", fetch = FetchType.LAZY)
	private List<ClientePontoVenda> clientesPontosVendas;
	
	@NotAudited
	@XmlTransient
	@OneToMany(mappedBy = "pontoVenda", fetch = FetchType.LAZY)
	private List<UsuarioPontoVenda> usuariosPontosVendas;

	public PontoVenda() {
	}

	public PontoVenda(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Calendar getDataFundacao() {
		return this.dataFundacao;
	}

	public void setDataFundacao(Calendar dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmissor() {
		return this.emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	public String getNome() {

		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public String getRgInscricaoEstadual() {
		return this.rgInscricaoEstadual;
	}

	public void setRgInscricaoEstadual(String rgInscricaoEstadual) {
		this.rgInscricaoEstadual = rgInscricaoEstadual;
	}

	public String getTelefoneCelular() {
		return this.telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneComercial() {
		return this.telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public String getTelefoneResidencial() {
		return this.telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getLimiteIngressoPorPedido() {
		return limiteIngressoPorPedido;
	}

	public void setLimiteIngressoPorPedido(Integer limiteIngressoPorPedido) {
		this.limiteIngressoPorPedido = limiteIngressoPorPedido;
	}

	public BigDecimal getTaxaAdministrativa() {
		return taxaAdministrativa;
	}

	public void setTaxaAdministrativa(BigDecimal taxaAdministrativa) {
		this.taxaAdministrativa = taxaAdministrativa;
	}

	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(pv) FROM PontoVenda pv ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct pv) FROM PontoVenda pv ";
	}

	@Override
	public String getObjetoRetorno() {
		return "pv";
	}

	@Override
	public String getJoin() {
		return "";
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
		PontoVenda other = (PontoVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PontoVenda [id=" + id + ", nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", email=" + email + ", emissor="
				+ emissor + ", rgInscricaoEstadual=" + rgInscricaoEstadual + ", telefoneCelular=" + telefoneCelular
				+ ", telefoneComercial=" + telefoneComercial + ", telefoneResidencial=" + telefoneResidencial
				+ ", taxaAdministrativa=" + taxaAdministrativa + ", limiteIngressoPorPedido=" + limiteIngressoPorPedido + "]";
	}
}