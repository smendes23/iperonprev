package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public interface FinanceiroRrc {

	FinanceiroPorMesDto devolveFinanceiro(ContribuicoeseAliquotas contribuicao,String ano);
	void setProximoFinanceiro(FinanceiroRrc proximo);
}
