package br.com.nx.tickets.rest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.nx.ingressos.rest.envio.IngressoEnvioPacote;
import br.com.nx.ingressos.rest.retorno.IngressoRetornoPacote;
import br.com.nx.ingressos.rest.retorno.IngressoRetornoPedidoCompleto;
import br.com.nx.tickets.componente.ConfiguracaoApplication;
import br.com.nx.tickets.componente.Credencial;
import br.com.nx.tickets.entidade.Ingresso;
import br.com.nx.tickets.entidade.Pedido;
import br.com.nx.tickets.entidade.PontoVenda;
import br.com.nx.tickets.entidade.Usuario;
import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.entidade.util.EFormatoData;
import br.com.nx.tickets.rest.envio.IngressoEnvioLogin;
import br.com.nx.tickets.rest.envio.IngressoEnvioObterConfiguracao;
import br.com.nx.tickets.rest.envio.IngressoEnvioVerificarConexao;
import br.com.nx.tickets.rest.retorno.IngressoRetornoConfiguracaoImpressora;
import br.com.nx.tickets.rest.retorno.IngressoRetornoLogin;
import br.com.nx.tickets.rest.retorno.IngressoRetornoVerificarConexao;
import br.com.nx.tickets.servico.ConfiguracaoImpressoraServico;
import br.com.nx.tickets.servico.IngressoServico;
import br.com.nx.tickets.servico.PedidoServico;
import br.com.nx.tickets.servico.PontoVendaServico;
import br.com.nx.tickets.servico.UsuarioServico;

