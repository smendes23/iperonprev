package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;

public class IntervaloMaior extends CompetenciaTemplate{

	public IntervaloMaior() {
	}
	
	public IntervaloMaior(DateTime dtI, DateTime dtF,PessoasFuncionais pf,BigDecimal baseDeCalculo) {
		super(dtI,dtF,pf,baseDeCalculo);
	}
	
	
	@Override
	protected void processaPeriodo() {
		Interval intervalo = new Interval(dtI, dtF);
		Period periodo = intervalo.toPeriod();
		listaDeContribuicoes.addAll(processaData(dtI, dtI.withMonthOfYear(12)));

		for (int i = 1; i < periodo.getYears(); i++) {
			listaDeContribuicoes.addAll(processaData(dtI.withMonthOfYear(1).plusYears(i),dtI.withDayOfMonth(1).withMonthOfYear(12).plusYears(i)));
		}
		listaDeContribuicoes.addAll(processaData(dtF.withMonthOfYear(1),dtF));
		
		if(!listaDeContribuicoes.isEmpty()) {
			
			listaDeContribuicoes.forEach(l->{
				/*ContribuicoeseAliquotas ca = new ContribuicoeseAliquotas();
				ca = new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).buscarPorId(l.getNUMG_idDoObjeto());*/
				new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).salvar(l);
				
			});
			
		}

	}

	@Override
	protected boolean validaAno() {
		return (dtI.getYear()+1) < dtF.getYear();
	}
	
}
