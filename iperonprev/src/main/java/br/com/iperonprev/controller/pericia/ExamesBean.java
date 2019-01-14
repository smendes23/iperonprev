package br.com.iperonprev.controller.pericia;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Exames;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
public class ExamesBean implements GenericBean<Exames>,Serializable{

	Exames exames = new Exames();
	
	public Exames getExames() {
		return exames;
	}

	public void setExames(Exames exames) {
		this.exames = exames;
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public void salvarObjeto() {
		try{
			new GenericPersistence<Exames>(Exames.class).salvar(this.exames);
			novoObjeto();
			Message.addSuccessMessage("Exame salvo com sucesso");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar exame.");
		}
	}

	@Override
	public void novoObjeto() {
		this.exames = new Exames();
	}

	@Override
	public List<Exames> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaExames");		
	}

	@Override
	public void pegaInstanciaDialogo(Exames obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.exames = (Exames)event.getObject();
	}
	
	public List<Exames> getListaDeExames(){
		return new GenericPersistence<Exames>(Exames.class).listarTodos("Exames");
	}

}
