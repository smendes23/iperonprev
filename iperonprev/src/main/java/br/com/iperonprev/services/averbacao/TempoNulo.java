package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.interfaces.TempoDeContribuicao;
import br.com.iperonprev.models.Averbacao;

public class TempoNulo implements TempoDeContribuicao{

	@Override
	public Averbacao devolvePeriodo(Averbacao a,int outrasDeducoes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void proximoPerido(TempoDeContribuicao tempoDeContribuicao) {
		// TODO Auto-generated method stub
		
	}

}
