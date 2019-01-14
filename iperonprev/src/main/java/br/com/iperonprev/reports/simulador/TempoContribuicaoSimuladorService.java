package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.iperonprev.controller.dto.TempoContribuicaoSimuladorDto;
import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.averbacao.DiaMesAno;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;

public class TempoContribuicaoSimuladorService {
	List<PessoasFuncionais> listaFuncionais = new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	RetornaTemposAverbacao rta =new RetornaTemposAverbacao();
	private List<DiaMesAno> listaDePeriodos;
	private int valor;
	public int getValor() {
		return valor;
	}
	public TempoContribuicaoSimuladorService(){}
	public TempoContribuicaoSimuladorService(Pessoas pessoa){
		valor = 0;
		try{
			listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(pessoa.getNUMR_cpf());
		}catch(Exception e){
			System.out.println("Erro ao carregar funcionais!");
		}
	}
	
	public List<TempoContribuicaoSimuladorDto> devolveListaServico(){
		List<TempoContribuicaoSimuladorDto> lista = new ArrayList<>();
		try{
			
			for (PessoasFuncionais pf : listaFuncionais) {
				TempoContribuicaoSimuladorDto dto = new TempoContribuicaoSimuladorDto();
				List<ContribuicoeseAliquotas> contribuicoes = new ContribuicaoDao().devolveListaContribuicoes(pf.getNUMG_idDoObjeto());
				
				if(!contribuicoes.isEmpty()){
					LocalDate dataDemissao = new LocalDate(pf.getDATA_efetivoExercicio()).plusMonths(contribuicoes.size());
					Days days = Days.daysBetween(new LocalDate(pf.getDATA_efetivoExercicio()),dataDemissao);
					
					dto.setMatricula(pf.getDESC_matricula());
					dto.setOrgao(pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_sigla());
					dto.setPeriodo(new StringBuilder().append(sdf.format(pf.getDATA_efetivoExercicio())).append(" a ").append(sdf.format(dataDemissao.toDate())).toString());
					dto.setQuantidadeDias(days.getDays());
					dto.setTempoExtenso(rta.formata(rta.devolveDiaMesAno(pf.getDATA_efetivoExercicio(), dataDemissao.toDate())));
					dto.setDiaMesAno(rta.devolveDiaMesAno(pf.getDATA_efetivoExercicio(), dataDemissao.toDate()));
					valor += days.getDays();
					listaDePeriodos.add(dto.getDiaMesAno());
					lista.add(dto);
				}
			}
		}catch(Exception e){
			System.out.println("Não foi possivel devolver a lista");
		}
		
		return lista;
	}
}
