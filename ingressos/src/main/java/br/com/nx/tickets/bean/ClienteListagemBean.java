package br.com.nx.tickets.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Cliente;
import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.servico.ClienteServico;
import br.com.nx.tickets.servico.EventoServico;
import br.com.nx.tickets.servico.PontoVendaServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ViewScoped
public class ClienteListagemBean extends BaseListagemBean implements Modable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ClienteServico clienteServico;
	@EJB
	private EventoServico eventoServico;
	@EJB
	private PontoVendaServico pontoVendaServico;
	
	private Cliente cliente;
	private List<PontoVenda> pontosVendasAssociados;
	private List<PontoVenda> pontosVendasDisponiveis;
	private List<Evento> eventos;
	
	private boolean exibirModalPontoVenda;
	private boolean exibirModalEvento;

	@Override
	@PostConstruct
	public void inicializar() {
		configurarPaginador(getFiltroPermissaoUsuario(), clienteServico, SistemaConstantes.DEZ);
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
	
	public void obterPontosVendasPorCliente(Cliente cliente) {
		try {
			this.cliente = cliente;
			exibirModalPontoVenda = !exibirModalPontoVenda;
			pontosVendasDisponiveis = pontoVendaServico.obterTodos();
			
			pontosVendasAssociados = clienteServico.obterPontosVendas(this.cliente);
			
			pontosVendasDisponiveis.removeAll(pontosVendasAssociados);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void obterEventosPorCliente(Cliente cliente) {
		try {
			this.cliente = cliente;
			exibirModalEvento = !exibirModalEvento;
			eventos = eventoServico.obterEventosPorCliente(this.cliente);
			for (Evento evento : eventos) {
				getLog().info("EVENTOS: " + evento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarPontoVenda(PontoVenda pv) {
		try {
			pontosVendasAssociados.add(pv);
			pontosVendasDisponiveis.remove(pv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removerPontoVenda(PontoVenda pv) {
		try {
			pontosVendasAssociados.remove(pv);
			pontosVendasDisponiveis.add(pv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvarPontoVendaAssociado() {
		try {
			getLog().info("CLIENTE: " + cliente.getId());
			clienteServico.atualizarPontosVendas(this.cliente, this.pontosVendasAssociados);
			fechar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void fechar() {
		exibirModalPontoVenda = false;
		exibirModalEvento = false;
	}
	
	public boolean isExibirModalPontoVenda() {
		return exibirModalPontoVenda;
	}

	public boolean isExibirModalEvento() {
		return exibirModalEvento;
	}
	
	public List<PontoVenda> getPontosVendasAssociados() {
		return pontosVendasAssociados;
	}
	
	public List<PontoVenda> getPontosVendasDisponiveis() {
		return pontosVendasDisponiveis;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}