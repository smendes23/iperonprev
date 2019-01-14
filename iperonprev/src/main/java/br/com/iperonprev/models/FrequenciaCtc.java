package br.com.iperonprev.models;

import java.io.Serializable;

public class FrequenciaCtc implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int ano;
	private int tempoBruto;
	private int tempoFicto;
	private int faltas;
	private int licencas;
	private int licencasSemVencimentos;
	private int suspensoes;
	private int Disponibilidade;
	private int outras;
	private int tempoLiquido;
	private int bissextos;
	private int soma;
	
	public int getSoma() {
		return soma;
	}
	public void setSoma(int soma) {
		this.soma = soma;
	}
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
	public int getTempoFicto() {
		return tempoFicto;
	}
	public void setTempoFicto(int tempoFicto) {
		this.tempoFicto = tempoFicto;
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
	public int getLicencasSemVencimentos() {
		return licencasSemVencimentos;
	}
	public void setLicencasSemVencimentos(int licencasSemVencimentos) {
		this.licencasSemVencimentos = licencasSemVencimentos;
	}
	public int getSuspensoes() {
		return suspensoes;
	}
	public void setSuspensoes(int suspensoes) {
		this.suspensoes = suspensoes;
	}
	public int getDisponibilidade() {
		return Disponibilidade;
	}
	public void setDisponibilidade(int disponibilidade) {
		Disponibilidade = disponibilidade;
	}
	public int getOutras() {
		return outras;
	}
	public void setOutras(int outras) {
		this.outras = outras;
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
		result = prime * result + Disponibilidade;
		result = prime * result + ano;
		result = prime * result + bissextos;
		result = prime * result + faltas;
		result = prime * result + licencas;
		result = prime * result + licencasSemVencimentos;
		result = prime * result + outras;
		result = prime * result + soma;
		result = prime * result + suspensoes;
		result = prime * result + tempoBruto;
		result = prime * result + tempoFicto;
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
		FrequenciaCtc other = (FrequenciaCtc) obj;
		if (Disponibilidade != other.Disponibilidade)
			return false;
		if (ano != other.ano)
			return false;
		if (bissextos != other.bissextos)
			return false;
		if (faltas != other.faltas)
			return false;
		if (licencas != other.licencas)
			return false;
		if (licencasSemVencimentos != other.licencasSemVencimentos)
			return false;
		if (outras != other.outras)
			return false;
		if (soma != other.soma)
			return false;
		if (suspensoes != other.suspensoes)
			return false;
		if (tempoBruto != other.tempoBruto)
			return false;
		if (tempoFicto != other.tempoFicto)
			return false;
		if (tempoLiquido != other.tempoLiquido)
			return false;
		return true;
	}
	
}
