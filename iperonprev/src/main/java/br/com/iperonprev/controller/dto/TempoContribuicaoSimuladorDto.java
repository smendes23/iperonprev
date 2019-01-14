package br.com.iperonprev.controller.dto;

import java.io.Serializable;

import br.com.iperonprev.util.averbacao.DiaMesAno;

public class TempoContribuicaoSimuladorDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String periodo = "-";
	private int quantidadeDias = 0;
	private String tempoExtenso = "-";
	private String totalTempoExtenso = "-";
	private int totalDias = 0;
	private String orgao = "-";
	private String matricula = "-";
	private DiaMesAno diaMesAno;
	
	public DiaMesAno getDiaMesAno() {
		return diaMesAno;
	}
	public void setDiaMesAno(DiaMesAno diaMesAno) {
		this.diaMesAno = diaMesAno;
	}
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
	public String getTotalTempoExtenso() {
		return totalTempoExtenso;
	}
	public void setTotalTempoExtenso(String totalTempoExtenso) {
		this.totalTempoExtenso = totalTempoExtenso;
	}
	public int getTotalDias() {
		return totalDias;
	}
	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public TempoContribuicaoSimuladorDto(){}
	public TempoContribuicaoSimuladorDto(String periodo, int quantidadeDias, String tempoExtenso,
			String totalTempoExtenso, int totalDias, String orgao, String matricula, DiaMesAno diaMesAno) {
		super();
		this.periodo = periodo;
		this.quantidadeDias = quantidadeDias;
		this.tempoExtenso = tempoExtenso;
		this.totalTempoExtenso = totalTempoExtenso;
		this.totalDias = totalDias;
		this.orgao = orgao;
		this.matricula = matricula;
		this.diaMesAno = diaMesAno;
	}
	
	
}
