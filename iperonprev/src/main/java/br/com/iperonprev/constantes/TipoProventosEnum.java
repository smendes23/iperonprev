package br.com.iperonprev.constantes;

public enum TipoProventosEnum {

	PROPORCIONAL("Proporcional"),INTEGRAL("Integral");
	private String nome;

	TipoProventosEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
