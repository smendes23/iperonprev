package br.com.iperonprev.interfaces;

import br.com.iperonprev.models.Averbacao;

public interface TempoDeContribuicao {
	Averbacao devolvePeriodo(Averbacao a,int outrasDeducoes);
	void proximoPerido(TempoDeContribuicao tempoDeContribuicao);
}
