package br.com.nx.tickets.entidade.util;

public enum EPeriodoDia {

	INICIO(0, 0, 0), FIM(23, 59, 59), OITO_HORAS(8, 0, 0), VINTE_DUAS_HORAS(22, 0, 0);;

	private final int hora;
	private final int minuto;
	private final int segundo;

	EPeriodoDia(int hora, int minuto, int segundo) {
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public int getSegundo() {
		return segundo;
	}
}
