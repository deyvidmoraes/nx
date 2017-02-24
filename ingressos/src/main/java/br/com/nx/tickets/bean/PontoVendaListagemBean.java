package br.com.nx.tickets.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.servico.EventoServico;
import br.com.nx.tickets.servico.PontoVendaServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ViewScoped
public class PontoVendaListagemBean extends BaseListagemBean implements Modable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PontoVendaServico pontoVendaServico;
	@EJB
	private EventoServico eventoServico;

	private PontoVenda pontoVenda;
	private Evento evento;
	private List<Evento> eventosDisponiveis;
	private List<Evento> eventosAssociados;
	
	private boolean exibirModalEvento;

	@Override
	@PostConstruct
	public void inicializar() {
		configurarPaginador(getFiltroPermissaoUsuario(), pontoVendaServico, SistemaConstantes.DEZ);
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
	
	public void obterEventosPorPontoVenda(PontoVenda pv) {
		try {
			this.pontoVenda = pv;
			exibirModalEvento = !exibirModalEvento;
			eventosDisponiveis = eventoServico.obterTodos();
			
			eventosAssociados = eventoServico.obterEventosPorPontoVenda(this.pontoVenda);
			
			eventosDisponiveis.removeAll(eventosAssociados);
			for (Evento evento : eventosAssociados) {
				getLog().info("EVENTOS ASSOCIADOS: " + evento);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarEventosAssociados() {
		try {
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionarEvento() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerEvento() {
		
	}

	@Override
	public void fechar() {
		exibirModalEvento = false;
	}


	public PontoVenda getPontoVenda() {
		return pontoVenda;
	}

	public Evento getEvento() {
		return evento;
	}

	public List<Evento> getEventosAssociados() {
		return eventosAssociados;
	}

	public List<Evento> getEventosDisponiveis() {
		return eventosDisponiveis;
	}
	
	public boolean isExibirModalEvento() {
		return exibirModalEvento;
	}
}
