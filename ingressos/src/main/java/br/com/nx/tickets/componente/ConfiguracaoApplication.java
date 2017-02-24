package br.com.nx.tickets.componente;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.nx.tickets.entidade.util.ConfiguracaoSistema;
import br.com.nx.tickets.servico.ConfiguracaoSistemaServico;
import br.com.nx.tickets.util.SistemaConstantes;

@Named
@ApplicationScoped
public class ConfiguracaoApplication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private transient Logger log;
	@EJB
	private ConfiguracaoSistemaServico configuracaoSistemaServico;
	private Map<String, ConfiguracaoSistema> cacheConfiguracaoSistema;

	@PostConstruct
	public void inicializar() {
		carregarConfiguracaoSistema();
	}

	public ConfiguracaoSistema obterConfiguracaoSistema(String descricao) {
		if (cacheConfiguracaoSistema.containsKey(descricao)) {
			return cacheConfiguracaoSistema.get(descricao);
		}
		return null;
	}

	public void carregarConfiguracaoSistema() {
		try {
			String hostname = InetAddress.getLocalHost().getHostName().toUpperCase();
			log.info("######################## HOSTNAME - INGRESSOS ######################## ");
			log.info("# HOSTNAME - " + hostname + " - HOSTNAME #");
			log.info("######################## HOSTNAME - INGRESSOS ######################## ");
			cacheConfiguracaoSistema = new HashMap<String, ConfiguracaoSistema>();
			List<ConfiguracaoSistema> configuracoes = configuracaoSistemaServico.obterTodos("descricao");
			for (ConfiguracaoSistema configuracao : configuracoes) {
				cacheConfiguracaoSistema.put(configuracao.getDescricao(), configuracao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExecutandoNoServidor() {
		try {
			String host1 = obterConfiguracaoSistema(SistemaConstantes.HOSTNAME_SERVIDOR_1).getValor();
			return getHostname().equals(host1);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getHostname() {
		try {
			return InetAddress.getLocalHost().getHostName().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return "Hostname nao encontrado.";
		}
	}
}