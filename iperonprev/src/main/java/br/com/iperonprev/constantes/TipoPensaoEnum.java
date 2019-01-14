package br.com.iperonprev.constantes;

public enum TipoPensaoEnum {

	T("Temporária"),V("Vitalícia");
	private String nome;

	TipoPensaoEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
