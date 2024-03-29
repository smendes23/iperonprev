package br.com.iperonprev.services.temposPrevidenciarios;

import br.com.iperonprev.interfaces.temposPrevidenciarios.TemposPrevidenciarios;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class TempoRPPS implements TemposPrevidenciarios{

	@Override
	public DiaMesAno devolve(Averbacao averbacao) {
		DiaMesAno dma = new DiaMesAno();
		if(averbacao.getNUMR_regime().getNUMG_idDoObjeto() == 2){
			dma = new AvaliaDevolvePeriodoAproveitado().executa(averbacao);
		}
		return dma;
	}

}
