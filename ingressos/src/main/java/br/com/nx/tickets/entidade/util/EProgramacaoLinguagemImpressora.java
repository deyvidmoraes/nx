package br.com.nx.tickets.entidade.util;

public enum EProgramacaoLinguagemImpressora {
	ZPL("ZPL"), 
	EPL("EPL");

	private final String label;

	EProgramacaoLinguagemImpressora(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}