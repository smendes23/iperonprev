package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.GestorOrgaos;
import br.com.iperonprev.util.jsf.Message;

public class GestorOrgaosDao implements GenericDao<GestorOrgaos>,Serializable{

	
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(GestorOrgaos obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Gestor salvo com sucesso");
		}catch(PersistenceException e){
			Message.addErrorMessage("Erro ao salvar o gestor.");
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GestorOrgaos> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GestorOrgaos> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<GestorOrgaos>(GestorOrgaos.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

}
