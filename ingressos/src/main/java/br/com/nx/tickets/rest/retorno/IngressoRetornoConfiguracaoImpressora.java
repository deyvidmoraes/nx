package br.com.nx.tickets.rest.retorno;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import br.com.nx.tickets.entidade.ConfiguracaoImpressora;

public class IngressoRetornoConfiguracaoImpressora extends IngressoRetorno {
	
	@XmlElement(name = "configuracao_impressora")
	private List<ConfiguracaoImpressora> configuracaoImpressora;

	public IngressoRetornoConfiguracaoImpressora() {
		super();
	}

	public List<ConfiguracaoImpressora> getConfiguracaoImpressora() {
		return configuracaoImpressora;
	}

	public void setConfiguracaoImpressora(List<ConfiguracaoImpressora> configuracaoImpressora) {
		this.configuracaoImpressora = configuracaoImpressora;
	}
}