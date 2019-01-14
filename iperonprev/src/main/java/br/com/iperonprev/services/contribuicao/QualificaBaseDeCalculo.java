package br.com.iperonprev.services.contribuicao;

import java.util.Date;

import br.com.iperonprev.interfaces.BaseDeCalculoPrevidencia;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class QualificaBaseDeCalculo {

	public ContribuicoeseAliquotas executa(ContribuicoeseAliquotas contribuicao, Date dataPosse){
		BaseDeCalculoPrevidencia contrib1 = new RegraBaseDeCalculo1();
		BaseDeCalculoPrevidencia contrib2 = new RegraBaseDeCalculo2();
		BaseDeCalculoPrevidencia contrib3 = new RegraBaseDeCalculo3();
		BaseDeCalculoPrevidencia contrib4 = new RegraBaseDeCalculo4();
		BaseDeCalculoPrevidencia contrib5 = new RegraBaseDeCalculo5();
		BaseDeCalculoPrevidencia contrib6 = new RegraBaseDeCalculo7();
		BaseDeCalculoPrevidencia contrib7 = new RegraBaseDeCalculo8();
		BaseDeCalculoPrevidencia contrib8 = new RegraBaseDeCalculo9();
		BaseDeCalculoPrevidencia nulo = new ZeraRegraBaseDeCalculo();
		
		contrib1.setProximoCalculo(contrib2);
		contrib2.setProximoCalculo(contrib3);
		contrib3.setProximoCalculo(contrib4);
		contrib4.setProximoCalculo(contrib5);
		contrib5.setProximoCalculo(contrib6);
		contrib6.setProximoCalculo(contrib7);
		contrib7.setProximoCalculo(contrib8);
		contrib8.setProximoCalculo(nulo);
		return contrib1.calcula(contribuicao, dataPosse);
	}
}
