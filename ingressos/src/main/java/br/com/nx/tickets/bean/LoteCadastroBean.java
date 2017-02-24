package br.com.nx.tickets.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Evento;
import br.com.nx.tickets.entidade.IngressoTipo;
import br.com.nx.tickets.entidade.Lote;
import br.com.nx.tickets.entidade.LoteSituacao;
import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.servico.BaseServicoException;
import br.com.nx.tickets.servico.EventoServico;
import br.com.nx.tickets.servico.IngressoTipoServico;
import br.com.nx.tickets.servico.LoteServico;
import br.com.nx.tickets.servico.LoteSituacaoServico;

@Named
@ViewScoped
public class LoteCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	@EJB
	private LoteServico loteServico;
	@EJB
	private EventoServico eventoServico;
	@EJB
	private LoteSituacaoServico loteSituacaoServico;
	@EJB
	private IngressoTipoServico ingressoTipoServico;

	private Lote lote;

	private List<Evento> eventoLista;
	private List<LoteSituacao> loteSituacaoLista;
	private List<IngressoTipo> ingressoTipoLista;

	private Integer idEventoSelecionado;
	private Integer idLoteSituacaoSelecionado;
	private Integer idIngressoTipoSelecionado;

	private Date dataInicio;
	private Date dataFim;

	@PostConstruct
	public void inicializar() {
		try {
			if (lote == null) {
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				String idParam = ctx.getRequestParameterMap().get("id");

				if (idParam != null && !idParam.equals("")) {
					try {
						lote = loteServico.obterPorId(Integer.parseInt(idParam));
						dataInicio = lote.getDataInicio().getTime();
						dataFim = lote.getDataFim().getTime();
						idEventoSelecionado = lote.getEvento().getId();
						idIngressoTipoSelecionado = lote.getIngressoTipo().getId();
						idLoteSituacaoSelecionado = lote.getLoteSituacao().getId();
					} catch (Exception e) {
						e.printStackTrace();
						adicionarError("Erro ao Buscar Evento ");
					}
				}
				if (this.lote == null) {
					this.lote = new Lote();
					this.lote.setEvento(new Evento());
					this.lote.setIngressoTipo(new IngressoTipo());
					this.lote.setLoteSituacao(new LoteSituacao());
				}
			}
			loteSituacaoLista = loteSituacaoServico.obterTodos("descricao");
			ingressoTipoLista = ingressoTipoServico.obterTodos("descricao");
			eventoLista = eventoServico.obterTodos("descricao");
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	public String salvar() {
		try {

			if (this.idIngressoTipoSelecionado == 0) {
				adicionarInfo("Selecione o tipo de ingresso.");
			}

			if (this.idLoteSituacaoSelecionado == 0) {
				adicionarInfo("Selecione a situação do lote.");
			}

			if (this.idEventoSelecionado == 0) {
				adicionarInfo("Selecione o evento.");
			}

			lote.setIngressoTipo(ingressoTipoServico.obterPorId(this.idIngressoTipoSelecionado));
			lote.setLoteSituacao(loteSituacaoServico.obterPorId(this.idLoteSituacaoSelecionado));
			lote.setEvento(eventoServico.obterPorId(this.idEventoSelecionado));
			lote.setUsuario(getLoginBean().getUsuarioLogado());

			lote.setDataInicio(DataUtil.getCalendar(this.dataInicio));
			lote.setDataFim(DataUtil.getCalendar(this.dataFim));

			if (lote.getId() != null) {
				loteServico.alterar(lote);
			} else {
				loteServico.salvar(lote);
			}
			adicionarInfo("Lote salvo com Sucesso.");
			return "lotes?faces-redirect=true";
		} catch (BaseServicoException e) {
			e.printStackTrace();
			adicionarError("Erro ao salvar Lote.");
			return null;
		}
	}

	public Integer getIdEventoSelecionado() {
		return idEventoSelecionado;
	}

	public void setIdEventoSelecionado(Integer idEventoSelecionado) {
		this.idEventoSelecionado = idEventoSelecionado;
	}

	public Integer getIdLoteSituacaoSelecionado() {
		return idLoteSituacaoSelecionado;
	}

	public void setIdLoteSituacaoSelecionado(Integer idLoteSituacaoSelecionado) {
		this.idLoteSituacaoSelecionado = idLoteSituacaoSelecionado;
	}

	public Integer getIdIngressoTipoSelecionado() {
		return idIngressoTipoSelecionado;
	}

	public void setIdIngressoTipoSelecionado(Integer idIngressoTipoSelecionado) {
		this.idIngressoTipoSelecionado = idIngressoTipoSelecionado;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Evento> getEventoLista() {
		return eventoLista;
	}

	public void setEventoLista(List<Evento> eventoLista) {
		this.eventoLista = eventoLista;
	}

	public List<LoteSituacao> getLoteSituacaoLista() {
		return loteSituacaoLista;
	}

	public void setLoteSituacaoLista(List<LoteSituacao> loteSituacaoLista) {
		this.loteSituacaoLista = loteSituacaoLista;
	}

	public List<IngressoTipo> getIngressoTipoLista() {
		return ingressoTipoLista;
	}

	public void setIngressoTipoLista(List<IngressoTipo> ingressoTipoLista) {
		this.ingressoTipoLista = ingressoTipoLista;
	}
}
