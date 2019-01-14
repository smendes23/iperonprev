package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.interfaces.TemposConcomitantesDuasAverbacoes;
import br.com.iperonprev.models.Averbacao;

public class ConcomitanciaNulaDuasAverbacoes implements TemposConcomitantesDuasAverbacoes{

	@Override
	public boolean verificaConcomitancia(Averbacao a1,int diasPeriodo1,Averbacao a2,int diasPeriodo2) {
		return true;
	}

	@Override
	public void setProximaConcomitancia(TemposConcomitantesDuasAverbacoes proximo) {
		// TODO Auto-generated method stub
		
	}

}
