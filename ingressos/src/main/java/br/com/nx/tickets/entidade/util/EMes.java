package br.com.nx.tickets.entidade.util;

public enum EMes {

	JANEIRO("Janeiro", 0),
	FEVEREIRO("Fevereiro", 1),
	MARCO("Março", 2),
	ABRIL("Abril", 3),
	MAIO("Maio", 4),
	JUNHO("Junho", 5),
	JULHO("Julho", 6),
	AGOSTO("Agosto", 7),
	SETEMBRO("Setembro", 8),
	OUTUBRO("Outubro", 9),
	NOVEMBRO("Novembro", 10),
	DEZEMBRO("Dezembro", 11);
	
	private String nome;
	private Integer codigo;
	
	EMes(String nome, Integer codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public Integer getCodigo() {
		return codigo;
	}
}
