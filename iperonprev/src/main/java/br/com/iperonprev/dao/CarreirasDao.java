package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Carreiras;
import br.com.iperonprev.util.jsf.Message;

public class CarreirasDao implements GenericDao<Carreiras>, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Carreiras obj) {
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
	public List<Carreiras> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<Carreiras>(Carreiras.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<Carreiras> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

}






