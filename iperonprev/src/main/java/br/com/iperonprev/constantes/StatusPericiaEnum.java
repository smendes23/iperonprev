package br.com.iperonprev.constantes;

public enum StatusPericiaEnum {
	EXPIRADO("Expirado"),
	EXPIRANDO("Expirando"),
	ANDAMENTO("Dentro do Prazo"),
	NAOPERICIA("Náo Pericia"),
	SEMPERICIA("Sem Pericia"),
	REALIZADO("Realizado");
	
	private String nome;

	StatusPericiaEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

}
