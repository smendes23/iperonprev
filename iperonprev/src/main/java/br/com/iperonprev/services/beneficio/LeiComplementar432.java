package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.interfaces.TemporalidadeDeBeneficios;

public class LeiComplementar432 implements TemporalidadeDeBeneficios{

	private TemporalidadeDeBeneficios proximo;

	@Override
	public LegislacaoBeneficioEnum devolveAtoLegal(int idade, Date dataDoObito, DecisaoEnum invalido,
			DecisaoEnum estudante, String tipoDependencia, SexoEnum sexo) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inicio = sdf.parse("13/03/2008");
			
			if(idade <= 21  && dataDoObito.after(inicio) && tipoDependencia.equals("Filho(a)")){

				return LegislacaoBeneficioEnum.LC432;
			}
		} catch (Exception e) {
			throw new  RuntimeException();
		}
		return proximo.devolveAtoLegal(idade, dataDoObito, invalido, estudante, tipoDependencia, sexo);
	}

	
	@Override
	public void setProximoTempoBeneficio(TemporalidadeDeBeneficios proximo) {
		this.proximo = proximo;
	}

}
