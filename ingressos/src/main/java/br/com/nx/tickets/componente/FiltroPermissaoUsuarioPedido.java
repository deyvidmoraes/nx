package br.com.nx.tickets.componente;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FiltroPermissaoUsuarioPedido extends FiltroPermissaoUsuario {

	private static final long serialVersionUID = 1L;
	private FiltroUsuarioPedido filtravel;

	@PostConstruct
	public void prepararFiltro() {
		filtravel = new FiltroUsuarioPedido(getLoginBean().getUsuarioLogado());
		inicializarFiltro(filtravel);
	}
	
	public FiltroUsuarioPedido getFiltravel() {
		return filtravel;
	}
}
