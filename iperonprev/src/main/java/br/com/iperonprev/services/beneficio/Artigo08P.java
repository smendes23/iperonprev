package br.com.iperonprev.services.beneficio;

import java.text.ParseException;
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
import br.com.iperonprev.util.jsf.RetornaTempos;
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;

public class Artigo08P  implements RegrasAposentadoria{
	
	Simulador simulador = new Simulador();
	@SuppressWarnings("static-access")
	Date dataAtual = new LocalDate().now().toDate();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@SuppressWarnings("static-access")
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao).getFuncional(pf);
		this.simulador.setRegraAposentadoria("E) Regra de Transição(Art. 8º, § 1º da EC20) Proporcional - Regra do Direito Adquirido");
		this.simulador.setTipoProventos("Paridade com Proporcionalidade da Última Remuneração");
		this.simulador.setDescricaoRegra("Aplicável apenas aos servidores titulares de cargo efetivo que tenham ingressado no serviço público até 16/12/1998 e cumprido todos os requisitos para se aposentar até 30/12/2003.");
		
		this.simulador.setRequisito1("Tempo no Cargo");
		this.simulador.setRequisito2("Tempo de Contribuição");
		this.simulador.setRequisito3("Tempo de Contribuição mais Pedágio 40%");
		this.simulador.setRequisito4("Idade");

		this.simulador.setRequerido1("5 anos");
		this.simulador.setRequerido2("25 anos");
		this.simulador.setRequerido3((RetornaTempos.retornaPedagioQuarentaPorcento(9125, "16/12/1998", pf.getDATA_efetivoExercicio())/365)+" anos");
		this.simulador.setRequerido4("48 anos");
		
		if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M){
			this.simulador.setRequerido2("30 anos");
			this.simulador.setRequerido3((RetornaTempos.retornaPedagioQuarentaPorcento(10950, "16/12/1998", pf.getDATA_efetivoExercicio())/365)+" anos");
			this.simulador.setRequerido4("53 anos");
		}
		this.simulador.setStatus("Indisponível");
		
		try {
			
			if(new LocalDate().now().toDate().before(sdf.parse("17/12/2003"))){
				this.simulador.setFaltante1(tp.retornaTempoSimulacao(5));
				this.simulador.setFaltante2(tp.retornaTempoContribuicaoSimulacao(25));
				this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(RetornaTempos.retornaPedagioVintePorcento(9125,"16/12/1998", pf.getDATA_efetivoExercicio()) /365));
				this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 48));
				
				if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M){
					this.simulador.setFaltante2(tp.retornaTempoContribuicaoSimulacao(30));
					this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(RetornaTempos.retornaPedagioVintePorcento(10950,"16/12/1998", pf.getDATA_efetivoExercicio()) /365));
					this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 53));
				}
			}
			
			if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){
					this.simulador.setStatus("Disponível");
					return RegrasAposentadoriaEnum.ARTIGO8P;
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
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGO8P){
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
