package br.com.iperonprev.interfaces;

import java.math.BigDecimal;
import java.util.Date;

import br.com.iperonprev.models.ContribuicoeseAliquotas;

public interface CalculaContribuicao {
	ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse, BigDecimal contribuicaoPrevidenciaria);
	void setProximoCalculo(CalculaContribuicao proximo);
}
