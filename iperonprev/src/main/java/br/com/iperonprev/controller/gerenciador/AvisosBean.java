package br.com.iperonprev.controller.gerenciador;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import br.com.iperonprev.websockets.AvisoDto;
import br.com.iperonprev.websockets.AvisosEndpoint;

@Model
public class AvisosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AvisoDto aviso = new AvisoDto();
	
	public AvisoDto getAviso() {
		return aviso;
	}

	public void setAviso(AvisoDto aviso) {
		this.aviso = aviso;
	}

	
	public void enviar(){
		new AvisosEndpoint().send(aviso);
	}
}
