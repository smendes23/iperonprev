package br.com.iperonprev.constantes;

public enum TipoBeneficioEnum {

COMPULSORIA("Aposentadoria Compulsoria"),
ESPECIAL("Aposentadoria Especial de Professor"),
ESPECIALPOLICIA("Aposentadoria Especial de Policial"),
IDADE("Aposentadoria Voluntária por Idade"),
INVALIDEZ("Aposentadoria Voluntária por Invalidez"),
IDADETEMPO("Aposentadoria Voluntária por Idade e Tempo de Contribuição"),
PENSAO("Pensão"),
RESERVA("Reserva Remunerada"),
REFORMA("Reforma"),
REVERSAO("Reversão de Aposentadoria"),
OUTROS("Outros")
;
	
	private String nome;

	TipoBeneficioEnum(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
}
