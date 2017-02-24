package br.com.nx.tickets.componente;

import javax.xml.bind.annotation.XmlTransient;


public interface Paginavel {
	
	@XmlTransient
	String getSqlSelect();
	
	@XmlTransient
	String getSqlCount();
	
	@XmlTransient
	String getObjetoRetorno();

	@XmlTransient
	String getJoin();
}
