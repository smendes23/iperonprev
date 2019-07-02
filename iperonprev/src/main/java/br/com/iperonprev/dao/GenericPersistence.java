package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class GenericPersistence<Obj> implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	private final Class<Obj> objeto;

	public GenericPersistence(Class<Obj> objeto) {
		this.objeto = objeto;
	}

	public void salvar(Obj obj) {
		EntityManager em = getEm();
		try {
			
			/*if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}*/
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (OptimisticLockException l) {
			Message.addErrorMessage("Registro já está sendo utilizando em outro processo!");
			em.getTransaction().rollback();
		}catch (Exception e) {
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			Message.addErrorMessage("Erro ao salvar dados!");
		}finally {
			em.close();
		}
	}
	
	public void removeObjeto(Obj obj){
		EntityManager em = getEm();
		try{
			
			/*if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}*/
			em.getTransaction().begin();
			em.remove(em.merge(obj));
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			Message.addErrorMessage("Não foi possivel remover objeto");
		}
	}
	
	public void removeObjetoComId(int idObjeto){
		EntityManager em = getEm();
		try{
			Obj o = getEm().find(objeto, idObjeto);
			
			/*if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}*/
			em.getTransaction().begin();
			em.remove(em.merge(o));
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			Message.addErrorMessage("Erro ao remover registro por ID!");
		}
	}
	
	public void remover(String tabela,String parametro,int id) {
		EntityManager em = getEm();
		try{
			
			em.getTransaction().begin();
			Query q = em.createQuery("delete from " + tabela + " o  where o."+parametro+" = :valor");
			q.setParameter("valor", id).executeUpdate();
			em.getTransaction().commit();
			Message.addSuccessMessage("Registro removido com sucesso");
		}catch (Exception e) {
			em.getTransaction().rollback();
			Message.addErrorMessage("Erro ao remover o registro!");
		}finally{
			em.close();
		}
	}
	
	public void removerComString(String valor) {
		Obj obj;
		EntityManager em = getEm();
		try {
			/*if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}*/

				em.getTransaction().begin();
				obj = em.find(objeto, valor);
				em.remove(obj);
				em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			Message.addErrorMessage("Erro ao remover com String");
		}finally{
			em.close();
		}
	}

	public Obj buscarPorId(Integer id) {
		Obj obj = null;
		EntityManager em = getEm();
		try {
			/*if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}*/

				em.getTransaction().begin();
				obj = em.find(objeto, id);
				em.close();
				
		} catch (Exception e) {
			em.getTransaction().rollback();
			em.close();
			Message.addErrorMessage("Erro ao buscar por ID!");
		}
		return obj;
	}
	
	public Obj buscarPorString(String valor) {
		Obj obj = null;
		EntityManager em = getEm();
		try {
			/*if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}*/

				em.getTransaction().begin();
				obj = em.find(objeto, valor);
		} catch (Exception e) {
			em.getTransaction().rollback();
			Message.addErrorMessage("Erro ao buscar por String!");
		}finally{
			em.close();
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<Obj> listarTodos(String obj) {
		EntityManager em = getEm();
		List<Obj> mov = new ArrayList<>();
		try{
			Query q = em.createQuery("select o from " + obj + " o");
			if(!q.getResultList().isEmpty()){
				mov = q.getResultList();
			}
		}catch(PersistenceException e){
			Message.addErrorMessage("Erro ao listar todos os registros de: "+e.getClass().getName());
		}finally{
			em.close();
		}
		return mov;
	}

	@SuppressWarnings("unchecked")
	public List<Obj> listarRelacionamento(String tabela, String campo, int valor) {
		List<Obj> lista = new ArrayList<>();
		EntityManager em = getEm();
		try{
			
			Query q = em.createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.NUMG_idDoObjeto = :valor");
			q.setParameter("valor", valor);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao lista relacionamento");
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Obj> listarMultiploRelacionamento(String tabela, String campo1, String campo2, String campo3, int id) {
		List<Obj> lista = new ArrayList<>();
		EntityManager em = getEm();
		try{
			Query q = em.createQuery("select o from " + tabela + " o JOIN o." + campo1 + " c JOIN c."+campo2 +" d where d."+campo3+ "= :valor");
			q.setParameter("valor", id);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao listar multiplo relacionamento");
		}finally {
			if(em.isOpen()){
				em.close();
			}
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Obj buscaObjetoRelacionamento(String tabela, String campo, int valor){
		Obj obj = null;
		EntityManager em = getEm();
		try{
			Query q = em.createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.NUMG_idDoObjeto = :valor");
			q.setParameter("valor", valor);
			if(!q.getResultList().isEmpty()){
				obj = (Obj) q.getResultList().get(0);
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao buscar objeto relacionamento");
		}finally{
			if(em.isOpen()){
				em.close();
			}
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public Obj buscaObjetoComUmParametro(String tabela, String parametro,String valor){
		Obj obj = null;
		EntityManager em = getEm();
		try{
			Query q = em.createQuery("select o from " + tabela + " o  where o."+parametro+" = :valor");
			q.setParameter("valor", valor);
			if(!q.getResultList().isEmpty()){
				obj = (Obj) q.getResultList().get(0);
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao buscar objeto com parametro");
		}finally{
			em.close();
		}
		return obj;
	}
	

}
