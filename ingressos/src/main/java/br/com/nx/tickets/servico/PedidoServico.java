package br.com.nx.tickets.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nx.tickets.dao.BaseDAOException;
import br.com.nx.tickets.dao.IngressoDAO;
import br.com.nx.tickets.dao.IngressoSituacaoDAO;
import br.com.nx.tickets.dao.PedidoDAO;
import br.com.nx.tickets.entidade.Ingresso;
import br.com.nx.tickets.entidade.IngressoSituacao;
import br.com.nx.tickets.entidade.Pedido;
import br.com.nx.tickets.entidade.util.ValueGenerator;

@Stateless
public class PedidoServico extends BaseServico<Pedido> {

	private static final long serialVersionUID = 1L;
	@Inject
	private PedidoDAO pedidoDao;
	@Inject
	private IngressoDAO ingressoDao;
	@Inject
	private IngressoSituacaoDAO ingressoSituacaoDao;

	@Override
	@PostConstruct
	public void inicializar() {
		setDao(pedidoDao);
	}
	
	public Pedido salvar(Pedido pedido, List<Ingresso> ingressos) throws BaseServicoException {
		try {
			IngressoSituacao is = ingressoSituacaoDao.consultarPorDescricao("AGUARDANDO VALIDACAO");
			pedido.setQuantidadeIngresso(ingressos.size());
			pedido = super.salvar(pedido);
			for (Ingresso ingresso : ingressos) {
				ingresso.setCodigo(ValueGenerator.numeroAleatorio(9).toString());
				ingresso.setPedido(pedido);
				ingresso.setIngressoSituacao(is);
				ingressoDao.salvar(ingresso);
			}
			return pedido;
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}
	}
	
	
	public Pedido obterPedidoCompletoPorId(Long id) throws BaseServicoException {
		try {
			return pedidoDao.consultarCompletoPorId(id);
		} catch (BaseDAOException e) {
			throw new BaseServicoException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseServicoException(e.getMessage());
		}
	}
}