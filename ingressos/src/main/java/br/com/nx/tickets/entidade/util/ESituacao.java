package br.com.nx.tickets.entidade.util;

public enum ESituacao {
	AMBOS("AMBOS"),
	ATIVO("ATIVO"), 
	INATIVO("INATIVO"); 
	
	private final String texto;
	
	ESituacao(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}