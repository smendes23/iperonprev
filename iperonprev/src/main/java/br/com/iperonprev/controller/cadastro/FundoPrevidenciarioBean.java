package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.FundoPrevidenciario;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class FundoPrevidenciarioBean implements GenericBean<FundoPrevidenciario>, Serializable{

	private static final long serialVersionUID = 1L;
	FundoPrevidenciario fundoPrevidenciario = new FundoPrevidenciario();
	List<FundoPrevidenciario> filtroDeCodigos;
	

	public List<FundoPrevidenciario> getFiltroDeCodigos() {
		return filtroDeCodigos;
	}

	public void setFiltroDeCodigos(List<FundoPrevidenciario> filtroDeCodigos) {
		this.filtroDeCodigos = filtroDeCodigos;
	}

	

	public FundoPrevidenciario getFundoPrevidenciario() {
		return fundoPrevidenciario;
	}

	public void setFundoPrevidenciario(FundoPrevidenciario fundoPrevidenciario) {
		this.fundoPrevidenciario = fundoPrevidenciario;
	}

	@Override
	public void salvarObjeto() {
		try{
			new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).salvar(fundoPrevidenciario);
			novoObjeto();
			Message.addSuccessMessage("Códigos salvos com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar código!");
		}
	}

	@Override
	public void novoObjeto() {
		this.fundoPrevidenciario = new FundoPrevidenciario();
	}

	@Override
	public List<FundoPrevidenciario> listaDeObjetos() {
		return null;
	}
	
	public List<FundoPrevidenciario> getListaDeCodigos() {
		return new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).listarTodos("FundoPrevidenciario");
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaFundoPrevidenciario");
	}

	@Override
	public void pegaInstanciaDialogo(FundoPrevidenciario obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.fundoPrevidenciario = (FundoPrevidenciario) event.getObject();
	}
	
	public void removeCodigo(int codigo){
		new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).remover("FundoPrevidenciario", "NUMG_codigo", codigo);
	}
	
	

}
