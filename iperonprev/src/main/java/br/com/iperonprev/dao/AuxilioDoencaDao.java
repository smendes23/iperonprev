package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AuxilioDoenca;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class AuxilioDoencaDao implements GenericDao<AuxilioDoenca>,Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	@Override
	public void salvaObjeto(AuxilioDoenca obj) {
		try{
			new GenericPersistence<AuxilioDoenca>(AuxilioDoenca.class).salvar(obj);
			Message.addSuccessMessage("Auxílio cadastrado com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao cadastrar auxilio!"+e.getMessage());
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuxilioDoenca> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuxilioDoenca> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AuxilioDoenca>(AuxilioDoenca.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public AuxilioDoenca buscaAuxilioDoenca(int idFuncional){
		AuxilioDoenca auxilio = new AuxilioDoenca();
		try{
			Query q = getEm().createQuery("select a from AuxilioDoenca a JOIN  a.NUMR_pessoaFuncional pf where pf.NUMG_idDoObjeto = :idFuncional",AuxilioDoenca.class);
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				auxilio = (AuxilioDoenca) q.getResultList().get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return auxilio;
	}

}
