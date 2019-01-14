package br.com.iperonprev.services.beneficio;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.interfaces.StatusBeneficio;
import br.com.iperonprev.models.Pensao;

public class StatusInvalido implements StatusBeneficio{

	StatusBeneficio proximo;
	@Override
	public StatusBeneficioEnum devolveBeneficio(Pensao pensao) {
		
			/*if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)") 
					&& pensao.getENUM_invalido() == DecisaoEnum.SIM
					&& new LocalDate().now().toDate().after(pensao.getDATA_fimDoBeneficio())
					|| new LocalDate().now().toDate() == pensao.getDATA_fimDoBeneficio()){
				
				return StatusBeneficioEnum.EXPIRADO;
				
			}else if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)") 
					&& pensao.getENUM_invalido() == DecisaoEnum.SIM
					&& new LocalDate().now().toDate().after(RetornaTempos.retornaDataFuturaComDiasDeduzidos(90, pensao.getDATA_fimDoBeneficio()))
					&& new LocalDate().now().toDate().before(pensao.getDATA_fimDoBeneficio())
					|| pensao.getDATA_fimDoBeneficio() == RetornaTempos.retornaDataFutura(90, new LocalDate().now().toDate())){
				
				return StatusBeneficioEnum.EXPIRANDO;
			}*/
			
		return proximo.devolveBeneficio(pensao);
	}

	@Override
	public void setaProximoStatus(StatusBeneficio proximo) {
		this.proximo = proximo;
	}

	
}
