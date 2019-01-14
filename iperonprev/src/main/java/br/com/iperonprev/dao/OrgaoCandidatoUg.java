package br.com.iperonprev.dao;

import br.com.iperonprev.interfaces.VerificaUnidadeGestora;
import br.com.iperonprev.models.Orgaos;

public class OrgaoCandidatoUg implements VerificaUnidadeGestora{

private VerificaUnidadeGestora proximo;
	
	@Override
	public void verificaOrgao(Orgaos orgaos) {
		
		Orgaos org = new OrgaosDao().verificaUnidadeGestora(orgaos);
		
		if (org.isNUMR_unidadeGestora() != true) {
			new GenericPersistence<Orgaos>(Orgaos.class).salvar(orgaos);
		}else{
			proximo.verificaOrgao(orgaos);
		}
	}

	@Override
	public void setProximoOrgao(VerificaUnidadeGestora proximo) {
		this.proximo = proximo;
	}
}
