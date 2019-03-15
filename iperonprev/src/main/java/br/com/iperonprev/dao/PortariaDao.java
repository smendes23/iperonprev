package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Indice;
import br.com.iperonprev.models.Portaria;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class PortariaDao implements GenericDao<Portaria>, Serializable {
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Portaria obj) {
		Portaria portaria = new Portaria();
		portaria.setNUMG_idDoObjeto(new GenericPersistence<Portaria>(Portaria.class).listarTodos("Portaria").size()+1);
		portaria.setDESC_competencia(obj.getDESC_competencia());
		portaria.setDESC_descricao(obj.getDESC_descricao());
		new GenericPersistence<Portaria>(Portaria.class).salvar(portaria);
		
		List<Indice> listaIndice = obj.getNUMR_indice();
		listaIndice.forEach(i->{
			i.setPortaria(portaria);
			new GenericPersistence<Indice>(Indice.class).salvar(i);
		});
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Portaria> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Portaria> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public Portaria buscaPortariaPorCompetencia(String competencia){
		Portaria portaria = new Portaria();
		try{
			Query q = getEm().createNativeQuery("select * from Portaria where DESC_competencia = :competencia",Portaria.class);
			q.setParameter("competencia", competencia);
			if(!q.getResultList().isEmpty()) {
				portaria = (Portaria)q.getResultList().get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getEm().close();
		}
		return portaria;
	}
	
	public boolean verificaExistenciaPortaiaPorCompetencia(String competencia){
		Portaria portaria = new Portaria();
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from Portaria where DESC_competencia = :competencia",Portaria.class);
			q.setParameter("competencia", competencia);
			if(!q.getResultList().isEmpty()) {
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getEm().close();
		}
		return res;
	}

}
