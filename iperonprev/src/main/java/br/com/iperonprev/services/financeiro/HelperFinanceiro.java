package br.com.iperonprev.services.financeiro;

import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.util.jsf.DecimalFormatter;

public class HelperFinanceiro implements FinanceiroRrc{

	protected FinanceiroRrc proximo;
	protected String valorFormatado = new String();
	DecimalFormatter df = new DecimalFormatter();
	FinanceiroPorMesDto fr = new FinanceiroPorMesDto();
	@Override
	
	public FinanceiroPorMesDto devolveFinanceiro(ContribuicoeseAliquotas contribuicao, String ano) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setProximoFinanceiro(FinanceiroRrc proximo) {
		this.proximo = proximo;
		
	}
	
	protected boolean  validaCompetenciaAno(ContribuicoeseAliquotas contribuicao, String ano,int mesEmNumero){
		if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == mesEmNumero
				&& contribuicao.getDESC_competencia().substring(2, 6).equals(ano)) {
			
			valorFormatado = df.formatterToCurrencyBrazilianWithoutSymbol(
					contribuicao.getVALR_contribuicaoPrevidenciaria());
			return true;
			
		}
		return false;
	}
}
