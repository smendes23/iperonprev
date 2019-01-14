package br.com.iperonprev.services.atoConcessorio;

import br.com.iperonprev.controller.dto.AtoConcessorioDto;
import br.com.iperonprev.interfaces.AtoConcessorio;
import br.com.iperonprev.models.AtosConcessorios;
import br.com.iperonprev.models.PoderPessoas;

public class QualificaAto {

	public AtoConcessorioDto executa(AtosConcessorios atosConcessorios,PoderPessoas chefePoder,PoderPessoas presidenteIperon){
		
		AtoConcessorio compulsoria = new AtoAposentadoriaCompulsoria();
		AtoConcessorio idade = new AtoAposentadoriaIdade();
		AtoConcessorio idadeContribuicao = new AtoAposentadoriaIdadeTempoContribuicao();
		AtoConcessorio invalidez = new AtoAposentadoriaInvalidez();
		AtoConcessorio professor = new AtoAposentadoriaEspecialProfessor();
		AtoConcessorio policial = new AtoAposentadoriaEspecialPolicial();
		AtoConcessorio reforma = new AtoReforma();
		AtoConcessorio reserva = new AtoReserva();
		AtoConcessorio reversao = new AtoReversaoAposentadoria();
		AtoConcessorio nulo = new AtoNulo();
		
		compulsoria.proximoAto(idade);
		idade.proximoAto(idadeContribuicao);
		idadeContribuicao.proximoAto(invalidez);
		invalidez.proximoAto(nulo);
		/*professor.proximoAto(policial);
		policial.proximoAto(reforma);
		reforma.proximoAto(reserva);
		reserva.proximoAto(reversao);
		reversao.proximoAto(nulo);*/
		
		return compulsoria.redirecionaAto(atosConcessorios,chefePoder,presidenteIperon);
	}
}
