package br.com.iperonprev.controller.gerenciador;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.iperonprev.util.jsf.Message;

@ManagedBean(name="loginBean")
@SessionScoped
public class Login {
	
	public void mensagem(){
		Message.addErrorMessage("teste");
	}
	public String redireciona(){
		return "/paginas/inicio/index.xhtml?faces-redirect=true";
	}
	
	private String username;
	   private String password;

	   public String getUsername() {
	      return username;
	   }

	   public void setUsername(String username) {
	      this.username = username;
	   }

	   public String getPassword() {
	      return password;
	   }

	   public void setPassword(String password) {
	      this.password = password;
	   }

	   public void loga() throws ServletException, IOException{  
		   
		   ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
	        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
	        FacesContext.getCurrentInstance().responseComplete();
	   }
	   

	
}
