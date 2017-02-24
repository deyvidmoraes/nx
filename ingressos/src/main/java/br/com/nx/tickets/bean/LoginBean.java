package br.com.nx.tickets.bean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.nx.tickets.componente.Credencial;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.servico.UsuarioServico;

@Named
@SessionScoped
public class LoginBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credencial credencial;
	@EJB
	private UsuarioServico usuarioServico;
	private Usuario usuarioLogado;
	
	public String fazerLogin() {
		try {
			usuarioLogado = usuarioServico.logar(credencial);
			if (usuarioLogado != null) {
				return "home?faces-redirect=true";
			}
			adicionarError("Nao foi posssivel realizar o login!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
}
