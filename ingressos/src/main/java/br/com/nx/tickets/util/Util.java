package br.com.nx.tickets.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import br.com.nx.tickets.entidade.util.EBoolean;
import br.com.nx.tickets.entidade.util.ESituacao;
import br.com.nx.tickets.servico.BaseServicoException;

public final class Util {

	public static final String SENHA_PADRAO = "ABCD1234*";

	private static final Integer NUMERO_1 = 0xff;
	private static final Integer NUMERO_2 = 0x100;

	private Util() {

	}

	// 12345*
	public static String criptografarString(String texto) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result;
			if (texto != null) {
				result = mDigest.digest(texto.getBytes());
			} else {
				result = mDigest.digest(SENHA_PADRAO.getBytes());
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				sb.append(
						Integer.toString((result[i] & NUMERO_1) + NUMERO_2, SistemaConstantes.DEZESSEIS).substring(1));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return criptografarString(SENHA_PADRAO);
		}
	}

	public static String formatarCamelCalse(String value) {
		String valor = ((String) value).trim().toLowerCase();
		return valor.substring(0, 1).toUpperCase() + valor.substring(1, valor.length());
	}

	public static String removerFormatacaoCep(String valor) {
		if (valor != null) {
			valor = valor.replaceAll("\\.", "").replace("-", "");
		}
		return valor;
	}

	public static ESituacao alteraSituacao(ESituacao situacao) {
		if (situacao.equals(ESituacao.ATIVO)) {
			return ESituacao.INATIVO;
		} else {
			return ESituacao.ATIVO;
		}
	}

	public static EBoolean alteraEBoolean(EBoolean situacao) {
		if (situacao.equals(EBoolean.TRUE)) {
			return EBoolean.FALSE;
		} else {
			return EBoolean.TRUE;
		}
	}

	public static boolean converterENumBooleanToBoolean(EBoolean eBoolean) {
		return eBoolean.equals(EBoolean.TRUE);
	}

	public static EBoolean converterBooleanToEnumBoolean(boolean bool) {
		if (bool) {
			return EBoolean.TRUE;
		} else {
			return EBoolean.FALSE;
		}
	}

	public static boolean isNumerico(String texto) {
		if (texto != null && !texto.isEmpty()) {
			if (texto.replaceAll("[\\d]", "").isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public static String toUpperCase(String texto) {
		if (texto != null) {
			return removerCaracteresSpeciais(texto.toUpperCase());
		}
		return null;
	}

	public static String formatarTelefone(Object value) {
		if (value != null) {
			String telefone = removerNaoDigitos(value.toString());
			String retorno = telefone;
			if (telefone.startsWith("0")) {
				telefone = telefone.substring(1); // mascara na tela de
													// acompanhamento de
													// chamada, telefones da
													// discadora tem ddd com 0
													// (043)
			}
			if (telefone.length() == SistemaConstantes.DEZ) {
				retorno = getDDD(telefone) + telefone.substring(SistemaConstantes.DOIS, SistemaConstantes.SEIS) + "-"
						+ telefone.substring(SistemaConstantes.SEIS);

			} else if (telefone.length() == SistemaConstantes.ONZE) {
				retorno = getDDD(telefone) + telefone.substring(SistemaConstantes.DOIS, SistemaConstantes.SETE) + "-"
						+ telefone.substring(SistemaConstantes.SETE);
			}

			return retorno;
		} else {
			return "";
		}
	}

	public static String formatarTelefoneSemDDD(Object value) {
		if (value != null) {
			String telefone = removerNaoDigitos(value.toString());
			String retorno = telefone;
			if (telefone.startsWith("0")) {
				telefone = telefone.substring(1); // mascara na tela de
													// acompanhamento de
													// chamada, telefones da
													// discadora tem ddd com 0
													// (043)
			}
			if (telefone.length() == SistemaConstantes.OITO) {
				retorno = telefone.substring(SistemaConstantes.ZERO, SistemaConstantes.QUATRO) + "-"
						+ telefone.substring(SistemaConstantes.QUATRO);

			} else if (telefone.length() == SistemaConstantes.NOVE) {
				retorno = telefone.substring(SistemaConstantes.ZERO, SistemaConstantes.CINCO) + "-"
						+ telefone.substring(SistemaConstantes.CINCO);
			}

			return retorno;
		} else {
			return "";
		}
	}

	public static boolean validarTelefone(String telefone) {
		try {
			telefone = Util.removerNaoDigitos(telefone);
			Long.parseLong(telefone);
			if (telefone == null || telefone.length() < SistemaConstantes.DEZ
					|| telefone.length() > SistemaConstantes.DOZE) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Valida numeros de telefone com 8 ou 9 digitos
	 * 
	 * @param telefone
	 * @return
	 */
	public static boolean validarTelefoneSemDDD(String telefone) {
		try {
			telefone = Util.removerNaoDigitos(telefone);
			Long.parseLong(telefone);
			if (telefone == null || telefone.length() < SistemaConstantes.OITO
					|| telefone.length() > SistemaConstantes.NOVE) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static String getDDD(String telefone) {
		String ddd = telefone.substring(SistemaConstantes.ZERO, SistemaConstantes.DOIS);
		ddd = "(" + ddd + ") ";
		return ddd;
	}

	private static String getDDDSemFormatacao(String telefone) {
		String ddd = telefone.substring(SistemaConstantes.ZERO, SistemaConstantes.DOIS);
		return ddd;
	}

	public static String removerFormatacaoTelefone(String value) {
		if (value != null) {
			value = removerNaoDigitos(value);
		}
		return value;
	}

	public static String removerNaoDigitos(String value) {
		if (value == null) {
			return "";
		}
		return value.replaceAll("[^\\d]", "");
	}

	public static String abreviar(Object value) {
		String abreviado = (String) value;
		String[] array = value.toString().split(" ");
		if (array.length > 0) {
			abreviado = array[0];
			if (array.length > 1) {
				for (int i = 1; i < array.length - 1; i++) {
					if (array[i].length() > 0) {
						abreviado += " " + array[i].charAt(0) + ".";
					} else {
						abreviado += " " + array[i];
					}
				}
				abreviado += " " + array[array.length - 1];
			}
		}

		return abreviado;
	}

	public static boolean isHorarioValido(String time) {
		try {
			Integer hora = Integer.parseInt(time.split(":")[0]);
			Integer minuto = Integer.parseInt(time.split(":")[1]);
			if (hora >= 0 && hora < SistemaConstantes.VINTE_QUATRO) {
				if (minuto >= 0 && minuto < SistemaConstantes.SESSENTA) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean isCPF(String cpf) {
		cpf = removerNaoDigitos(cpf);
		if (verificarValorRepetido(cpf)) {
			return (false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = SistemaConstantes.DEZ;
			for (i = 0; i < SistemaConstantes.NOVE; i++) {
				num = cpf.charAt(i) - SistemaConstantes.QUARENTA_OITO;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = SistemaConstantes.ONZE - (sm % SistemaConstantes.ONZE);
			if ((r == SistemaConstantes.DEZ) || (r == SistemaConstantes.ONZE)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + SistemaConstantes.QUARENTA_OITO);
			}

			sm = 0;
			peso = SistemaConstantes.ONZE;
			for (i = 0; i < SistemaConstantes.DEZ; i++) {
				num = cpf.charAt(i) - SistemaConstantes.QUARENTA_OITO;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = SistemaConstantes.ONZE - (sm % SistemaConstantes.ONZE);
			if ((r == SistemaConstantes.DEZ) || (r == SistemaConstantes.ONZE)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + SistemaConstantes.QUARENTA_OITO);
			}

			if ((dig10 == cpf.charAt(SistemaConstantes.NOVE)) && (dig11 == cpf.charAt(SistemaConstantes.DEZ))) {
				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static boolean verificarValorRepetido(String cpf) {
		return cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != SistemaConstantes.ONZE);
	}

	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);
		return m.find();
	}

	public static boolean isLoginValido(String login) {
		Pattern p = Pattern.compile("[^\\w\\.]");
		Matcher m = p.matcher(login);

		return !m.find();
	}

	public static boolean isTelefone(String telefone) {
		if (telefone == null) {
			return false;
		}
		// [1-9][1-9][1-5][0-9][0-9][0-9][0-9][0-9][0-9][0-9] = FIXO
		Pattern fixoPattern = Pattern.compile("^([1-9]{2})([1-5]{1})([0-9]{7})$");

		// [1-9][1-9][6-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9] = MOVEL
		Pattern movelPattern = Pattern.compile("^([1-9]{2})([6-9]{1})([0-9]{7})$");

		// [1-9][1-9]9[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9] = MOVEL
		Pattern movelNonoDigitoPattern = Pattern.compile("^([1-9]{2})([9]{1})([0-9]{8})$");

		Matcher mFixo = fixoPattern.matcher(telefone);
		Matcher mMovel = movelPattern.matcher(telefone);
		Matcher mMovelNonoDigito = movelNonoDigitoPattern.matcher(telefone);

		if (mFixo.find() || mMovel.find() || mMovelNonoDigito.find()) {
			return true;
		}
		return false;
	}

	public static ChannelSftp abrirCanalSFTP(String host, String user, String pass, String dir)
			throws BaseServicoException {
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(user, host);
			session.setPassword(pass);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(dir);
		} catch (Exception e) {
			throw new BaseServicoException(e.getMessage());
		}

		return channelSftp;
	}

	public static String substituirCaracteresEspeciais(String text) {
		String retorno = "";
		if (text != null) {
			text = Normalizer.normalize(text, Normalizer.Form.NFD);
			text = text.replaceAll("[^\\p{ASCII}]", "");
			text = text.replaceAll("\"", "");
			text = text.replaceAll("\'", "");
			text = text.replaceAll("\t", " ");
			text = text.replaceAll("\n", " ");
			text = text.replaceAll("\r", " ");
			text = text.replaceAll("  ", " ");
			text = text.replaceAll(";", " ");
			text = text.replaceAll(",", " ");
			retorno = text;
		}
		return retorno.trim();
	}

	public static String substituirCaracteresEspeciaisSemVirgula(String text) {
		String retorno = "";
		if (text != null) {
			text = Normalizer.normalize(text, Normalizer.Form.NFD);
			text = text.replaceAll("[^\\p{ASCII}]", "");
			text = text.replaceAll("\t", " ");
			text = text.replaceAll("\n", " ");
			text = text.replaceAll("\r", " ");
			text = text.replaceAll("  ", " ");
			retorno = text;
		}
		return retorno.trim();
	}

	// efetua a leitura do arquivo
	public static byte[] readFile(File file) throws IOException {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.print("ArquivoNovo não encontrado");
		} catch (IOException e) {
			System.out.print("Erro na leitura do arquivo");
			e.printStackTrace();
		} finally {
			if (inFile != null) {
				inFile.close();
			}
		}
		return sendBuf;
	}

	public static boolean isFixo(String contato) {
		if (contato == null) {
			return false;
		}
		/*
		 * _0[1-9][1-9][1-5][0-9][0-9][0-9][0-9][0-9][0-9][0-9] = FIXO
		 * _0[1-9][1-9][6-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9] = MOVEL
		 * _0[1-9][1-9]9[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9] = MOVEL
		 */
		if (contato.length() == SistemaConstantes.ONZE) {
			return false;
		} else if (contato.length() == SistemaConstantes.DEZ) {
			if (Integer.valueOf(
					(contato.substring(SistemaConstantes.DOIS, SistemaConstantes.TRES))) > SistemaConstantes.CINCO) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public static void gravarArquivoEmDisco(String caminhoArquivo, Part arquivo, String strArquivo)
			throws BaseServicoException {
		BufferedInputStream bufferedInputStream = null;
		FileOutputStream fileOutputStream = null;
		// Salvando o arquivo
		try {
			bufferedInputStream = new BufferedInputStream(arquivo.getInputStream());
			fileOutputStream = new FileOutputStream(caminhoArquivo + strArquivo);
			byte[] buffer = new byte[SistemaConstantes.MIL_VINTE_QUATRO];
			int count;
			while ((count = bufferedInputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, count);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void downloadFromByteArray(byte[] array, String fileName) throws IOException {
		try {
			if (array == null) {
				throw new IOException("Não foi possivel fazer o download do arquivo: " + fileName);
			}
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context.getResponse();
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			response.setContentLength(array.length);
			response.setContentType("application/octet-stream");
			OutputStream out = response.getOutputStream();
			out.write(array);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Nao foi possivel fazer o download do arquivo: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Nao foi possivel fazer o download do arquivo: " + fileName);
		}
	}

	public static String removerCaracteresSpeciais(String texto) {
		if (texto != null) {
			texto = removerCaracteresSpeciaisClienteNovo(texto);
		}
		return substituirCaracteresEspeciais(texto);
	}

	public static String removerCaracteresSpeciaisClienteNovo(String texto) {
		if (texto != null) {
			texto = texto.replaceAll("[ÁÀÂÃÄÃ]", "A").replaceAll("[ÉÈÊË]", "E").replaceAll("[ÍÌÎÏ]", "I")
					.replaceAll("[ÓÒÔÕÖ]", "O").replaceAll("[ÚÙÛÜ]", "U").replaceAll("[Ñ]", "N").replaceAll("[Ç]", "C");
		}
		return texto.trim();
	}

	public static void compactarArquivoZip(String caminhoArquivo) {
		try {
			String comando = "zip -r " + caminhoArquivo + ".zip " + caminhoArquivo;
			Runtime rt = Runtime.getRuntime();
			rt.exec(comando);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Integer validarRetornoInteger(Object object) {
		if (object != null) {
			return (Integer) object;
		}
		return null;
	}

	public static String validarRetornoString(Object object) {
		if (object != null) {
			return (String) object;
		}
		return "";
	}

	public static Long validarRetornoLong(Object object) {
		if (object != null) {
			return (Long) object;
		}
		return null;
	}

	public static BigInteger validarRetornoBigInteger(Object object) {
		if (object != null) {
			return (BigInteger) object;
		}
		return new BigInteger("0");
	}
	
	public static Calendar validarRetornoData(Object object) {
		if (object != null) {
			Timestamp data = (Timestamp) object;
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(data.getTime());
			return c;
		}
		return null;
	}

	public static void zipFile(File arquivo, String nomeArquivoZipado) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(nomeArquivoZipado);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

			ZipEntry zipEntry = new ZipEntry(arquivo.getName());
			zipOutputStream.putNextEntry(zipEntry);

			FileInputStream fileInputStream = new FileInputStream(arquivo);
			byte[] buf = new byte[SistemaConstantes.MIL_VINTE_QUATRO];
			int bytesRead;

			while ((bytesRead = fileInputStream.read(buf)) > 0) {
				zipOutputStream.write(buf, 0, bytesRead);
			}

			zipOutputStream.closeEntry();

			zipOutputStream.close();
			fileOutputStream.close();
			fileInputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String convertoToJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	public static String converterNonoDigito(String telefone) {
		if (validarTelefone(telefone) && !isFixo(telefone)) {
			String telefoneSemDDD = telefone.substring(2);
			String ddd = getDDDSemFormatacao(telefone);
			if (isDDDNonoDigito(ddd) && telefoneSemDDD.length() == 8) {
				telefone = ddd + "9" + telefone.substring(2);
			}
		}
		return telefone;
	}

	public static boolean isDDDNonoDigito(String ddd) {
		int dddInt = Integer.valueOf(ddd);
		if ((dddInt >= 11 && dddInt <= 19) // SAO PAULO
				|| (dddInt >= 21 && dddInt <= 24) // RIO DE JANEIRO
				|| (dddInt >= 27 && dddInt <= 28) // ESPIRITO SANTOS
				|| (dddInt >= 81 && dddInt <= 89) || (dddInt >= 31 && dddInt <= 35) || (dddInt >= 37 && dddInt <= 38)
				|| (dddInt == 71 || dddInt == 73 || dddInt == 74 || dddInt == 75 || dddInt == 77 || dddInt == 79)
				|| (dddInt >= 91 && dddInt <= 99)) { // AMAPA, AMAZONAS,
														// MARANHAO, PARA,
														// RORAIMA)
			return true;
		}
		return false;

	}

	public static void downloadAudioFile(File file) {
		try {
			InputStream fis = new FileInputStream(file);
			byte[] buf = new byte[fis.available()];
			int offset = 0;
			int numRead = 0;
			while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length - offset)) >= 0)) {
				offset += numRead;
			}
			fis.close();
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			response.getOutputStream().write(buf);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getBoolean(EBoolean valor) {
		return valor.equals(EBoolean.TRUE);
	}

	public static EBoolean getEBoolean(boolean valor) {
		return valor ? EBoolean.TRUE : EBoolean.FALSE;
	}
}