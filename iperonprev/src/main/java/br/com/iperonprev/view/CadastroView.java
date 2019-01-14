package br.com.iperonprev.view;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CadastroView {

	
	public String atosLegais(){
		return "/paginas/cadastro/atos.xhtml?faces-redirect=true";
	}
}
