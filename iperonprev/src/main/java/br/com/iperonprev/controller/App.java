package br.com.iperonprev.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.contribuicao.QualificaCompetencia;

public class App {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTime dtI = new DateTime(sdf.parse("01/03/2000"));
		DateTime dtF = new DateTime(sdf.parse("03/09/2003"));
		
		Interval intervalo = new Interval(dtI, dtF);
		Period periodo = intervalo.toPeriod();
		

		new QualificaCompetencia().executa(dtI, dtF, new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(19411),new BigDecimal("250.25"));
//		String comp = "012000";
//		System.out.println(comp.substring(2, 6));
		
		/*List<ContribuicoeseAliquotas> lista = new ContribuicaoDao().devolveListaContribuicaoPor("012001", "042001", 19411);
		
		lista.forEach(l->{
			System.out.println(l.getDESC_competencia());
		});*/
		
		
		
		
	}
	

}
