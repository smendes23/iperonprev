package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Cargos;

public class CargosDao implements GenericDao<Cargos>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Cargos obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cargos> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<Cargos>(Cargos.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<Cargos> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

}
