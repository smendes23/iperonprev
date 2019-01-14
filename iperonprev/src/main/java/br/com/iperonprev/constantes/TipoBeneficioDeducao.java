package br.com.iperonprev.constantes;

public enum TipoBeneficioDeducao {

	AD("Auxilio Doença"), SM("Salário Maternidade");

	private String nome;

	TipoBeneficioDeducao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
