package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.util.Date;

import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.interfaces.CalculaContribuicao;

public class ContribuicaoNulo implements CalculaContribuicao{

	@Override
	public ContribuicaoDto calcula(ContribuicaoDto contribuicao,Date dataPosse,BigDecimal contribuicaoPrevidenciaria,boolean verbaContributiva) {
		// TODO Auto-generated method stub
		return new ContribuicaoDto();
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		// TODO Auto-generated method stub
		
	}

}
