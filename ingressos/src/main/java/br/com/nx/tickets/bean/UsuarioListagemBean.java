package br.com.nx.tickets.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.servico.UsuarioServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ViewScoped
public class UsuarioListagemBean extends BaseListagemBean {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioServico usuarioServico;

	@Override
	@PostConstruct
	public void inicializar() {
		configurarPaginador(getFiltroPermissaoUsuario(), usuarioServico, SistemaConstantes.DEZ);
		buscarRegistros();
	}

	public void buscarRegistros() {
		try {
			paginar(1);
			buscarRegistrosComPaginacao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
