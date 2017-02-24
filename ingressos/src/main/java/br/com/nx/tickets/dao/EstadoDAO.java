package br.com.nx.tickets.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.Estado;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class EstadoDAO extends BaseDAO<Estado> {

	private static final long serialVersionUID = 1L;

	public EstadoDAO() {
		super(Estado.class);
	}

	@Override
	public void verificarDuplicidade(Estado estado)
			throws ViolacaoDeConstraintException {
		if (registroJaCadastrado("nome", estado.getNome())) {
			throw new ViolacaoDeConstraintException(
					"Dados da estado inválidos!");
		}
	}

	public Estado buscarPorUF(String uf) {
		try {
			Query query = getEm().createQuery(
					"SELECT e FROM Estado e WHERE e.uf =:_uf").setParameter(
					"_uf", uf);
			query.setHint("org.hibernate.cacheable", true);
			return (Estado) query.getSingleResult();
		} catch (NoResultException nr) {
			getLog().info("Registro nao encontrado: " + uf);
			nr.printStackTrace();
			throw new BaseDAOException(nr.getMessage());
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
		// TODO Auto-generated method stub
		return null;
	}
}