package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.interfaces.CalculaContribuicao;

public class RegraAliquotaContribuicao5 implements CalculaContribuicao{

	private CalculaContribuicao proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicaoDto calcula(ContribuicaoDto contribuicao, Date dataPosse,BigDecimal contribuicaoPrevidenciaria, boolean verbaContributiva) {
		
		String dataContribuicao = new StringBuilder().append("01/")
				.append(contribuicao.getDESC_competencia().substring(0, 2))
				.append("/").append(contribuicao.getDESC_competencia().substring(2, 6)).toString();
		try{
			if(sdf.parse(dataContribuicao).after(sdf.parse("31/05/2006")) && sdf.parse(dataContribuicao).before(sdf.parse("20/03/2016"))){
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.11)).setScale(3, BigDecimal.ROUND_CEILING));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.115)).setScale(3, BigDecimal.ROUND_CEILING));
				return contribuicao;
			}
			
		}catch(Exception e){
			System.out.println("Erro regra aliquota 5");
		}
		return proximo.calcula(contribuicao,dataPosse,contribuicaoPrevidenciaria, verbaContributiva);
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		this.proximo = proximo;
	}
}
