package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.interfaces.TemposConcomitanteUmaAverbacao;
import br.com.iperonprev.models.Averbacao;

public class ConcomitanciaNulaUmaAverbacao implements TemposConcomitanteUmaAverbacao{

	@Override
	public boolean verificaConcomitancia(Averbacao a1) {
		return true;
	}

	@Override
	public void setProximaConcomitancia(TemposConcomitanteUmaAverbacao proximo) {
	}
	

}
