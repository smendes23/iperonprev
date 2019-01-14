package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class AfastamentoLicencaDao implements GenericDao<AfastamentosLicenca>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(AfastamentosLicenca obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AfastamentosLicenca> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<AfastamentosLicenca>(AfastamentosLicenca.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<AfastamentosLicenca> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AfastamentosLicenca>(AfastamentosLicenca.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AfastamentosLicenca> devolveListaDeAfastamentosInteresseParticular(int idFuncional){
		List<AfastamentosLicenca> lista = new ArrayList<>();
		try{
			Query  q = getEm().createNativeQuery("select * from AfastamentosLicenca where NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = :idFuncional "
					+ "and NUMR_tipoLicenca_NUMG_idDoObjeto = 1",AfastamentosLicenca.class);
			q.setParameter("idFuncional", idFuncional);
			
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao carregar lista de afastamentos");
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> devolveListaDiasAfastados(int idFuncional)
	  {
	    List<Integer> lista = new ArrayList<>();
	    try
	    {
	      Query q = getEm().createNativeQuery("SELECT  ( DATEDIFF(DAY, CONVERT(date,DATA_inicioLicenca,111), CONVERT(date,DATA_fimLicenca,111)) ) as deducao from AfastamentosLicenca where NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = :idFuncional and FLAG_contribuicao = 0 and NUMR_tipoLicenca_NUMG_idDoObjeto = 1");
	      
	      q.setParameter("idFuncional", Integer.valueOf(idFuncional));
	      if (!q.getResultList().isEmpty()) {
	        lista = q.getResultList();
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.out.println("Erro ao carregar lista de afastamentos");
	    }
	    return lista;
	  }

}
