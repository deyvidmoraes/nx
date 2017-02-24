package br.com.nx.tickets.entidade;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Table(name = "cliente", schema = "public")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Cliente implements Serializable, Paginavel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "clienteSeq", sequenceName = "cliente_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "clienteSeq")
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@XmlElement(name = "nome")
	@Column(name = "nome", length = SistemaConstantes.CENTO_CINQUENTA)
	private String nome;
	
	@Column(name = "cpf_cnpj", length = SistemaConstantes.CPF_CNPJ)
	private String cpfCnpj;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_fundacao")
	private Calendar dataFundacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	@Column(name = "email", length = SistemaConstantes.EMAIL)
	private String email;

	@Column(name = "emissor", length = SistemaConstantes.DEZ)
	private String emissor;

	@Column(name = "rg_inscricao_estadual", length = SistemaConstantes.VINTE)
	private String rgInscricaoEstadual;

	@Column(name = "telefone_celular", length = SistemaConstantes.TELEFONE)
	private String telefoneCelular;

	@XmlElement(name = "telefone_comercial")
	@Column(name = "telefone_comercial", length = SistemaConstantes.TELEFONE)
	private String telefoneComercial;

	@Column(name = "telefone_residencial", length = SistemaConstantes.TELEFONE)
	private String telefoneResidencial;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_segmento", foreignKey = @ForeignKey(name = "fk_segmento"), nullable=false)
	private Segmento segmento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_endereco", foreignKey = @ForeignKey(name = "fk_endereco"))
	private Endereco endereco;
	
	@NotAudited
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<ClientePontoVenda> clientesPontosVendas;
	
	@NotAudited
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<UsuarioCliente> usuariosClientes;

	public Cliente() {
	}

	public Cliente(Integer id) {
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
	
	@Override
	public String getSqlSelect() {
		return "SELECT distinct(cl) FROM Cliente cl ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct cl.id) FROM Cliente cl ";
	}

	@Override
	public String getObjetoRetorno() {
		return "cl";
	}

	@Override
	public String getJoin() {
		return " JOIN FETCH cl.segmento sg ";
	}
	
}