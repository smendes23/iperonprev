package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class FuncionaisFuncoesDao implements GenericDao<FuncionaisFuncoes>,Serializable {
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(FuncionaisFuncoes obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FuncionaisFuncoes> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FuncionaisFuncoes> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean verificaExistenciaFuncionalFuncoes(int idPessoas){
		
		try{
			Query q = getEm().createNativeQuery("select * from FuncionaisFuncoes where NUMR_idPessoas_NUMG_idDoObjeto = :id",FuncionaisFuncoes.class);
			q.setParameter("id",idPessoas);
			if(!q.getResultList().isEmpty()){
				return true;
			}
		}catch(Exception e){
			System.out.println("Não foi possível carregar a Situação Previdenciária");
		}
		return false;
	}
	
	public List<FuncionaisFuncoes> devolveFuncionalFuncoes(String cpf) {
        List<FuncionaisFuncoes> listaFuncionais = new ArrayList<FuncionaisFuncoes>();
        try {
            Query q = this.getEm().createNativeQuery("select top 1 ff.* from FuncionaisFuncoes ff inner join Pessoas p on p.NUMG_idDoObjeto = ff.NUMR_idPessoas_NUMG_idDoObjeto where p.NUMR_cpf = :cpf", FuncionaisFuncoes.class);
            q.setParameter("cpf", (Object)cpf);
            if (!q.getResultList().isEmpty()) {
                listaFuncionais = q.getResultList();
            }
        }
        catch (Exception e) {
            System.out.println("N\u00e3o foi possível carregar a Situação Previdenciária");
        }
        return listaFuncionais;
    }

}
