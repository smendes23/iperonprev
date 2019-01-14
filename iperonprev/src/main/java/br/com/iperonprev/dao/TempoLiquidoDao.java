package br.com.iperonprev.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.models.TempoLiquidoAverbacao;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class TempoLiquidoDao {

	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	public TempoLiquidoAverbacao devolveTempoLiquido(int idFuncional){
		TempoLiquidoAverbacao tla = new TempoLiquidoAverbacao();
		try{
			Query q = getEm().createNativeQuery("select top 1 * from TempoLiquidoAverbacao where NUMR_pessoaFuncional_NUMG_idDoObjeto = :idFuncional order by NUMG_idDoObjeto desc",
					TempoLiquidoAverbacao.class);
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				tla = (TempoLiquidoAverbacao) q.getResultList().get(0);
			}
			System.out.println(tla.getDiasAproveitado());
			getEm().close();
		}catch(Exception e){
			System.out.println("Não foi possível carregar o tempo liquido averbado");
		}
		
		return tla;
	}
}
