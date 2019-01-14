package br.com.iperonprev.interfaces;

import java.util.Date;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.constantes.SexoEnum;

public interface TemporalidadeDeBeneficios {

	LegislacaoBeneficioEnum devolveAtoLegal(int idade,Date dataDoObito,
			DecisaoEnum invalido, DecisaoEnum estudante, String tipoDependencia, SexoEnum sexo);
	void setProximoTempoBeneficio(TemporalidadeDeBeneficios proximo);
}
