package br.com.nx.tickets.dao.filtro;

public enum ESQLOperator {

	EQUALS(" = "), NOT_EQUALS(" != "), LESS_THAN(" < "), GREATER_THAN(" > "), LIKE(" LIKE "), BETWEEN(" BETWEEN ");

	private final String operator;

	ESQLOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}
}
