package br.com.iperonprev.controller.dto;

import java.io.Serializable;

public class TemposDeduzidosDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoDeducao = "-";
	private String periodo = "-";
	private int quantidade = 0;
	private int totalDias = 0;
	public String getTipoDeducao() {
		return tipoDeducao;
	}
	public void setTipoDeducao(String tipoDeducao) {
		this.tipoDeducao = tipoDeducao;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getTotalDias() {
		return totalDias;
	}
	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}
	
}
