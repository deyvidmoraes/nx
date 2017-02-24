package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.entidade.util.DescritivelIdentificavel;
import br.com.nx.tickets.entidade.util.EFormatoData;
import br.com.nx.tickets.util.SistemaConstantes;
import br.com.nx.tickets.util.Util;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "usuario", schema = "public")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Usuario implements Serializable, Cloneable, DescritivelIdentificavel,
		Comparable<Usuario>, Paginavel {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "usuarioSeq", sequenceName = "usuario_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarioSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotEmpty
	@NotNull
	@Column(name = "nome", length = SistemaConstantes.CENTO_CINQUENTA, nullable = false)
	@XmlElement(name = "nome")
	private String nome;

	@XmlTransient
	@Column(name = "cpf", length = SistemaConstantes.ONZE, nullable = false)
	private String cpf;
	
	@XmlTransient
	@Column(name = "rg", length = SistemaConstantes.VINTE)
	private String rg;
	
	@NotNull
	@Email(message = "Email inválido")
	@Column(name = "email", length = SistemaConstantes.EMAIL, nullable = false)
	@XmlTransient
	private String email;

	@NotNull
	@Column(name = "login", length = SistemaConstantes.CINQUENTA, nullable = false)
	@XmlElement(name = "login")
	private String login;

	@NotNull
	@Column(name = "senha", length = SistemaConstantes.CENTO_CINQUENTA, nullable = false)
	@XmlTransient
	private String senha;

	@Column(name = "skype", length = SistemaConstantes.CENTO_CINQUENTA)
	@XmlTransient
	private String skype;

	@Column(name = "gtalk", length = SistemaConstantes.CENTO_CINQUENTA)
	@XmlTransient
	private String gtalk;

	@XmlTransient
	@NotNull
	@NotAudited
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_nivel", foreignKey = @ForeignKey(name = "fk_nivel"), nullable = false)
	private Nivel nivel;

	
	@NotAudited
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
	@JoinColumn(name = "_ultima_situacao", foreignKey = @ForeignKey(name = "fk_ultima_situacao"), nullable = false)
	private UsuarioSituacao ultimaSituacao;

	@XmlTransient
	@NotAudited
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<UsuarioHistorico> usuarioHistoricos;

	@XmlTransient
	@NotAudited
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<UsuarioTelefone> usuarioTelefones;

	@NotAudited
	@Column(name = "data_ultima_interacao")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataUltimaInteracao;
	
	@Column(name = "data_cadastro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Calendar dataCadastro;
	
	@NotAudited
	@XmlTransient
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<UsuarioPontoVenda> usuariosPontosVendas;
	
	@NotAudited
	@XmlTransient
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<UsuarioCliente> usuariosClientes;

	public Usuario() {
		setDataCadastro();
	}

	public Usuario(Integer id) {
		this.id = id;
	}

	public Usuario(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Usuario(String cpf, String nome, String login, Nivel nivel) {
		this.cpf = cpf;
		this.nome = nome;
		this.login = login;
		this.senha = Util.criptografarString("12345");
		this.nivel = nivel;
		this.email = "SISTEMA@NXMULTISERVICOS.COM.BR";
	}

	public Usuario(Integer id, String nome, String login, Integer idMotivoInteracao, String descricaoMotivoInteracao,
			String iconeMotivoInteracao, Calendar dataUltimaInteracao, Integer idSituacao, String descricaoSituacao,
			String iconeSituacao) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.dataUltimaInteracao = dataUltimaInteracao;
		this.ultimaSituacao = new UsuarioSituacao(idSituacao, descricaoSituacao, iconeSituacao);
	}

	// Construtor usado na sql que retorna usuarios pro chat
	public Usuario(String login, String razaoSocial, String nome, String nivel) {
		this.login = login;
		this.nome = nome;
		this.nivel = new Nivel(nivel);
	}

	public Usuario(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public String getPrimeiroNome() {
		try {
			String[] nomes = this.nome.split(" ");
			if (nomes.length > 0) {
				return nomes[0];
			}
			return this.nome;
		} catch (Exception e) {
			return this.nome;
		}
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Nivel getNivel() {
		return this.nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public UsuarioSituacao getUltimaSituacao() {
		return this.ultimaSituacao;
	}

	public void setUltimaSituacao(UsuarioSituacao ultimaSituacao) {
		this.ultimaSituacao = ultimaSituacao;
	}

	@PrePersist
	private void prePersist() throws NoSuchAlgorithmException {
		criptografarSenha();
		setDataCadastro();
	}

	private void criptografarSenha() throws NoSuchAlgorithmException {
		this.senha = Util.criptografarString(getSenha());
		this.dataUltimaInteracao = Calendar.getInstance();
	}

	public Calendar getDataCadastro() {
		return this.dataCadastro;
	}

	private void setDataCadastro() {
		this.dataCadastro = Calendar.getInstance();
	}

	public String getGtalk() {
		return gtalk;
	}

	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.getLogin())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return +((id != null) ? id : 0) + ";" + (getNome() != null ? getNome() : "") + ";"
				+ (getLogin() != null ? getLogin() : "") + ";"
				+ (getNivel() != null && getNivel().getDescricao() != null ? getNivel().getDescricao() : "");
	}

	public String getTelefone1() {
		if (usuarioTelefones != null && !usuarioTelefones.isEmpty()) {
			return usuarioTelefones.get(0).getTelefone();
		} else {
			return "NAO INFORMADO";
		}
	}

	public static final Comparator<Usuario> COMPARAR_POR_NOME = new Comparator<Usuario>() {

		@Override
		public int compare(Usuario o1, Usuario o2) {
			if (o1.getNome().compareTo(o2.getNome()) > 0) {
				return 1;
			} else if (o1.getNome().compareTo(o2.getNome()) < 0) {
				return -1;
			}
			return 0;
		}
	};

	public static void ordenarPorNome(List<Usuario> usuarios) {
		Collections.sort(usuarios, Usuario.COMPARAR_POR_NOME);
	}

	@Override
	public String getDescricao() {
		return this.nome;
	}

	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public int compareTo(Usuario o) {
		return 0;
	}
	
	@Override
	public String getSqlSelect() {
		return "SELECT distinct(usu) FROM Usuario usu ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct usu) FROM Usuario usu ";
	}

	@Override
	public String getObjetoRetorno() {
		return "usu";
	}

	@Override
	public String getJoin() {
		return "JOIN FETCH usu.nivel nv JOIN FETCH usu.ultimaSituacao uls";
	}
	
}