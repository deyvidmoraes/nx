package br.com.nx.tickets.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.stat.Statistics;

import br.com.nx.tickets.entidade.util.Descritivel;
import br.com.nx.tickets.entidade.util.Identificavel;
import br.com.nx.tickets.entidade.util.PersistenceUnitConfig;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

@Local(InterfaceDAO.class)
public abstract class BaseDAO<T> implements InterfaceDAO<T> {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(name = PersistenceUnitConfig.NAME)
	private EntityManager entityManager;
	@PersistenceContext(name = PersistenceUnitConfig.NAME, type = PersistenceContextType.EXTENDED)
	private EntityManager extended;
	private Class<T> classe;
	@Inject
	private transient Logger log;

	private static Statistics statistics;

	public BaseDAO(Class<T> classe) {
		this.classe = classe;
	}

	protected StatelessSession getOpenStatelesSession() {
		return entityManager.unwrap(Session.class).getSessionFactory()
				.openStatelessSession();
	}

	public void getStatistics() {
		if (statistics == null) {
			statistics = entityManager.unwrap(Session.class).getSessionFactory().getStatistics();
			statistics.setStatisticsEnabled(true);
		}
	}
	
	@Override
	public T salvar(T t) {
		try {
			if (t instanceof Descritivel) {
				Descritivel desc = (Descritivel) t;
				if (desc.getDescricao() == null || desc.getDescricao().isEmpty()) {
					throw new BaseDAOException("Descrição não pode ser vazia!");
				}
			}
			if (t instanceof Identificavel) {
				if (((Identificavel) t).getId() != null) {
					return entityManager.merge(t);
				} 
			}
			verificarDuplicidade(t);
			entityManager.persist(t);
			return t;
		} catch (ViolacaoDeConstraintException vdc) {
			log.error(vdc.getMessage());
			throw new BaseDAOException(vdc.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BaseDAOException(ex.getMessage());
		}
	}

	@Override
	public T alterar(T t) {
		try {
			return entityManager.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public void deletar(T t, Integer id) {
		try {
			entityManager.remove(entityManager.getReference(t.getClass(), id));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> consultarTodos() {
		try {
			Query query = entityManager.createQuery("SELECT obj FROM ".concat(
					this.classe.getSimpleName()).concat(" obj"));
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> consultarTodos(String orderBy) {
		try {
			Query query = entityManager.createQuery("SELECT obj FROM "
					.concat(this.classe.getSimpleName()).concat(" obj ")
					.concat("ORDER BY obj.").concat(orderBy));
			query.setHint("org.hibernate.cacheable", true);
			List<T> list = query.getResultList();
			Collections.unmodifiableCollection(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	public void alterarSituacao(SituacaoAlteravel t) {
		try {
			t.alterarSituacao();
			entityManager.persist(t);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public int consultarQuantidade() {
		try {
			return ((Long) entityManager.createQuery(
					"SELECT count(obj.id) FROM ".concat(
							this.classe.getSimpleName()).concat(" obj"))
					.getSingleResult()).intValue();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	public T consultarPorId(int id) {
		try {
			return entityManager.find(classe, id);
		} catch (NoResultException e) {
			throw new BaseDAOException(classe.getSimpleName() + " não encontrado(a)!");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BaseDAOException(e.getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K extends Descritivel> T consultarPorDescricao(String descricao) {
		try {
			return (T) getEm().createQuery("SELECT obj FROM " + this.classe.getSimpleName() + " obj WHERE obj.descricao =:_descricao")
					.setParameter("_descricao", descricao).getSingleResult();
		} catch (NoResultException e) {
			throw new BaseDAOException("Não encontrado: " + descricao);
		} catch (Exception ee) {
			log.error(ee.getMessage());
			throw new BaseDAOException(ee.getMessage());
		}
	}

	public boolean registroJaCadastrado(String atributo, Object valor) {
		try {
			if (valor == null) {
				return true;
			}
			getEm().createQuery(
					"SELECT obj FROM " + this.classe.getSimpleName()
							+ " obj WHERE obj." + atributo + " =:_valor")
					.setParameter("_valor", valor).getSingleResult();
			return true;
		} catch (NoResultException e) {
			log.info("Não há duplicidade!");
			return false;
		}
	}

	protected EntityManager getEm() {
		return entityManager;
	}
	
	protected EntityManager getExtended() {
		extended.setFlushMode(FlushModeType.COMMIT);
		return extended;
	}

	protected Logger getLog() {
		return log;
	}
}
