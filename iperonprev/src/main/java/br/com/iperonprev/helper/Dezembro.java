package br.com.iperonprev.helper;

import java.math.BigDecimal;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.interfaces.FinanceiroPorMes;

public class Dezembro implements FinanceiroPorMes{

	
	@Override
	public void proximoPeriodo(FinanceiroPorMes financeiroPorMes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FinanceiroPorMesDto devolvePeriodo(String competencia, BigDecimal valor) {
		// TODO Auto-generated method stub
		return null;
	}

}
