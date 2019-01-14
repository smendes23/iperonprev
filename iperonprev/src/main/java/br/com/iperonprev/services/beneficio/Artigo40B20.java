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
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;

public class Artigo40B20 implements RegrasAposentadoria{
	@SuppressWarnings("static-access")
	Date dataAtual = new LocalDate().now().toDate();
	Simulador simulador = new Simulador();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao).getFuncional(pf);
		this.simulador.setRegraAposentadoria("G) Regra Anterior Art. 40, § 1º,III \"b\" da CF(EC20) Proporcional - Regra Por Idade da EC Nº 20/98");
		this.simulador.setTipoProventos("Paridade com Proporcionalidade da Última Remuneração");
		this.simulador.setDescricaoRegra("Aplicável apenas aos servidores titulares de cargo efetivo que tenham ingressado no serviço público até 30/12/2003.");
		
		this.simulador.setRequisito1("Tempo no Cargo");
		this.simulador.setRequisito2("Tempo no Serviço Público");
		this.simulador.setRequisito3("Idade");

		this.simulador.setRequerido1("5 anos");
		this.simulador.setRequerido2("10 anos");
		this.simulador.setRequerido3("60 anos");
		
		if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M){
			this.simulador.setRequerido3("65 anos");
		}
		this.simulador.setStatus("Indisponível");
		
		try {
		
			if(pf.getDATA_efetivoExercicio().before(sdf.parse("31/12/2003"))){
				this.simulador.setFaltante1(tp.retornaTempoSimulacao(5));
				this.simulador.setFaltante2(tp.retornaTempoSimulacao(10));
				this.simulador.setFaltante3(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 60));
				if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M){
					this.simulador.setFaltante3(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 65));
				}
			}
			
			if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){
					this.simulador.setStatus("Disponível");
					return RegrasAposentadoriaEnum.ARTIGO40B20;
			}
		} catch (Exception e) {
			System.out.println("Erro de conversão: "+e.getMessage());
		}
		return null;
	}
	private RegrasAposentadoria proximo;
	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		Map<String,Object> mapaObjeto = new HashMap<>();
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGO40B20){
			mapaObjeto.put("provento", TipoProventosEnum.PROPORCIONAL);
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
