package br.com.iperonprev.services.beneficio;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.interfaces.StatusBeneficio;
import br.com.iperonprev.models.Pensao;

public class QualificaStatus {

	public StatusBeneficioEnum executa(Pensao pensao){
		StatusBeneficio conjuge = new StatusConjuge();
		StatusBeneficio invalido = new StatusInvalido();
		StatusBeneficio estudante = new StatusEstudante();
		StatusBeneficio statusNulo = new StatusNulo();
		
		conjuge.setaProximoStatus(invalido);
		invalido.setaProximoStatus(estudante);
		estudante.setaProximoStatus(statusNulo);
		return conjuge.devolveBeneficio(pensao);
	}
}
