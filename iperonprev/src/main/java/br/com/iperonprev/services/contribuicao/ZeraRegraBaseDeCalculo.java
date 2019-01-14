package br.com.iperonprev.services.contribuicao;

import java.util.Date;

import br.com.iperonprev.interfaces.BaseDeCalculoPrevidencia;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class ZeraRegraBaseDeCalculo implements BaseDeCalculoPrevidencia {

	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse) {
		// TODO Auto-generated method stub
		return new ContribuicoeseAliquotas();
	}

	@Override
	public void setProximoCalculo(BaseDeCalculoPrevidencia proximo) {
		// TODO Auto-generated method stub
		
	}



}
