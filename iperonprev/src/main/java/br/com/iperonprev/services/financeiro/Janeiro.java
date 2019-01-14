package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class Janeiro extends HelperFinanceiro implements FinanceiroRrc{
	
	
	@Override
	public FinanceiroPorMesDto devolveFinanceiro(ContribuicoeseAliquotas contribuicao,String ano) {
		
		if (validaCompetenciaAno(contribuicao, ano, 1) == true) {
			
			fr.setJan(valorFormatado);
			
			return fr;
		}
		
		return this.proximo.devolveFinanceiro(contribuicao,ano);
	}
	
		

}
