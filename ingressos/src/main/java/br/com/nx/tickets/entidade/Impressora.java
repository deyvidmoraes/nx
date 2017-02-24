package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "impressora", schema = "public")
public class Impressora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "impressoraSeq", sequenceName = "impressora_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "impressoraSeq")
	@Column(unique = true, nullable = false)
	private Integer id;
	
}
