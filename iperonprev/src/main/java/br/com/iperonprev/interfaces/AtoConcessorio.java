package br.com.iperonprev.interfaces;

import br.com.iperonprev.controller.dto.AtoConcessorioDto;
import br.com.iperonprev.models.AtosConcessorios;
import br.com.iperonprev.models.PoderPessoas;

public interface AtoConcessorio {

	AtoConcessorioDto redirecionaAto(AtosConcessorios atosConcessorios,PoderPessoas chefePoder,PoderPessoas presidenteIperon);
	void proximoAto(AtoConcessorio ato);
}
