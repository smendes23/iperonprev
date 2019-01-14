package br.com.iperonprev.constantes;

public enum TipoDeducaoEnum {
FALTA("Falta"),SUSPENSAO("Suspensão"),OUTROS("Outros"),CONCOMITANCIA("Concomitância");
	
	private String nome;

	TipoDeducaoEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
