package br.com.nx.tickets.rest.envio;

import javax.xml.bind.annotation.XmlElement;

public class IngressoEnvioLogin extends IngressoEnvio {

	@XmlElement(name = "login")
	private String login;
	@XmlElement(name = "senha")
	private String senha;

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	@Override
	public String toString() {
		return "ImpressoraEnvioLogin [login=" + login + ", senha=" + senha + "]";
	}
}