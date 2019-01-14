package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaCompulsoria;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class AposentadoriaCompulsoriaDao implements GenericDao<AposentadoriaCompulsoria>,Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	@Override
	public void salvaObjeto(AposentadoriaCompulsoria obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaCompulsoria> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaCompulsoria> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AposentadoriaCompulsoria>(AposentadoriaCompulsoria.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public AposentadoriaCompulsoria buscaObjetoRelacionamento(String tabela, String campo, int valor){
		AposentadoriaCompulsoria compulsoria = new AposentadoriaCompulsoria();
		Query q = getEm().createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.id = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			compulsoria = (AposentadoriaCompulsoria) q.getSingleResult();
		}
		getEm().close();
		return compulsoria;
	}
	
	public AposentadoriaCompulsoria buscaObjetoComUmParametro(String tabela, String parametro,String valor){
		AposentadoriaCompulsoria compulsoria = new AposentadoriaCompulsoria();
		Query q = getEm().createQuery("select o from " + tabela + " o  where o."+parametro+" = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			compulsoria = (AposentadoriaCompulsoria) q.getSingleResult();
		}
		getEm().close();
		return compulsoria;
	}
	
	public boolean verificaExistenciaAposentadoria(int idFuncional){
		boolean res = false;
		EntityManager em = getEm();
		try{
			Query q = em.createNativeQuery("select * from AposentadoriaCompulsoria where NUMR_idDoObejtoPessoasFuncionais_NUMG_idDoObjeto = :idFuncional");
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao verificar a existência da aposentadoria");
		}finally {
			em.close();
		}
		return res;
	}
}
