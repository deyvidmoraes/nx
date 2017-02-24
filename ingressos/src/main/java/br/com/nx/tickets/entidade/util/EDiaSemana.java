package br.com.nx.tickets.entidade.util;

public enum EDiaSemana {

	DOMINGO("Domingo", 1),
	SEGUNDA("Segunda", 2),
	TERCA("Terça", 3),
	QUARTA("Quarta", 4),
	QUINTA("Quinta", 5),
	SEXTA("Sexta", 6),
	SABADO("Sábado", 7);
	
	private String nome;
	private Integer codigo;
	
	EDiaSemana(String nome, Integer codigo) {
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
