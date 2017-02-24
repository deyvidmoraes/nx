package br.com.nx.tickets.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "estado", schema = "public")
public class Estado implements Serializable, Comparable<Estado> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 2)
	@XmlElement(name = "uf")
	private String uf;

	@NotNull
	@Column(name = "nome", length = SistemaConstantes.CENTO_CINQUENTA, nullable = false)
	@XmlElement(name = "nome")
	private String nome;

	@XmlTransient
	@OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
	private List<Cidade> cidades;

	public Estado() {
	}

	public Estado(String uf, String nome) {
		this.uf = uf;
		this.nome = nome;
	}

	public Estado(String uf) {
		this.uf = uf;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade adicionarCidade(Cidade cidade) {
		getCidades().add(cidade);
		cidade.setEstado(this);

		return cidade;
	}

	public Cidade removerCidade(Cidade cidade) {
		getCidades().remove(cidade);
		cidade.setEstado(null);

		return cidade;
	}

	@Override
	public int compareTo(Estado o) {
		return 0;
	}
}