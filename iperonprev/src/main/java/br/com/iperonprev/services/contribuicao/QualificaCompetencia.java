package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import br.com.iperonprev.models.PessoasFuncionais;

public class QualificaCompetencia {

	public void executa(DateTime dtI, DateTime dtF,PessoasFuncionais pf,BigDecimal baseDeCalculo) {
		CompetenciaTemplate temp1 = new AnosIguais(dtI, dtF,pf,baseDeCalculo);
		CompetenciaTemplate temp2 = new AnoInicioMaisUmIgualAnoFim(dtI, dtF,pf,baseDeCalculo);
		CompetenciaTemplate temp3 = new IntervaloMaior(dtI, dtF,pf,baseDeCalculo);
		CompetenciaTemplate nulo = new CompetenciaNula(dtI, dtF,pf,baseDeCalculo);
		
		temp1.setProximo(temp2);
		temp2.setProximo(temp3);
		temp3.setProximo(nulo);
		temp1.executa();
		
		
	}
}
