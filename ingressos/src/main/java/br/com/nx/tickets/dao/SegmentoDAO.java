package br.com.nx.tickets.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.entidade.Segmento;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;


public class SegmentoDAO extends BaseDAO<Segmento> {

	private static final long serialVersionUID = 1L;
	
	public SegmentoDAO() {
		super(Segmento.class);
	}

	@Override
	public Segmento salvar(Segmento s) {
		if (s.getId() == null) {
			return super.salvar(s);
		} else {
			return super.alterar(s);
		}
	}

	@Override
	public void verificarDuplicidade(Segmento s)
			throws ViolacaoDeConstraintException {
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}
	
//	@Override
//	public List<Segmento> consultarPorFiltroHierarquia(FiltravelNovo filtravel) {
//		try {
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new BaseDAOException(e.getMessage());
//		}
//	}
	
	@Override
	public Segmento consultarPorDescricao(String descricao) {
		try {
			TypedQuery<Segmento> query = getEm()
					.createQuery("SELECT s FROM Segmento s WHERE s.descricao =:_descricao", Segmento.class)
					.setParameter("_descricao", descricao);
			query.setHint("org.hibernate.cacheable", true);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
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
}