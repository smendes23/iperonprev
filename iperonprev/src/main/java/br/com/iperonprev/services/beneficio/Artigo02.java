package br.com.iperonprev.services.beneficio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import br.com.iperonprev.util.jsf.RetornaTempos;
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;

public class Artigo02 implements RegrasAposentadoria{
	Simulador simulador = new Simulador();
	@SuppressWarnings("static-access")
	Date dataAtual = new LocalDate().now().toDate();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao).getFuncional(pf);
		
		this.simulador.setRegraAposentadoria("B) Regra de Transição(Art. 2º EC 41/2003)");
		this.simulador.setTipoProventos("Benefício Médio com Reajuste por Índice Oficial");
		this.simulador.setDescricaoRegra("Aplicável apenas aos servidores titulares de cargo efetivo que tenham ingressado no serviço público até 16/12/1998.");
		
		this.simulador.setRequisito1("Tempo no Cargo");
		this.simulador.setRequisito2("Tempo de Contribuição");
		this.simulador.setRequisito3("Tempo de Contrbuição mais Pedágio 20%");
		this.simulador.setRequisito4("Idade");
		
		this.simulador.setRequerido1("5 anos");
		this.simulador.setRequerido2("30 anos");
		this.simulador.setRequerido3((RetornaTempos.retornaPedagioVintePorcento(10950,"16/12/1998", pf.getDATA_efetivoExercicio()) /365)+" anos");
		this.simulador.setRequerido4("48 anos");
		
		this.simulador.setStatus("Indisponível");
		
		if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M){
			this.simulador.setRequerido3((RetornaTempos.retornaPedagioVintePorcento(12775,"16/12/1998", pf.getDATA_efetivoExercicio()) /365)+" anos");
			this.simulador.setRequerido4("53 anos");
			if(StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
				this.simulador.setRequerido3((RetornaTempos.retornaPedagioVintePorcentoComBonusDezessetePorcento(12775,"16/12/1998", pf.getDATA_efetivoExercicio()) /365)+" anos");
			}
		}
		
		
		try {
			if(pf.getDATA_efetivoExercicio().before(sdf.parse("17/12/1998"))){
				
				this.simulador.setFaltante1(tp.retornaTempoSimulacao(5));
				this.simulador.setFaltante2(tp.retornaTempoContribuicaoSimulacao(30));
				this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(RetornaTempos.retornaPedagioVintePorcento(10950,"16/12/1998", pf.getDATA_efetivoExercicio()) /365));
				this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 48));
				
				if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.F && StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
					this.simulador.setRequerido3(tp.retornaTempoContribuicaoSimulacao(RetornaTempos.retornaPedagioVintePorcento(10950,"16/12/1998", pf.getDATA_efetivoExercicio()) /365));
				}
				
				if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M ){
					this.simulador.setFaltante2(tp.retornaTempoContribuicaoSimulacao(35));
					this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(RetornaTempos.retornaPedagioVintePorcento(12775,"16/12/1998", pf.getDATA_efetivoExercicio()) /365));
					this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 53));
					
					
					if(StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
						this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(RetornaTempos.retornaPedagioVintePorcento(12775,"16/12/1998", pf.getDATA_efetivoExercicio()) /365));
					}
				}
				
				if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){
					
					this.simulador.setStatus("Disponível");
					return RegrasAposentadoriaEnum.ARTIGO2;
					
				}
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private RegrasAposentadoria proximo;
	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		Map<String,Object> mapaObjetos = new HashMap<>();
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGO2){
			mapaObjetos.put("provento", TipoProventosEnum.PROPORCIONAL);
			mapaObjetos.put("rejuste", TipoReajusteEnum.CONFORMELEI);
			return mapaObjetos;
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
