package br.com.iperonprev.interfaces;

import java.util.List;

import org.primefaces.event.SelectEvent;

public interface GenericBean<T> {

	public void salvarObjeto();
	public void novoObjeto();
	public List<T> listaDeObjetos();
	public void exibeListaDeObjetos();
	public void pegaInstanciaDialogo(T obj);
	public void selecionaObjetoDialogo(SelectEvent event);
}
