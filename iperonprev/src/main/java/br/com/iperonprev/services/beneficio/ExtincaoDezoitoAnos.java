package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.interfaces.ExtincaoDoBeneficio;
import br.com.iperonprev.models.Pensao;

public class ExtincaoDezoitoAnos implements ExtincaoDoBeneficio{

	private ExtincaoDoBeneficio proximo;
	@Override
	public Date devolveDataDeExtincao(Pensao pensao) {
		/*if(pensao.getDATA_fimDoBeneficio() != null){
			
			if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)") 
//					&& pensao.getENUM_legislacao() == LegislacaoBeneficioEnum.LC228
					&& new LocalDate().now().toDate().after(RetornaTempos.retornaDataFuturaComAnos(18, pensao.getNUMR_idDoObjetoDependentes().getNUMR_idDoObjetoDependentes().getDATA_nascimento()))
					&& new LocalDate().now().toDate().before(RetornaTempos.retornaDataFuturaComAnos(21, pensao.getNUMR_idDoObjetoDependentes().getNUMR_idDoObjetoDependentes().getDATA_nascimento()))
					|| new LocalDate().now().toDate() == RetornaTempos.retornaDataFuturaComAnos(18, pensao.getNUMR_idDoObjetoDependentes().getNUMR_idDoObjetoDependentes().getDATA_nascimento())
					|| new LocalDate().now().toDate() == RetornaTempos.retornaDataFuturaComAnos(21, pensao.getNUMR_idDoObjetoDependentes().getNUMR_idDoObjetoDependentes().getDATA_nascimento())){
				
				return RetornaTempos.retornaDataFutura(180, pensao.getDATA_fimDoBeneficio());
			}
		}else if(pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome().equals("Filho(a)")
//				&& pensao.getENUM_legislacao() == LegislacaoBeneficioEnum.LC228
				){
			return RetornaTempos.retornaDataFuturaComAnos(18, pensao.getNUMR_idDoObjetoDependentes().getNUMR_idDoObjetoDependentes().getDATA_nascimento());
		}*/
		return proximo.devolveDataDeExtincao(pensao);
	}

	@Override
	public void proximaData(ExtincaoDoBeneficio extincaoDoBeneficio) {
		this.proximo = extincaoDoBeneficio;
			
	}

}
