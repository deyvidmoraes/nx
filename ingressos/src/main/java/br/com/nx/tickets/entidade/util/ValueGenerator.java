package br.com.nx.tickets.entidade.util;

import java.util.ArrayList;
import java.util.Random;

import br.com.nx.tickets.util.SistemaConstantes;

public final class ValueGenerator {

	private static final String[] ARRAY_ALFABETO = { "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "X", "Y", "W", "Z" };
	private static final Integer[] ARRAY_NUMEROS = { 0, 1, 2, 3, 4, 5, 6, 7, 8,
			9 };
	private static final String GMAIL = "@NXMULTISERVICOS.COM.BR";
	private static Random random = new Random();

	private static final int ANO_INICIO = 1920;

	private static final int ANO_FIM = 2014;

	private static final int MES_INICIO = 0;

	private static final int MES_FIM = 11;

	private static final int DIA_INICIO = 1;

	private static final int DIA_FIM = 30;

	private ValueGenerator() {

	}

	public static String stringAleatorio(Integer quantidadeCaracteres) {
		String valor = "";
		for (int i = 1; i <= quantidadeCaracteres; i++) {
			valor += ARRAY_ALFABETO[new Random().nextInt(ARRAY_ALFABETO.length)];
			if (i % SistemaConstantes.DEZ == 0 && i >= SistemaConstantes.DEZ) {
				valor += " ";
			}
			if (valor.length() == quantidadeCaracteres) {
				break;
			}
		}
		return valor.trim();
	}
	
	public static String sqlAlias(Integer quantidadeCaracteres) {
		return stringAleatorio(quantidadeCaracteres).replace(" ", "");
	}

	public static String emailAleatorio() {
		String email = "";
		for (int i = 0; i < SistemaConstantes.DEZ; i++) {
			email += ARRAY_ALFABETO[new Random().nextInt(ARRAY_ALFABETO.length)];
		}
		return email + GMAIL;
	}

	public static Long numeroAleatorio(Integer quantidadeNumeros) {
		String valor = "";
		for (int i = 0; i < quantidadeNumeros; i++) {
			valor += ARRAY_NUMEROS[new Random().nextInt(ARRAY_NUMEROS.length)]
					+ "";
		}
		return Long.parseLong(valor);
	}

	public static Integer retornaNumeroRandom(int numeroMaximo) {
		return random.nextInt(numeroMaximo);
	}

	public static String dataAleatoria() {
		ArrayList<String> datasAleatoria = new ArrayList<String>();
		for (int ano = ANO_INICIO; ano < ANO_FIM; ano++) {
			for (int mes = MES_INICIO; mes < MES_FIM; mes++) {
				for (int dia = DIA_INICIO; dia < DIA_FIM; dia++) {
					datasAleatoria.add(new String(ano + "/" + mes + "/" + dia));
				}
			}
		}
		return datasAleatoria.get(ValueGenerator
				.retornaNumeroRandom(datasAleatoria.size()));
	}
}