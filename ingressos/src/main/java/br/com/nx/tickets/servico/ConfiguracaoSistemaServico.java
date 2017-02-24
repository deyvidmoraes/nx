package br.com.nx.tickets.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.com.nx.tickets.componente.ConfiguracaoApplication;
import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.ConfiguracaoSistemaDAO;
import br.com.nx.tickets.entidade.util.ConfiguracaoSistema;
import br.com.nx.tickets.entidade.util.EBoolean;
import br.com.nx.tickets.entidade.util.ESituacao;

@Stateless
public class ConfiguracaoSistemaServico extends
		BaseServico<ConfiguracaoSistema> {

	private static final long serialVersionUID = 1L;
	@Inject
	private ConfiguracaoSistemaDAO configuracaoSistemaDao;
	@Inject
	private ConfiguracaoApplication configuracaoApplication;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(configuracaoSistemaDao);
	}

	@Override
	public ConfiguracaoSistema salvar(ConfiguracaoSistema t)
			throws BaseServicoException {
		try {
			ConfiguracaoSistema c = super.salvar(t);
			configuracaoApplication.carregarConfiguracaoSistema();
			return c;
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	@Override
	public ConfiguracaoSistema alterar(ConfiguracaoSistema t)
			throws BaseServicoException {
		try {
			ConfiguracaoSistema c = super.alterar(t);
			configuracaoApplication.carregarConfiguracaoSistema();
			return c;
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	@Override
	public ConfiguracaoSistema obterPorDescricao(String descricao)
			throws BaseServicoException, NoResultException {
		try {
			return configuracaoApplication.obterConfiguracaoSistema(descricao);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public EBoolean obterConfiguracaoSistemaBoolean(String configuracaoSistema)
			throws BaseServicoException {
		try {
			String valor = configuracaoApplication.obterConfiguracaoSistema(
					configuracaoSistema).getValor();
			return EBoolean.valueOf(valor.toUpperCase());
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public ESituacao obterConfiguracaoSistemaSituacao(String configuracaoSistema)
			throws BaseServicoException {
		try {
			String valor = configuracaoApplication.obterConfiguracaoSistema(
					configuracaoSistema).getValor();
			return ESituacao.valueOf(valor.toUpperCase());
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public void removerPorDescricao(ConfiguracaoSistema configuracaoSistema)
			throws BaseServicoException {
		try {
			configuracaoSistemaDao.removerPorDescricao(configuracaoSistema);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<ConfiguracaoSistema> obterTodosPorFiltro(String descricao)
			throws BaseServicoException {
		try {
			return configuracaoSistemaDao.consultarPorFiltro(descricao);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
}