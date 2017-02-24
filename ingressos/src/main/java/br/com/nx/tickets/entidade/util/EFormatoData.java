package br.com.nx.tickets.entidade.util;

public enum EFormatoData {

	BRASILEIRO_DATA_HORA("dd/MM/yyyy HH:mm"),
	BRASILEIRO_DATA_HORA_SEGUNDOS("dd/MM/yyyy HH:mm:ss"),
	BRASILEIRO_DATA("dd/MM/yyyy"),
	BRASILEIRO_EXTENSO("dd MMMM "),
	BRASILEIRO_EXTENSO_MES_ANO("MMMM/yyyy "),
	BRASILEIRO_HORA("HH:mm:ss"),
	BRASILEIRO_HORA_MINUTO("HH:mm"),
	AMERICANO_HORA("yyyy/MM/dd HH:mm"),
	AMERICANO_DATA("yyyy/MM/dd"),
	DISCADORA("yyyy-MM-dd HH:mm:ss"),
	DIRETORIO_TABULACAO_INTEGRACAO("yyyy-MM-dd"),
	SAIDA_ARQUIVO("dd_MM_yyyy_HH_mm_ss"),
	VENDA_REDE_INOVA_DATA("yyyy-MM-dd"),
	DIA("dd"),
	MES("MM"),
	ANO("yyyy"),
	PROTOCOLO("MMddHHmmss");
	
	private final String padrao;
	
	EFormatoData(String padrao) {
		this.padrao = padrao;
	}
	
	public String getPadrao() {
		return padrao;
	}
}
