package br.com.nx.tickets.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.Cidade;
import br.com.nx.tickets.entidade.Endereco;
import br.com.nx.tickets.entidade.Estado;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;



public class CidadeDAO extends BaseDAO<Cidade> {

	private static final long serialVersionUID = 1L;

	public CidadeDAO() {
		super(Cidade.class);
	}

	@Override
	public void verificarDuplicidade(Cidade cidade) throws ViolacaoDeConstraintException {
	}

	@Override
	public Cidade salvar(Cidade t) {
		try {
			Cidade cidade = super.salvar(t);
			getEm().flush();
			getEm().clear();
			return cidade;
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public Cidade consultarPorId(int id) {
		try {
			Cidade cidade = getEm().find(Cidade.class, id);
			return cidade;
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}

	public Cidade consultarPorEndereco(Endereco endereco) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c From Endereco ender JOIN ender.cidade c ");
			sql.append("WHERE ender =:_endereco");

			return getEm().createQuery(sql.toString(), Cidade.class).setParameter("_endereco", endereco)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public Cidade consultarPorNomeUf(String nome, String uf) {
		try {

			if ("NA".equals(uf)) {
				return consultarCidadeSemUF(nome);
			}

			TypedQuery<Cidade> query = getEm()
					.createQuery("SELECT c FROM Cidade c JOIN FETCH c.estado e LEFT JOIN FETCH c.subcluster sc "
							+ "WHERE c.nome = :_nome and e.uf = :_uf", Cidade.class)
					.setParameter("_nome", nome).setParameter("_uf", uf).setMaxResults(1);
			query.setHint("org.hibernate.cacheable", true);
			return query.getSingleResult();
		} catch (NoResultException e) {
			getLog().error("Não encontrado: [" + nome + "] [" + uf + "]");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			getLog().error("Não encontrado: [" + nome + "] [" + uf + "]");
			throw new BaseDAOException(e.getMessage());
		}
	}

	private Cidade consultarCidadeSemUF(String nome) {
		try {
			TypedQuery<Cidade> query = getEm().createQuery("SELECT c FROM Cidade c " + "JOIN FETCH c.estado e "
					+ "LEFT JOIN FETCH c.subcluster sc " + "WHERE c.nome = :_nome ORDER BY c.nome, e.uf", Cidade.class)
					.setParameter("_nome", nome).setMaxResults(1);
			return query.getSingleResult();
		} catch (NoResultException e) {
			getLog().error("Não encontrado: [" + nome + "]");
			return null;
		} catch (Exception e) {
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Cidade> consultarPorUf(String uf) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Cidade c ");
			sql.append("WHERE c.estado.uf = :_uf ORDER BY c.nome");

			TypedQuery<Cidade> query = getEm().createQuery(sql.toString(), Cidade.class).setParameter("_uf", uf);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Cidade> consultarPorEstado(Estado estado) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT new br.com.nx.multisales.entidade.Cidade(c.id, c.nome, e.uf, e.nome) FROM Cidade c "
					+ "JOIN c.estado e WHERE c.estado = :_estado ORDER BY c.nome");
			TypedQuery<Cidade> query = getEm().createQuery(sql.toString(), Cidade.class).setParameter("_estado",
					estado);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Cidade> consultarPorEstadoComSubcluster(Estado estado) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Cidade c LEFT JOIN FETCH c.subcluster sc "
					+ "WHERE c.estado = :_estado ORDER BY c.nome");
			TypedQuery<Cidade> query = getEm().createQuery(sql.toString(), Cidade.class).setParameter("_estado",
					estado);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Cidade> consultarComSubcluster() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Cidade c JOIN FETCH c.subcluster sc ORDER BY c.nome");
			TypedQuery<Cidade> query = getEm().createQuery(sql.toString(), Cidade.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Cidade> consultarPorNome(String nome) {
		try {
			TypedQuery<Cidade> query = getEm()
					.createQuery("SELECT c FROM Cidade c WHERE c.nome = :_nome ", Cidade.class)
					.setParameter("_nome", nome);
			query.setHint("org.hibernate.cacheable", true);
			return query.getResultList();
		} catch (NoResultException e) {
			getLog().error("Não encontrado: [" + nome + "] ");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public Cidade consultarPorIdComEstado(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Cidade c ");
			sql.append("JOIN FETCH c.estado e ");
			sql.append("WHERE c.id =:_id");

			Cidade c = getEm().createQuery(sql.toString(), Cidade.class).setParameter("_id", id).getSingleResult();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public List<Cidade> consultarCidadesVigentesSimuladorPorFiltroNome(String filtro) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c FROM Cidade c ");
			sql.append("JOIN c.planilhaGrupoCidade pgc ");
			sql.append("JOIN pgc.planilhaGrupo pg ");
			sql.append("JOIN pg.planilha pla ");
			sql.append("WHERE :_dataAtual BETWEEN pla.dataInicio and pla.dataFim ");
			sql.append("AND c.nome LIKE :_filtro ");

			return getEm().createQuery(sql.toString(), Cidade.class).setParameter("_dataAtual", Calendar.getInstance())
					.setParameter("_filtro", "%" + filtro + "%").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		// TODO Auto-generated method stub
		return null;
	}

}