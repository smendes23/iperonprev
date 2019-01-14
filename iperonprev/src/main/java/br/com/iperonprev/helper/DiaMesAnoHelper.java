package br.com.iperonprev.helper;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.services.temposPrevidenciarios.AvaliaDevolvePeriodoAproveitado;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class DiaMesAnoHelper {
	static LocalDate dataLocal;
	static int dia;
	static int mes;
	static int ano;
	static Period periodo;
	final int diasMes = 30;
	final int mesesAno = 12;
	DiaMesAno dmaRes = new DiaMesAno();
	
	@SuppressWarnings("static-access")
	public  DiaMesAno devolve(Date dataInicio, Date dataFim) {
		int bissextos = devolveBissextos(dataInicio, dataFim);
		
		periodo = new Period(dataLocal.fromDateFields(dataInicio), dataLocal.fromDateFields(dataFim),
				PeriodType.yearMonthDay()).plusDays(bissextos);
		
		dia = periodo.getDays();
		mes = periodo.getMonths();
		ano = periodo.getYears();
		return ordenaPeriodo(new DiaMesAno(dia, mes, ano));
	}

	@SuppressWarnings("static-access")
	public  DiaMesAno devolveDiaMesAnoSimulacao(Date dataInicio, Date dataFim) {
		periodo = new Period(dataLocal.fromDateFields(dataInicio), dataLocal.fromDateFields(dataFim),
				PeriodType.yearMonthDay());
		
		dia = periodo.getDays();
		mes = periodo.getMonths();
		ano = periodo.getYears();
		return ordenaPeriodo(new DiaMesAno(dia, mes, ano));
	}
	
	private DiaMesAno ordenaPeriodo(DiaMesAno dma) {

		dia = dma.getDia();

		if (dia == 31) {
			dia = 30;
		}

		mes = dma.getMes();
		ano = dma.getAno();

		if (dia >= 30) {
			mes += (dia / 30);
			dia %= 30;
		}

		if (mes >= 12) {
			ano += (mes / 12);
			mes %= 12;
		}
		DiaMesAno d = new DiaMesAno(dia, mes, ano);

		return d;
	}
	
	private int devolveBissextos(Date data1, Date data2) {

		int bissextos = 0;
		for (int i = new LocalDate(data1).getYear(); i < new LocalDate(data2).getYear(); i++) {
			if (new DateTime().withYear(i).year().isLeap() == true) {
				bissextos++;
			}
		}

		return bissextos;
	}
	
	public DiaMesAno subtraiDoisPeriodos(DiaMesAno periodo1, DiaMesAno periodo2) {

		if (periodo2.getMes() > periodo1.getMes()) {
			mes = periodo1.getMes() + 12;
			ano = periodo1.getAno() - 1;
		} else {
			mes = periodo1.getMes();
			ano = periodo1.getAno();
		}

		if (periodo2.getDia() > periodo1.getDia()) {
			dia = periodo1.getDia() + 30;
			mes--;
		} else {
			dia = periodo1.getDia();
		}

		return new DiaMesAno(dia - periodo2.getDia(), mes - periodo2.getMes(), ano - periodo2.getAno());
	}
	
	public DiaMesAno somaPeriodos(List<Averbacao> lista){
		
		for (Averbacao averbacao : lista) {
			DiaMesAno dma = new DiaMesAno();
			dma =  new AvaliaDevolvePeriodoAproveitado().executa(averbacao);
			dmaRes = new DiaMesAno(dmaRes.getDia()+dma.getDia(), dmaRes.getMes()+dma.getMes(), dmaRes.getAno()+dma.getAno());
		}
		return dmaRes;
	}
	
	public String devolvePeriodoFormatado() {
		DiaMesAno dm = ordenaPeriodo(dmaRes);
		return dm.getAno() + " ano(s), " + dm.getMes() + " mes(es) e " + dm.getDia() + " dia(s).";
	}
}
