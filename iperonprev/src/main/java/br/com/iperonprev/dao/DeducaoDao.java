package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class DeducaoDao implements GenericDao<Deducao>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private GenericPersistence<Deducao> persistencia = new GenericPersistence<Deducao>(Deducao.class);
	@Override
	public void salvaObjeto(Deducao obj) {
		persistencia.salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deducao> buscaTodosObjetos(String nomeDaTabela) {
		return persistencia.listarTodos(nomeDaTabela);
	}

	@Override
	public List<Deducao> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<Deducao>(Deducao.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		persistencia.remover("Deducao", "NUMG_idDoObjeto", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Deducao> devolveFaltas(int idFuncional){
		List<Deducao> lista = new ArrayList<>();
		try{
			Query  q = getEm().createNativeQuery("select * from Deducao where NUMR_pessoasFuncionais_NUMG_idDoObjeto = :idFuncional and ENUM_tipoDeducao = :tipo",Deducao.class);
			q.setParameter("idFuncional", idFuncional);
			q.setParameter("tipo", "FALTA");
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Não foi possivel carregar a lista de faltas");
		}
		return lista;
	}

}
