package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class Default implements FinanceiroRrc{

	@Override
	public FinanceiroPorMesDto devolveFinanceiro(ContribuicoeseAliquotas contribuicao, String ano) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProximoFinanceiro(FinanceiroRrc proximo) {
		// TODO Auto-generated method stub
		
	}

}
