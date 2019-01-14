package br.com.iperonprev.constantes;

public enum SituacaoPrevidenciariaEnum {

ATIVO("Servidor Ativo"),INATIVO("Servidor Inativo");
	
	private String nome;

	SituacaoPrevidenciariaEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
