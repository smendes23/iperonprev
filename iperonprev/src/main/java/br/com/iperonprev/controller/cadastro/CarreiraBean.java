package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.CarreirasDao;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Carreiras;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean(name="carreirasBean")
public class CarreiraBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Carreiras carreira = new Carreiras();
	GenericDao<Carreiras> dao;
	static List<Carreiras> lista = new ArrayList<Carreiras>();
	static private List<Carreiras> filtroDeCarreiras;
	
	public List<Carreiras> getFiltroDeCarreiras() {
		return filtroDeCarreiras;
	}

	@SuppressWarnings("static-access")
	public void setFiltroDeCarreiras(List<Carreiras> filtroDeCarreiras) {
		this.filtroDeCarreiras = filtroDeCarreiras;
	}
	@PostConstruct
	public void init() {
		lista = new CarreirasDao().buscaTodosObjetos("Carreiras");
	}
	
	public Carreiras getCarreira() {
		return carreira;
	}

	public void setCarreira(Carreiras carreira) {
		this.carreira = carreira;
	}
	
	public void salvarCarreira() {
		try{
			dao = new CarreirasDao();
			dao.salvaObjeto(carreira);
			this.carreira = new Carreiras();
			Message.addSuccessMessage("Carreira salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Carreira!");
		}
	}
	
	public void novaCarreira(){
		this.carreira = new Carreiras();
		actionButtonCarreira = false;
	}
	
	public List<Carreiras> getListaDeCarreiras() {
		return lista;
	}
	
	public void abreDialogoCarreiras() {
		new DialogsPrime().showDialog(true, true, true, false, "listaCarreiras");
	}
	
	public void pegaInstanciaDialogo(Carreiras carreiras){
		new DialogsPrime().getInstanceObjectDialog(carreiras);
	}
	
	private static boolean actionButtonCarreira = false;

	public void fechaCarreira(){
		actionButtonCarreira = true;
		RequestContext.getCurrentInstance().closeDialog("listaCarreiras");
	}
	
	
	public void actionEditCarreira(){
		actionButtonCarreira = false;
	}
	
	public void selecionaCarreiraDialogo(SelectEvent event){
		if(actionButtonCarreira != true){
			this.carreira = (Carreiras)event.getObject();
		}
		actionButtonCarreira = false;
	}
}
