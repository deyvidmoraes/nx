package br.com.nx.tickets.servico;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.OperadoraTelefoniaDAO;
import br.com.nx.tickets.entidade.OperadoraTelefonia;

@Stateless
public class OperadoraTelefoniaServico extends BaseServico<OperadoraTelefonia> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OperadoraTelefoniaDAO operadoraTelefoniaDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(operadoraTelefoniaDao);
	}
}