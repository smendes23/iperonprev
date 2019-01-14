package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.interfaces.TemposConcomitanteUmaAverbacao;
import br.com.iperonprev.models.Averbacao;

public class QualificaConcomitanciaUmaAverbacao {

	public boolean executa(Averbacao a1){
		TemposConcomitanteUmaAverbacao con1 = new ConcomitanciaDataAdmissaoComDataPosse();
		TemposConcomitanteUmaAverbacao con2 = new ConcomitanciaDataAdmissaoIgualDataPosse();
		TemposConcomitanteUmaAverbacao con3 = new ConcomitanciaDataExoneracaoComDataPosse();
		TemposConcomitanteUmaAverbacao con4 = new ConcomitanciaDataExoneracaoIgualDataPosse();
		TemposConcomitanteUmaAverbacao nulo = new ConcomitanciaNulaUmaAverbacao();
				con1.setProximaConcomitancia(con2);
				con2.setProximaConcomitancia(con3);
				con3.setProximaConcomitancia(con4);
				con4.setProximaConcomitancia(nulo);
			return con1.verificaConcomitancia(a1);
	}
}
