package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.interfaces.TempoDeContribuicao;
import br.com.iperonprev.models.Averbacao;

public class QualificaTempoDeContribuicao {
	public Averbacao executa(Averbacao averbacao,int outrasDeducoes){
		
		TempoDeContribuicao sem = new TempoSemConcomitancia();
		TempoDeContribuicao com = new TempoComConcomitancia();
		TempoDeContribuicao comEdeducao = new TempoComConcomitanciaDeducao();
		TempoDeContribuicao comDeducao = new TempoComDeducao();
		TempoDeContribuicao tempoNulo = new TempoNulo();
		
		sem.proximoPerido(com);
		com.proximoPerido(comDeducao);
		comDeducao.proximoPerido(comEdeducao);
		comEdeducao.proximoPerido(tempoNulo);
		return sem.devolvePeriodo(averbacao,outrasDeducoes);
	}
}
