package br.com.iperonprev.controller.pericia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.SelectEvent;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.AreaMedica;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
public class AreaMedicaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AreaMedica area = new AreaMedica();
	List<AreaMedica> filtroAreaMedica = new ArrayList<AreaMedica>();
	
	

	public List<AreaMedica> getFiltroAreaMedica() {
		return filtroAreaMedica;
	}

	public void setFiltroAreaMedica(List<AreaMedica> filtroAreaMedica) {
		this.filtroAreaMedica = filtroAreaMedica;
	}

	public AreaMedica getArea() {
		return area;
	}

	public void setArea(AreaMedica area) {
		this.area = area;
	}

	public void salvarObjeto() {
		try {
			new GenericPersistence<AreaMedica>(AreaMedica.class).salvar(this.area);
			novoObjeto();
			Message.addSuccessMessage("Área médica salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar área médica");
		}
	}

	public void novoObjeto() {
		this.area = new AreaMedica();
	}

	public List<AreaMedica> listaDeObjetos() {
		return null;
	}

	public void exibeAreaMedica() {
		new DialogsPrime().showDialog(true, true, true, false, "listaAreaMedica");
	}

	public void pegaAreaMedica(AreaMedica area) {
		new DialogsPrime().getInstanceObjectDialog(area);
	}

	public void selecionaAreaMedica(SelectEvent event) {
		this.area = (AreaMedica)event.getObject();
	}
	
	public List<AreaMedica> getListaAreaMedica(){
		return new GenericPersistence<AreaMedica>(AreaMedica.class).listarTodos("AreaMedica");
	}

}
