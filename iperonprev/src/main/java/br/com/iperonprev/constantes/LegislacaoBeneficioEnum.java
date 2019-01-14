package br.com.iperonprev.constantes;

public enum LegislacaoBeneficioEnum {

	LEI68("Lei 068 de 1992"),
	LC228("Lei Complementar 228 de 2000"),
	LC253("Lei Complementar 253 de 2002"),
	LC432("Lei Complementar 432 de 2008"),
	DL09A42("Decreto  Lei 09-A de 1982 e Decreto 42 de 1093"),
	DL09A683("Decreto Lei 09-A de 1982 com a Lei 683 de 1996");
	
	private String nome;
	private LegislacaoBeneficioEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
}
