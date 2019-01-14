package br.com.iperonprev.services.beneficio;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.interfaces.StatusBeneficio;
import br.com.iperonprev.models.Pensao;

public class StatusNulo implements StatusBeneficio {

	@Override
	public StatusBeneficioEnum devolveBeneficio(Pensao pensao) {
		// TODO Auto-generated method stub
		return StatusBeneficioEnum.NORMAL;
	}

	@Override
	public void setaProximoStatus(StatusBeneficio proximo) {
		// TODO Auto-generated method stub

	}

}
