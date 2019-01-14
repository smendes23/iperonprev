package br.com.iperonprev.services.temposPrevidenciarios;

import org.joda.time.LocalDate;

import br.com.iperonprev.helper.DiaMesAnoHelper;
import br.com.iperonprev.interfaces.temposPrevidenciarios.TemposAverbados;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class PeriodoComConcomitancia implements TemposAverbados {
	TemposAverbados proximo;
	@Override
	public DiaMesAno devolvePeriodo(Averbacao a) {
		DiaMesAno dma = new DiaMesAno();
		if(a.getDATA_inicioConcomitancia() != null){
			dma = new DiaMesAnoHelper().devolve(a.getDATA_inicioConcomitancia(),
							new LocalDate(a.getDATA_fimConcomitancia()).plusDays(a.getNUMR_deducao()).toDate());
			return new DiaMesAnoHelper().subtraiDoisPeriodos(new DiaMesAnoHelper().devolve(a.getDATA_admissao(), a.getDATA_demissao()), dma); 
		}
		
		return this.proximo.devolvePeriodo(a);
	}

	@Override
	public void proximoPerido(TemposAverbados tempoAverbado) {
		this.proximo = tempoAverbado;
	}


}
