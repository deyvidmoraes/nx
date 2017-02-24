package br.com.nx.tickets.dao.filtro;

import br.com.nx.tickets.entidade.util.ValueGenerator;
import br.com.nx.tickets.util.SistemaConstantes;

public class SQLFilterComplexDTO {

	private String alias;
	
	private String attribute;
	
	private Object value;
	
	public SQLFilterComplexDTO(String attribute, Object value) {
		this.alias	   = "_" + ValueGenerator.sqlAlias(SistemaConstantes.DEZ);
		this.attribute = attribute;
		this.value 	   = value;
	}

	public String getAlias() {
		return alias;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "SQLFilterComplexDTO [alias=" + alias + ", attribute="
				+ attribute + ", value=" + value + "]";
	}
}
