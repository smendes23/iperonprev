package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.models.AtestadosVidaResidencia;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class AtestadoVidaResidenciaDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<AtestadosVidaResidencia> devolveListaDeAtesdatos(int idCenso){
		List<AtestadosVidaResidencia> lista = new ArrayList<>();
		try{
			Query q = getEm().createNativeQuery("select * from AtestadosVidaResidencia where REL_censo_NUMG_idDoObjeto = :idCenso",AtestadosVidaResidencia.class);
			q.setParameter("idCenso", idCenso);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar a lista de Orgaos previdenciários");
		}
		return lista;
	}
	
	
	
	public boolean verificaExistenciaDeAtesdatos(int idCenso){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from AtestadosVidaResidencia where REL_censo_NUMG_idDoObjeto = :idCenso",AtestadosVidaResidencia.class);
			q.setParameter("idCenso", idCenso);
			
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar a lista de Orgaos previdenciários");
		}
		return res;
	}
}
