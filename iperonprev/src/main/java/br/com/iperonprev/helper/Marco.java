package br.com.iperonprev.helper;

import java.math.BigDecimal;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.interfaces.FinanceiroPorMes;
import br.com.iperonprev.util.jsf.DecimalFormatter;

public class Marco implements FinanceiroPorMes{

	FinanceiroPorMes proximo;
	DecimalFormatter df = new DecimalFormatter();
	FinanceiroPorMesDto fr = new FinanceiroPorMesDto();
	
	@Override
	public FinanceiroPorMesDto devolvePeriodo(String competencia, BigDecimal valor) {
		if (Integer.parseInt(competencia.substring(0, 2)) == 3) {
			fr.setMar(df.formatterToCurrencyBrazilianWithoutSymbol(valor));
			return fr;
		}
		return this.proximo.devolvePeriodo(competencia, valor);
	}

	@Override
	public void proximoPeriodo(FinanceiroPorMes financeiroPorMes) {
		this.proximo = financeiroPorMes;
	}

	
}
