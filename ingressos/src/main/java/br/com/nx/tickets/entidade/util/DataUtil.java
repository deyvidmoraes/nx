package br.com.nx.tickets.entidade.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import br.com.nx.tickets.entidade.Feriado;
import br.com.nx.tickets.util.SistemaConstantes;

public final class DataUtil {

	private DataUtil() {

	}

	public static Calendar formatarData(Calendar data, EPeriodoDia periodo) {
		Calendar dataAlterada = (Calendar) data.clone();
		dataAlterada.set(dataAlterada.get(Calendar.YEAR), dataAlterada.get(Calendar.MONTH),
				dataAlterada.get(Calendar.DATE), periodo.getHora(), periodo.getMinuto(), periodo.getSegundo());
		dataAlterada.set(Calendar.MILLISECOND, 0);
		return dataAlterada;
	}

	public static long intervaloSegundosEntreHorario(Calendar dataHorarioInicio, Calendar dataHorarioFim) {
		if ((dataHorarioInicio == null || dataHorarioFim == null) || (dataHorarioFim.before(dataHorarioInicio))) {
			throw new IllegalArgumentException("Dados de entrada de data invalidos!");
		}
		return ((dataHorarioFim.getTimeInMillis() - dataHorarioInicio.getTimeInMillis()) / SistemaConstantes.MIL);
	}

	public static Calendar alterarDataOriginal(Integer tipo, Integer quantidade, Calendar dataFutura) {
		Calendar dataAlterada = (Calendar) dataFutura.clone();
		dataAlterada.add(tipo, quantidade);
		return dataAlterada;
	}

	public static int compararDatasSemHoras(Calendar data1, Calendar data2) {
		data1.set(Calendar.HOUR_OF_DAY, 0);
		data1.set(Calendar.MINUTE, 0);
		data1.set(Calendar.SECOND, 0);
		data1.set(Calendar.MILLISECOND, 0);
		data2.set(Calendar.HOUR_OF_DAY, 0);
		data2.set(Calendar.MINUTE, 0);
		data2.set(Calendar.SECOND, 0);
		data2.set(Calendar.MILLISECOND, 0);

		if (data1.before(data2)) {
			return -1;
		} else if (data1.after(data2)) {
			return 1;
		}

		return 0;
	}

