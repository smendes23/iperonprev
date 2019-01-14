package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposConcomitantesDuasAverbacoes;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class QualificaConcomitanciaDuasAverbacoes {

	public boolean executa(Averbacao a1,Averbacao a2){
		
		try{
			Averbacao av1 = new GenericPersistence<Averbacao>(Averbacao.class).buscarPorId(a1.getNUMG_idDoObjeto());
			Averbacao av2 = new GenericPersistence<Averbacao>(Averbacao.class).buscarPorId(a2.getNUMG_idDoObjeto());
			
			if(av1.getNUMG_idDoObjeto() != av2.getNUMG_idDoObjeto()){
					
					TemposConcomitantesDuasAverbacoes con1 = new ConcomitanciaAdmissaoDemissao();
					TemposConcomitantesDuasAverbacoes con2 = new ConcomitanciaEntrePeriodosIguais();
					TemposConcomitantesDuasAverbacoes con3 = new ConcomitanciaDataAdmissao();
					TemposConcomitantesDuasAverbacoes con4 = new ConcomitanciaDemissao();
					TemposConcomitantesDuasAverbacoes con5 = new ConcomitanciaAdmissaoIgualDemissaoMenor();
					TemposConcomitantesDuasAverbacoes nulo = new ConcomitanciaNulaDuasAverbacoes();
					
					con1.setProximaConcomitancia(con2);
					con2.setProximaConcomitancia(con3);
					con3.setProximaConcomitancia(con4);
					con4.setProximaConcomitancia(con5);
					con5.setProximaConcomitancia(nulo);
					
					return con1.verificaConcomitancia(av1, RetornaTempos.retornaDiasApartirDeDuasDatas(av1.getDATA_admissao(), av1.getDATA_demissao()), av2, RetornaTempos.retornaDiasApartirDeDuasDatas(av2.getDATA_admissao(), av2.getDATA_demissao()));
					
			}
		}catch(Exception e){
			System.out.println("Erro ao qualificar averbações!");
		}
		
		
		return false;
	}
	
	
}
