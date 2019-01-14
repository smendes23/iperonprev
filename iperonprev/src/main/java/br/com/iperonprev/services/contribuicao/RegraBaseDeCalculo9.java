package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.interfaces.BaseDeCalculoPrevidencia;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class RegraBaseDeCalculo9 implements BaseDeCalculoPrevidencia{

	private BaseDeCalculoPrevidencia proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse) {
		contribuicao.setNUMR_aliquotaSegurado(11);
		contribuicao.setNUMR_aliquotaPatronal(11.5);
		String dataContribuicao = new StringBuilder().append("01/")
				.append(contribuicao.getDESC_competencia().substring(0, 2))
				.append("/").append(contribuicao.getDESC_competencia().substring(2, 6)).toString();
		try{
			if(sdf.parse(dataContribuicao).after(sdf.parse("31/12/2018"))){
				contribuicao.setNUMR_aliquotaSegurado(13.5);
				contribuicao.setNUMR_aliquotaPatronal(14.5);
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.135)).setScale(3, BigDecimal.ROUND_DOWN));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.145)).setScale(3, BigDecimal.ROUND_DOWN));
				return contribuicao;
			}
			
		}catch(Exception e){
			System.out.println("Erro regra aliquota 5");
		}
		return proximo.calcula(contribuicao,dataPosse);
	}

	@Override
	public void setProximoCalculo(BaseDeCalculoPrevidencia proximo) {
		this.proximo = proximo;
	}
}
