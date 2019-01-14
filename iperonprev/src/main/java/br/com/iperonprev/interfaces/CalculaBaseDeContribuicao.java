package br.com.iperonprev.interfaces;

import java.math.BigDecimal;

import br.com.iperonprev.models.Financeiro;

public interface CalculaBaseDeContribuicao {
	BigDecimal calculaBase(Financeiro financeiro);
	void setProximoCalculo(CalculaBaseDeContribuicao proximo);
}
