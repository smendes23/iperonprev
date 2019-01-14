package br.com.iperonprev.interfaces;

import br.com.iperonprev.models.Averbacao;

public interface TemposConcomitanteUmaAverbacao {
	boolean verificaConcomitancia(Averbacao a1);
	void setProximaConcomitancia(TemposConcomitanteUmaAverbacao proximo);
}
