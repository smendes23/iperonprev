package br.com.iperonprev.constantes;

public enum TipoOperacaoEnum {

I("Isenrir"),U("Editar"),D("Excluir");
	
	private String nome;

	TipoOperacaoEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
}
