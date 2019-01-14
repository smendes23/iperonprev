package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.helper.ValidaRegrasAposentadoria;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class ArtigoPcI implements RegrasAposentadoria{
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Simulador simulador = new Simulador();
	
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		this.simulador.setRegraAposentadoria("L) Regra de Policial Civil de acordo com a Lei Complementar Nº 59, de Novembro de 2006");
		this.simulador.setTipoProventos( "Integral");
		try {
			if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){
					return RegrasAposentadoriaEnum.ARTIGOPCI;
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	private RegrasAposentadoria proximo;
	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		Map<String,Object> mapaObjeto = new HashMap<>();
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGOPCI){
			mapaObjeto.put("provento", TipoProventosEnum.INTEGRAL);
			mapaObjeto.put("reajuste", TipoReajusteEnum.PARIDADE);
			return mapaObjeto;
		}
		return proximo.verificaProventosEReajuste(regrasAposentadoria);
	}
	@Override
	public void proximoObject(RegrasAposentadoria proximo) {
		this.proximo = proximo;
	}
	
	public Simulador getSimulador() {
		return simulador;
	}
	
	
}
