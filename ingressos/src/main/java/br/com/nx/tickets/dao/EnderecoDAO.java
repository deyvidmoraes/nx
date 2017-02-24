package br.com.nx.tickets.dao;

import java.util.List;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.Endereco;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class EnderecoDAO extends BaseDAO<Endereco> {

	private static final long serialVersionUID = 1L;

	public EnderecoDAO() {
		super(Endereco.class);
	}

	@Override
	public void verificarDuplicidade(Endereco endereco)
			throws ViolacaoDeConstraintException {
	}

	public Endereco obterCompletoPorId(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ender FROM Endereco ender ");
			sql.append("JOIN FETCH ender.cidade cid ");
			sql.append("JOIN FETCH cid.estado est ");
			sql.append("WHERE ender.id =:_id");

			return getEm().createQuery(sql.toString(), Endereco.class)
					.setParameter("_id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(
			Paginador<Paginavel> paginador, Filtravel permissaoUsuario) {
		return null;
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}
}