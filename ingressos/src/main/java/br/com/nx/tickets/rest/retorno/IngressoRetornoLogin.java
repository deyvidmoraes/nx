package br.com.nx.tickets.rest.retorno;

import javax.xml.bind.annotation.XmlElement;

import br.com.nx.tickets.componente.ConfiguracaoApplication;
import br.com.nx.tickets.entidade.Usuario;

public class IngressoRetornoLogin extends IngressoRetorno {
	
	@XmlElement(name = "usuario")
	private Usuario usuario;
	// @XmlElement(name = "sistema")
	// private ESistema sistema;
	// @XmlElement(name = "criptografia_info")
	// private CriptografiaInfo criptografiaInfo;
	// @XmlElement(name = "nivel_grupo")
	// private ENivelGrupo nivelGrupo;

	public IngressoRetornoLogin(ConfiguracaoApplication configuracaoApplication) {
		//this.criptografiaInfo = configuracaoApplication.getCriptografiaInfo();
		//this.sistema = configuracaoApplication.getApplication();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
