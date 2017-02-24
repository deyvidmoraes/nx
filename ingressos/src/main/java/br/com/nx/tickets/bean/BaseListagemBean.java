package br.com.nx.tickets.bean;

import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.FiltroPermissaoUsuario;
import br.com.nx.tickets.componente.Original;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.BuscavelPorPaginacao;
import br.com.nx.tickets.servico.BaseServicoException;
import br.com.nx.tickets.servico.UsuarioServico;

public abstract class BaseListagemBean extends BaseBean {

	protected static final long serialVersionUID = 1L;

	@Inject
	@Original
	private FiltroPermissaoUsuario filtroPermissaoUsuario;
	@EJB
	private UsuarioServico usuarioServico;

	private Filtravel filtravel;

	private Paginador<Paginavel> paginador;

	private BuscavelPorPaginacao buscavelPorPaginacao;

	public abstract void inicializar();

	public void montarEstruturaJsonGrafico() {

	}

	public void configurarPaginador(FiltroPermissaoUsuario filt, BuscavelPorPaginacao buscavel, Integer quantidadePorPaginal) {
		try {
			if (filt != null) {
				this.filtravel 	 = filt.getFiltravel();
			} else {
				this.filtravel 	  = filtroPermissaoUsuario.getFiltravel();
			}
			this.buscavelPorPaginacao = buscavel;
			this.paginador 			  = new Paginador<Paginavel>(quantidadePorPaginal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarRegistrosComPaginacao() throws BaseServicoException {
		try {
			info("INICIO BUSCANDO COM PAGINACAO: " + this.getClass().getSimpleName());
			if (paginador == null || buscavelPorPaginacao == null) {
				throw new IllegalArgumentException(
						"Depedências não injetadas corretamente!");
			}
			setupFiltro();
			paginador.setDadosDTO(null);
			paginador = buscavelPorPaginacao.obterPorFiltro(paginador, filtravel);
			if (paginador.getDadosDTO() != null) {
				info("INICIO MONTANDO JSON COM PAGINACAO: " + this.getClass().getSimpleName());
				// @pattern - Chamando metodo da subclasse
				montarEstruturaJsonGrafico();
				info("FIM MONTANDO JSON COM PAGINACAO: " + this.getClass().getSimpleName());
			}
			info("FIM BUSCANDO COM PAGINACAO: " + this.getClass().getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	private void setupFiltro() {
	}

	public void primeiraPagina() throws BaseServicoException {
		getPaginador().paginar(1);
		buscarRegistrosComPaginacao();
	}

	public void ultimaPagina() throws BaseServicoException {
		getPaginador().paginar(getPaginador().getTotalPaginas());
		buscarRegistrosComPaginacao();
	}

	public void paginar(Integer numeroPaginaDestino) throws BaseServicoException {
		getPaginador().paginar(numeroPaginaDestino);
		buscarRegistrosComPaginacao();
	}

	public Paginador<Paginavel> getPaginador() {
		return paginador;
	}

	protected Object recuperarParametro(String parametro) {
		ExternalContext ctx = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ctx.getRequestParameterMap().get(parametro);
	}

	@Override
	public Filtravel getFiltravel() {
		return super.getFiltravel();
	}

	public Filtravel getFiltravelNovo() {
		return filtravel;
	}

	public FiltroPermissaoUsuario getFiltroPermissaoUsuario() {
		return filtroPermissaoUsuario;
	}
}