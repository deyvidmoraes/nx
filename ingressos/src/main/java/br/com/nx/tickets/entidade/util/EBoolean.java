package br.com.nx.tickets.entidade.util;

public enum EBoolean {
	TRUE("SIM"), FALSE("NAO");

	private final String label;

	EBoolean(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
