package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.SalarioMinimo;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class SalarioMinimoBean implements Serializable, GenericBean<SalarioMinimo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SalarioMinimo salario = new SalarioMinimo();

	public SalarioMinimo getSalario() {
		return salario;
	}

	public void setSalario(SalarioMinimo salario) {
		this.salario = salario;
	}

	@Override
	public void salvarObjeto() {
		try{
			new GenericPersistence<SalarioMinimo>(SalarioMinimo.class).salvar(this.salario);
			novoObjeto();
			Message.addSuccessMessage("Salario Salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao cadastrar salario minimo.");
		}
	}

	@Override
	public void novoObjeto() {
		this.salario = new SalarioMinimo();
	}

	@Override
	public List<SalarioMinimo> listaDeObjetos() {
		List<SalarioMinimo> listaSalario = new GenericPersistence<SalarioMinimo>(SalarioMinimo.class).listarTodos("SalarioMinimo");
		listaSalario.sort(new Comparator<SalarioMinimo>() {

			@Override
			public int compare(SalarioMinimo o1, SalarioMinimo o2) {
				return o2.getNUMR_ano() - o1.getNUMR_ano();
			}
		});
		return listaSalario;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pegaInstanciaDialogo(SalarioMinimo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	 public void onRowEdit(RowEditEvent event) {
		 	try{
		 		SalarioMinimo sal = (SalarioMinimo)event.getObject();
		 		System.out.println(sal.getNUMR_ano());
		 		System.out.println(sal.getDt_vigencia());
		 		System.out.println(sal.getNUMR_valor());
		 		new GenericPersistence<SalarioMinimo>(SalarioMinimo.class).salvar(sal);
		 		Message.addSuccessMessage("Salario Salvo com sucesso!");
		 	}catch(Exception e){
		 		Message.addErrorMessage("Erro ao cadastrar salario minimo.");
		 	}
	 }
	     
	 public void onRowCancel(RowEditEvent event) {
		 System.out.println("Cancelou.");
	 }
}