	public static String formatarDataDeEntrada(Calendar data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data.getTime());
	}

	public static Date zerarHorario() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Calendar zerarHorario(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static String formatarData(Calendar data, EFormatoData formatoData) {
		if (data == null) {
			return null;
		}
		return new SimpleDateFormat(formatoData.getPadrao(), new Locale("pt", "BR")).format(data.getTime());
	}

	public static Calendar formatarHorarioDeEntrada(Date intervaloSugerido) {
		if (intervaloSugerido == null) {
			return null;
		}
		Calendar retornoPrevisto = Calendar.getInstance();
		Calendar retornoSugerido = Calendar.getInstance();
		retornoSugerido.setTimeInMillis(intervaloSugerido.getTime());
		retornoPrevisto.add(Calendar.HOUR, retornoSugerido.get(Calendar.HOUR));
		retornoPrevisto.add(Calendar.MINUTE, retornoSugerido.get(Calendar.MINUTE));
		retornoPrevisto.add(Calendar.SECOND, retornoSugerido.get(Calendar.SECOND));
		return retornoPrevisto;
	}

	public static Calendar getPrimeiroDiaMes() {
		Calendar diaAtual = Calendar.getInstance();
		diaAtual.set(Calendar.DAY_OF_MONTH, 1);
		return DataUtil.formatarData(diaAtual, EPeriodoDia.INICIO);
	}

	public static Calendar getUltimoDiaMes() {
		Calendar diaAtual = Calendar.getInstance();
		diaAtual.set(Calendar.DAY_OF_MONTH, diaAtual.getActualMaximum(Calendar.DAY_OF_MONTH));
		return DataUtil.formatarData(diaAtual, EPeriodoDia.FIM);
	}

	public static Calendar getPrimeiroDiaMes(Calendar calendar) {
		Calendar dia = (Calendar) calendar.clone();
		dia.set(Calendar.DAY_OF_MONTH, 1);
		return DataUtil.formatarData(dia, EPeriodoDia.INICIO);
	}

	public static Calendar getUltimoDiaMes(Calendar calendar) {
		Calendar dia = (Calendar) calendar.clone();
		dia.set(Calendar.DAY_OF_MONTH, dia.getActualMaximum(Calendar.DAY_OF_MONTH));
		return DataUtil.formatarData(dia, EPeriodoDia.FIM);
	}

	public static Calendar getUltimoDiaAno(Calendar calendar) {
		Calendar dia = (Calendar) calendar.clone();
		dia.set(Calendar.MONTH, Calendar.DECEMBER);
		dia.set(Calendar.DAY_OF_MONTH, dia.getActualMaximum(Calendar.DAY_OF_MONTH));
		return DataUtil.formatarData(dia, EPeriodoDia.FIM);
	}

	public static Calendar getDataFimDia(Calendar data) {
		if (data == null) {
			return data;
		}
		return DataUtil.formatarData(data, EPeriodoDia.FIM);
	}

	public static Calendar getDataInicioDia(Calendar data) {
		if (data == null) {
			return data;
		}
		return DataUtil.formatarData(data, EPeriodoDia.INICIO);
	}

	public static Calendar getDataInicioJornada(Calendar data) {
		Calendar retorno = getDataInicioDia(data);
		retorno.set(Calendar.HOUR_OF_DAY, SistemaConstantes.OITO);
		return retorno;
	}

	public static Calendar getDataFimJornada(Calendar data) {
		Calendar retorno = getDataInicioDia(data);
		retorno.set(Calendar.HOUR_OF_DAY, SistemaConstantes.VINTE_UM);
		return retorno;
	}

	public static int getDiasUteis(Calendar dataInicio, Calendar dataFim) {
		Calendar dtI = Calendar.getInstance();
		Calendar dtF = Calendar.getInstance();
		dtI.setTime(dataInicio.getTime());
		dtF.setTime(dataFim.getTime());
		int diasUteis = 0;
		int diferencaEmDias = getDiferencaEmDias(dtI, dtF);

		for (int i = 0; i <= diferencaEmDias; i++) {
			if (isDiaUtil(dtI)) {
				diasUteis++;
			}
			dtI.add(Calendar.DATE, 1);
		}
		return diasUteis;
	}

	public static boolean isDiaUtil(Calendar data) {
		return (!(data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				&& !(data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY));
	}

	public static ETipoDia getTipoDia(Calendar data) {
		if (DataUtil.isDiaUtil(data)) {
			return ETipoDia.DIA_UTIL;
		} else {
			if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				return ETipoDia.SABADO;
			}
		}
		return ETipoDia.DOMINGO;
	}

	// Método utilizado para cálculos
	public static double getDiasUteisSabadoFeriado(Calendar data, List<Feriado> feriados) {

		Calendar dt = Calendar.getInstance();
		dt.setTime(data.getTime());
		double dias = 0;
		int diasMes = data.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < diasMes; i++) {
			if (!(dt.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
					&& !(dt.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) && !(isFeriado(dt, feriados))) {
				dias++;
			} else if (dt.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				dias = (dias + SistemaConstantes.ZERO_PONTO_CINCO);
			}
			dt.add(Calendar.DATE, 1);
		}
		return dias;
	}

	public static int getTotalDiasUteis(Calendar data, List<Feriado> feriados) {

		Calendar dt = Calendar.getInstance();
		dt.setTime(data.getTime());
		int dias = 0;
		int diasMes = data.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < diasMes; i++) {
			if (!(dt.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
					&& !(dt.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) && !(isFeriado(dt, feriados))) {
				dias++;
			}
		}

		return dias;
	}

	public static boolean isFeriado(Calendar data, List<Feriado> feriados) {
		int diaMes = data.get(Calendar.DAY_OF_MONTH);

		for (Feriado feriado : feriados) {
			if (feriado.getDia() == diaMes) {
				return true;
			}
		}
		return false;
	}

	public static double getDiasUteisComSabado(Calendar dataInicio, Calendar dataFim) {
		Calendar dtI = Calendar.getInstance();
		Calendar dtF = Calendar.getInstance();
		dtI.setTime(dataInicio.getTime());
		dtF.setTime(dataFim.getTime());
		double diasUteis = 0;
		int diferencaEmDias = getDiferencaEmDias(dtI, dtF);

		for (int i = 0; i <= diferencaEmDias; i++) {
			if (!(dtI.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
					&& !(dtI.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				diasUteis++;
			} else if (dtI.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				diasUteis = diasUteis + SistemaConstantes.ZERO_PONTO_CINCO;
			}
			dtI.add(Calendar.DATE, 1);
		}
		return diasUteis;
	}

	public static int getDiferencaEmDias(Calendar initialDate, Calendar finalDate) {
		if (initialDate == null || finalDate == null) {
			return 0;
		}
		return Days.daysBetween(new DateTime(initialDate.getTime()), new DateTime(finalDate.getTime())).getDays();
	}

	public static int getDiferencaEmMinutos(Calendar initialDate, Calendar finalDate) {
		if (initialDate == null || finalDate == null) {
			return 0;
		}
		return Minutes.minutesBetween(new DateTime(initialDate.getTime()), new DateTime(finalDate.getTime()))
				.getMinutes();
	}
	
	public static String getDiferencaFormatada(Calendar initialDate, Calendar finalDate) {
		if (initialDate == null || finalDate == null) {
			return "--";
		}
		
		PeriodFormatter daysHoursMinutes = new PeriodFormatterBuilder()
		.appendYears().appendSuffix(" ano", " anos").appendSeparator(" ")
	    .appendMonths().appendSuffix(" mes", " meses").appendSeparator(" ")
	    .appendDays().appendSuffix(" dia", " dias").toFormatter();
		
		 Period periodo2 = new Period(initialDate.getTimeInMillis(), finalDate.getTimeInMillis(), PeriodType.yearMonthDayTime());
		 
		 return daysHoursMinutes.print(periodo2);
	}
	
	public static Duration getDiferencaCalendar(Calendar initialDate, Calendar finalDate) {
		if (initialDate == null || finalDate == null) {
			return Duration.ZERO;
		}
		return new Duration(new DateTime(initialDate.getTime()), new DateTime(finalDate.getTime()));
	}

	public static Calendar manipularMinutos(Calendar data, Integer minutos) {
		Calendar dataAlterada = (Calendar) data.clone();
		dataAlterada.add(Calendar.MINUTE, minutos);
		return dataAlterada;
	}

	public static Calendar manipularDia(Calendar data, Integer dias) {
		Calendar dataAlterada = (Calendar) data.clone();
		dataAlterada.add(Calendar.DAY_OF_MONTH, dias);
		return dataAlterada;
	}

	public static Calendar getCalendar(Date date) {
		Calendar retorno = Calendar.getInstance();
		retorno.setTime(date);
		return retorno;
	}

	public static String converterSegundosEmHoras(BigDecimal seg) {
		String retorno = "";
		BigDecimal segundos = seg.remainder(new BigDecimal(SistemaConstantes.SESSENTA));
		BigDecimal minutos = seg.divide(new BigDecimal(SistemaConstantes.SESSENTA), RoundingMode.DOWN)
				.remainder(new BigDecimal(SistemaConstantes.SESSENTA));
		BigDecimal horas = new BigDecimal(0);

		if (!seg.divide(new BigDecimal(SistemaConstantes.SESSENTA), RoundingMode.DOWN).equals(new BigDecimal(0))) {
			horas = seg.divide(new BigDecimal(SistemaConstantes.SESSENTA), RoundingMode.DOWN)
					.divide(new BigDecimal(SistemaConstantes.SESSENTA), RoundingMode.DOWN);
		}
		if (("" + horas.longValue()).toCharArray().length == SistemaConstantes.UM) {
			retorno += "0";
		}
		retorno += horas.longValue() + ":";

		if (("" + minutos.longValue()).toCharArray().length == SistemaConstantes.UM) {
			retorno += "0";
		}
		retorno += minutos.longValue() + ":";

		if (("" + segundos.longValue()).toCharArray().length == SistemaConstantes.UM) {
			retorno += "0";
		}
		retorno += segundos.longValue();
		return retorno;
	}

	public static Calendar converterStringParaCalendar(String strData) throws ParseException {
		if (strData.trim().equals("") || strData.length() < SistemaConstantes.DEZ) {
			return null;
		}
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse(strData);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Calendar converterStringParaCalendar(String strData, EFormatoData formatoData) throws ParseException {
		if (strData.trim().equals("")) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatoData.getPadrao());
		Date date = formatter.parse(strData);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static int getDiasUteisNoMesCorrente() {
		int qtdDiasTrabalhados = DataUtil.getDiasUteis(DataUtil.getPrimeiroDiaMes(),
				DataUtil.getDataFimDia(Calendar.getInstance()));
		return qtdDiasTrabalhados;
	}

	public static DateTimeZone getDateTimeZonePorId(String idTimeZone) throws IOException {
		try {
			return DateTimeZone.forID(idTimeZone);
		} catch (RuntimeException e) {
			System.out.println("ERRO ao obter o timezone!");
			return null;
		}
	}

	public static String converterAnoMesEmMesAno(String anoMes) {
		String ano = anoMes.substring(SistemaConstantes.ZERO, SistemaConstantes.QUATRO);
		String mes = anoMes.substring(SistemaConstantes.QUATRO, SistemaConstantes.SEIS);

		return mes + "/" + ano;
	}

	public static EDiaSemana getDiaSemana(Calendar calendar) {
		EDiaSemana[] diasSemana = EDiaSemana.values();
		for (EDiaSemana diaSemana : diasSemana) {
			if (diaSemana.getCodigo().equals(calendar.get(Calendar.DAY_OF_WEEK))) {
				return diaSemana;
			}
		}
		return null;
	}

	public static EMes getMes(Calendar calendar) {
		EMes[] meses = EMes.values();
		for (EMes mes : meses) {
			if (mes.getCodigo().equals(calendar.get(Calendar.MONTH))) {
				return mes;
			}
		}
		return null;
	}

	public static Calendar calcularDataInicialPorDiasUteis(Calendar dataFinal, int diasUteis) {
		Calendar dtI = Calendar.getInstance();
		int qntDias = DataUtil.getDiasUteis(dtI, dataFinal);
		while (qntDias <= diasUteis) {
			dtI.add(Calendar.DAY_OF_MONTH, -1);
			qntDias = DataUtil.getDiasUteis(dtI, dataFinal);
		}

		return dtI;
	}

	public static Calendar getHojeDataInicio() {
		Calendar data = Calendar.getInstance();
		return getDataInicioDia(data);
	}

	public static Calendar getHojeDataFim() {
		Calendar data = Calendar.getInstance();
		return getDataFimDia(data);
	}

	public static String converterSegundosParaFormatoHora(Long ss) {
		long segundos = ss % SistemaConstantes.SESSENTA;
		long minutos = (ss / SistemaConstantes.SESSENTA) % SistemaConstantes.SESSENTA;
		long horas = ss / SistemaConstantes.TRES_MIL_SEISSENTOS;

		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}

	public static Calendar getDataPadraoHorarioAgenteAutorizado(Calendar dataPadrao) {
		dataPadrao.set(Calendar.DAY_OF_MONTH, 1);
		dataPadrao.set(Calendar.MONTH, 1);
		dataPadrao.set(Calendar.YEAR, SistemaConstantes.DOIS_MIL);
		return dataPadrao;
	}
	
	public static Calendar setupCalendarFromDate(Date d) {
		Calendar data = Calendar.getInstance();
		data.setTime(d);
		return data;
	}

	public static ETipoDia getTipoDia(int dia) {
		switch (dia) {
		case Calendar.MONDAY:
		case Calendar.TUESDAY:
		case Calendar.WEDNESDAY:
		case Calendar.THURSDAY:
		case Calendar.FRIDAY:
			return ETipoDia.DIA_UTIL;
		case Calendar.SATURDAY:
			return ETipoDia.SABADO;
		case Calendar.SUNDAY:
			return ETipoDia.DOMINGO;
		}
		return null;
	}

	public static List<Calendar> getUltimosMeses(int quantidadeMeses) {
		List<Calendar> mesesAnos = new ArrayList<Calendar>();
		for (int i = 0; i < quantidadeMeses; i++) {
			Calendar data = Calendar.getInstance();
			int mes = i * -1;
			data.add(Calendar.MONTH, mes);
			mesesAnos.add(data);
		}
		return mesesAnos;
	}

	public static boolean validarHorarioJornada(Calendar dataAtual, Date horaInicial, Date horaFinal) {
		Calendar dtInicial = (Calendar) dataAtual.clone();
		Calendar dtFinal = (Calendar) dataAtual.clone();
		Calendar cTmp = Calendar.getInstance();

		cTmp.setTime(horaInicial);
		dtInicial.set(Calendar.HOUR_OF_DAY, cTmp.get(Calendar.HOUR_OF_DAY));
		dtInicial.set(Calendar.MINUTE, cTmp.get(Calendar.MINUTE));

		cTmp.setTime(horaFinal);
		dtFinal.set(Calendar.HOUR_OF_DAY, cTmp.get(Calendar.HOUR_OF_DAY));
		dtFinal.set(Calendar.MINUTE, cTmp.get(Calendar.MINUTE));

		if (horaFinal.before(horaInicial)) {
			Calendar dtFinalDia = (Calendar) dataAtual.clone();
			dtFinalDia = DataUtil.getDataFimDia(dtFinalDia);

			Calendar dtInicioDia = (Calendar) dataAtual.clone();
			dtInicioDia = DataUtil.zerarHorario(dtInicioDia);

			if ((dataAtual.after(dtInicial) && dataAtual.before(dtFinalDia))
					|| (dataAtual.after(dtInicioDia) && dataAtual.before(dtFinal))) {
				return true;
			}
			return false;
		}

		if ((dataAtual.after(dtInicial) && dataAtual.before(dtFinal))) {
			return true;
		}
		return false;
	}

	public static boolean validarHorarioPausa(Calendar dataAtual, Date horaInicial, Date horaFinal) {
		Calendar dtInicial = (Calendar) dataAtual.clone();
		Calendar dtFinal = (Calendar) dataAtual.clone();
		Calendar cTmp = Calendar.getInstance();

		cTmp.setTime(horaInicial);
		dtInicial.set(Calendar.HOUR_OF_DAY, cTmp.get(Calendar.HOUR_OF_DAY));
		dtInicial.set(Calendar.MINUTE, cTmp.get(Calendar.MINUTE));

		cTmp.setTime(horaFinal);
		dtFinal.set(Calendar.HOUR_OF_DAY, cTmp.get(Calendar.HOUR_OF_DAY));
		dtFinal.set(Calendar.MINUTE, cTmp.get(Calendar.MINUTE));

		if (horaFinal.before(horaInicial)) {
			Calendar dtFinalDia = (Calendar) dataAtual.clone();
			dtFinalDia = DataUtil.getDataFimDia(dtFinalDia);

			Calendar dtInicioDia = (Calendar) dataAtual.clone();
			dtInicioDia = DataUtil.zerarHorario(dtInicioDia);

			if ((dataAtual.after(dtInicial) && dataAtual.before(dtFinalDia))
					|| (dataAtual.after(dtInicioDia) && dataAtual.before(dtFinal))) {
				return false;
			}
			return true;
		}

		if ((dataAtual.after(dtInicial) && dataAtual.before(dtFinal))) {
			return false;
		}
		return true;
	}

	public static Calendar getDataOntem() {
		Calendar diaAtual = Calendar.getInstance();
		diaAtual.add(Calendar.DAY_OF_MONTH, -1);
		return DataUtil.formatarData(diaAtual, EPeriodoDia.FIM);
	}
}