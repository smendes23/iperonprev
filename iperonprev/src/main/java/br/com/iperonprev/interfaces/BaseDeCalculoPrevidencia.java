package br.com.iperonprev.interfaces;

import java.util.Date;

import br.com.iperonprev.models.ContribuicoeseAliquotas;

public interface BaseDeCalculoPrevidencia {
	ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse);
	void setProximoCalculo(BaseDeCalculoPrevidencia proximo);
}
