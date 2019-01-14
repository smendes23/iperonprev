package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.util.jsf.Message;

public class AtosLegaisDao implements GenericDao<AtosLegais>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(AtosLegais obj) {
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
	public List<AtosLegais> buscaTodosObjetos(String nomeDaTabela) {
		return  new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
	}

	@Override
	public List<AtosLegais> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

}
