package br.com.iperonprev.constantes;

public enum TipoOnusEnum {
	ORIGEM("Orgão de Origem"),DESTINO("Orgão de Destino");
	
	private String nome;

	TipoOnusEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
