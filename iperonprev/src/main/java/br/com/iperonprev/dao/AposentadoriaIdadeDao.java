package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaIdade;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class AposentadoriaIdadeDao implements GenericDao<AposentadoriaIdade>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(AposentadoriaIdade obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaIdade> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaIdade> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public AposentadoriaIdade buscaObjetoRelacionamento(String tabela, String campo, int valor){
		AposentadoriaIdade idade = new AposentadoriaIdade();
		Query q = getEm().createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.id = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			idade = (AposentadoriaIdade) q.getSingleResult();
		}
		getEm().close();
		return idade;
	}
	
	public boolean verificaExistenciaAposentadoria(int idFuncional){
		boolean res = false;
		EntityManager em = getEm();
		try{
			Query q = em.createNativeQuery("select * from AposentadoriaIdade where NUMR_idDoObejtoPessoasFuncionais_NUMG_idDoObjeto = :idFuncional");
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
	
/*	public AposentadoriaIdade buscaObjetoComUmParametro(String tabela, String parametro,String valor){
		AposentadoriaIdade idade = new AposentadoriaIdade();
		Query q = em.createQuery("select o from " + tabela + " o  where o."+parametro+" = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			idade = (AposentadoriaIdade) q.getSingleResult();
		}
		em.close();
		return idade;
	}*/

}
