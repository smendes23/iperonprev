package br.com.iperonprev.constantes;

public enum VinculoPrevidenciarioEnum {

EFETIVO("Servidor cargo efetivo"),COMISSIONADO("Servidor cargo comissionado"),SEMVINCULO("Servidor sem vínculo"),MILITAR("Militar"),INATIVO("Inativo");
	
	private String nome;

	VinculoPrevidenciarioEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
