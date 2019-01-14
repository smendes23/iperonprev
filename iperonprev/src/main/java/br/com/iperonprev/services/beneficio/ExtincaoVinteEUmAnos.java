package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.interfaces.ExtincaoDoBeneficio;
import br.com.iperonprev.models.Pensao;

public class ExtincaoVinteEUmAnos implements ExtincaoDoBeneficio{

	private ExtincaoDoBeneficio proximo;
	
	@Override
	public Date devolveDataDeExtincao(Pensao pensao) {
		/*if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)") 
				&& pensao.getENUM_legislacao() == LegislacaoBeneficioEnum.LC432
				|| pensao.getENUM_legislacao() == LegislacaoBeneficioEnum.LC253
				|| pensao.getENUM_legislacao() == LegislacaoBeneficioEnum.LEI68){
			return RetornaTempos.retornaDataFuturaComAnos(21,pensao.getNUMR_idDoObjetoDependentes().getNUMR_idDoObjetoDependentes().getDATA_nascimento());
		}*/
		return proximo.devolveDataDeExtincao(pensao);
	}

	@Override
	public void proximaData(ExtincaoDoBeneficio extincaoDoBeneficio) {
		proximo = extincaoDoBeneficio;
	}

}
