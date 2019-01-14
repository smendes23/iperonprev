package br.com.iperonprev.dao;

import br.com.iperonprev.interfaces.VerificaUnidadeGestora;
import br.com.iperonprev.models.Orgaos;

public class QualificaOrgao {

	public void valida(Orgaos orgaos){
		VerificaUnidadeGestora naoECandidata = new OrgaoNaoCandidatoUg();
		VerificaUnidadeGestora eCandidata = new OrgaoCandidatoUg();
		VerificaUnidadeGestora eUnidade = new OrgaoUg();
		VerificaUnidadeGestora nula = new OrgaoNulo();
		
		naoECandidata.setProximoOrgao(eCandidata);
		eCandidata.setProximoOrgao(eUnidade);
		eUnidade.setProximoOrgao(nula);
		
		naoECandidata.verificaOrgao(orgaos);
	}
}
