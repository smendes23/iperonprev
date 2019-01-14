package br.com.iperonprev.models;

import java.io.Serializable;

public class FrequenciaCts implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int ano;
	private int tempoBruto;
	private int faltas;
	private int licencas;
	private int suspensoes;
	private int outros;
	private int soma;
	private int tempoLiquido;
	private int bissextos;
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getTempoBruto() {
		return tempoBruto;
	}
	public void setTempoBruto(int tempoBruto) {
		this.tempoBruto = tempoBruto;
	}
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	public int getLicencas() {
		return licencas;
	}
	public void setLicencas(int licencas) {
		this.licencas = licencas;
	}
	public int getSuspensoes() {
		return suspensoes;
	}
	public void setSuspensoes(int suspensoes) {
		this.suspensoes = suspensoes;
	}
	public int getOutros() {
		return outros;
	}
	public void setOutros(int outros) {
		this.outros = outros;
	}
	public int getSoma() {
		return soma;
	}
	public void setSoma(int soma) {
		this.soma = soma;
	}
	public int getTempoLiquido() {
		return tempoLiquido;
	}
	public void setTempoLiquido(int tempoLiquido) {
		this.tempoLiquido = tempoLiquido;
	}
	public int getBissextos() {
		return bissextos;
	}
	public void setBissextos(int bissextos) {
		this.bissextos = bissextos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + bissextos;
		result = prime * result + faltas;
		result = prime * result + licencas;
		result = prime * result + outros;
		result = prime * result + soma;
		result = prime * result + suspensoes;
		result = prime * result + tempoBruto;
		result = prime * result + tempoLiquido;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrequenciaCts other = (FrequenciaCts) obj;
		if (ano != other.ano)
			return false;
		if (bissextos != other.bissextos)
			return false;
		if (faltas != other.faltas)
			return false;
		if (licencas != other.licencas)
			return false;
		if (outros != other.outros)
			return false;
		if (soma != other.soma)
			return false;
		if (suspensoes != other.suspensoes)
			return false;
		if (tempoBruto != other.tempoBruto)
			return false;
		if (tempoLiquido != other.tempoLiquido)
			return false;
		return true;
	}

}
