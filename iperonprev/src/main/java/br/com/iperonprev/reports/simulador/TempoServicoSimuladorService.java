package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import com.ibm.icu.text.SimpleDateFormat;
import br.com.iperonprev.controller.dto.TempoContribuicaoSimuladorDto;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.averbacao.DiaMesAno;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;

	public class TempoServicoSimuladorService {
	private List<PessoasFuncionais> listaFuncionais;
	private RetornaTemposAverbacao rta;
	private List<DiaMesAno> listaDePeriodos;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	int totalDia;
	public TempoServicoSimuladorService(){}
	public TempoServicoSimuladorService(Pessoas pessoa){
		rta =new RetornaTemposAverbacao();
		listaFuncionais = new ArrayList<>();
		listaDePeriodos = new ArrayList<>();
		listaDePeriodos = new ArrayList<>();
		totalDia = 0;
		try{
			listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(pessoa.getNUMR_cpf());
		}catch(Exception e){
			System.out.println("Erro ao carregar funcionais!");
		}
	}
	
	public List<TempoContribuicaoSimuladorDto> devolveListaServico(){
		List<TempoContribuicaoSimuladorDto> lista = new ArrayList<>();
		List<TempoContribuicaoSimuladorDto> listaTempoContribuicaoSimulado = new ArrayList<>();
		try{
			System.out.println("Tamanho lista de funcionais: "+listaFuncionais);
			for (PessoasFuncionais pf : listaFuncionais) {
				LocalDate dataDemissao = new LocalDate(devolveNovaData(pf.getDATA_exoneracao()));
				
				TempoContribuicaoSimuladorDto dto = new TempoContribuicaoSimuladorDto();
				
				Days days = Days.daysBetween(new LocalDate(pf.getDATA_efetivoExercicio()),dataDemissao);
				
				dto.setMatricula(pf.getDESC_matricula());
				dto.setOrgao(pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_sigla());
				dto.setPeriodo(new StringBuilder().append(sdf.format(pf.getDATA_efetivoExercicio())).append(" a ").append(sdf.format(dataDemissao.toDate())).toString());
				dto.setQuantidadeDias(days.getDays());
				dto.setTempoExtenso(rta.formata(rta.devolveDiaMesAno(pf.getDATA_efetivoExercicio(), dataDemissao.toDate())));
				dto.setDiaMesAno(rta.devolveDiaMesAno(pf.getDATA_efetivoExercicio(), dataDemissao.toDate()));
				lista.add(dto);
				
				totalDia +=days.getDays();
			}
			
			listaTempoContribuicaoSimulado.addAll(lista);
			
			for (TempoContribuicaoSimuladorDto td : listaTempoContribuicaoSimulado) {
				listaDePeriodos.add(td.getDiaMesAno());
				
			}
		}catch(Exception e){
			System.out.println("Não foi possivel devolver a lista");
		}
		
		return lista;
	}
	
	@SuppressWarnings("static-access")
	private Date devolveNovaData(Date data){
		Date novaData = new LocalDate().now().toDate();
		try{
			if(data != null){
				return data;
			}
		}catch(Exception e){
			System.out.println("Data nula");
		}
		
		return novaData;
	}
	
	public String devolveTotalPeriodos(){
		return rta.formata(rta.devolveDiaMesAno(listaDePeriodos));
	}
	
	public int devolveTotalDias(){
		return totalDia;
	}
	
	
}
