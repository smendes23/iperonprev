package br.com.iperonprev.services.averbacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class Periodo {
	public Period retornaPeriodo(String dtInicio, String dtFim, PeriodType tipo)throws ParseException{
		
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar inicio = new GregorianCalendar();
		GregorianCalendar fim = new GregorianCalendar();

		inicio.setTime(formata.parse(dtInicio));
		fim.setTime(formata.parse(dtFim));

		String mesIn = dtInicio.substring(3, 5);
		int mesI = Integer.parseInt(mesIn);

		String mesFi = dtFim.substring(3, 5);
		int mesF = Integer.parseInt(mesFi);

		DateTime start, end;

		Period p;

		@SuppressWarnings("unused")
		int diasMes = 30, mesesAno = 12;

		start = new DateTime(inicio.get(GregorianCalendar.YEAR), mesI,
				inicio.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0, 0);
		end = new DateTime(fim.get(GregorianCalendar.YEAR), mesF,
				fim.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0, 0);
		p = new Period(start, end, tipo);
		return p;
		
	}
}
