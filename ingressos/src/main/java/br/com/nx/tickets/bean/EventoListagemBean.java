package br.com.nx.tickets.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Atracao;
import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.servico.ClienteServico;
import br.com.nx.tickets.servico.EventoServico;
import br.com.nx.tickets.servico.PontoVendaServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ViewScoped
public class EventoListagemBean extends BaseListagemBean implements Modable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private EventoServico eventoServico;
	@EJB
	private PontoVendaServico pontoVendaServico;
	@EJB
	private ClienteServico clienteServico;
	
	private Evento evento;
	private List<PontoVenda> pontosVendasAssociados;
	private List<PontoVenda> pontosVendasDisponiveis;
	private List<Atracao> atracoesAssociadas;
	private List<Atracao> atracoesDisponiveis;
	
	private boolean exibirModalPontoVenda;
	private boolean exibirModalEvento;
	private boolean exibirModalAtracao;

	@Override
	@PostConstruct
	public void inicializar() {
		configurarPaginador(getFiltroPermissaoUsuario(), eventoServico, SistemaConstantes.DEZ);
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
	
	public void obterPontosVendasPorEvento(Evento evento) {
		try {
			this.evento = evento;
			exibirModalPontoVenda = !exibirModalPontoVenda;
			pontosVendasDisponiveis = pontoVendaServico.obterTodos();
			
			pontosVendasAssociados = eventoServico.obterPontosVendas(this.evento);
			
			pontosVendasDisponiveis.removeAll(pontosVendasAssociados);
			for (PontoVenda pontoVenda: pontosVendasAssociados) {
				getLog().info("PONTOS DE VENDA ASSOCIADOS: " + pontoVenda);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void obterAtracoesPorEvento(Evento evento) {
		try {
			this.evento = evento;
			exibirModalAtracao = !exibirModalAtracao;
//			atracoesDisponiveis = atracaoServico.obterTodos();
			
			atracoesAssociadas = eventoServico.obterAtracoes(this.evento);
			
			atracoesDisponiveis.removeAll(atracoesAssociadas);
			for (Atracao atracao : atracoesAssociadas) {
				getLog().info("ATRAÇÕES ASSOCIADAS: " + atracao);
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
			getLog().info("EVENTO: " + evento.getId());
			eventoServico.atualizarPontosVendas(this.evento, this.pontosVendasAssociados);
			fechar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarAtracao(Atracao atracao) {
		try {
			atracoesAssociadas.add(atracao);
			atracoesDisponiveis.remove(atracao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removerAtracao(Atracao atracao) {
		try {
			atracoesAssociadas.remove(atracao);
			atracoesDisponiveis.add(atracao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvarAtracaoAssociado() {
		try {
			getLog().info("EVENTO: " + evento.getId());
			eventoServico.atualizarAtracoes(this.evento, this.atracoesAssociadas);
			fechar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exibirDetalhesEvento(Evento evento) {
		exibirModalEvento = true;
		this.evento = evento;
	}
	
	@Override
	public void fechar() {
		exibirModalEvento = false;
		exibirModalPontoVenda = false;
		exibirModalAtracao = false;
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

	public Evento getEvento() {
		return evento;
	}
	
	public List<Atracao> getAtracoesAssociadas() {
		return atracoesAssociadas;
	}
	
	public List<Atracao> getAtracoesDisponiveis() {
		return atracoesDisponiveis;
	}
	
	public boolean isExibirModalAtracao() {
		return exibirModalAtracao;
	}
}
