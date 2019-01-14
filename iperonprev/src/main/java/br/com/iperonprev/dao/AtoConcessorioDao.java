package br.com.iperonprev.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.models.AtosConcessorios;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class AtoConcessorioDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	public AtosConcessorios devolveAto(int idFuncional){
		AtosConcessorios ato = new AtosConcessorios();
		try{
			Query q = getEm().createNativeQuery("select * from AtosConcessorios where REL_pessoasFuncionais_NUMG_idDoObjeto = :idFuncional",AtosConcessorios.class);
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				ato = (AtosConcessorios) q.getResultList().get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao buscar Ato Concessório!");
		}
		return ato;
	}
	
}
