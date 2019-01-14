package br.com.iperonprev.constantes;

public enum TipoGuiaFundoPrevidenciarioEnum {

	O("Défice"),F("Financeiro"), C("Capitalizado");

	private String nome;

	TipoGuiaFundoPrevidenciarioEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
