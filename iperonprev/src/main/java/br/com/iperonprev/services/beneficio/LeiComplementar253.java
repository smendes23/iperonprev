package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.interfaces.TemporalidadeDeBeneficios;

public class LeiComplementar253 implements TemporalidadeDeBeneficios{

	TemporalidadeDeBeneficios proximo;
	
	@Override
	public void setProximoTempoBeneficio(TemporalidadeDeBeneficios proximo) {
		this.proximo = proximo;
	}

	
	@Override
	public LegislacaoBeneficioEnum devolveAtoLegal(int idade, Date dataDoObito, DecisaoEnum invalido,
			DecisaoEnum estudante, String tipoDependencia, SexoEnum sexo) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inicio = sdf.parse("14/01/2002");
			Date fim = sdf.parse("12/03/2008");
			
			if(idade <= 21  && dataDoObito.after(inicio) && dataDoObito.before(fim)
					|| dataDoObito.compareTo(inicio) == 0 || dataDoObito.compareTo(fim) == 0
					&& tipoDependencia.equals("Fillho(a)")){

				return LegislacaoBeneficioEnum.LC253;
			}
		} catch (Exception e) {
			throw new  RuntimeException();
		}
		return proximo.devolveAtoLegal(idade, dataDoObito, invalido, estudante, tipoDependencia, sexo);
	}

	
}
