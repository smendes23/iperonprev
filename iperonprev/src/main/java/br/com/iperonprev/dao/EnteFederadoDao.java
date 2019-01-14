package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.EntesFederados;
import br.com.iperonprev.util.jsf.Message;

public class EnteFederadoDao implements Serializable,GenericDao<EntesFederados>{

	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(EntesFederados obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Ente Salvo com sucesso");
		}catch(PersistenceException e){
			Message.addErrorMessage("Erro ao salvar o Ente.");
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntesFederados> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<EntesFederados>(EntesFederados.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<EntesFederados> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

}





