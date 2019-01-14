package br.com.iperonprev.constantes;

public enum DecenioEnum {
	PRIMEIRO("1° Quinquênio"),
	SEGUNDO("2° Quinquênio"),
	TERCEIRO("3° Quinquênio"),
	QUARTO("4° Quinquênio"),
	QUINTO("5° Quinquênio"),
	SEXTO("6° Quinquênio"),
	SETIMO("7° Quinquênio");
	
	private String nome;
	DecenioEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
