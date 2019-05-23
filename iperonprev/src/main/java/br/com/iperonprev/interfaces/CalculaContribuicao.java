package br.com.iperonprev.interfaces;

import java.math.BigDecimal;
import java.util.Date;

import br.com.iperonprev.controller.dto.ContribuicaoDto;

public interface CalculaContribuicao {
	ContribuicaoDto calcula(ContribuicaoDto contribuicao, Date dataPosse, BigDecimal contribuicaoPrevidenciaria, boolean verbaContributiva);
	void setProximoCalculo(CalculaContribuicao proximo);
}
