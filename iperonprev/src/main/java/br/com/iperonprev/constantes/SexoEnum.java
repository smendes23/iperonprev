package br.com.iperonprev.constantes;

public enum SexoEnum {
	M("Masculino"),
	F("Feminino");
	
	private String nome;

	SexoEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

}
