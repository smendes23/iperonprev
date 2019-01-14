package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class ArtigoPcP implements RegrasAposentadoria{
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Simulador simulador = new Simulador();
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		
		try {
			/*if(tempoServicoPublico >= 9125 && tempoCarreira >= 7300 && idade < 70
					&& StringUtils.containsIgnoreCase(cargos.getDESC_nome(), "policia") && dataPosse.before(sdf.parse("23/11/2006"))){
				
				if(sexo == SexoEnum.M && tempoContribuicao == 10950 && idade >= 55
						|| sexo == SexoEnum.F && tempoContribuicao == 9125 && idade >= 50){
					
					return RegrasAposentadoriaEnum.ARTIGOPCP;
				}
			}*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	private RegrasAposentadoria proximo;
	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		Map<String,Object> mapaObjeto = new HashMap<>();
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGOPCP){
			mapaObjeto.put("provento", TipoProventosEnum.PROPORCIONAL);
			mapaObjeto.put("reajuste", TipoReajusteEnum.CONFORMELEI);
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
