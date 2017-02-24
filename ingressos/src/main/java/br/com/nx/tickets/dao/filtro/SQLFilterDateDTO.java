package br.com.nx.tickets.dao.filtro;

import java.util.Calendar;
import java.util.Date;

import br.com.nx.tickets.entidade.util.DataUtil;
import br.com.nx.tickets.entidade.util.ValueGenerator;
import br.com.nx.tickets.util.SistemaConstantes;

public class SQLFilterDateDTO {

	private String attribute;
	
	private String aliasDate1;
	private Date date1;
	
	private String aliasDate2;
	private Date date2;
	
	public SQLFilterDateDTO(String attribute, Date date1, Date date2) {
		this.attribute  = attribute;
		this.aliasDate1 = "_" + ValueGenerator.sqlAlias(SistemaConstantes.DEZ);
		this.date1 	    = date1;
		this.aliasDate2 = "_" + ValueGenerator.sqlAlias(SistemaConstantes.DEZ);
		this.date2 	    = date2;
	}

	public String getAttribute() {
		return attribute;
	}
	
	public Calendar getDate1() {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		return DataUtil.getDataInicioDia(c1);
	}
	
	public Calendar getDate2() {
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return DataUtil.getDataFimDia(c2);
	}
	
	public String getAliasDate1() {
		return aliasDate1;
	}
	
	public String getAliasDate2() {
		return aliasDate2;
	}

	@Override
	public String toString() {
		return "SQLFilterDateDTO [attribute=" + attribute + ", aliasDate1="
				+ aliasDate1 + ", date1=" + date1 + ", aliasDate2="
				+ aliasDate2 + ", date2=" + date2 + "]";
	}
}
