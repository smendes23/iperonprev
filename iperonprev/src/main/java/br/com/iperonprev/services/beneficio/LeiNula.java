package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.interfaces.TemporalidadeDeBeneficios;

public class LeiNula implements TemporalidadeDeBeneficios{

	
	@Override
	public void setProximoTempoBeneficio(TemporalidadeDeBeneficios proximo) {
	}

	@Override
	public LegislacaoBeneficioEnum devolveAtoLegal(int idade, Date dataDoObito, DecisaoEnum invalido,
			DecisaoEnum estudante, String tipoDependencia, SexoEnum sexo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
