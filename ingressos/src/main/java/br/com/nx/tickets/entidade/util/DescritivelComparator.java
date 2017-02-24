package br.com.nx.tickets.entidade.util;

import java.util.Comparator;

public class DescritivelComparator implements Comparator<Descritivel> {

	@Override
	public int compare(Descritivel o1, Descritivel o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}
}
