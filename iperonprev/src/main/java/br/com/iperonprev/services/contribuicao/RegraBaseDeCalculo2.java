package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.interfaces.BaseDeCalculoPrevidencia;
import br.com.iperonprev.models.ContribuicoeseAliquotas;

public class RegraBaseDeCalculo2 implements BaseDeCalculoPrevidencia{

	private BaseDeCalculoPrevidencia proximo;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public ContribuicoeseAliquotas calcula(ContribuicoeseAliquotas contribuicao, Date dataPosse) {
		String dataContribuicao = new StringBuilder().append("01/")
				.append(contribuicao.getDESC_competencia().substring(0, 2))
				.append("/").append(contribuicao.getDESC_competencia().substring(2, 6)).toString();
		try{
			if(sdf.parse(dataContribuicao).after(sdf.parse("31/03/2000")) && sdf.parse(dataContribuicao).before(sdf.parse("01/06/2006"))){
				contribuicao.setNUMR_aliquotaSegurado(8);
				contribuicao.setNUMR_aliquotaPatronal(8);
				contribuicao.setVALR_contribSegurado(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.08)).setScale(3, BigDecimal.ROUND_DOWN));
				contribuicao.setVALR_contribPatronal(contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(0.08)).setScale(3, BigDecimal.ROUND_DOWN));
				return contribuicao;
			}
			
		}catch(Exception e){
			System.out.println("Erro regra aliquota 2");
		}
		return proximo.calcula(contribuicao,dataPosse);
	}

	@Override
	public void setProximoCalculo(BaseDeCalculoPrevidencia proximo) {
		this.proximo = proximo;
	}

}
