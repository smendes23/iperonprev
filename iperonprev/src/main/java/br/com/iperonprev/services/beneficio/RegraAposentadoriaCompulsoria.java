package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class RegraAposentadoriaCompulsoria implements RegrasAposentadoria{
	Simulador simulador = new Simulador();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		this.simulador.setRegraAposentadoria("K) Compulsória");
		this.simulador.setTipoProventos( "Proporcional");
		this.simulador.setDescricaoRegra(new StringBuilder().append("Pode se aposentar a partir de: ")
				.append(sdf.format(RetornaTempos.retornaDataFuturaComAnos(70, pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento()))).toString());
		
		return RegrasAposentadoriaEnum.ARTIGO40COMPULSORIA;
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
		return simulador;
	}

}
