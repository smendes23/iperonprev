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
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;

public class Artigo06 implements RegrasAposentadoria {
	
	Simulador simulador = new Simulador();
	@SuppressWarnings("static-access")
	Date dataAtual = new LocalDate().now().toDate();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao).getFuncional(pf);
		this.simulador.setRegraAposentadoria("A) Regra de Transição(Art. 6º EC 41/2003)");
		this.simulador.setTipoProventos("Integralidade e Paridade");
		this.simulador.setDescricaoRegra("Aplicável apenas aos servidores titulares de cargo efetivo que tenham ingressado no serviço público até 31/12/2003.");
		
		this.simulador.setRequisito1("Tempo no Cargo");
		this.simulador.setRequisito2("Tempo na Carreira");
		this.simulador.setRequisito3("Tempo no Serviço Público");
		this.simulador.setRequisito4("Tempo de Contribuição");
		this.simulador.setRequisito5("Idade");
		
		this.simulador.setRequerido1("5 anos");
		this.simulador.setRequerido2("10 anos");
		this.simulador.setRequerido3("20 anos");
		this.simulador.setRequerido4("30 anos");
		this.simulador.setRequerido5("55 anos");
		
		this.simulador.setStatus("Indisponível");
		
		if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M && !StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
			this.simulador.setRequerido4("35 anos");
			this.simulador.setRequerido5("60 anos");
			
		}else if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.F && StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
			this.simulador.setRequerido4("25 anos");
			this.simulador.setRequerido5("50 anos");
		}
		
		try {
			
			if(pf.getDATA_efetivoExercicio().before(sdf.parse("01/01/2004"))){
				/*this.simulador.setFaltante1(rta.formata(rta.devolveDiaMesAnoSimulacao(dataAtual,new LocalDate(pf.getDATA_posse()).plusYears(5).toDate())));
				this.simulador.setFaltante2(rta.formata(rta.devolveDiaMesAnoSimulacao(dataAtual,new LocalDate(pf.getDATA_posse()).plusYears(10).toDate())));
				this.simulador.setFaltante3(rta.formata(rta.devolveDiaMesAnoSimulacao(dataAtual,new LocalDate(pf.getDATA_posse()).plusYears(20).toDate())));
				this.simulador.setFaltante4(rta.devolveTempoFaltanteRegraPrevidencia(30));*/
				this.simulador.setFaltante1(tp.retornaTempoSimulacao(5));
				this.simulador.setFaltante2(tp.retornaTempoSimulacao(10));
				this.simulador.setFaltante3(tp.retornaTempoSimulacao(20));
				this.simulador.setFaltante4(tp.retornaTempoContribuicaoSimulacao(30));
				this.simulador.setFaltante5(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 55));
				
				if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M && !StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
//					this.simulador.setFaltante4(rta.devolveTempoFaltanteRegraPrevidencia(35));
					this.simulador.setFaltante4(tp.retornaTempoContribuicaoSimulacao(35));
					this.simulador.setFaltante5(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 60));
					
				}else if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.F && StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){

//					this.simulador.setFaltante4(rta.devolveTempoFaltanteRegraPrevidencia(25));
					this.simulador.setFaltante4(tp.retornaTempoContribuicaoSimulacao(25));
					this.simulador.setFaltante5(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 50));
				}
			}
			
			if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){

					this.simulador.setStatus("Disponível");
					return RegrasAposentadoriaEnum.ARTIGO6;
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private RegrasAposentadoria proximo;
	
	@Override
	public Map<String, Object> verificaProventosEReajuste(RegrasAposentadoriaEnum regrasAposentadoria) {
		Map<String,Object> mapaObjeto = new HashMap<>();
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGO6){
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
