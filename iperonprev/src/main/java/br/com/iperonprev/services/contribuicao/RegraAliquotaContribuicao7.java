package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.interfaces.CalculaContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class RegraAliquotaContribuicao7 implements CalculaContribuicao{

	private CalculaContribuicao proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicaoDto calcula(ContribuicaoDto contribuicao, Date dataPosse,BigDecimal contribuicaoPrevidenciaria,boolean verbaContributiva) {
		String dataContribuicao = new StringBuilder().append("01/")
				.append(contribuicao.getDESC_competencia().substring(0, 2))
				.append("/").append(contribuicao.getDESC_competencia().substring(2, 6)).toString();
		
		try{
			if(sdf.parse(dataContribuicao).after(sdf.parse("31/03/2017")) && sdf.parse(dataContribuicao).before(sdf.parse("01/01/2018"))){
				contribuicao.setNUMR_aliquotaSegurado(11.5);
				contribuicao.setNUMR_aliquotaPatronal(12.5);
				
				if(verbaContributiva == false) {
					contribuicao.setVALR_contribuicaoPrevidenciaria(contribuicaoPrevidenciaria.divide(new BigDecimal("0.115"),3, RoundingMode.DOWN));
				}
				
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.115)).setScale(3, BigDecimal.ROUND_DOWN));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.125)).setScale(3, BigDecimal.ROUND_DOWN));
				return contribuicao;
			}
			
		}catch(Exception e){
			System.out.println("Erro regra aliquota 7");
		}
		return proximo.calcula(contribuicao,dataPosse,contribuicaoPrevidenciaria,verbaContributiva);
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		this.proximo = proximo;
	}
}
