package br.com.nx.tickets.entidade;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "endereco", schema = "public")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "enderecoSeq", sequenceName = "endereco_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "enderecoSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@XmlElement(name = "bairro")
	@Column(name = "bairro", length = SistemaConstantes.CEM)
	private String bairro;

	@XmlElement(name = "bloco")
	@Column(name = "bloco", length = SistemaConstantes.CINQUENTA)
	private String bloco;

	@XmlElement(name = "cep")
	@Column(name = "cep", length = SistemaConstantes.OITO)
	private String cep;

	@XmlElement(name = "complemento")
	@Column(name = "complemento", length = SistemaConstantes.CEM)
	private String complemento;

	@XmlElement(name = "tipo_logradouro")
	@Column(name = "tipo_logradouro", length = SistemaConstantes.CENTO_CINQUENTA)
	private String tipoLogradouro;

	@XmlElement(name = "logradouro")
	@Column(name = "logradouro", length = SistemaConstantes.CENTO_CINQUENTA)
	private String logradouro;

	@XmlElement(name = "numero")
	@Column(name = "numero", length = SistemaConstantes.DEZ)
	private String numero;

	@XmlElement(name = "apartamento")
	@Column(name = "apartamento", length = SistemaConstantes.VINTE)
	private String apartamento;

	@XmlElement(name = "ponto_referencia")
	@Column(name = "ponto_referencia", length = SistemaConstantes.CEM)
	private String pontoReferencia;

	@XmlTransient
	@Column(name = "latitude")
	private Double latitude;

	@XmlTransient
	@Column(name = "longitude")
	private Double longitude;

	@XmlElement(name = "cidade")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_cidade", nullable = false, foreignKey = @ForeignKey(name = "fk_cidade"))
	private Cidade cidade;

	public Endereco() {

	}

	public Endereco(Integer id) {
		this.id = id;
	}

	public Endereco(String tipoLogradouro, String logradouro, String numero,
			String bairro, String cep, String cidade, String uf) {
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = new Cidade(cidade, uf);
	}

	public Integer getId() {
		return this.id;
	}

	public String getApartamento() {
		return this.apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBloco() {
		return this.bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTipoLogradouro() {
		if (tipoLogradouro == null) {
			return "";
		}
		if (tipoLogradouro.equals("R")) {
			return "RUA";
		}
		if (tipoLogradouro.equals("AL")) {
			return "ALAMEDA";
		}
		if (tipoLogradouro.equals("AV")) {
			return "AVENIDA";
		}
		if (tipoLogradouro.equals("VLA")) {
			return "VILA";
		}
		if (tipoLogradouro.equals("ROD")) {
			return "RODOVIA";
		}
		if (tipoLogradouro.equals("EST")) {
			return "ESTRADA";
		}
		if (tipoLogradouro.equals("TV")) {
			return "TRAVESSA";
		}
		return tipoLogradouro;
	}

	public String getEnderecoCompleto() {
		return new StringBuilder(getTipoLogradouro()).append(" ")
				.append(this.logradouro).append(", ").append(this.numero)
				.append(", ").append(this.bairro).append(", ").append(this.cep)
				.append(", ").append(this.cidade.getNome()).append("/")
				.append(this.cidade.getEstado().getUf()).toString();
	}

	public String getLogradouroCompleto() {
		return new StringBuilder(getTipoLogradouro()).append(" ")
				.append(this.logradouro).append(", ").append(this.numero)
				.append(", ").toString();
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPontoReferencia() {
		return this.pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getHashcodeTabulacao() {
		String hash = "";
		if (bairro != null) {
			hash += bairro;
		}

		if (cep != null) {
			hash += cep;
		}

		if (complemento != null) {
			hash += complemento;
		}

		if (logradouro != null) {
			hash += logradouro;
		}

		if (numero != null) {
			hash += numero;
		}
		return hash;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", apartamento=" + apartamento
				+ ", bairro=" + bairro + ", bloco=" + bloco + ", cep=" + cep
				+ ", complemento=" + complemento + ", logradouro=" + logradouro
				+ ", numero=" + numero + ", pontoReferencia=" + pontoReferencia
				+ "]";
	}
}