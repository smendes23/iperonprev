package br.com.iperonprev.services.temposPrevidenciarios;

import br.com.iperonprev.util.averbacao.DiaMesAno;

public abstract class PeriodosDecorator {

	private PeriodosDecorator outroPeriodo;

	public PeriodosDecorator getOutroPeriodo() {
		return outroPeriodo;
	}

	public void setOutroPeriodo(PeriodosDecorator outroPeriodo) {
		this.outroPeriodo = outroPeriodo;
	}
	
	public PeriodosDecorator() {
		this.outroPeriodo = null;
	}
	
	public PeriodosDecorator(PeriodosDecorator outroPeriodo) {
		this.outroPeriodo = outroPeriodo;
	}
	
	public abstract DiaMesAno calculaPeriodo(DiaMesAno dma);
	
	protected DiaMesAno calculaOutroPeriodo(DiaMesAno dma){
		DiaMesAno dm = new DiaMesAno(0, 0, 0);
		if(outroPeriodo == null) return dm;
		return outroPeriodo.calculaOutroPeriodo(dma);
	}
}
