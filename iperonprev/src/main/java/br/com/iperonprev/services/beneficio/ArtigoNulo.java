package br.com.iperonprev.services.beneficio;

import java.util.List;
import java.util.Map;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class ArtigoNulo implements RegrasAposentadoria{

	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void proximoObject(RegrasAposentadoria proximo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Simulador getSimulador() {
		// TODO Auto-generated method stub
		return null;
	}

}
