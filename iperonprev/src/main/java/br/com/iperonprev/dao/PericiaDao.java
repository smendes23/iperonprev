package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.iperonprev.models.Pericia;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class PericiaDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pericia> listaPorIdFuncional(int idFuncional) {
		EntityManager em = getEm();
		List<Pericia> lista = new ArrayList<>();
		try{
			Query q = em.createNativeQuery("select top 1 * from Pericia where NUMR_idDoObejtoPessoasFuncionais_NUMG_idDoObjeto = :idFuncional order by NUMG_idDoObjeto desc",Pericia.class);
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(PersistenceException e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao lista Perícias");
		}finally{
			em.close();
		}
		return lista;
	}

}
