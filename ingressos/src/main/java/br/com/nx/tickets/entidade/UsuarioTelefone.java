package br.com.nx.tickets.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.nx.tickets.entidade.util.EBoolean;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@Table(name = "usuario_telefone")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class UsuarioTelefone implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuarioTelefoneSeq", sequenceName = "usuario_telefone_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarioTelefoneSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "telefone", length = SistemaConstantes.ONZE, nullable = false)
	private String telefone;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_operadora_telefonia", nullable = false, 
	foreignKey = @ForeignKey(name = "fk_operadora_telefonia"))
	private OperadoraTelefonia operadoraTelefonia;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario"))
	private Usuario usuario;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "whatsapp", columnDefinition = SistemaConstantes.E_BOOLEAN_DEFAULT_FALSE)
	private EBoolean whatsapp = EBoolean.FALSE;
	
	@Column(name = "numero_ramal", length = SistemaConstantes.ONZE)
	private String numeroRamal;

	public UsuarioTelefone() {
	}
	
	public UsuarioTelefone(String telefone, String numeroRamal, OperadoraTelefonia operadora, Usuario usuario) {
		this.telefone = telefone;
		this.numeroRamal = numeroRamal;
		this.operadoraTelefonia = operadora;
		this.usuario = usuario;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public OperadoraTelefonia getOperadoraTelefonia() {
		return this.operadoraTelefonia;
	}

	public void setOperadoraTelefonia(OperadoraTelefonia operadoraTelefonia) {
		this.operadoraTelefonia = operadoraTelefonia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public EBoolean getWhatsapp() {
		return whatsapp;
	}
	
	public void setWhatsapp(EBoolean whathsapp) {
		this.whatsapp = whathsapp;
	}
	
	public String getNumeroRamal() {
		return numeroRamal;
	}

	public void setNumeroRamal(String numeroRamal) {
		this.numeroRamal = numeroRamal;
	}

	@Override
	protected UsuarioTelefone clone() throws CloneNotSupportedException {
		UsuarioTelefone cloned = (UsuarioTelefone) super.clone();
		cloned.setId(null);
		return cloned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((operadoraTelefonia == null) ? 0 : operadoraTelefonia
						.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		UsuarioTelefone other = (UsuarioTelefone) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (operadoraTelefonia == null) {
			if (other.operadoraTelefonia != null) {
				return false;
			}
		} else if (!operadoraTelefonia.equals(other.operadoraTelefonia)) {
			return false;
		}
		if (telefone == null) {
			if (other.telefone != null) {
				return false;
			}
		} else if (!telefone.equals(other.telefone)) {
			return false;
		}
		return true;
	}
	
}