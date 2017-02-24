package br.com.nx.tickets.entidade.util;

public enum ETipoDia {
	DIA_UTIL("DIA UTIL"), 
	SABADO("SABADO"),
	DOMINGO("DOMINGO"),
	FERIADO("FERIADO");
	
	
	private final String texto;
	
	ETipoDia(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}
