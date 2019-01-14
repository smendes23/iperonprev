package br.com.iperonprev.constantes;

public enum TipoGuiaEnum {

	SP("Segurado/Patronal"),S("Segurado"), P("Patronal");

	private String nome;

	TipoGuiaEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
