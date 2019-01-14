package br.com.iperonprev.services.temposPrevidenciarios;

import br.com.iperonprev.helper.DiaMesAnoHelper;
import br.com.iperonprev.interfaces.temposPrevidenciarios.TemposAverbados;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class PeriodoDefault implements TemposAverbados{

	@Override
	public DiaMesAno devolvePeriodo(Averbacao a) {
		return new DiaMesAnoHelper().devolve(a.getDATA_admissao(), a.getDATA_demissao());
	}

	@Override
	public void proximoPerido(TemposAverbados tempoAverbado) {
		
	}

}
