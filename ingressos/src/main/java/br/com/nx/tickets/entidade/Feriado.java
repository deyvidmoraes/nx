package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.EBoolean;
import br.com.nx.tickets.util.SistemaConstantes;


@Entity
@Table(name = "feriado", schema = "public")
public class Feriado implements Serializable, Paginavel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "feriadoSeq", sequenceName = "feriado_id_multi_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "feriadoSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "descricao", length = SistemaConstantes.CENTO_CINQUENTA, nullable = false)
	private String descricao;

	@NotNull
	@Column(name = "dia")
	private Integer dia;
	
	@NotNull
	@Column(name = "mes")
	private Integer mes;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "nacional", columnDefinition = SistemaConstantes.E_BOOLEAN_DEFAULT_TRUE)
	private EBoolean nacional = EBoolean.TRUE;

	public Feriado() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDia() {
		return dia;
	}
	
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	public Integer getMes() {
		return mes;
	}
	
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public EBoolean getNacional() {
		return nacional;
	}
	
	public void setNacional(EBoolean nacional) {
		this.nacional = nacional;
	}

	@Override
	public String getSqlSelect() {
		return "SELECT distinct(fe) FROM Feriado fe ";
	}

	@Override
	public String getSqlCount() {
		return "SELECT count(distinct fe.id) FROM Feriado fe ";
	}

	@Override
	public String getObjetoRetorno() {
		return "fe";
	}

	@Override
	public String getJoin() {
		return "";
	}
}