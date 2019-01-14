package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.interfaces.CalculaContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class RegraAliquotaContribuicao4 implements CalculaContribuicao{

	private CalculaContribuicao proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse,BigDecimal contribuicaoPrevidenciaria) {
		String dataContribuicao = new StringBuilder().append("01/")
				.append(contribuicao.getDESC_competencia().substring(0, 2))
				.append("/").append(contribuicao.getDESC_competencia().substring(2, 6)).toString();
		
		try{
			if(sdf.parse(dataContribuicao).after(sdf.parse("31/12/2009")) && sdf.parse(dataContribuicao).before(sdf.parse("23/03/2016"))){
				contribuicao.setNUMR_aliquotaSegurado(11);
				contribuicao.setVALR_contribuicaoPrevidenciaria(contribuicaoPrevidenciaria.divide(new BigDecimal("0.11"),3, RoundingMode.FLOOR));
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal("0.11")).setScale(3, RoundingMode.FLOOR));
				
				contribuicao.setNUMR_aliquotaPatronal(11.5);
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal("0.115")).setScale(3, RoundingMode.FLOOR));
				return contribuicao;
			}
			
		}catch(Exception e){
			System.out.println("Erro regra aliquota 4");
		}
		return proximo.calcula(contribuicao,dataPosse,contribuicaoPrevidenciaria);
	}

	@Override
	public void setProximoCalculo(CalculaContribuicao proximo) {
		this.proximo = proximo;
	}
}
