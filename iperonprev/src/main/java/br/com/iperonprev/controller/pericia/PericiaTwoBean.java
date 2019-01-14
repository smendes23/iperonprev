package br.com.iperonprev.controller.pericia;

import java.io.Serializable;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;


public class PericiaTwoBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	public String redirecionaPericia(String url){
		return url;
	}

}
