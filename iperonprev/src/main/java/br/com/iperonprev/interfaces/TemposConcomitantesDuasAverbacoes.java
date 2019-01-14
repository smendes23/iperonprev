package br.com.iperonprev.interfaces;

import br.com.iperonprev.models.Averbacao;

public interface TemposConcomitantesDuasAverbacoes {
	boolean verificaConcomitancia(Averbacao a1,int diasPeriodo1,Averbacao a2,int diasPeriodo2);
	void setProximaConcomitancia(TemposConcomitantesDuasAverbacoes proximo);
}
