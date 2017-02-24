package br.com.nx.tickets.bean;

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
import br.com.nx.tickets.entidade.Local;
import br.com.nx.tickets.entidade.Portaria;
import br.com.nx.tickets.servico.CidadeServico;
import br.com.nx.tickets.servico.EstadoServico;
import br.com.nx.tickets.servico.LocalServico;

@Named
@ViewScoped
public class LocalCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	@EJB
	private LocalServico localServico;
	@EJB
	private EstadoServico estadoServico;
	@EJB
	private CidadeServico cidadeServico;

	private Local local;
	private List<Portaria> portarias;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	private String ufEstadoSelecionado;
	private Integer idCidadeSelecionada;
	
	@PostConstruct
	public void inicializar() {
		try {
			if (local == null) {
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				String idParam = ctx.getRequestParameterMap().get("id");

				if (idParam != null && !idParam.equals("")) {
					try {
						local = localServico.obterPorId(Integer.parseInt(idParam));
					} catch (Exception e) {
						e.printStackTrace();
						adicionarError("Erro ao Buscar Local ");
					}
				}
				if (this.local == null) {
					this.local = new Local();
					this.local.setEndereco(new Endereco());
				}
			}
			estados = estadoServico.obterTodos("nome");
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	public String salvar() {
		try {
			this.local.getEndereco().setCidade(cidadeServico.obterPorId(this.idCidadeSelecionada));
			localServico.salvar(local);
			return "locais?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError("Erro ao Salvar Local.");
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

	public Local getLocal() {
		return local;
	}
	
	public List<Portaria> getPortarias() {
		return portarias;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public String getUfEstadoSelecionado() {
		return ufEstadoSelecionado;
	}
	
	public void setUfEstadoSelecionado(String ufEstadoSelecionado) {
		this.ufEstadoSelecionado = ufEstadoSelecionado;
	}
	
	public Integer getIdCidadeSelecionada() {
		return idCidadeSelecionada;
	}
	
	public void setIdCidadeSelecionada(Integer idCidadeSelecionada) {
		this.idCidadeSelecionada = idCidadeSelecionada;
	}
}