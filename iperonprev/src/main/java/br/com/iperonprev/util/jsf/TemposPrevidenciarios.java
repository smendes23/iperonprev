package br.com.iperonprev.util.jsf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.averbacao.FormataAnoMesDia;
import br.com.iperonprev.services.averbacao.ModificadorDeAcessoDiaMesAno;

public class TemposPrevidenciarios {

	static List<Averbacao> listaDeAverbacoes = new ArrayList<Averbacao>();
	private PessoasFuncionais funcional;
	public TemposPrevidenciarios() {
	}
	
	public TemposPrevidenciarios(List<Averbacao> listaAverbacao) {
		listaDeAverbacoes = listaAverbacao;
	}
	
	public TemposPrevidenciarios getFuncional(PessoasFuncionais funcional){
		this.funcional = funcional;
		return this;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		
	public String retornaAnoMesDiaTempoTotalContribuicaoAverbado(){
		try{
		
			List<ModificadorDeAcessoDiaMesAno> listaModificadorAverbacao = new ArrayList<ModificadorDeAcessoDiaMesAno>();
			for (Averbacao a : listaDeAverbacoes) {
					FormataAnoMesDia fa = new FormataAnoMesDia();
					
					ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno();
					
					if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null){
						fa = new FormataAnoMesDia(a.getDATA_inicioTempoAproveitado(), new LocalDate(a.getDATA_fimTempoAproveitado()).minusDays(a.getNUMR_deducao()).toDate());
					}else{
						fa = new FormataAnoMesDia(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).minusDays(a.getNUMR_deducao()).toDate());
					}
					fa.devolveAnoMesDiasFormatadosSemParametros();
					
					modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
					listaModificadorAverbacao.add(modificador);
				
			}
			
			return devolveTempoComUmModificador(listaModificadorAverbacao);
		
		}catch(Exception e){
			System.out.println("Erro ao adicionar tempo previdenciário");
		}
		return "0 ano(s), 0 mes(es), 0 dia(s).";
	}
	
	@SuppressWarnings("static-access")
	private ModificadorDeAcessoDiaMesAno retornaAnoMesDiaTempoContribuicao(){
		ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno();
		try{
			FormataAnoMesDia fa = new FormataAnoMesDia();
			PessoasFuncionais pf = listaDeAverbacoes.get(0).getNUMR_pessoasFuncionais();
			
			fa = new FormataAnoMesDia(pf.getDATA_efetivoExercicio(), new LocalDate().now().toDate());
			fa.devolveAnoMesDiasFormatadosSemParametros();
			modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
		
		}catch(Exception e){
			System.out.println("Erro ao adicionar tempo previdenciário");
		}
		return modificador;
	}
	
	private List<ModificadorDeAcessoDiaMesAno> retornaTempoContribuicaoServicoPublico(){
		List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<>();
		PessoasFuncionais pf = new PessoasFuncionais();
		try{
			FormataAnoMesDia fa = new FormataAnoMesDia();
			if(!listaDeAverbacoes.isEmpty()){
				pf = listaDeAverbacoes.get(0).getNUMR_pessoasFuncionais();
			}else{
				pf = this.funcional;
			}
			
			if(pf.getDATA_efetivoExercicio().before(sdf.parse("30/06/1994"))){
				fa = new FormataAnoMesDia(pf.getDATA_efetivoExercicio(), sdf.parse("30/06/1994"));
				fa.devolveAnoMesDiasFormatadosSemParametros();
				listaModificador.add(new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno()));
			}
			
			listaModificador.add(devolveAnoMesApartirDeMeses(new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).listarRelacionamento("ContribuicoeseAliquotas", "NUMR_idPessoasFuncionais", pf.getNUMG_idDoObjeto()).size()));
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao adicionar tempo Serviço publico");
		}
		return listaModificador;
	}
	
	private List<ModificadorDeAcessoDiaMesAno> retornaAnoMesDiaPorIniciativa(int tipoIniciativa){
		List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<ModificadorDeAcessoDiaMesAno>();
		try{
			if(!listaDeAverbacoes.isEmpty()){
				for (Averbacao a : listaDeAverbacoes) {
					FormataAnoMesDia fa = new FormataAnoMesDia();
					ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno();
					if(a.getNUMR_regime().getNUMG_idDoObjeto() == tipoIniciativa){
						
						if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null){
							fa = new FormataAnoMesDia(a.getDATA_admissao(), new LocalDate(a.getDATA_inicioConcomitancia()).minusDays(a.getNUMR_deducao()).toDate());
						}else{
							fa = new FormataAnoMesDia(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).minusDays(a.getNUMR_deducao()).toDate());
						}
						fa.devolveAnoMesDiasFormatadosSemParametros();
						modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia()+1, fa.getMes(), fa.getAno());
						listaModificador.add(modificador);
					}
				}
			}
			
		}catch(Exception e){
			System.out.println("Erro ao retornar iniciativa");
		}
		
		return listaModificador;
	}
	

	public String retornaAnoMesDiaTempoTotalConcomitante(){
		List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<ModificadorDeAcessoDiaMesAno>();
		
		listaDeAverbacoes.forEach(a->{
			try{
				if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null){
					FormataAnoMesDia fa = new FormataAnoMesDia(a.getDATA_inicioConcomitancia(), a.getDATA_fimConcomitancia());
					fa.devolveAnoMesDiasFormatadosSemParametros();
					ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
					listaModificador.add(modificador);
				}
			}catch(Exception e){
				System.out.println("Erro ao adicionar concomitância ao tempo previdenciário");
			}
		});
		
		return devolveTempoComUmModificador(listaModificador);
	}
	
	public String devolveTempoComUmModificador(List<ModificadorDeAcessoDiaMesAno> listaModificador){
		int dia = 0;
		int mes = 0;
		int ano = 0;
		
		for (ModificadorDeAcessoDiaMesAno m : listaModificador) {
			dia += m.getDia();
			mes += m.getMes();
			ano += m.getAno();
		}
		
		int resultadoDia = dia%30;
		int resultadoMes = ((((dia/30)%12)+mes)%12); 
		int resultadoAno = (((dia/30)%12)+mes)/12+ano;
		
		
		return resultadoAno+" ano(s), "+resultadoMes+" mes(es), "+resultadoDia+" dia(s).";
	}
	
	
	public String retornaAnoMesDiaTempoTotalIniciativa(int tipoIniciativa){
		return devolveTempoComUmModificador(retornaAnoMesDiaPorIniciativa(tipoIniciativa));
	}
	
	public String retornaAnoMesDiaTempoContribuicaoServicoPublico(){
		return devolveTempoComUmModificador(retornaTempoContribuicaoServicoPublico());
	}
	
	public String retornaAnoMesDiaTempoTotalContribuicao(){
		List<ModificadorDeAcessoDiaMesAno> lista = new ArrayList<>();
		lista.addAll(retornaAnoMesDiaPorIniciativa(1));
		lista.addAll(retornaAnoMesDiaPorIniciativa(2));
		lista.addAll(retornaTempoContribuicaoServicoPublico());
		return devolveTempoComUmModificador(lista);
	}
	
	public String retornaTempoSimulacao(int tempoRequerido) throws ParseException{
		
		List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<ModificadorDeAcessoDiaMesAno>();
		PessoasFuncionais pf = new PessoasFuncionais();
		
		if(!listaDeAverbacoes.isEmpty()){
			pf = listaDeAverbacoes.get(0).getNUMR_pessoasFuncionais();
		}else{
			pf = this.funcional;
		}
			@SuppressWarnings("static-access")
			Period periodo = new Period(new LocalDate().fromDateFields(pf.getDATA_efetivoExercicio()), new LocalDate().now(),
					PeriodType.days());
			
			int diasFuncional = periodo.getDays();
			
			int tempoRes = 0;
			
			if((tempoRequerido*365) >= diasFuncional){
				tempoRes = (tempoRequerido*365) - diasFuncional;
				
			}
		
		@SuppressWarnings("static-access")
		Period periodo2 = new Period(new LocalDate().fromDateFields(pf.getDATA_efetivoExercicio()), new LocalDate().fromDateFields(pf.getDATA_efetivoExercicio()).plusDays(tempoRes),
				PeriodType.yearMonthDay());
		ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno(periodo2.getDays(), periodo2.getMonths(), periodo2.getYears());
		listaModificador.add(modificador);
		
		return devolveTempoComUmModificador(listaModificador);
	}
	
	public String retornaTempoContribuicaoSimulacao(int tempoRequerido) throws ParseException{
		List<ModificadorDeAcessoDiaMesAno> lista = new ArrayList<>();
		lista.addAll(retornaAnoMesDiaPorIniciativa(1));
		lista.addAll(retornaAnoMesDiaPorIniciativa(2));
		lista.addAll(retornaTempoContribuicaoServicoPublico());
		
		List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<>();
		
		ModificadorDeAcessoDiaMesAno mad = devolveModificador(lista);
		int dia = mad.getDia();
		int mes = mad.getMes();
		int ano = mad.getAno();
		
		
		if(tempoRequerido > ano){
			if(dia > 0){
				dia = 30 -dia;
				mes = (12-mes) - 1;
				ano = (tempoRequerido-ano)-1;
			}
			
		}else{
			ano = 0;
			mes = 0;
			dia = 0;
			if(dia > 0 && mes > 0){
				dia = 30 -dia;
				mes = (12-mes) - 1;
			}
		}
		listaModificador.add(new ModificadorDeAcessoDiaMesAno(dia,mes,ano));
		
		return devolveTempoComUmModificador(listaModificador);
	}
	
	private ModificadorDeAcessoDiaMesAno devolveModificador(List<ModificadorDeAcessoDiaMesAno> listaModificador){
		
		int dia = 0;
		int mes = 0;
		int ano = 0;
		
		for (ModificadorDeAcessoDiaMesAno m : listaModificador) {
			dia += m.getDia();
			mes += m.getMes();
			ano += m.getAno();
		}
	
		return new ModificadorDeAcessoDiaMesAno(dia%30, (mes+dia/30)%12, ano+mes/12);
		
		
	}
	
	public String devolveTempoComUmaData(Date inicio,int tempoRequerido){
		@SuppressWarnings("static-access")
		Period periodo = new Period(new LocalDate().fromDateFields(inicio),new LocalDate().now(),PeriodType.yearMonthDay());
		List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<>();
		int dia = periodo.getDays();
		int mes = periodo.getMonths();
		int ano = periodo.getYears();
		

		if(tempoRequerido > ano){
			if(dia > 0){
				dia = 30 -dia;
				mes = (12-mes) - 1;
				ano = (tempoRequerido-ano)-1;
			}
			
		}else{
			ano = 0;
			mes = 0;
			dia = 0;
			if(dia > 0 && mes > 0){
				dia = 30 -dia;
				mes = (12-mes) - 1;
			}
		}
		listaModificador.add(new ModificadorDeAcessoDiaMesAno(dia,mes,ano));
		
		return devolveTempoComUmModificador(listaModificador);
	}
	
	
	public String retornaAnoMesDiaOutrasDedudoes(){
		List<ModificadorDeAcessoDiaMesAno> listaModificadorD = new ArrayList<ModificadorDeAcessoDiaMesAno>();
		try{
			listaDeAverbacoes.forEach(a->{
				FormataAnoMesDia fa = new FormataAnoMesDia();
				ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno();
				if(a.getNUMR_deducao() > 0){
					if(a.getDATA_inicioConcomitancia() == null && a.getDATA_fimConcomitancia() == null){
						fa = new FormataAnoMesDia(a.getDATA_admissao(), new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_deducao()).toDate());
					}else{
						fa = new FormataAnoMesDia(a.getDATA_inicioTempoAproveitado(), new LocalDate(a.getDATA_inicioTempoAproveitado()).plusDays(a.getNUMR_deducao()).toDate());
					}
					fa.devolveAnoMesDiasFormatadosSemParametros();
					modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
					listaModificadorD.add(modificador);
				}
				
			});
			
		}catch(Exception e){
			System.out.println("Erro ao retornar tempo Deduzido");
		}
		return devolveTempoComUmModificador(listaModificadorD);
	}
	
	@SuppressWarnings("static-access")
	public int devolveTotalDiasAproveitados(){
		int total = 0;
		try{
			
			for (Averbacao a : listaDeAverbacoes) {
				
				if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null && a.getNUMR_deducao() == 0){
					total += new Period(new LocalDate().fromDateFields(a.getDATA_admissao()),new LocalDate().fromDateFields(a.getDATA_demissao()),PeriodType.days()).getDays()
							- new Period(new LocalDate().fromDateFields(a.getDATA_inicioConcomitancia()),new LocalDate().fromDateFields(a.getDATA_fimConcomitancia()),PeriodType.days()).getDays();
				}else if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null && a.getNUMR_deducao() > 0){
					total += new Period(new LocalDate().fromDateFields(a.getDATA_admissao()),new LocalDate().fromDateFields(a.getDATA_demissao()),PeriodType.days()).getDays()
							- (new Period(new LocalDate().fromDateFields(a.getDATA_inicioConcomitancia()),new LocalDate().fromDateFields(a.getDATA_fimConcomitancia()),PeriodType.days()).getDays()+a.getNUMR_deducao());
				}else if(a.getDATA_inicioConcomitancia() == null && a.getDATA_fimConcomitancia() == null && a.getNUMR_deducao() > 0){
					total += a.getNUMR_diasContribuicao() - a.getNUMR_deducao();
				}else{
					total += new Period(new LocalDate().fromDateFields(a.getDATA_admissao()),new LocalDate().fromDateFields(a.getDATA_demissao()),PeriodType.days()).getDays();
				}
				
			}
		}catch(Exception e){
			System.out.println("Não foi possível converter a data");
		}
		
		return total;
	}
	
	private ModificadorDeAcessoDiaMesAno devolveAnoMesApartirDeMeses(int meses){
		return new ModificadorDeAcessoDiaMesAno(0, meses%12, meses/12);
	}
	
	public int devolveSituacaoPrevidenciaria(PessoasFuncionais pf){
		int res = 0;
		if(pf.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto() == 2 ||
				pf.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto() == 4){
			res =pf.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto(); 
		}
		return res;
	}
	
	
}
