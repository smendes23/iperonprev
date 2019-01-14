package br.com.iperonprev.constantes;

public enum DecisaoEnum {
SIM("Sim"),NAO("Não");
	
	private String nome;
	DecisaoEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
