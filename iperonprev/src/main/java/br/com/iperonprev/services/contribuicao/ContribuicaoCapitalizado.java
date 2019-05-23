package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.interfaces.CalculaContribuicao;

public class ContribuicaoCapitalizado implements CalculaContribuicao{
	private CalculaContribuicao proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicaoDto calcula(ContribuicaoDto contribuicao, Date dataPosse,BigDecimal contribuicaoPrevidenciaria,boolean verbaContributiva ) {
		try{
			int ano = Integer.parseInt(contribuicao.getDESC_competencia().substring(2, 6));
//			contribuicao.setENUM_fundoPrevidenciario(FundoPrevidenciarioEnum.CAPITALIZADO);
			if(dataPosse.after(sdf.parse("20/03/2016")) && ano >= 2016){
				contribuicao.setNUMR_aliquotaSegurado(11);
				contribuicao.setNUMR_aliquotaPatronal(13.27);
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.11)).setScale(2, BigDecimal.ROUND_FLOOR));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.1327)).setScale(2, BigDecimal.ROUND_FLOOR));
				return contribuicao;
				
			}
				contribuicao.setNUMR_aliquotaSegurado(11);
				contribuicao.setNUMR_aliquotaPatronal(11.5);
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.11)).setScale(2, BigDecimal.ROUND_FLOOR));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.115)).setScale(2, BigDecimal.ROUND_FLOOR));
				return contribuicao;
			
		}catch(Exception e){
			System.out.println("sem");
		}
		return proximo.calcula(contribuicao,dataPosse,contribuicaoPrevidenciaria, verbaContributiva);
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		this.proximo = proximo;
	}

}
