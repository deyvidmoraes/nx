package br.com.nx.tickets.rest.retorno;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class IngressoRetorno implements IngressoRetornavel {

	@XmlElement(name = "mensagem")
	private String mensagem;
	@XmlElement(name = "codigo_retorno")
	private String codigoRetorno = "1";
	@XmlElement(name = "extras")
	private Map<String, Object> extras;
	
	public IngressoRetorno() {
		
	}
	
	public IngressoRetorno(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public Map<String, Object> getExtras() {
		return extras;
	}
	
	public void putExtra(String key, Object value) {
		if (extras == null) {
			extras = new HashMap<String, Object>();
		}
		extras.put(key, value);
	}
}
