package br.com.iperonprev.services.averbacao;

import java.text.ParseException;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import br.com.iperonprev.interfaces.FormataTempoServico;



public class DiasDeduzidos implements FormataTempoServico{

	@Override
	public String tempoFormatado(String dtInicio, String dtFim)
			throws ParseException {
		
		String res = null;

		Period p = new Periodo().retornaPeriodo(dtInicio, dtFim, PeriodType.days()).plusDays(1);
		res = String.valueOf(p.getDays());
		return res;
	}

}
