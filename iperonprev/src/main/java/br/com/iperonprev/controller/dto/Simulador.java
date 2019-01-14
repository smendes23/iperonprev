package br.com.iperonprev.controller.dto;

import java.io.Serializable;

public class Simulador implements Serializable {

	private static final long serialVersionUID = 1L;
	private String regraAposentadoria;
	private String tipoProventos;
	private String descricaoRegra;
	private String requisito1;
	private String requisito2;
	private String requisito3;
	private String requisito4;
	private String requisito5;
	
	private String requerido1;
	private String requerido2;
	private String requerido3;
	private String requerido4;
	private String requerido5;
	
	private String faltante1;
	private String faltante2;
	private String faltante3;
	private String faltante4;
	private String faltante5;
	
	private String status;

	public String getRegraAposentadoria() {
		return regraAposentadoria;
	}

	public void setRegraAposentadoria(String regraAposentadoria) {
		this.regraAposentadoria = regraAposentadoria;
	}

	public String getTipoProventos() {
		return tipoProventos;
	}

	public void setTipoProventos(String tipoProventos) {
		this.tipoProventos = tipoProventos;
	}

	public String getDescricaoRegra() {
		return descricaoRegra;
	}

	public void setDescricaoRegra(String descricaoRegra) {
		this.descricaoRegra = descricaoRegra;
	}

	public String getRequisito1() {
		return requisito1;
	}

	public void setRequisito1(String requisito1) {
		this.requisito1 = requisito1;
	}

	public String getRequisito2() {
		return requisito2;
	}

	public void setRequisito2(String requisito2) {
		this.requisito2 = requisito2;
	}

	public String getRequisito3() {
		return requisito3;
	}

	public void setRequisito3(String requisito3) {
		this.requisito3 = requisito3;
	}

	public String getRequisito4() {
		return requisito4;
	}

	public void setRequisito4(String requisito4) {
		this.requisito4 = requisito4;
	}

	public String getRequisito5() {
		return requisito5;
	}

	public void setRequisito5(String requisito5) {
		this.requisito5 = requisito5;
	}

	public String getRequerido1() {
		return requerido1;
	}

	public void setRequerido1(String requerido1) {
		this.requerido1 = requerido1;
	}

	public String getRequerido2() {
		return requerido2;
	}

	public void setRequerido2(String requerido2) {
		this.requerido2 = requerido2;
	}

	public String getRequerido3() {
		return requerido3;
	}

	public void setRequerido3(String requerido3) {
		this.requerido3 = requerido3;
	}

	public String getRequerido4() {
		return requerido4;
	}

	public void setRequerido4(String requerido4) {
		this.requerido4 = requerido4;
	}

	public String getRequerido5() {
		return requerido5;
	}

	public void setRequerido5(String requerido5) {
		this.requerido5 = requerido5;
	}

	public String getFaltante1() {
		return faltante1;
	}

	public void setFaltante1(String faltante1) {
		this.faltante1 = faltante1;
		if(this.faltante1.substring(0,1).equals("-")){
			this.faltante1  = "";
		}
	}

	public String getFaltante2() {
		return faltante2;
	}

	public void setFaltante2(String faltante2) {
		this.faltante2 = faltante2;
		if(this.faltante2.substring(0,1).equals("-")){
			this.faltante2  = "";
		}
	}

	public String getFaltante3() {
		return faltante3;
	}

	public void setFaltante3(String faltante3) {
		this.faltante3 = faltante3;
		if(this.faltante3.substring(0,1).equals("-")){
			this.faltante3  = "";
		}
	}

	public String getFaltante4() {
		return faltante4;
	}

	public void setFaltante4(String faltante4) {
		this.faltante4 = faltante4;
		if(this.faltante4.substring(0,1).equals("-")){
			this.faltante4  = "";
		}
	}

	public String getFaltante5() {
		return faltante5;
	}

	public void setFaltante5(String faltante5) {
		this.faltante5 = faltante5;
		if(this.faltante5.substring(0,1).equals("-")){
			this.faltante5  = "";
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
