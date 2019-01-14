package br.com.iperonprev.constantes;

public enum TipoGuiaArrecadacaoEnum {

SP("Segurado/Patronal"),S("Segurado"),P("Patronal");
	
	private String nome;

	TipoGuiaArrecadacaoEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
