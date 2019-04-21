package br.com.iperonprev.constantes;

public enum TipoAtosDiversosEnum {

	 ABONO("Abono de Permanência"),
	 AVERBACAO("Averbação"),
	 NOMEACAO("Nomeação"),
	 DESIGNACOES("Designações"),
	 DESPENSA("Despensa"),
	 REMOCAO("Remoção"),
	 TRANSFERENCIA("Transferência"),
	 PROGRESSAO("Asceção ou Prog. Funcional"),
	 ELOGIO("Elogio"),
	 PENALIDADE("Penalidade"),
	 OUTROS("Outros");
		private String nome;

		TipoAtosDiversosEnum(String nome){
			this.nome = nome;
		}
		public String getNome() {
			return nome;
		}
}
