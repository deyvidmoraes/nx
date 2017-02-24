package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.ArquivoTipoDAO;
import br.com.nx.tickets.entidade.ArquivoTipo;

@Stateless
public class ArquivoTipoServico extends BaseServico<ArquivoTipo> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ArquivoTipoDAO arquivoTipoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(arquivoTipoDao);
	}
}