package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.ArquivoDAO;
import br.com.nx.tickets.entidade.Arquivo;

@Stateless
public class ArquivoServico extends BaseServico<Arquivo> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ArquivoDAO arquivoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(arquivoDao);
	}
}