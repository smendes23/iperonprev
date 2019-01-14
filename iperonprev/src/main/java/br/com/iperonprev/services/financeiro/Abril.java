package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class Abril extends HelperFinanceiro implements FinanceiroRrc{

	@Override
	public FinanceiroPorMesDto devolveFinanceiro(ContribuicoeseAliquotas contribuicao,String ano) {
		
		if (validaCompetenciaAno(contribuicao, ano, 4) == true) {
			
			fr.setAbr(valorFormatado);
			
			return fr;
		}
		
		return this.proximo.devolveFinanceiro(contribuicao,ano);
	}
}
