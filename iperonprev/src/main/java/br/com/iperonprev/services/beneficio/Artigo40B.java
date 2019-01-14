package br.com.iperonprev.services.beneficio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.helper.ValidaRegrasAposentadoria;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;

public class Artigo40B implements RegrasAposentadoria{
	RetornaTemposAverbacao rta = new RetornaTemposAverbacao();
	@SuppressWarnings("static-access")
	Date dataAtual = new LocalDate().now().toDate();
	Simulador simulador = new Simulador();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao).getFuncional(pf);
		
		this.simulador.setRegraAposentadoria("C) Regra Permanente Art. 40, § 1º \"b\" da CF(EC41) de acordo com a lei 10.887/04");
		this.simulador.setTipoProventos("Benefício Médio com Reajuste por Índice Oficial");
		
		this.simulador.setRequisito1("Tempo no Cargo");
		this.simulador.setRequisito2("Tempo de Serviço Público");
		this.simulador.setRequisito3("Idade");

		this.simulador.setRequerido1("5 anos");
		this.simulador.setRequerido2("10 anos");
		this.simulador.setRequerido3("60 anos");
		
		this.simulador.setStatus("Indisponível");
		
		try{
			this.simulador.setFaltante1(tp.retornaTempoSimulacao(5));
			this.simulador.setFaltante2(tp.retornaTempoSimulacao(10));
			this.simulador.setFaltante3(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 60));
		}catch(Exception e){
		}
		
		if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M){
			this.simulador.setRequerido3("65 anos");
		}
		
		if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){
				this.simulador.setStatus("Disponível");
				return RegrasAposentadoriaEnum.ARTIGO40B;
		}
		return null;
	}
	private RegrasAposentadoria proximo;
	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		Map<String,Object> mapaObjeto = new HashMap<>();
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGO40B){
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
