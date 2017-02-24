package br.com.nx.tickets.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.componente.Filtravel;
import br.com.nx.tickets.componente.Paginador;
import br.com.nx.tickets.componente.Paginavel;
import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.CidadeDAO;
import br.com.nx.tickets.entidade.Cidade;
import br.com.nx.tickets.entidade.Endereco;
import br.com.nx.tickets.entidade.Estado;


@Stateless
public class CidadeServico extends BaseServico<Cidade> {

	private static final long serialVersionUID = 1L;
	@Inject
	private CidadeDAO cidadeDao;
	@Inject
	private EstadoServico estadoServico;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(cidadeDao);
	}

	@Override
	public Cidade salvar(Cidade cidade) throws BaseServicoException {
		try {
			Estado estado = estadoServico.obterPorUF(cidade.getEstado().getUf());
			cidade.setEstado(estado);
			cidade = super.salvar(cidade);
			getLog().info("Cidade: " + cidade.getNome() + " " + cidade.getEstado().getUf() + " - " + cidade.getId());
			return cidade;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public Cidade obterPorEndereco(Endereco endereco) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorEndereco(endereco);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	@Override
	public Paginador<Paginavel> obterPorFiltro(Paginador<Paginavel> paginador, Filtravel filtravel)
			throws BaseServicoException {
		try {
			return cidadeDao.consultarPorFiltro(paginador, filtravel);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public Cidade obterPorNomeUF(String nome, String uf) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorNomeUf(nome, uf);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Cidade> obterPorNome(String nome) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorNome(nome);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Cidade> obterPorUf(String uf) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorUf(uf);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Cidade> obterPorEstado(Estado estado) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorEstado(estado);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Cidade> obterPorEstadoComSubcluster(Estado estado) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorEstadoComSubcluster(estado);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public List<Cidade> obterComSubcluster() throws BaseServicoException {
		try {
			return cidadeDao.consultarComSubcluster();
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}

	public Cidade obterPorIdComEstado(Integer id) throws BaseServicoException {
		try {
			return cidadeDao.consultarPorIdComEstado(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}
}