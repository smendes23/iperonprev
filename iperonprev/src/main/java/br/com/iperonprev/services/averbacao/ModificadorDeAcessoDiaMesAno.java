package br.com.iperonprev.services.averbacao;

public class ModificadorDeAcessoDiaMesAno {

	private int dia;
	private int mes;
	private int ano;
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public ModificadorDeAcessoDiaMesAno(){}
	
	public ModificadorDeAcessoDiaMesAno(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	
}
