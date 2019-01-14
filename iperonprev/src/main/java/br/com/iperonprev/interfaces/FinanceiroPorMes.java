package br.com.iperonprev.interfaces;

import java.math.BigDecimal;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;

public interface FinanceiroPorMes {
	FinanceiroPorMesDto devolvePeriodo(String competencia, BigDecimal valor);
	void proximoPeriodo(FinanceiroPorMes financeiroPorMes);
}
