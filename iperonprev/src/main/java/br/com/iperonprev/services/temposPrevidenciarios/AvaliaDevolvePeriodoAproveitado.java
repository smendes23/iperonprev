package br.com.iperonprev.services.temposPrevidenciarios;

import br.com.iperonprev.interfaces.temposPrevidenciarios.TemposAverbados;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class AvaliaDevolvePeriodoAproveitado{

	public DiaMesAno executa(Averbacao a){
		TemposAverbados concomitante = new  PeriodoComConcomitancia();
		TemposAverbados deducao = new  PeriodoComDeducao();
		TemposAverbados padrao = new  PeriodoDefault();
		
		concomitante.proximoPerido(deducao);
		deducao.proximoPerido(padrao);
		
		return concomitante.devolvePeriodo(a);
	}

}
