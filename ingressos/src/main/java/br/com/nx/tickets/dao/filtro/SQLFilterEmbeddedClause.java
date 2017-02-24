package br.com.nx.tickets.dao.filtro;

import java.util.List;

public class SQLFilterEmbeddedClause {

	private String sql;
	private List<SQLFilterEmbeddedParameters> filterEmbeddedParameters; 
	
	public SQLFilterEmbeddedClause(String sql, List<SQLFilterEmbeddedParameters> filterEmbeddedParameters) {
		this.sql = sql;
		this.filterEmbeddedParameters = filterEmbeddedParameters;
	}
	
	public String getSql() {
		return sql;
	}
	
	public List<SQLFilterEmbeddedParameters> getFilterEmbeddedParameters() {
		return filterEmbeddedParameters;
	}
}
