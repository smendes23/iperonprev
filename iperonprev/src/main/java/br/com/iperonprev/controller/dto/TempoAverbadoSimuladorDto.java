package br.com.iperonprev.controller.dto;

import java.io.Serializable;

public class TempoAverbadoSimuladorDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String periodo = "-";
	private int quantidadeDias = 0;
	private String tempoExtenso = "-";
	private String regime= "-";
	private String atividade= "-";
	private String totalTempoExtensoRpps= "-";
	private String totalTempoExtensoRgps= "-";
	private int totalDiasRgps = 0;
	private int totalDiasRpps = 0;
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public int getQuantidadeDias() {
		return quantidadeDias;
	}
	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}
	public String getTempoExtenso() {
		return tempoExtenso;
	}
	public void setTempoExtenso(String tempoExtenso) {
		this.tempoExtenso = tempoExtenso;
	}
	public String getRegime() {
		return regime;
	}
	public void setRegime(String regime) {
		this.regime = regime;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getTotalTempoExtensoRpps() {
		return totalTempoExtensoRpps;
	}
	public void setTotalTempoExtensoRpps(String totalTempoExtensoRpps) {
		this.totalTempoExtensoRpps = totalTempoExtensoRpps;
	}
	public String getTotalTempoExtensoRgps() {
		return totalTempoExtensoRgps;
	}
	public void setTotalTempoExtensoRgps(String totalTempoExtensoRgps) {
		this.totalTempoExtensoRgps = totalTempoExtensoRgps;
	}
	public int getTotalDiasRgps() {
		return totalDiasRgps;
	}
	public void setTotalDiasRgps(int totalDiasRgps) {
		this.totalDiasRgps = totalDiasRgps;
	}
	public int getTotalDiasRpps() {
		return totalDiasRpps;
	}
	public void setTotalDiasRpps(int totalDiasRpps) {
		this.totalDiasRpps = totalDiasRpps;
	}
	
}
