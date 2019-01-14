package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.models.ModulosPerfil;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class CadastroUsuarioDao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<ModulosPerfil> listarRelacionamento(String tabela, String campo, int valor) {
		List<ModulosPerfil> lista = new ArrayList<>();
		try{
			
			Query q = getEm().createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.id = :valor");
			q.setParameter("valor", valor);
			lista = q.getResultList();
			getEm().close();
		}catch(Exception e){
			System.out.println("Erro ao listar relacionamento");
		}finally {
			if(getEm().isOpen()){
				getEm().close();
			}
		}
		return lista;
	}

}
