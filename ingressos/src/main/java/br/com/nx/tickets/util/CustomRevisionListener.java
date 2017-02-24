package br.com.nx.tickets.util;

import javax.faces.context.FacesContext;

import org.hibernate.envers.RevisionListener;

import br.com.nx.tickets.bean.LoginBean;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.entidade.util.Revisao;

public class CustomRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		Revisao rev = (Revisao) revisionEntity;
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			LoginBean login = context.getApplication().evaluateExpressionGet(
					context, "#{loginBean}", LoginBean.class);
			rev.setUsuario(login.getUsuarioLogado());
		} else {
			rev.setUsuario(new Usuario(1));
		}
	}
}