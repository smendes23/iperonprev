package br.com.iperonprev.interfaces;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.models.Pensao;

public interface StatusBeneficio {

	StatusBeneficioEnum devolveBeneficio(Pensao pensao);
	void setaProximoStatus(StatusBeneficio proximo);
}
