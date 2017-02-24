package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.IngressoTipoDAO;
import br.com.nx.tickets.entidade.IngressoTipo;

@Stateless
public class IngressoTipoServico extends BaseServico<IngressoTipo> {

	private static final long serialVersionUID = 1L;
	@Inject
	private IngressoTipoDAO ingressoTipoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(ingressoTipoDao);
	}
}