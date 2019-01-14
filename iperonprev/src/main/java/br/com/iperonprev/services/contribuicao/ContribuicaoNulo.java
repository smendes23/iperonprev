package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.util.Date;

import br.com.iperonprev.interfaces.CalculaContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class ContribuicaoNulo implements CalculaContribuicao{

	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao,Date dataPosse,BigDecimal contribuicaoPrevidenciaria) {
		// TODO Auto-generated method stub
		return new ContribuicoeseAliquotas();
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		// TODO Auto-generated method stub
		
	}

}