@Path("/ingresso")
public class IngressosRest implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private UsuarioServico usuarioServico;
	@EJB
	private ConfiguracaoImpressoraServico configuracaoImpressoraServico;
	@EJB
	private PontoVendaServico pontoVendaServico;
	@EJB
	private IngressoServico ingressoServico;
	@EJB
	private PedidoServico pedidoServico;
	@Inject
	private ConfiguracaoApplication configuracaoApplication;
	@Inject
    private transient Logger log;

	@POST
	@Path("baixarPacote")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response baixarPacote(IngressoEnvioPacote impressoraEnviavel) {
		IngressoRetornoPacote retorno = new IngressoRetornoPacote(configuracaoApplication);
		try {
//			log.info("BAIXAR PACOTE: " + impressoraEnviavel);
//			
//			Usuario usuario = usuarioServico.obterPorId(impressoraEnviavel.getIdUsuario());
//			PontoVenda pontoVenda = 
//			
//			if (usuario != null) {
//				retorno.setUsuario(usuario);
//				retorno.setCodigoRetorno("1");
//				retorno.setMensagem("Ok. Autenticacao realizada com sucesso!");
//			} else {
//				retorno.setCodigoRetorno("2");
//				retorno.setUsuario(null);
//				retorno.set
//				retorno.setMensagem("Erro na autenticação.");
//			}
			return Response.ok(retorno).build();
		} catch (Exception e) {
			e.printStackTrace();	
			retorno.setCodigoRetorno("2");
			retorno.setUsuario(null);
			retorno.setMensagem("ERROR: " + e.getMessage());
			return Response.ok(retorno).build();
		}
	}
	
	
	@POST
	@Path("obterPedidoCompleto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obterPedidoCompleto(IngressoEnvioPacote impressoraEnviavel) {
		IngressoRetornoPedidoCompleto retorno = new IngressoRetornoPedidoCompleto(configuracaoApplication);
		try {
			log.info("BAIXAR PEDIDO COMPLETO: " + impressoraEnviavel);
			
			Usuario usuario = usuarioServico.obterPorId(1);
			
			PontoVenda pontoVenda = pontoVendaServico.obterPorUsuario(1);
			
			Pedido pedido = pedidoServico.obterPedidoCompletoPorId(Long.parseLong("1"));
			pedido.setDataCadastroIntegracao(DataUtil.formatarData(Calendar.getInstance(), EFormatoData.AMERICANO_HORA));
			
			pedido.getEvento().setDataEventoIntegracao(DataUtil.formatarData(Calendar.getInstance(), EFormatoData.AMERICANO_HORA));
			pedido.getEvento().setDataAberturaPortaoIntegracao(DataUtil.formatarData(Calendar.getInstance(), EFormatoData.AMERICANO_HORA));
			
			List<Ingresso> ingressos = ingressoServico.obterPorIdPedido(Long.parseLong("1"));
			pedido.setIngressos(ingressos);
			
			retorno.setUsuario(usuario);
			retorno.setPontoVenda(pontoVenda);
			retorno.setPedido(pedido);
			retorno.setCodigoRetorno("1");
			retorno.setMensagem("Ok. Autenticacao realizada com sucesso!");
			
			return Response.ok(retorno).build();
			
		} catch (Exception e) {
			e.printStackTrace();	
			retorno.setCodigoRetorno("2");
			retorno.setUsuario(null);
			retorno.setMensagem("ERROR: " + e.getMessage());
			return Response.ok(retorno).build();
		}
	}
	@POST
	@Path("logar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logar(IngressoEnvioLogin impressoraEnviavel) {
		
		IngressoRetornoLogin retorno = new IngressoRetornoLogin(configuracaoApplication);
		try {
			log.info("LOGAR: " + impressoraEnviavel);
			Credencial credencial = new Credencial(impressoraEnviavel.getLogin(), impressoraEnviavel.getSenha());
			Usuario usuario 	  = usuarioServico.logar(credencial);
			if (usuario != null) {
				retorno.setUsuario(usuario);
				retorno.setCodigoRetorno("1");
				retorno.setMensagem("Ok. Autenticacao realizada com sucesso!");
			} else {
				retorno.setCodigoRetorno("2");
				retorno.setUsuario(null);
				retorno.setMensagem("Erro na autenticação.");
			}
			return Response.ok(retorno).build();
		} catch (Exception e) {
			e.printStackTrace();	
			retorno.setCodigoRetorno("2");
			retorno.setUsuario(null);
			retorno.setMensagem("ERROR: " + e.getMessage());
			return Response.ok(retorno).build();
		}
	}

	@POST
	@Path("verificarConexao")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response verificarConexao(IngressoEnvioVerificarConexao impressoraEnviavel) {
		try {
			log.info("VERIFICAR CONEXAO: " + impressoraEnviavel);
			IngressoRetornoVerificarConexao retorno = new IngressoRetornoVerificarConexao();
			retorno.setMensagem("Ok. Conexao verificada!");
			retorno.setCodigoRetorno("1");
			return Response.ok(retorno).build();
		} catch (Exception e) {
			e.printStackTrace();
			IngressoRetornoVerificarConexao retorno = new IngressoRetornoVerificarConexao();
			retorno.setCodigoRetorno("2");
			retorno.setMensagem("ERROR: " + e.getMessage());
			return Response.ok(retorno).build();
		}
	}
	
	@POST
	@Path("obterConfiguracaoImpressora")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obterConfiguracaoImpressora(IngressoEnvioObterConfiguracao impressoraEnviavel) {
		try {
			log.info("OBTER CONFIGURACAO IMPRESSORA: " + impressoraEnviavel);
			IngressoRetornoConfiguracaoImpressora retorno = new IngressoRetornoConfiguracaoImpressora();
			retorno.setMensagem("Ok");
			retorno.setCodigoRetorno("1");
			retorno.setConfiguracaoImpressora(configuracaoImpressoraServico.obterTodos());
			return Response.ok(retorno).build();
		} catch (Exception e) {
			e.printStackTrace();
			IngressoRetornoConfiguracaoImpressora retorno = new IngressoRetornoConfiguracaoImpressora();
			retorno.setCodigoRetorno("2");
			retorno.setMensagem("ERROR: " + e.getMessage());
			return Response.ok(retorno).build();
		}
	}
}
