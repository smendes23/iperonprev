package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AfastamentosDisposicao;

public class AfastamentoDisposicaoDao implements GenericDao<AfastamentosDisposicao>,Serializable{

	
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(AfastamentosDisposicao obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AfastamentosDisposicao> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AfastamentosDisposicao> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

}
