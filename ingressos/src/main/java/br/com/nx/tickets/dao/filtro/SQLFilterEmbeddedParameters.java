package br.com.nx.tickets.dao.filtro;

public class SQLFilterEmbeddedParameters {

	private String key;
	private Object value;
	
	public SQLFilterEmbeddedParameters(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	
	public Object getValue() {
		return value;
	}
}
