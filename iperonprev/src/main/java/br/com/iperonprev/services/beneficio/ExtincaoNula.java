package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.interfaces.ExtincaoDoBeneficio;
import br.com.iperonprev.models.Pensao;

public class ExtincaoNula implements ExtincaoDoBeneficio{

	@Override
	public Date devolveDataDeExtincao(Pensao pensao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void proximaData(ExtincaoDoBeneficio extincaoDoBeneficio) {
		// TODO Auto-generated method stub
		
	}

}
