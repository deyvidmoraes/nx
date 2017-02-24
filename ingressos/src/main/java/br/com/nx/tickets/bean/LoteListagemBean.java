package br.com.nx.tickets.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
  
import br.com.nx.tickets.servico.LoteServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ViewScoped
public class LoteListagemBean  extends BaseListagemBean implements Modable {

	private static final long serialVersionUID = 1L;

	@EJB
	private LoteServico loteServico; 
	
	@Override
	@PostConstruct
	public void inicializar() {
		configurarPaginador(getFiltroPermissaoUsuario(), loteServico, SistemaConstantes.DEZ);
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

	@Override
	public void fechar() {
		// TODO Auto-generated method stub
		
	}
	
}
