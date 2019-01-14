package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Pensao;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class RubricasDao implements GenericDao<Rubricas>,Serializable{
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(Rubricas obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rubricas> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rubricas> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public Rubricas devolveRubricaPeloCodigo(int codigoRubrica) {
		Rubricas ru = new Rubricas();
		try {
			Query q = getEm().createNativeQuery("select * From Rubricas where NUMR_codigo = :codigoRubrica",Rubricas.class);
			q.setParameter("codigoRubrica", codigoRubrica);
			if(!q.getResultList().isEmpty()){
				ru = (Rubricas)q.getResultList().get(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ru;
	}
	
	

}
