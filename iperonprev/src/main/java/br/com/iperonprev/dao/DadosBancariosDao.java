package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.DadosBancarios;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class DadosBancariosDao implements GenericDao<DadosBancarios>, Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(DadosBancarios obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Ato Salvo com sucesso");
		}catch(PersistenceException e){
			Message.addErrorMessage("Erro ao salvar o Ato.");
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DadosBancarios> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<DadosBancarios>(DadosBancarios.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<DadosBancarios> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DadosBancarios> devolveDadosBancariosPessoa(int idPessoa){
		List<DadosBancarios> lista = new ArrayList<>();
		try{
			Query  q = getEm().createNativeQuery("select * from DadosBancarios where REL_pessoa_NUMG_idDoObjeto = :idPessoa",DadosBancarios.class);
			q.setParameter("idPessoa", idPessoa);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Não foi possivel carregar os dados bancários");
		}
		return lista;
	}

}






