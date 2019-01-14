package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.interfaces.CalculaContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class RegraAliquotaContribuicao10 implements CalculaContribuicao{

	private CalculaContribuicao proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse,BigDecimal contribuicaoPrevidenciaria) {
		String dataContribuicao = new StringBuilder().append("01/")
				.append(contribuicao.getDESC_competencia().substring(0, 2))
				.append("/").append(contribuicao.getDESC_competencia().substring(2, 6)).toString();
		
		
		try{
			if(sdf.parse(dataContribuicao).after(sdf.parse("31/12/2017"))){
				contribuicao.setNUMR_aliquotaSegurado(13.5);
				contribuicao.setNUMR_aliquotaSegurado(14.5);
				contribuicao.setVALR_contribuicaoPrevidenciaria(contribuicaoPrevidenciaria.divide(new BigDecimal("0.135"),3, RoundingMode.CEILING));
				contribuicao.setVALR_contribSegurado(contribuicaoPrevidenciaria.multiply(new BigDecimal(0.135)).setScale(3, BigDecimal.ROUND_CEILING));
				contribuicao.setVALR_contribPatronal(contribuicaoPrevidenciaria.multiply(new BigDecimal(0.145)).setScale(3, BigDecimal.ROUND_CEILING));
				return contribuicao;
			}
			
		}catch(Exception e){
			System.out.println("Erro regra aliquota 8");
		}
		return proximo.calcula(contribuicao,dataPosse,contribuicaoPrevidenciaria);
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		this.proximo = proximo;
	}
}
