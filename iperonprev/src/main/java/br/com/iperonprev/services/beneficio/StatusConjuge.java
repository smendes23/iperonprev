package br.com.iperonprev.services.beneficio;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.interfaces.StatusBeneficio;
import br.com.iperonprev.models.Pensao;

public class StatusConjuge implements StatusBeneficio{

	StatusBeneficio proximo;
	

	@Override
	public void setaProximoStatus(StatusBeneficio proximo) {
		this.proximo = proximo;
	}


	@Override
	public StatusBeneficioEnum devolveBeneficio(Pensao pensao) {
		/*if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Conjuge")){
			return StatusBeneficioEnum.VITALICIO;
		}*/
		return proximo.devolveBeneficio(pensao);
	}

}
