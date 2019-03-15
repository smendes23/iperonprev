package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
/**
 * 
 * @author Cassiana Lira
 *Valida se o ano da data inicial+1 é igual ao ano da data final
 */
public class AnoInicioMaisUmIgualAnoFim extends CompetenciaTemplate{

	public AnoInicioMaisUmIgualAnoFim() {
	}
	
	public AnoInicioMaisUmIgualAnoFim(DateTime dtI, DateTime dtF,PessoasFuncionais pf,BigDecimal baseDeCalculo) {
		super(dtI,dtF,pf,baseDeCalculo);
	}
	
	
	@Override
	protected void processaPeriodo() {
		listaDeContribuicoes.addAll(processaData(dtI, dtF.withDayOfMonth(1).withMonthOfYear(12)));
		listaDeContribuicoes.addAll(processaData(dtF.withDayOfMonth(1).withMonthOfYear(1), dtF));
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
		return (dtI.getYear()+1) == dtF.getYear();
	}

}
