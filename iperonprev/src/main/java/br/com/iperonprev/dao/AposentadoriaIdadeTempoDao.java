package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaIdadeTempo;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class AposentadoriaIdadeTempoDao implements GenericDao<AposentadoriaIdadeTempo>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(AposentadoriaIdadeTempo obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaIdadeTempo> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaIdadeTempo> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AposentadoriaIdadeTempo>(AposentadoriaIdadeTempo.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

	public AposentadoriaIdadeTempo buscaObjetoRelacionamento(String tabela, String campo, int valor){
		AposentadoriaIdadeTempo idadeTempo = new AposentadoriaIdadeTempo();
		Query q = getEm().createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.id = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			idadeTempo = (AposentadoriaIdadeTempo) q.getSingleResult();
		}
		getEm().close();
		return idadeTempo;
	}
	
	public AposentadoriaIdadeTempo buscaObjetoComUmParametro(String tabela, String parametro,String valor){
		AposentadoriaIdadeTempo idadeTempo = new AposentadoriaIdadeTempo();
		Query q = getEm().createQuery("select o from " + tabela + " o  where o."+parametro+" = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			idadeTempo = (AposentadoriaIdadeTempo) q.getSingleResult();
		}
		getEm().close();
		return idadeTempo;
	}
	
	public boolean verificaExistenciaAposentadoria(int idFuncional){
		boolean res = false;
		EntityManager em = getEm();
		try{
			Query q = em.createNativeQuery("select * from AposentadoriaIdadeTempo where NUMR_idDoObejtoPessoasFuncionais_NUMG_idDoObjeto = :idFuncional");
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
