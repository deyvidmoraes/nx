package br.com.nx.tickets.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Atracao;
import br.com.nx.tickets.entidade.Cliente;
import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.EventoSituacao;
import br.com.nx.tickets.entidade.EventoTipo;
import br.com.nx.tickets.entidade.Local;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.servico.AtracaoServico;
import br.com.nx.tickets.servico.ClienteServico;
import br.com.nx.tickets.servico.EventoServico;
import br.com.nx.tickets.servico.EventoSituacaoServico;
import br.com.nx.tickets.servico.EventoTipoServico;
import br.com.nx.tickets.servico.LocalServico;
import br.com.nx.tickets.servico.PontoVendaServico;

@Named
@ViewScoped
public class EventoCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	@EJB
	private EventoServico eventoServico;
	@EJB
	private LocalServico localServico;
	@EJB
	private ClienteServico clienteServico;
	@EJB
	private EventoTipoServico eventoTipoServico;
	@EJB
	private EventoSituacaoServico eventoSituacaoServico;
	@EJB
	private PontoVendaServico pontoVendaServico;
	@EJB
	private AtracaoServico atracaoServico;

	private Evento evento;
	private Cliente cliente;
	private List<Local> locais;
	private List<Cliente> clientes;
	private List<EventoTipo> eventosTipos;
	private List<PontoVenda> pontosVendasDisponiveis;
	private List<PontoVenda> pontosVendasAssociados = new ArrayList<>();
	private List<Atracao> atracoesAssociadas = new ArrayList<>();
	private List<Atracao> atracoesDisponiveis;
	
	private Integer idClienteSelecionado;
	private Integer idEventoTipoSelecionado;
	private Integer idLocalSelecionado;
	private Integer idPontoVendaSelecionado;
	private Integer idAtracaoSelecionada;
	private String taxaAdministrativa;

	private Date dataInicioVendaIngresso;
	private Date dataAberturaPortao;
	private Date dataEvento;

	@PostConstruct
	public void inicializar() {
		try {
			if (evento == null) {
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				String idParam = ctx.getRequestParameterMap().get("id");

				if (idParam != null && !idParam.equals("")) {
					try {

					} catch (Exception e) {
						e.printStackTrace();
						adicionarError("Erro ao Buscar Evento ");
					}
				}
				if (this.evento == null) {
					this.evento = new Evento();
					this.evento.setLocal(new Local());
					this.evento.setEventoSituacao(new EventoSituacao());
					this.evento.setEventoTipo(new EventoTipo());
					this.evento.setCliente(new Cliente());
				}
			}
			clientes = clienteServico.obterTodos("nome");
			locais = localServico.obterTodos("descricao");
			eventosTipos = eventoTipoServico.obterTodos("descricao");
			atracoesDisponiveis = atracaoServico.obterTodos("nome");
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	public String salvar() {
		try {
			evento.setDataAberturaPortao(DataUtil.getCalendar(this.dataAberturaPortao));
			evento.setDataInicioVendaIngresso(DataUtil.getCalendar(this.dataInicioVendaIngresso));
			evento.setDataEvento(DataUtil.getCalendar(this.dataEvento));
			evento.setCliente(clienteServico.obterPorId(this.idClienteSelecionado));
			evento.setLocal(localServico.obterPorId(this.idLocalSelecionado));
			evento.setEventoTipo(eventoTipoServico.obterPorId(this.idEventoTipoSelecionado));
			evento.setEventoSituacao(eventoSituacaoServico.obterPorDescricao("ABERTO"));
			evento = eventoServico.salvar(evento);
			eventoServico.atualizarPontosVendas(evento, pontosVendasAssociados);
			eventoServico.atualizarAtracoes(evento, atracoesAssociadas);
			adicionarInfo("Evento salvo com sucesso.");
			return "eventos?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError("Erro ao Salvar Evento.");
			return null;
		}
	}

	public void obterPontosVendasPorCliente() {
		try {
			if (idClienteSelecionado != null && idClienteSelecionado > 0) {
				this.cliente = clienteServico.obterPorId(idClienteSelecionado);
				pontosVendasDisponiveis = clienteServico.obterPontosVendas(this.cliente);
			} else {
				pontosVendasDisponiveis.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionarPontoVenda() {
		try {
			if (this.idPontoVendaSelecionado != null) {
				pontosVendasAssociados.add(pontoVendaServico.obterPorId(this.idPontoVendaSelecionado));
				pontosVendasDisponiveis.removeAll(pontosVendasAssociados);
			}
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
	
	public void adicionarAtracao() {
		try {
			if (this.idAtracaoSelecionada != null) {
				atracoesAssociadas.add(atracaoServico.obterPorId(this.idAtracaoSelecionada));
				atracoesDisponiveis.removeAll(atracoesAssociadas);
			}
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<EventoTipo> getEventosTipos() {
		return eventosTipos;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Date getDataInicioVendaIngresso() {
		return dataInicioVendaIngresso;
	}

	public void setDataInicioVendaIngresso(Date dataInicioVendaIngresso) {
		this.dataInicioVendaIngresso = dataInicioVendaIngresso;
	}

	public Date getDataAberturaPortao() {
		return dataAberturaPortao;
	}

	public void setDataAberturaPortao(Date dataAberturaPortao) {
		this.dataAberturaPortao = dataAberturaPortao;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getTaxaAdministrativa() {
		return taxaAdministrativa;
	}

	public void setTaxaAdministrativa(String taxaAdministrativa) {
		this.taxaAdministrativa = taxaAdministrativa;
	}

	public Integer getIdClienteSelecionado() {
		return idClienteSelecionado;
	}

	public void setIdClienteSelecionado(Integer idClienteSelecionado) {
		this.idClienteSelecionado = idClienteSelecionado;
	}

	public Integer getIdEventoTipoSelecionado() {
		return idEventoTipoSelecionado;
	}

	public void setIdEventoTipoSelecionado(Integer idEventoTipoSelecionado) {
		this.idEventoTipoSelecionado = idEventoTipoSelecionado;
	}

	public Integer getIdLocalSelecionado() {
		return idLocalSelecionado;
	}

	public void setIdLocalSelecionado(Integer idLocalSelecionado) {
		this.idLocalSelecionado = idLocalSelecionado;
	}

	public List<PontoVenda> getPontosVendasDisponiveis() {
		return pontosVendasDisponiveis;
	}

	public List<PontoVenda> getPontosVendasAssociados() {
		return pontosVendasAssociados;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Integer getIdPontoVendaSelecionado() {
		return idPontoVendaSelecionado;
	}
	
	public void setIdPontoVendaSelecionado(Integer idPontoVendaSelecionado) {
		this.idPontoVendaSelecionado = idPontoVendaSelecionado;
	}
	
	public List<Atracao> getAtracoesAssociadas() {
		return atracoesAssociadas;
	}
	
	public List<Atracao> getAtracoesDisponiveis() {
		return atracoesDisponiveis;
	}
	
	public Integer getIdAtracaoSelecionada() {
		return idAtracaoSelecionada;
	}
	
	public void setIdAtracaoSelecionada(Integer idAtracaoSelecionada) {
		this.idAtracaoSelecionada = idAtracaoSelecionada;
	}
}