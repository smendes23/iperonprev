package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;

public class AnosIguais extends CompetenciaTemplate{
	
	public AnosIguais() {}
	
	public AnosIguais(DateTime dtI, DateTime dtF,PessoasFuncionais pf,BigDecimal baseDeCalculo) {
		super(dtI, dtF,pf,baseDeCalculo);
	}

	@Override
	protected void processaPeriodo() {
		listaDeContribuicoes.addAll(processaData(dtI, dtF));
		
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
		return dtI.getYear() == dtF.getYear();
	}

}
