package br.com.nx.tickets.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Cidade;
import br.com.nx.tickets.entidade.Endereco;
import br.com.nx.tickets.entidade.Estado;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.Segmento;
import br.com.nx.tickets.servico.CidadeServico;
import br.com.nx.tickets.servico.EstadoServico;
import br.com.nx.tickets.servico.PontoVendaServico;
import br.com.nx.tickets.servico.SegmentoServico;

@Named
@ViewScoped
public class PontoVendaCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private PontoVendaServico pontoVendaServico;
	@EJB
	private CidadeServico cidadeServico;
	@EJB
	private EstadoServico estadoServico;
	@EJB
	private SegmentoServico segmentoServico;

	private PontoVenda pontoVenda;
	private List<Cidade> cidades;
	private List<Estado> estados;
	private List<Segmento> segmentos;

	private Integer idCidadeSelecionada;
	private Integer idSegmentoSelecionado;
	private String ufEstadoSelecionado;

	private Date dataFundacao;
	private Date dataNascimento;

	@PostConstruct
	public void inicializar() {
		getLog().info("Criando Ponto Venda Cadastro Bean");
		try {
			if (pontoVenda == null) {
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				String idParam = ctx.getRequestParameterMap().get("id");
				if (idParam != null && !idParam.equals("")) {
					try {
						pontoVenda = pontoVendaServico.obterPorId(Integer.parseInt(idParam));
					} catch (Exception e) {
						e.printStackTrace();
						adicionarError("Erro ao Buscar Ponto de Venda ");
					}
				}
				if (this.pontoVenda == null) {
					this.pontoVenda = new PontoVenda();
					this.pontoVenda.setEndereco(new Endereco());
					this.pontoVenda.setSegmento(new Segmento());
				}
			}
			estados = estadoServico.obterTodos("nome");
			segmentos = segmentoServico.obterTodos("descricao");
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	public String salvar() {
		try {
			pontoVenda.getEndereco().setCidade(cidadeServico.obterPorId(this.idCidadeSelecionada));
			if (this.idSegmentoSelecionado != null) {
				pontoVenda.setSegmento(segmentoServico.obterPorId(this.idSegmentoSelecionado));
			}
			if (pontoVenda.getId() != null) {
				pontoVendaServico.alterar(pontoVenda);
				adicionarInfo("Alterado com Sucesso.");
			} else {
				pontoVendaServico.salvar(pontoVenda);
				adicionarInfo("Cadastrado com sucesso.");
			}
			return "pontosVendas?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
			return null;
		}
	}

	public void carregarCidades() {
		try {
			cidades = cidadeServico.obterPorUf(this.ufEstadoSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PontoVenda getPontoVenda() {
		return pontoVenda;
	}

	public void setPontoVenda(PontoVenda pontoVenda) {
		this.pontoVenda = pontoVenda;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public List<Segmento> getSegmentos() {
		return segmentos;
	}
	
	public void setSegmentos(List<Segmento> segmentos) {
		this.segmentos = segmentos;
	}

	public Integer getIdCidadeSelecionada() {
		return idCidadeSelecionada;
	}

	public void setIdCidadeSelecionada(Integer idCidadeSelecionada) {
		this.idCidadeSelecionada = idCidadeSelecionada;
	}
	
	public Integer getIdSegmentoSelecionado() {
		return idSegmentoSelecionado;
	}
	
	public void setIdSegmentoSelecionado(Integer idSegmentoSelecionado) {
		this.idSegmentoSelecionado = idSegmentoSelecionado;
	}

	public String getUfEstadoSelecionado() {
		return ufEstadoSelecionado;
	}

	public void setUfEstadoSelecionado(String ufEstadoSelecionado) {
		this.ufEstadoSelecionado = ufEstadoSelecionado;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
