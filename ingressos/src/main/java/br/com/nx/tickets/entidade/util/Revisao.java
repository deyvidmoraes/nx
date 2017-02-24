package br.com.nx.tickets.entidade.util;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.util.CustomRevisionListener;

@Entity
@RevisionEntity(CustomRevisionListener.class)
@Table(name = "revisao", schema = "revisao")
public class Revisao {

	@Id
	@SequenceGenerator(name = "revisaoSeq", sequenceName = "revisao_id_multi_seq", schema = "revisao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "revisaoSeq")
	@Column(unique = true, nullable = false)
	@RevisionNumber
	private Long id;
	@JoinColumn(name = "_usuario", foreignKey = @ForeignKey(name = "fk_usuario"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	@RevisionTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_revisao", nullable = false)
	private Date dataRevisao;

	public Long getId() {
		return id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Date getDataRevisao() {
		return dataRevisao;
	}
}