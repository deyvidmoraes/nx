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
import javax.xml.bind.annotation.XmlElement;

import br.com.nx.tickets.entidade.util.EProgramacaoLinguagemImpressora;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.ETipoComunicacaoImpressora;
import br.com.nx.tickets.util.SistemaConstantes;

@Entity
@Table(name = "configuracao_impressora", schema = "public")
public class ConfiguracaoImpressora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlElement(name = "id")
	@SequenceGenerator(name = "configuracaoImpressoraSeq", sequenceName = "configuracao_impressora_id_seq", 
	allocationSize = 1, schema = "public")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "configuracaoImpressoraSeq")
	@Column(unique = true, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "printer_name", length = SistemaConstantes.CENTO_CINQUENTA, nullable = false)
	@XmlElement(name = "printer_name")
	private String printerName;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "communication_type")
	@XmlElement(name = "communication_type")
	private ETipoComunicacaoImpressora communicationType;

	@NotNull
	@Column(name = "dpi", length = SistemaConstantes.CINQUENTA, nullable = false)
	@XmlElement(name = "dpi")
	private String dpi;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "programming_language")
	@XmlElement(name = "programming_language")
	private EProgramacaoLinguagemImpressora programmingLanguage;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = SistemaConstantes.E_SITUACAO_DEFAULT_ATIVO)
	@XmlElement(name = "situacao")
	private ESituacao situacao = ESituacao.ATIVO;

	public ConfiguracaoImpressora() {
		super();
	}

	public ConfiguracaoImpressora(Integer id, String printerName, ETipoComunicacaoImpressora communicationType,
			String dpi, EProgramacaoLinguagemImpressora programmingLanguage, ESituacao situacao) {
		super();
		this.id = id;
		this.printerName = printerName;
		this.communicationType = communicationType;
		this.dpi = dpi;
		this.programmingLanguage = programmingLanguage;
		this.situacao = situacao;
	}

	public ETipoComunicacaoImpressora getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(ETipoComunicacaoImpressora communicationType) {
		this.communicationType = communicationType;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	public EProgramacaoLinguagemImpressora getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(EProgramacaoLinguagemImpressora programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
}