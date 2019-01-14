package br.com.iperonprev.interfaces;

import java.util.List;
import java.util.Map;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public interface RegrasAposentadoria {

	RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao);
	
	Map<String,Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria);
	void proximoObject(RegrasAposentadoria proximo);
	Simulador getSimulador();
}
