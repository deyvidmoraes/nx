package br.com.nx.tickets.componente;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.nx.tickets.bean.LoginBean;

@Original
public class FiltroPermissaoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoginBean loginBean;
	@Inject
	private transient Logger log;
	
	private Filtravel filtravelFiltro;
	private Filtravel filtravel;

	@PostConstruct
	public void prepararFiltro() {
		if (loginBean != null) {
			Filtravel ffn = new FiltroUsuarioPadrao(loginBean.getUsuarioLogado());
			filtravel 		= ffn;
			filtravelFiltro = ffn;
		}
	}

	protected void inicializarFiltro(Filtravel ffn) {
		this.filtravel 		 = ffn;
		this.filtravelFiltro = ffn;
		inicializarFiltro();
	}

	public void inicializarFiltro() {
		try {
//			if (getLoginBean().isPerfilAgenteAutorizado()) {
//				filtravel.setAgenteAutorizadoSelecionado(getLoginBean().getAgenteAutorizado().getId());
//			}
//			if (getLoginBean().isVendedor()) {
//				filtravel.setVendedorSelecionado(getLoginBean().getUsuarioLogado().getId());
//			}
//			carregarFiltrosPorUsuario();
//			exibirAgenteAutorizado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Filtravel getFiltravel() {
		return filtravel;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
}