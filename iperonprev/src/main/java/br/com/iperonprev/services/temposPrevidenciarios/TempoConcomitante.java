package br.com.iperonprev.services.temposPrevidenciarios;

import br.com.iperonprev.helper.DiaMesAnoHelper;
import br.com.iperonprev.interfaces.temposPrevidenciarios.TemposPrevidenciarios;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class TempoConcomitante implements TemposPrevidenciarios{

	@Override
	public DiaMesAno devolve(Averbacao averbacao) {
		DiaMesAno dma = new DiaMesAno();
		try{
			dma = new DiaMesAnoHelper().devolve(averbacao.getDATA_inicioConcomitancia(), averbacao.getDATA_fimConcomitancia()); 
		}catch(Exception e){
//			System.out.println("Data concomitância nula.");
		}
		return dma;
	}

}
