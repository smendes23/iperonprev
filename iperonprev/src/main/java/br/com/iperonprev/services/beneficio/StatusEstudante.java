package br.com.iperonprev.services.beneficio;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.interfaces.StatusBeneficio;
import br.com.iperonprev.models.Pensao;

public class StatusEstudante implements StatusBeneficio{

	StatusBeneficio proximo;
	@Override
	public StatusBeneficioEnum devolveBeneficio(Pensao pensao) {
		
		/*if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)")
					&& pensao.getENUM_estudante() == DecisaoEnum.SIM
					&& new LocalDate().now().toDate().after(RetornaTempos.retornaDataFuturaComDiasDeduzidos(90, pensao.getDATA_fimDoBeneficio()))
					&& new LocalDate().now().toDate().before(pensao.getDATA_fimDoBeneficio())
					|| new LocalDate().now().toDate() == pensao.getDATA_fimDoBeneficio())
					{
			
				return StatusBeneficioEnum.EXPIRANDO;
		}else if(pensao.getDATA_fimDoBeneficio() != null){
			if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)")
					&& pensao.getENUM_estudante() == DecisaoEnum.SIM
					&& new LocalDate().now().toDate().after(pensao.getDATA_fimDoBeneficio())
					|| new LocalDate().now().toDate() == pensao.getDATA_fimDoBeneficio()){
				
				return StatusBeneficioEnum.EXPIRADO;
			}
		}*/
		return proximo.devolveBeneficio(pensao);
	}

	@Override
	public void setaProximoStatus(StatusBeneficio proximo) {
		this.proximo = proximo;
	}

}
