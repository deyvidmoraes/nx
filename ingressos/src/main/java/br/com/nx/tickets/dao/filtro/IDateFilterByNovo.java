package br.com.nx.tickets.dao.filtro;

import java.util.Date;

public interface IDateFilterByNovo {

	IOrderByNovo filterDateBy(String filterBy, Date d1, Date d2);
	
}
