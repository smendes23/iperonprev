package br.com.iperonprev.services.beneficio;

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

public class Artigo40A implements RegrasAposentadoria{
	Simulador simulador = new Simulador();
	@SuppressWarnings("static-access")
	Date dataAtual = new LocalDate().now().toDate();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public RegrasAposentadoriaEnum verificaRegra(PessoasFuncionais pf,List<Averbacao> listaAverbacao) {
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao).getFuncional(pf);
		this.simulador.setRegraAposentadoria("D) Regra Permanente Art. 40, § 1º \"a\" da CF(EC41) de acordo com a lei 10.887/04");
		this.simulador.setTipoProventos("Beneficio Médio com Reajuste por Índice Oficial");
		
		this.simulador.setRequisito1("Tempo no Cargo");
		this.simulador.setRequisito2("Tempo de Serviço Público");
		this.simulador.setRequisito3("Tempo de Contribuição");
		this.simulador.setRequisito4("Idade");

		this.simulador.setRequerido1("5 anos");
		this.simulador.setRequerido2("10 anos");
		this.simulador.setRequerido3("30 anos");
		this.simulador.setRequerido4("55 anos");
		
		try{
			this.simulador.setFaltante1(tp.retornaTempoSimulacao(5));
			this.simulador.setFaltante2(tp.retornaTempoSimulacao(10));
			this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(30));
			this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 55));
		}catch(Exception e){
			
		}
		
		this.simulador.setStatus("Indisponível");
		try {
		
		if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M && !StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
			this.simulador.setRequerido3("35 anos");
			this.simulador.setRequerido4("60 anos");
			
			this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(30));
			this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 60));
		}else if(pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.F && StringUtils.containsIgnoreCase(pf.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")){
			
			this.simulador.setRequerido3("25 anos");
			this.simulador.setRequerido4("50 anos");
			
			this.simulador.setFaltante3(tp.retornaTempoContribuicaoSimulacao(25));
			this.simulador.setFaltante4(tp.devolveTempoComUmaData(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento(), 50));
		}
		
			if(new ValidaRegrasAposentadoria().verificaRegraDisponivel(simulador) == true){
					this.simulador.setStatus("Disponível");
					return RegrasAposentadoriaEnum.ARTIGO40A;
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
		if(regrasAposentadoria == RegrasAposentadoriaEnum.ARTIGO40A){
			mapaObjeto.put("provento", TipoProventosEnum.INTEGRAL);
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
