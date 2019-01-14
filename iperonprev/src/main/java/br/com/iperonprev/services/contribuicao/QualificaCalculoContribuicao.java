package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.util.Date;

import br.com.iperonprev.interfaces.CalculaContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class QualificaCalculoContribuicao {

	public ContribuicoeseAliquotas executa(ContribuicoeseAliquotas contribuicao, Date dataPosse,BigDecimal contribuicaoPrevidenciaria){
		CalculaContribuicao contrib1 = new RegraAliquotaContribuicao1();
		CalculaContribuicao contrib2 = new RegraAliquotaContribuicao2();
		CalculaContribuicao contrib3 = new RegraAliquotaContribuicao3();
		CalculaContribuicao contrib4 = new RegraAliquotaContribuicao4();
//		CalculaContribuicao contrib5 = new RegraAliquotaContribuicao5();
		CalculaContribuicao contrib5 = new RegraAliquotaContribuicao6();
		CalculaContribuicao contrib6 = new RegraAliquotaContribuicao7();
		CalculaContribuicao contrib7 = new RegraAliquotaContribuicao8();
		CalculaContribuicao contrib8 = new RegraAliquotaContribuicao9();
		CalculaContribuicao nulo = new ContribuicaoNulo();
		
		contrib1.setProximoCalculo(contrib2);
		contrib2.setProximoCalculo(contrib3);
		contrib3.setProximoCalculo(contrib4);
		contrib4.setProximoCalculo(contrib5);
		contrib5.setProximoCalculo(contrib6);
		contrib6.setProximoCalculo(contrib7);
		contrib7.setProximoCalculo(contrib8);
		contrib8.setProximoCalculo(nulo);
		return contrib1.calcula(contribuicao, dataPosse,contribuicaoPrevidenciaria);
	}
}