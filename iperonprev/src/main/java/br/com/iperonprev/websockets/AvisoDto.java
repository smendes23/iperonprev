package br.com.iperonprev.websockets;

import java.io.Serializable;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

public class AvisoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String mensagem;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String toJson() {
		JSONObject jo = new JSONObject();
			
		try {
			jo.append("titulo", this.titulo)
					.append("mensagem", this.mensagem);
		} catch (JSONException e) {
			e.printStackTrace();
		}
			
		return jo.toString();
	}

}
