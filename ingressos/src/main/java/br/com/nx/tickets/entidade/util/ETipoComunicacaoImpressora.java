package br.com.nx.tickets.entidade.util;

public enum ETipoComunicacaoImpressora {
	USB("USB"), 
	ETHERNET("ETHERNET"),
	COM("COM");

	private final String texto;
	
	ETipoComunicacaoImpressora(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}