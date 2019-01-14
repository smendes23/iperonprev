package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class QualificaFinanceiro {

	public FinanceiroPorMesDto executa(ContribuicoeseAliquotas contribuicao,String ano){
		FinanceiroRrc janeiro = new Janeiro();
		FinanceiroRrc fevereiro = new Fevereiro();
		FinanceiroRrc marco = new Marco();
		FinanceiroRrc abril = new Abril();
		FinanceiroRrc maio = new Maio();
		FinanceiroRrc junho = new Junho();
		FinanceiroRrc julho = new Julho();
		FinanceiroRrc agosto = new Agosto();
		FinanceiroRrc setembro = new Setembro();
		FinanceiroRrc outubro = new Outubro();
		FinanceiroRrc novembro = new Novembro();
		FinanceiroRrc dezembro = new Dezembro();
		FinanceiroRrc padrao = new Default();
		
		janeiro.setProximoFinanceiro(fevereiro);
		fevereiro.setProximoFinanceiro(marco);
		marco.setProximoFinanceiro(abril);
		abril.setProximoFinanceiro(maio);
		maio.setProximoFinanceiro(junho);
		junho.setProximoFinanceiro(julho);
		julho.setProximoFinanceiro(agosto);
		agosto.setProximoFinanceiro(setembro);
		setembro.setProximoFinanceiro(outubro);
		outubro.setProximoFinanceiro(novembro);
		novembro.setProximoFinanceiro(dezembro);
		dezembro.setProximoFinanceiro(padrao);
		
		return janeiro.devolveFinanceiro(contribuicao, ano);
	}
}
