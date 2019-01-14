package br.com.iperonprev.constantes;

public enum TipoRecadastramentoEnum {

	O("Online"),P("Presencial");
	private String nome;

	TipoRecadastramentoEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
