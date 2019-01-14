package br.com.iperonprev.dao;

import br.com.iperonprev.interfaces.VerificaUnidadeGestora;
import br.com.iperonprev.models.Orgaos;

public class OrgaoUg implements VerificaUnidadeGestora{
	
	private VerificaUnidadeGestora proximo;
	
	@Override
	public void verificaOrgao(Orgaos orgaos) {
		
		Orgaos org = new OrgaosDao().verificaUnidadeGestora(orgaos);
		
		if (org.isNUMR_unidadeGestora() != false) {
			new GenericPersistence<Orgaos>(Orgaos.class).salvar(orgaos);
			org.setNUMR_unidadeGestora(false);
			new GenericPersistence<Orgaos>(Orgaos.class).salvar(org);
		}else{
			proximo.verificaOrgao(orgaos);
		}
	}

	@Override
	public void setProximoOrgao(VerificaUnidadeGestora proximo) {
		this.proximo = proximo;
	}
}
