package br.com.nx.tickets.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.nx.tickets.entidade.Nivel;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.entidade.UsuarioSituacao;
import br.com.nx.tickets.servico.BaseServicoException;
import br.com.nx.tickets.servico.NivelServico;
import br.com.nx.tickets.servico.UsuarioServico;
import br.com.nx.tickets.servico.UsuarioSituacaoServico;

@Named
@ViewScoped
public class UsuarioCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioServico usuarioServico;
	@EJB
	private NivelServico nivelServico;
	@EJB
	private UsuarioSituacaoServico usuarioSituacaoServico;

	private Usuario usuario;
	private List<Nivel> niveis;

	private Integer idNivelSelecionado;

	@PostConstruct
	public void inicializar() {
		try {
			if (usuario == null) {
				ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
				String idParam = ctx.getRequestParameterMap().get("id");

				if (idParam != null && !idParam.equals("")) {
					try {
						usuario = usuarioServico.obterPorId(Integer.parseInt(idParam));
					} catch (Exception e) {
						e.printStackTrace();
						adicionarError("Erro ao Buscar Usuario");
					}
				}
				if (this.usuario == null) {
					this.usuario = new Usuario();
					this.usuario.setNivel(new Nivel());
					this.usuario.setUltimaSituacao(new UsuarioSituacao());
				}
			}
			niveis = nivelServico.obterTodos("descricao");
		} catch (Exception e) {
			e.printStackTrace();
			adicionarError(e.getMessage());
		}
	}

	public String salvar() {
		try {
			usuario.setNivel(nivelServico.obterPorId(this.idNivelSelecionado));
			usuario.setUltimaSituacao(usuarioSituacaoServico.obterPorDescricao("CADASTRADO"));

			if (usuario.getId() != null) {
				usuarioServico.alterar(usuario);
			} else {
				usuarioServico.salvar(usuario);
			}
			adicionarInfo("Usuario Salvo com Sucesso.");
			return "usuarios?faces-redirect=true";
		} catch (BaseServicoException e) {
			e.printStackTrace();
			adicionarError("Erro ao Salvar Usuario.");
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Nivel> getNiveis() {
		return niveis;
	}

	public void setNiveis(List<Nivel> niveis) {
		this.niveis = niveis;
	}

	public Integer getIdNivelSelecionado() {
		return idNivelSelecionado;
	}

	public void setIdNivelSelecionado(Integer idNivelSelecionado) {
		this.idNivelSelecionado = idNivelSelecionado;
	}
}