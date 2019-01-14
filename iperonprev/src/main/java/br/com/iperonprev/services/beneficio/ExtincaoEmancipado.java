package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.interfaces.ExtincaoDoBeneficio;
import br.com.iperonprev.models.Pensao;

public class ExtincaoEmancipado implements ExtincaoDoBeneficio{
	private ExtincaoDoBeneficio proximo;
	@Override
	public Date devolveDataDeExtincao(Pensao pensao) {
		try{
			/*if("Filho(a)".equals(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome()) 
					&& pensao.getENUM_emancipado() == DecisaoEnum.SIM){
				return new LocalDate().now().toDate();
			}*/
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return proximo.devolveDataDeExtincao(pensao);
	}
		
	@Override
	public void proximaData(ExtincaoDoBeneficio extincaoDoBeneficio) {
		this.proximo = extincaoDoBeneficio;
	}

}
