package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.Enquadramento;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
public class EnquadramentoBean implements GenericBean<Enquadramento>,Serializable{

	
	private static final long serialVersionUID = 1L;
	
	Enquadramento enquadramento = new Enquadramento();
	Cargos cargo = new Cargos();
	public Enquadramento getEnquadramento() {
		return enquadramento;
	}
	public void setEnquadramento(Enquadramento enquadramento) {
		this.enquadramento = enquadramento;
	}
	
	
	
	public Cargos getCargo() {
		return cargo;
	}
	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}
	@Override
	public void salvarObjeto() {
		try{
			this.enquadramento.setREL_cargos(this.cargo);
			new GenericPersistence<Enquadramento>(Enquadramento.class).salvar(enquadramento);
			novoObjeto();
			Message.addSuccessMessage("Enquadramento salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar enquadramento");
		}
	}
	@Override
	public void novoObjeto() {
		this.enquadramento = new Enquadramento();
		this.cargo = new Cargos();
	}
	@Override
	public List<Enquadramento> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pegaInstanciaDialogo(Enquadramento obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Cargos> getListaCargos(){
		return new GenericPersistence<Cargos>(Cargos.class).listarTodos("Cargos");
	}
	

}
