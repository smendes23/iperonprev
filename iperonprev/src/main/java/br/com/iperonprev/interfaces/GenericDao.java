package br.com.iperonprev.interfaces;

import java.util.List;

public interface GenericDao<T> {

	public void salvaObjeto(T obj);
	public Object buscaObjetoIndividual(int idDoObjeto);
	public List<T> buscaTodosObjetos(String nomeDaTabela);
	public List<T> listaRelacionamenoDoObjeto(String tabela, String campo, int valor);
	public void removeObjeto(int id);
}
