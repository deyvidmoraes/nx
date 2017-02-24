package br.com.nx.tickets.bean;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.nx.tickets.componente.ConfiguracaoApplication;
import br.com.nx.tickets.componente.Filtravel;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private transient Logger log;
	@Inject
	private LoginBean loginBean;
	@Inject
	private ConfiguracaoApplication configuracaoApplication;
	
	private Filtravel filtravel;
	
	public BaseBean() {
	}
	
	private void adicionarMensagem(FacesMessage.Severity severity, String mensagem) {
		limparMensagens();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensagem, null));
	}

	public void limparMensagens() {
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	public void adicionarInfo(String mensagem) {
		if (loginBean != null && loginBean.getUsuarioLogado() != null) {
			info(mensagem);
		}
		this.adicionarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public void adicionarError(String mensagem) {
		if (loginBean != null && loginBean.getUsuarioLogado() != null) {
			error(mensagem);
		}
		this.adicionarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	public void adicionarWarn(String mensagem) {
		if (loginBean != null && loginBean.getUsuarioLogado() != null) {
			warn(mensagem);
		}
		this.adicionarMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	
	public ConfiguracaoApplication getConfiguracaoApplication() {
		return configuracaoApplication;
	}
	
	public void info(String mensagem) {
		if (log != null) {
			log.info(criaLogMensagem(mensagem));
		}
	}
	
	public void error(String mensagem) {
		if (log != null) {
			log.error(criaLogMensagem(mensagem));
		}
	}
	
	public void warn(String mensagem) {
		if (log != null) {
			log.warn(criaLogMensagem(mensagem));
		}
	}

	private String criaLogMensagem(String mensagem) {
		if (getLoginBean() != null) {
//			return Util.montar
		}
		return mensagem;
	}

	public Logger getLog() {
		return log;
	}
	
	public Filtravel getFiltravel() {
		return filtravel;
	}
	
	public void setFiltravel(Filtravel filtravel) {
		this.filtravel = filtravel;
	}
	
	public String irParaPagina(String pagina) {
		return pagina + "?faces-redirect=true";
	}
	
	public String paginaCorrente() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}
}
