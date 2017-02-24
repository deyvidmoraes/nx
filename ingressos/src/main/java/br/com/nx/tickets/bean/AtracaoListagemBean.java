package br.com.nx.tickets.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.servico.AtracaoServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ViewScoped
public class AtracaoListagemBean extends BaseListagemBean {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private AtracaoServico atracaoServico;

	@Override
	@PostConstruct
	public void inicializar() {
		configurarPaginador(getFiltroPermissaoUsuario(), atracaoServico, SistemaConstantes.DEZ);
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
