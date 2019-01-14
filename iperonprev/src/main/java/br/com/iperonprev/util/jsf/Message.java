package br.com.iperonprev.util.jsf;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message {
	public static void addSuccessMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Sucesso!", message)); 
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro!", message)); 
	}
	
	public static void addWarningMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aviso!", message)); 
	}
}
