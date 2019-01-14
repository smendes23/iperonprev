package br.com.iperonprev.controller.arrecadacao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
public class AliquotaContribuicaoBean implements Serializable,GenericBean<AliquotaContribuicaoBean> {

	
	private static final long serialVersionUID = 1L;
	private Part file;
	
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	@Override
	public void salvarObjeto() {
		try{
			novoObjeto();
			Message.addSuccessMessage("Alíquota salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Alíquota");
		}
	}

	@Override
	public void novoObjeto() {
	}

	@Override
	public List<AliquotaContribuicaoBean> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pegaInstanciaDialogo(AliquotaContribuicaoBean obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private List<String> listaDeAliquotas(Part arq){
		return null;
	}
}
