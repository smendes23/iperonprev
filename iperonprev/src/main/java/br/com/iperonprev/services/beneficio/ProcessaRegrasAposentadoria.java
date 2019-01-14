package br.com.iperonprev.services.beneficio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.models.Cargos;

public class ProcessaRegrasAposentadoria {

	public List<RegrasAposentadoriaEnum> devolveListaDeRegras(int idade, int tempoContribuicao, int tempoServicoPublico,
			int tempoCargo, int tempoCarreira, SexoEnum sexo,Date dataPosse, Cargos cargos,TipoBeneficioEnum tipoAposentadoria,Date requisitos){
		
			List<RegrasAposentadoriaEnum> lista = new ArrayList<RegrasAposentadoriaEnum>();
			/*lista.add(0,new Artigo06().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(1,new Artigo02().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(2,new Artigo40B().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(3,new Artigo40A().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(4,new Artigo08P().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(5,new Artigo08I().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(6,new Artigo40B20().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(7,new Artigo40A20().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));
			lista.add(8,new Artigo03().verificaRegra(idade, tempoContribuicao, tempoServicoPublico, tempoCargo, tempoCarreira, sexo, dataPosse, cargos, tipoAposentadoria, requisitos));*/
		return lista;
	}
}
