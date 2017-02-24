package br.com.nx.tickets.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.EventoDAO;
import br.com.nx.tickets.entidade.Atracao;
import br.com.nx.tickets.entidade.Cliente;
import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.Ingresso;
import br.com.nx.tickets.entidade.PontoVenda;

@Stateless
public class EventoServico extends BaseServico<Evento> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoDAO eventoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(eventoDao);
	}

	public List<Evento> obterEventosPorCliente(Cliente cliente) throws BaseServicoException {
		try {
			return eventoDao.consultarEventosPorCliente(cliente);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
	
	public void atualizarPontosVendas(Evento evento, List<PontoVenda> pontosVendas) throws BaseServicoException {
		try {
			eventoDao.atualizarPontosVendas(evento, pontosVendas);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
	
	public void atualizarAtracoes(Evento evento, List<Atracao> atracoes) throws BaseServicoException {
		try {
			eventoDao.atualizarAtracoes(evento, atracoes);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<PontoVenda> obterPontosVendas(Evento evento) throws BaseServicoException {
		try {
			return eventoDao.consultarPontosVendas(evento);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}


	public List<Evento> obterEventosPorPontoVenda(PontoVenda pontoVenda) throws BaseServicoException {
		try {
			return eventoDao.consultarEventosPorPontoVenda(pontoVenda);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Atracao> obterAtracoes(Evento evento) throws BaseServicoException {
		try {
			return eventoDao.consultarAtracoes(evento);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Ingresso> obterPedidosPorEvento(Evento evento) throws BaseServicoException {
		try {
			return eventoDao.obterPedidosPorEvento(evento);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
}