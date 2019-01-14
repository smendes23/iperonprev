package br.com.iperonprev.constantes;

public enum TipoReajusteEnum {

	 CONFORMELEI("Conforme a Lei"),PARIDADE("Paridade");
	private String nome;

	TipoReajusteEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
