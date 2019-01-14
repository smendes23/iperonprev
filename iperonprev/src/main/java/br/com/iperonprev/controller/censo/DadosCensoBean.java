package br.com.iperonprev.controller.censo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.CensoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class DadosCensoBean implements GenericBean<DadosCenso>,Serializable{
	
	private static final long serialVersionUID = 1L;

	DadosCenso dadoscenso = new DadosCenso();
	static List<DadosCenso> filtroCenso;
	
	
	public DadosCenso getDadoscenso() {
		return dadoscenso;
	}

	public void setDadoscenso(DadosCenso dadoscenso) {
		this.dadoscenso = dadoscenso;
	}

	public List<DadosCenso> getFiltroCenso() {
		return filtroCenso;
	}

	@SuppressWarnings("static-access")
	public void setFiltroCenso(List<DadosCenso> filtroCenso) {
		this.filtroCenso = filtroCenso;
	}


	@Override
	public void salvarObjeto() {
		try{
			if(new CensoDao().verificaExistenciaCenso(this.dadoscenso.getDATA_inicio(), this.dadoscenso.getDATA_fim()) != true){
				new GenericPersistence<DadosCenso>(DadosCenso.class).salvar(dadoscenso);
				novoObjeto();
				Message.addSuccessMessage("Censo salvo com sucesso!");
			}else{
				Message.addErrorMessage("Este censo já existe");
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Censo!");
		}
	}

	@Override
	public void novoObjeto() {
		this.dadoscenso = new DadosCenso();
	}

	@Override
	public List<DadosCenso> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDadosCenso");
	}

	@Override
	public void pegaInstanciaDialogo(DadosCenso obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.dadoscenso = (DadosCenso)event.getObject();
	}
	
	public List<DadosCenso> getListaDeCenso(){
		return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	}
	
	
}
