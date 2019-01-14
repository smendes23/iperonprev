package br.com.iperonprev.controller.gerenciador;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
public class Logout {
	
	public void sair(){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext  external = context.getExternalContext();
			external.invalidateSession();
			external.redirect("/iperonprev/");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public String fechaConexao(){
		return "#{request.contextPath}/logout";
	}
}
