package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import br.com.iperonprev.models.PessoasFuncionais;

public class CompetenciaNula extends CompetenciaTemplate{

	public CompetenciaNula() {
		// TODO Auto-generated constructor stub
	}
	
	public CompetenciaNula(DateTime dtI, DateTime dtF,PessoasFuncionais pf,BigDecimal baseDeCalculo) {
		super(dtI, dtF,pf,baseDeCalculo);
	}
	@Override
	protected void processaPeriodo() {
		
	}

	@Override
	protected boolean validaAno() {
		// TODO Auto-generated method stub
		return false;
	}

}
