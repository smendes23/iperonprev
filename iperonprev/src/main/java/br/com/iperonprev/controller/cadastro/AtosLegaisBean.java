package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.AtosLegaisDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.TipoAtosLegais;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean(name="atosLegais")
public class AtosLegaisBean implements Serializable{

	private static final long serialVersionUID = 1L;
	GenericDao<AtosLegais> dao;
	private AtosLegais atos = new AtosLegais();
	
	public AtosLegais getAtos() {
		return atos;
	}

	public void setAtos(AtosLegais atos) {
		this.atos = atos;
	}
	
	public void salvarAto(){
		try{
			dao = new AtosLegaisDao();
			dao.salvaObjeto(this.atos);
			this.atos = new AtosLegais();
			actionButtonAto = false;
			Message.addSuccessMessage("Atos Legais salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Atos Legais!");
		}
	}
	/*Inicio Formulário Dialog*/
	public void listaAtosLegais(){
		new DialogsPrime().showDialog(true, true, true, false, "listaAtosLegais");
	}
	
	public void pegaInstanciaDialogo(AtosLegais atosLegais){
		new DialogsPrime().getInstanceObjectDialog(atosLegais);
	}
	
	private static boolean actionButtonAto = false;

	public void fechaAto(){
		actionButtonAto = true;
		RequestContext.getCurrentInstance().closeDialog("listaAtosLegais");
	}
	
	public void actionCloseListaAto(){
		actionButtonAto = false;
	}
	
	public void selecionaAtoDialogo(SelectEvent event){
		if(actionButtonAto != true){
			AtosLegais ato = (AtosLegais)event.getObject();
			this.atos = new GenericPersistence<AtosLegais>(AtosLegais.class).buscarPorId(ato.getNUMG_idDoObjeto());
		}
		actionButtonAto = false;
	}
	/*Fim Formulario Dialog*/
	
	public List<TipoAtosLegais> getTipoAtosLegais(){
		List<TipoAtosLegais> lista = new GenericPersistence<TipoAtosLegais>(TipoAtosLegais.class).listarTodos("TipoAtosLegais"); 
		return lista;
	}
	
	public List<AtosLegais> getListaAtos(){
		dao = new AtosLegaisDao();
		return dao.buscaTodosObjetos("AtosLegais");
	}
	
	public void novoRegistro(){
		this.atos = new AtosLegais();
	}

	private List<AtosLegais> filtroDeAtos;

	public List<AtosLegais> getFiltroDeAtos() {
		return filtroDeAtos;
	}

	public void setFiltroDeAtos(List<AtosLegais> filtroDeAtos) {
		this.filtroDeAtos = filtroDeAtos;
	}
	
	public List<TipoBeneficioEnum> getListaDeBeneficio(){
		return Arrays.asList(TipoBeneficioEnum.values());
	}
	
}
