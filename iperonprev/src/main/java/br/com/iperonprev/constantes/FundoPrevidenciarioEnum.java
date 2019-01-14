package br.com.iperonprev.constantes;

public enum FundoPrevidenciarioEnum {
	FINANCEIRO("Financeiro"),
	CAPITALIZADO("Capitalizado");
	
	private String nome;

	FundoPrevidenciarioEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
