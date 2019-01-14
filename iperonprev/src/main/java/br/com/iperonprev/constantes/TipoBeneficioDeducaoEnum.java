package br.com.iperonprev.constantes;

public enum TipoBeneficioDeducaoEnum {

	AD("Auxilio Doença"), SM("Salário Maternidade");

	private String nome;

	TipoBeneficioDeducaoEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
