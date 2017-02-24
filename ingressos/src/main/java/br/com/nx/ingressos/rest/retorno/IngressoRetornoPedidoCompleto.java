package br.com.nx.ingressos.rest.retorno;

import javax.xml.bind.annotation.XmlElement;

import br.com.nx.tickets.componente.ConfiguracaoApplication;
import br.com.nx.tickets.entidade.Pedido;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.rest.retorno.IngressoRetorno;

public class IngressoRetornoPedidoCompleto extends IngressoRetorno {
	
	@XmlElement(name = "usuario")
	private Usuario usuario;
	
	@XmlElement(name = "ponto_venda")
	private PontoVenda pontoVenda;
	
	@XmlElement(name = "pedido")
	private Pedido pedido;
//	
//	@XmlElement(name = "ingressos")
//	private List<Ingresso> ingressos;
	
	
	
	

	public IngressoRetornoPedidoCompleto(ConfiguracaoApplication configuracaoApplication) {
		//this.criptografiaInfo = configuracaoApplication.getCriptografiaInfo();
		//this.sistema = configuracaoApplication.getApplication();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PontoVenda getPontoVenda() {
		return pontoVenda;
	}

	public void setPontoVenda(PontoVenda pontoVenda) {
		this.pontoVenda = pontoVenda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
//
//	public List<Ingresso> getIngressos() {
//		return ingressos;
//	}
//
//	public void setIngressos(List<Ingresso> ingressos) {
//		this.ingressos = ingressos;
//	}
}
