package br.com.iperonprev.services.atoConcessorio;

import br.com.iperonprev.controller.dto.AtoConcessorioDto;
import br.com.iperonprev.interfaces.AtoConcessorio;
import br.com.iperonprev.models.AtosConcessorios;
import br.com.iperonprev.models.PoderPessoas;

public class AtoAposentadoriaEspecialProfessor implements AtoConcessorio{
	private AtoConcessorio proximo;
	@Override
	public AtoConcessorioDto redirecionaAto(AtosConcessorios atosConcessorios,PoderPessoas chefePoder,PoderPessoas presidenteIperon) {
		
		return proximo.redirecionaAto(atosConcessorios,chefePoder,presidenteIperon);
	}

	@Override
	public void proximoAto(AtoConcessorio ato) {
		this.proximo = ato;
	}

}
