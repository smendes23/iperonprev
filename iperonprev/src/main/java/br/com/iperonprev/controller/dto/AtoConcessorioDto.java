package br.com.iperonprev.controller.dto;

public class AtoConcessorioDto {

	private String tituloRelatorio;
	private String cabecalho;
	private String texto1;
	private String texto2;
	private String texto3;
	private String chefePoder;
	private String poder;
	private String chefeIperon;
	private String numeroProcesso;
	
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	public String getTituloRelatorio() {
		return tituloRelatorio;
	}
	public void setTituloRelatorio(String tituloRelatorio) {
		this.tituloRelatorio = tituloRelatorio;
	}
	public String getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	public String getTexto1() {
		return texto1;
	}
	public void setTexto1(String texto1) {
		this.texto1 = texto1;
	}
	public String getTexto2() {
		return texto2;
	}
	public void setTexto2(String texto2) {
		this.texto2 = texto2;
	}
	public String getTexto3() {
		return texto3;
	}
	public void setTexto3(String texto3) {
		this.texto3 = texto3;
	}
	public String getChefePoder() {
		return chefePoder;
	}
	public void setChefePoder(String chefePoder) {
		this.chefePoder = chefePoder;
	}
	public String getPoder() {
		return poder;
	}
	public void setPoder(String poder) {
		this.poder = poder;
	}
	public String getChefeIperon() {
		return chefeIperon;
	}
	public void setChefeIperon(String chefeIperon) {
		this.chefeIperon = chefeIperon;
	}
}
