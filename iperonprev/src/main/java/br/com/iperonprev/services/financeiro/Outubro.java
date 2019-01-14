package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class Outubro extends HelperFinanceiro implements FinanceiroRrc{

	@Override
	public FinanceiroPorMesDto devolveFinanceiro(ContribuicoeseAliquotas contribuicao,String ano) {
		
		if (validaCompetenciaAno(contribuicao, ano, 10) == true) {
			
			fr.setOut(valorFormatado);
			
			return fr;
		}
		
		return this.proximo.devolveFinanceiro(contribuicao,ano);
	}
}
