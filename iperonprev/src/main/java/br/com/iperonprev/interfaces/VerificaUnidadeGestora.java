package br.com.iperonprev.interfaces;

import br.com.iperonprev.models.Orgaos;

public interface VerificaUnidadeGestora {

	void verificaOrgao(Orgaos orgaos);
	void setProximoOrgao(VerificaUnidadeGestora proximo);
}
