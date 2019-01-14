package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.interfaces.TemporalidadeDeBeneficios;

public class Lei068 implements TemporalidadeDeBeneficios{

	private TemporalidadeDeBeneficios proximo;
	

	@Override
	public void setProximoTempoBeneficio(TemporalidadeDeBeneficios proximo) {
		this.proximo = proximo;
	}


	@Override
	public LegislacaoBeneficioEnum devolveAtoLegal(int idade, Date dataDoObito, DecisaoEnum invalido,
			DecisaoEnum estudante, String tipoDependencia, SexoEnum sexo) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inicio = sdf.parse("09/12/1992");
			Date fim = sdf.parse("30/01/2000");
			
			if(idade <= 21  && dataDoObito.after(inicio) && dataDoObito.before(fim)
					|| dataDoObito.compareTo(inicio) == 0 || dataDoObito.compareTo(fim) == 0
					&& tipoDependencia.equals("Filho(a)")){

				return LegislacaoBeneficioEnum.LEI68;
			}
		} catch (Exception e) {
			throw new  RuntimeException();
		}
		return proximo.devolveAtoLegal(idade, dataDoObito, invalido, estudante, tipoDependencia, sexo);
	}

}
