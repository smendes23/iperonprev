package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.interfaces.CalculaContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class ContribuicaoFinanceiro implements CalculaContribuicao{
	private CalculaContribuicao proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao,Date dataPosse,BigDecimal contribuicaoPrevidenciaria) {
		int ano = Integer.parseInt(contribuicao.getDESC_competencia().substring(2, 6));
		try{
			if(dataPosse.before(sdf.parse("31/12/2009")) &&  ano >= 2000){
				contribuicao.setNUMR_aliquotaSegurado(11);
				contribuicao.setNUMR_aliquotaPatronal(11.5);
//				contribuicao.setENUM_fundoPrevidenciario(FundoPrevidenciarioEnum.FINANCEIRO);
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.11)).setScale(2, BigDecimal.ROUND_FLOOR));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.115)).setScale(2, BigDecimal.ROUND_FLOOR));
				return contribuicao;
			}else{
				contribuicao.setNUMR_aliquotaSegurado(8);
				contribuicao.setNUMR_aliquotaPatronal(8.5);
//				contribuicao.setENUM_fundoPrevidenciario(FundoPrevidenciarioEnum.FINANCEIRO);
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.08)).setScale(2, BigDecimal.ROUND_FLOOR));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.085)).setScale(2, BigDecimal.ROUND_FLOOR));
				return contribuicao;
			}
		}catch(Exception e){
			System.out.println("sem");
		}
		return proximo.calcula(contribuicao,dataPosse,contribuicaoPrevidenciaria);
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		this.proximo = proximo;
	}

}
