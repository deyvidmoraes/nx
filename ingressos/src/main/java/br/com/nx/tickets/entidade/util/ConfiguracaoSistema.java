package br.com.nx.tickets.entidade.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@Table(name = "configuracao_sistema", schema = "public")
public class ConfiguracaoSistema implements Descritivel, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "descricao", length = SistemaConstantes.DESCRICAO, nullable = false)
	private String descricao;

	@NotNull
	@Column(name = "valor", length = SistemaConstantes.DUZENTOS_CINQUENTA, nullable = false)
	private String valor;

	public ConfiguracaoSistema(String descricao, String valor) {
		this.descricao = descricao;
		this.valor = valor;
	}

	public ConfiguracaoSistema() {

	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public String getValor() {
		if (valor != null) {
			return valor.toUpperCase();
		} else {
			return valor;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		ConfiguracaoSistema other = (ConfiguracaoSistema) obj;
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		return true;
	}
}