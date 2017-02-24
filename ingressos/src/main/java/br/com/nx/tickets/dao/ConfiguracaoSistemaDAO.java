package br.com.nx.tickets.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.util.ConfiguracaoSistema;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class ConfiguracaoSistemaDAO extends BaseDAO<ConfiguracaoSistema> {

	private static final long serialVersionUID = 1L;

	public ConfiguracaoSistemaDAO() {
		super(ConfiguracaoSistema.class);
	}

	public void removerPorDescricao(ConfiguracaoSistema configuracao) {
		try {
			getEm().createNativeQuery(
					"DELETE FROM configuracao_sistema WHERE descricao =:_descricao")
					.setParameter("_descricao", configuracao.getDescricao())
					.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public ConfiguracaoSistema consultarPorDescricao(String descricao) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT cs FROM ConfiguracaoSistema cs WHERE cs.descricao =:_descricao ORDER BY cs.descricao");
			TypedQuery<ConfiguracaoSistema> query = getEm().createQuery(
					sql.toString(), ConfiguracaoSistema.class).setParameter(
					"_descricao", descricao);
			return query.getSingleResult();
		} catch (Exception ee) {
			throw new BaseDAOException(ee.getMessage());
		}
	}

	@Override
	public void verificarDuplicidade(ConfiguracaoSistema configuracaoSistema)
			throws ViolacaoDeConstraintException {
		if (registroJaCadastrado("descricao",
				configuracaoSistema.getDescricao())
				|| configuracaoSistema.getDescricao().isEmpty()) {
			throw new ViolacaoDeConstraintException(
					"Descrição da configuração inválida!");
		}
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(
			Paginador<Paginavel> paginador, Filtravel permissaoUsuario) {
		return null;
	}

	public List<ConfiguracaoSistema> consultarPorFiltro(String descricao) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT cs FROM ConfiguracaoSistema cs ");
			sql.append(" WHERE descricao LIKE :_descricao ORDER BY cs.descricao");
			return getEm()
					.createQuery(sql.toString(), ConfiguracaoSistema.class)
					.setParameter("_descricao", "%" + descricao + "%")
					.getResultList();
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}
}
