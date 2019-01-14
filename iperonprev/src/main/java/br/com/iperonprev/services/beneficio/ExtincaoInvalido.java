package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.interfaces.ExtincaoDoBeneficio;
import br.com.iperonprev.models.Pensao;

public class ExtincaoInvalido implements ExtincaoDoBeneficio{

	private ExtincaoDoBeneficio proximo;
	
	@Override
	public Date devolveDataDeExtincao(Pensao pensao) {
		/*if(pensao.getDATA_fimDoBeneficio() != null){
			
			if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)")
					&& pensao.getENUM_invalido() == DecisaoEnum.SIM){
				return RetornaTempos.retornaDataFuturaComAnos(1, pensao.getDATA_fimDoBeneficio());
			}
		}*/
		return proximo.devolveDataDeExtincao(pensao);
	}

	@Override
	public void proximaData(ExtincaoDoBeneficio extincaoDoBeneficio) {
		this.proximo = extincaoDoBeneficio;
	}

}
