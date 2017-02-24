package br.com.nx.ingressos.rest.retorno;

import javax.xml.bind.annotation.XmlElement;

import br.com.nx.tickets.componente.ConfiguracaoApplication;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.rest.retorno.IngressoRetorno;

public class IngressoRetornoPacote extends IngressoRetorno {
	
	@XmlElement(name = "usuario")
	private Usuario usuario;
	@XmlElement(name = "ponto_venda")
	private PontoVenda pontoVenda;

	public IngressoRetornoPacote(ConfiguracaoApplication configuracaoApplication) {
		//this.criptografiaInfo = configuracaoApplication.getCriptografiaInfo();
		//this.sistema = configuracaoApplication.getApplication();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PontoVenda getPontoVenda() {
		return pontoVenda;
	}

	public void setPontoVenda(PontoVenda pontoVenda) {
		this.pontoVenda = pontoVenda;
	}
}
