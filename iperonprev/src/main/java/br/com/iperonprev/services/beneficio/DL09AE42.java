package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.interfaces.TemporalidadeDeBeneficios;

public class DL09AE42 implements TemporalidadeDeBeneficios{

	private TemporalidadeDeBeneficios proximo;

	@Override
	public LegislacaoBeneficioEnum devolveAtoLegal(int idade, Date dataDoObito, DecisaoEnum invalido,
			DecisaoEnum estudante,String tipoDependencia,SexoEnum sexo) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inicio = sdf.parse("17/03/1982");
			Date fim = sdf.parse("09/12/1996");
			
			if(idade < 18  && dataDoObito.after(inicio) && dataDoObito.before(fim)
					&& dataDoObito.compareTo(inicio) == 0 && dataDoObito.compareTo(fim) == 0
					&& tipoDependencia.equals("Filho(a)") && sexo == SexoEnum.M){

				return LegislacaoBeneficioEnum.DL09A42;
			}else if(sexo == SexoEnum.F && dataDoObito.after(inicio) && dataDoObito.before(fim)
					&& dataDoObito.compareTo(inicio) == 0 && dataDoObito.compareTo(fim) == 0
					&& tipoDependencia.equals("Filho(a)")){
				return LegislacaoBeneficioEnum.DL09A42;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proximo.devolveAtoLegal(idade, dataDoObito, invalido, estudante, tipoDependencia, sexo);
	}
	
	@Override
	public void setProximoTempoBeneficio(TemporalidadeDeBeneficios proximo) {
		this.proximo = proximo;
	}







}
