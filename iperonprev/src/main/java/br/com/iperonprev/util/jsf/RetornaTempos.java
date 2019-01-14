package br.com.iperonprev.util.jsf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.Years;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.TipoDeducaoEnum;
import br.com.iperonprev.controller.dto.DateTimeInicioFimDto;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.FrequenciaCtc;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.averbacao.FormataAnoMesDia;
import br.com.iperonprev.services.averbacao.ModificadorDeAcessoDiaMesAno;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class RetornaTempos {

	static LocalDate dataLocal;
	static Period periodo;
	static Date data;
	static int dia;
	static int mes;
	static int ano;
	
	static final int diasAno = 365;
	static final int diasMes = 30;
	
	@SuppressWarnings("static-access")
	public static int retornaAnosApartirDeUmaData(Date dataInicio){
		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.now(),PeriodType.years());
		return periodo.getYears();
	}
	
	@SuppressWarnings("static-access")
	public static int retornaAnosApartirDeDuasDatas(Date dataInicio,Date dataFim){
		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.fromDateFields(dataFim),PeriodType.years());
		return periodo.getYears()+1;
	}
	
	@SuppressWarnings("static-access")
	public static int retornaTamnhoFrequencia(Date dataInicio,Date dataFim){
		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.fromDateFields(dataFim),PeriodType.years());
		return periodo.getYears();
	}
	
	@SuppressWarnings("static-access")
	public static int retornaDiasApartirDeDuasDatas(Date dataInicio, Date dataFim){
		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.fromDateFields(dataFim),PeriodType.days());
		return periodo.getDays();
	}
	
	@SuppressWarnings("static-access")
	public static Period retornaPeriodoApartirDeDiasEData(int dias, Date data){
		return periodo = new Period(dataLocal.fromDateFields(data),dataLocal.fromDateFields(data).plusDays(dias),PeriodType.yearMonthDay());
	}
	
	@SuppressWarnings("static-access")
	public static Date retornaDataFutura(int dias,Date dataInicio){
		Date dataInicio2 = dataInicio;
		data = new Date();
		data = dataLocal.fromDateFields(dataInicio2).plusDays(dias).toDate();
		return data;
	}
	
	@SuppressWarnings("static-access")
	public static Date retornaDataFuturaComDiasDeduzidos(int dias,Date dataInicio){
		Date dataInicio2 = dataInicio;
		data = new Date();
		data = dataLocal.fromDateFields(dataInicio2).minusDays(dias).toDate();
		return data;
	}
	
	@SuppressWarnings("static-access")
	public static Date retornaDataFuturaComAnos(int anos,Date dataInicio){
		Date dataInicio2 = dataInicio;
		data = new Date();
		data = dataLocal.fromDateFields(dataInicio2).plusYears(anos).toDate();
		return data;
	}
	
	@SuppressWarnings("static-access")
	public static StringBuilder retornaDiaMesAno(Date dataInicio, Date dataFim){

		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.fromDateFields(dataFim),PeriodType.yearMonthDay());
		StringBuilder periodoFormatado = new StringBuilder();
		periodoFormatado.append(periodo.getYears())
		.append(" ano(s), ").append(periodo.getMonths())
		.append(" mes(es) e ").append(periodo.getDays())
		.append(" dia(s).");
		return retornaDiaMesApartirDeUmPeriodo(periodo);
//		return periodoFormatado;
	}
	
	@SuppressWarnings("static-access")
	public static StringBuilder retornaDiaMesAnoBissextos(List<FrequenciaCtc> listaDeFrequencia){
		dia = 0;
		mes = 0;
		ano = 0;
		int totalDias = 0;
		int bissexto = 0;
		for (FrequenciaCtc frequenciaCtc : listaDeFrequencia) {
			bissexto += frequenciaCtc.getBissextos();
			totalDias += frequenciaCtc.getTempoLiquido();
		}
		
		periodo = new Period(dataLocal.fromDateFields(new LocalDate().now().toDate()),
				dataLocal.fromDateFields(new LocalDate().now().plusDays(totalDias).toDate()),
				PeriodType.yearMonthDay());
		
		if(bissexto != 0){
			dia = periodo.getDays()+bissexto;
		}
		mes = periodo.getMonths();
		ano = periodo.getYears();
		
		if(dia >= 30){
			mes = mes + (dia /30);
			dia %= 30;
		}
		if(mes >=12){
			ano = ano +(mes/12);
			mes %= 12;
		}
		
		StringBuilder periodoFormatado = new StringBuilder();
		periodoFormatado.append(ano)
		.append(" ano(s), ").append(mes)
		.append(" mes(es) e ").append(dia)
		.append(" dia(s).");
		return periodoFormatado;
	}
	
	@SuppressWarnings("static-access")
	public static StringBuilder retornaDiaMesAnoPrevidenciario(Date dataInicio, Date dataFim){
		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.fromDateFields(dataFim).plusDays(1),PeriodType.yearMonthDay());
		StringBuilder periodoFormatado = new StringBuilder();
		periodoFormatado.append(periodo.getYears())
		.append(" ano(s), ").append(periodo.getMonths())
		.append(" mes(es) e ").append(periodo.getDays())
		.append(" dia(s).");
		return periodoFormatado;
	}
	
	public static StringBuilder retornaDiaMesAnoApartirDias(int dias){
		 ano = dias/diasAno;
		 mes = (dias-ano*diasAno)/diasMes;
		dia = (dias-ano*diasAno)-(mes*diasMes);
		StringBuilder sb = new StringBuilder().append(ano)
				.append(" ano(s), ").append(mes)
				.append(" mes(es) e ").append(dia)
				.append(" dia(s).");
		
		return sb;
	}
	
	public static StringBuilder retornaDiaMesApartirDeUmPeriodo(Period p){
		dia = 0;
		mes = 0;
		ano = 0;
		
			dia += p.getDays();
			mes += p.getMonths();
			ano += p.getYears();
		
		if(dia >= 30){
			mes = mes + (dia /30);
			dia %= 30;
			
		}
		if(mes >=12){
			ano = ano +(mes/12);
			mes %= 12;
		}
		
		StringBuilder periodoFormatado = new StringBuilder();
		periodoFormatado.append(ano)
		.append(" ano(s), ").append(mes)
		.append(" mes(es) e ").append(dia)
		.append(" dia(s).");
		return periodoFormatado;
	}
		
	public static StringBuilder retornaDiaMesAnoPeriodos(List<Period> listaDePeriodos){
		dia = 0;
		mes = 0;
		ano = 0;
		
		listaDePeriodos.forEach(p->{
			dia += p.getDays();
			mes += p.getMonths();
			ano += p.getYears();
		});
		
		if(dia >= 30){
			mes = mes + (dia /30);
			dia %= 30;
		}
		if(mes >=12){
			ano = ano +(mes/12);
			mes %= 12;
		}
		
		StringBuilder periodoFormatado = new StringBuilder();
		periodoFormatado.append(ano)
		.append(" ano(s), ").append(mes)
		.append(" mes(es) e ").append(dia)
		.append(" dia(s).");
		return periodoFormatado;
	}
	
	@SuppressWarnings("static-access")
	public static String retornaTempoDeContribuicaoFormatadoDiaMesAno(PessoasFuncionais funcionais){
		
		try{
			List<Averbacao> listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcionais.getNUMG_idDoObjeto());
			List<ModificadorDeAcessoDiaMesAno> listaModificadorAverbacao = new ArrayList<ModificadorDeAcessoDiaMesAno>();
			FormataAnoMesDia fa = new FormataAnoMesDia();
			ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno();
			for (Averbacao a : listaAverbacao) {
					if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null){
						fa = new FormataAnoMesDia(a.getDATA_inicioTempoAproveitado(), new LocalDate(a.getDATA_fimTempoAproveitado()).minusDays(a.getNUMR_deducao()).toDate());
					}else{
						fa = new FormataAnoMesDia(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).minusDays(a.getNUMR_deducao()).toDate());
					}
					
					fa.devolveAnoMesDiasFormatadosSemParametros();
					
					modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
					listaModificadorAverbacao.add(modificador);
				
			}
			fa = new FormataAnoMesDia(funcionais.getDATA_efetivoExercicio(), new LocalDate().now().toDate());
			fa.devolveAnoMesDiasFormatadosSemParametros();
			modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
			listaModificadorAverbacao.add(modificador);
			
			return new TemposPrevidenciarios().devolveTempoComUmModificador(listaModificadorAverbacao);
		
		}catch(Exception e){
			System.out.println("Erro ao adicionar tempo previdenciário");
		}
		return "0 ano(s), 0 mes(es), 0 dia(s).";
		
	}
	
	@SuppressWarnings("static-access")
	public static int retornaTempoDeContribuicaoEmAnos(PessoasFuncionais funcionais){
		int res = 0;
		try{
			List<Averbacao> listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcionais.getNUMG_idDoObjeto());
			List<ModificadorDeAcessoDiaMesAno> listaModificadorAverbacao = new ArrayList<ModificadorDeAcessoDiaMesAno>();
			FormataAnoMesDia fa = new FormataAnoMesDia();
			ModificadorDeAcessoDiaMesAno modificador = new ModificadorDeAcessoDiaMesAno();
			
			for (Averbacao a : listaAverbacao) {
				
					if(a.getNUMR_deducao() >= 0 && a.getDATA_inicioTempoAproveitado() != null && a.getDATA_fimTempoAproveitado() != null || 
							a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null){
						
						fa = new FormataAnoMesDia(a.getDATA_inicioTempoAproveitado(), new LocalDate(a.getDATA_fimTempoAproveitado()).minusDays(a.getNUMR_deducao()).toDate());
						
					}else if(a.getDATA_inicioTempoAproveitado() == null && a.getDATA_fimTempoAproveitado() == null){
						
						fa = new FormataAnoMesDia(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).minusDays(a.getNUMR_deducao()).toDate());
					}
					
					fa.devolveAnoMesDiasFormatadosSemParametros();
					
					modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
					listaModificadorAverbacao.add(modificador);
				
			}
			
			fa = new FormataAnoMesDia(funcionais.getDATA_efetivoExercicio(), new LocalDate().now().toDate());
			fa.devolveAnoMesDiasFormatadosSemParametros();
			modificador = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
			listaModificadorAverbacao.add(modificador);

			for (ModificadorDeAcessoDiaMesAno m : listaModificadorAverbacao) {
				res += m.getAno();
			}
			
		
		}catch(Exception e){
			System.out.println("Erro ao adicionar tempo previdenciário");
		}
		return res;
		
	}
	
	@SuppressWarnings("static-access")
	public static int retornaTempoDeContribuicaoEmDias(PessoasFuncionais funcionais){
		dia = 0;
		
		List<Averbacao> listaAverbacao = new ArrayList<>();
		try{
			listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcionais.getNUMG_idDoObjeto());
		}catch(Exception e){
			
		}
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao);
		dia += tp.devolveTotalDiasAproveitados();
		dia += retornaDiasApartirDeDuasDatas(funcionais.getDATA_efetivoExercicio(), dataLocal.now().toDate());
		return dia;
	}
	
	@SuppressWarnings("static-access")
	public static int retornaTempoDeServicoPublicoDias(PessoasFuncionais funcionais){
		dia = 0;
		try{
			FuncionaisFuncoes sp = new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).buscaObjetoRelacionamento("FuncionaisFuncoes", "NUMR_idPessoas", funcionais.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
			List<Averbacao> listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcionais.getNUMG_idDoObjeto());
			listaAverbacao.forEach(a->{
				if(a.getNUMR_iniciativa().getNUMG_idDoObejto() == 1){
					dia += (a.getNUMR_diasContribuicao() - a.getNUMR_deducao());
					if(a.getDATA_inicioConcomitancia() != null && a.getDATA_fimConcomitancia() != null){
						dia -= retornaDiasApartirDeDuasDatas(a.getDATA_inicioConcomitancia(),a.getDATA_fimConcomitancia());
					}
				}
			});
			dia += retornaDiasApartirDeDuasDatas(sp.getDATA_ingressoEnte(), dataLocal.now().toDate());
		}catch(Exception e){
			System.out.println("Erro ao Carregar Funcionais Funções");
		}
		return dia;
	}
	
	@SuppressWarnings("static-access")
	public static int retornaTempoCarreiraEmDias(PessoasFuncionais funcionais){
		dia = 0;
		LocalDate local = new LocalDate();
		List<PessoasFuncionais> listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class)
				.listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", funcionais.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
		
		listaFuncionais.forEach(f->{
			if(funcionais.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoCarreiras() 
					== f.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoCarreiras() && !funcionais.getDATA_exoneracao().equals(null)){
				dia += retornaDiasApartirDeDuasDatas(funcionais.getDATA_efetivoExercicio(), funcionais.getDATA_exoneracao());
			}else{
				dia += 0;
			}
		});
		
		if(dia == 0){
			dia += retornaDiasApartirDeDuasDatas(funcionais.getDATA_efetivoExercicio(), local.now().toDate());
		}
		
		return dia;
	}
	
		
	public static List<FrequenciaCtc> retornaListaDeFrequencia(List<PessoasFuncionais> listaFuncionais,Date dataAdmissao, Date dataExo){
		 List<PessoasFuncionais> listaFuncional = listaFuncionais;
	        FrequenciaCtc freq = new FrequenciaCtc();
	        ArrayList<FrequenciaCtc> listaDeFrequencias = new ArrayList<FrequenciaCtc>();
	        ArrayList<Deducao> listaDeducao = new ArrayList<Deducao>();
	        ArrayList<DateTimeInicioFimDto> listaLicenca = new ArrayList<DateTimeInicioFimDto>();
	        Years y = null;
	        int ano = 0;
	        Date dataPosse = new Date();
	        Date dataExoneracao = new Date();
	        try {
	            for (PessoasFuncionais pessoasFuncionais : listaFuncional) {
	                listaDeducao.addAll(new GenericPersistence<Deducao>(Deducao.class).listarRelacionamento("Deducao", "NUMR_pessoasFuncionais", pessoasFuncionais.getNUMG_idDoObjeto()));
	                listaLicenca.addAll(RetornaTempos.devolveListaLicencas(pessoasFuncionais.getNUMG_idDoObjeto()));
	            }
	            dataExoneracao = !RetornaTempos.dataNula(dataExo) ? dataExo : listaFuncional.get(0).getDATA_exoneracao();
	            if (!RetornaTempos.dataNula(dataAdmissao)) {
	                dataPosse = dataAdmissao;
	            } else if (listaFuncional.size() > 1) {
	                if (listaFuncional.get(1).getDATA_efetivoExercicio().before(listaFuncional.get(0).getDATA_efetivoExercicio())) {
	                    dataPosse = listaFuncional.get(1).getDATA_efetivoExercicio();
	                }
	                if (listaFuncional.get(1).getDATA_exoneracao().after(listaFuncional.get(0).getDATA_exoneracao())) {
	                    dataExoneracao = listaFuncional.get(1).getDATA_exoneracao();
	                }
	            } else {
	                dataPosse = listaFuncional.get(0).getDATA_efetivoExercicio();
	            }
	            ano = LocalDate.fromDateFields((Date)dataPosse).getYear();
	            y = Years.yearsBetween((ReadablePartial)LocalDate.fromDateFields((Date)dataPosse), (ReadablePartial)LocalDate.fromDateFields((Date)new LocalDate((Object)dataExoneracao).minusDays(1).toDate()));
	            freq.setAno(ano);
	            freq.setTempoBruto(new Period((ReadablePartial)LocalDate.fromDateFields((Date)dataPosse), (ReadablePartial)LocalDate.fromDateFields((Date)new LocalDate().withYear(ano).withMonthOfYear(12).dayOfMonth().withMaximumValue().toDate()).plusDays(1), PeriodType.days()).getDays());
	            if (ano == LocalDate.fromDateFields((Date)dataExoneracao).getYear()) {
	                freq.setTempoBruto(RetornaTempos.retornaDiasApartirDeDuasDatas(dataPosse, dataExoneracao));
	            }
	            listaDeFrequencias.add(0, freq);
	            int i = 0;
	            while (i <= y.getYears()) {
	                FrequenciaCtc frequencia = new FrequenciaCtc();
	                Date begin = new LocalDate().withYear(++ano).withMonthOfYear(1).dayOfMonth().withMinimumValue().toDate();
	                Date end = new LocalDate().withYear(ano).withMonthOfYear(12).dayOfMonth().withMaximumValue().toDate();
	                new org.joda.time.LocalDate();
	                new org.joda.time.LocalDate();
	                if (LocalDate.fromDateFields((Date)dataExoneracao).getYear() >= LocalDate.fromDateFields((Date)begin).getYear()) {
	                    new org.joda.time.LocalDate();
	                    if (LocalDate.fromDateFields((Date)dataExoneracao).getYear() == ano) {
	                        frequencia.setTempoBruto(new Period((ReadablePartial)LocalDate.fromDateFields((Date)begin), (ReadablePartial)LocalDate.fromDateFields((Date)dataExoneracao), PeriodType.days()).getDays());
	                    } else {
	                        frequencia.setTempoBruto(new Period((ReadablePartial)LocalDate.fromDateFields((Date)begin), (ReadablePartial)LocalDate.fromDateFields((Date)end).plusDays(1), PeriodType.days()).getDays());
	                    }
	                    if (frequencia.getTempoBruto() == 366) {
	                        frequencia.setBissextos(1);
	                    }
	                    frequencia.setAno(ano);
	                    listaDeFrequencias.add(i + 1, frequencia);
	                }
	                ++i;
	            }
	            listaDeFrequencias.forEach(f -> {
	                int faltas = 0;
	                int licencas = 0;
	                int outros = 0;
	                int suspensoes = 0;
	                for (Deducao d : listaDeducao) {
	                    DateTime inicio = new DateTime((Object)d.getDATA_inicio());
	                    DateTime fim = new DateTime((Object)d.getDATA_fim());
	                    if (d.getENUM_compensasaoDeducao() != DecisaoEnum.NAO || f.getAno() != inicio.getYear() || f.getAno() != fim.getYear()) continue;
	                    if (d.getENUM_tipoDeducao() == TipoDeducaoEnum.FALTA) {
	                        faltas += d.getNUMR_qtdDias();
	                        continue;
	                    }
	                    if (d.getENUM_tipoDeducao() == TipoDeducaoEnum.OUTROS) {
	                        outros += d.getNUMR_qtdDias();
	                        continue;
	                    }
	                    if (d.getENUM_tipoDeducao() != TipoDeducaoEnum.SUSPENSAO) continue;
	                    suspensoes += d.getNUMR_qtdDias();
	                }
	                for (DateTimeInicioFimDto l : listaLicenca) {
	                    if (f.getAno() != l.getDataInicio().getYear() || f.getAno() != l.getDataFim().getYear() || l.getDataInicio() == null || l.getDataFim() == null) continue;
	                    licencas += RetornaTempos.retornaDiasApartirDeDuasDatas(l.getDataInicio().toDate(), l.getDataFim().toDate()) + 1;
	                }
	                f.setFaltas(faltas);
	                f.setOutras(outros);
	                f.setSuspensoes(suspensoes);
	                f.setLicencasSemVencimentos(licencas);
	                f.setTempoLiquido(f.getTempoBruto() - (faltas + licencas + suspensoes + outros));
	                f.setSoma(faltas + licencas + suspensoes + outros);
	            });
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao gerar lista de frequencia.");
	        }
	        return listaDeFrequencias;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static List<DateTimeInicioFimDto> devolveListaLicencas(int idFuncional) {
        List<AfastamentosLicenca> listaLicenca = new ArrayList<AfastamentosLicenca>();
        ArrayList<DateTimeInicioFimDto> listaInicioFim = new ArrayList<DateTimeInicioFimDto>();
        try {
            listaLicenca = new GenericPersistence(AfastamentosLicenca.class).listarRelacionamento("AfastamentosLicenca", "NUMR_idDoObjetoFuncional", idFuncional);
            listaLicenca.forEach(l -> {
                DateTime dataInicio = new DateTime(l.getDATA_inicioLicenca());
                DateTime dataFim = new DateTime((Object)l.getDATA_fimLicenca());
                Interval intervalo = new Interval((ReadableInstant)dataInicio, (ReadableInstant)dataFim);
                if (l.getFLAG_contribuicao() == 0) {
                    int i = 0;
                    while (i <= intervalo.toPeriod().getYears()) {
                        Date dataI = null;
                        Date dataF = null;
                        if (intervalo.toPeriod().getYears() > 0) {
                            if (i == 0) {
                                dataI = dataInicio.toDate();
                                dataF = new LocalDate().withYear(intervalo.getStart().getYear()).withMonthOfYear(12).withDayOfMonth(31).toDate();
                            } else if (i == intervalo.toPeriod().getYears()) {
                                dataI = new LocalDate().withYear(intervalo.getEnd().getYear()).withMonthOfYear(1).withDayOfMonth(1).toDate();
                                dataF = dataFim.toDate();
                            } else {
                                dataI = new LocalDate().withYear(intervalo.getStart().getYear() + i).withMonthOfYear(1).withDayOfMonth(1).toDate();
                                dataF = new LocalDate().withYear(intervalo.getStart().getYear() + i).withMonthOfYear(12).withDayOfMonth(31).toDate();
                            }
                        } else {
                            dataI = dataInicio.toDate();
                            dataF = dataFim.toDate();
                        }
                        listaInicioFim.add(new DateTimeInicioFimDto(new DateTime((Object)dataI), new DateTime((Object)dataF)));
                        ++i;
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaInicioFim;
    }
	
	private static boolean dataNula(Date dataInicial){
		boolean res = true;
		try{
			if(dataInicial!= null){
				res = false;
			}
		}catch(Exception e){
			System.out.println("erro ao verificar a data");
		}
		return res;
	}
	
	public static Integer retornaTotalDiasFrequenciaCtc(List<FrequenciaCtc> listaDeFrequencia){
		int res = 0;
		for (FrequenciaCtc frequenciaCtc : listaDeFrequencia) {
			res += frequenciaCtc.getTempoLiquido();
		}
		return res;
	}
	
	public static Integer retornaPedagioVintePorcento(int tempoContribuicao,String dataLimite, Date dataPosse){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int resultado = 0;
		try {
			int diferencaEmDias = retornaDiasApartirDeDuasDatas(dataPosse,sdf.parse(dataLimite));
			resultado = (((tempoContribuicao - diferencaEmDias) * 20/100))+tempoContribuicao;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static Integer retornaPedagioVintePorcentoComBonusDezessetePorcento(int tempoContribuicao,String dataLimite, Date dataPosse){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int tempoContribuicaoAdquirida = 0;
		int tempoContribuicaoFaltante = 0;
		try {
			
			tempoContribuicaoAdquirida = retornaDiasApartirDeDuasDatas(dataPosse,sdf.parse(dataLimite));
			tempoContribuicaoAdquirida += (tempoContribuicaoAdquirida *0.17);
			tempoContribuicaoFaltante = tempoContribuicao+((tempoContribuicao - tempoContribuicaoAdquirida) * 20/100);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tempoContribuicaoFaltante;
	}
	
	public static Integer retornaPedagioVintePorcentoComBonusVintePorcento(int tempoContribuicao,String dataLimite, Date dataPosse){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int tempoContribuicaoAdquirida = 0;
		int tempoContribuicaoFaltante = 0;
		try {
			
			tempoContribuicaoAdquirida = retornaDiasApartirDeDuasDatas(dataPosse,sdf.parse(dataLimite));
			tempoContribuicaoAdquirida += (tempoContribuicaoAdquirida *0.2);
			tempoContribuicaoFaltante = tempoContribuicao+((tempoContribuicao - tempoContribuicaoAdquirida) * 20/100);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tempoContribuicaoFaltante;
	}
	
	public static Integer retornaPedagioQuarentaPorcento(int tempoContribuicao,String dataLimite, Date dataPosse){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int resultado = 0;
		try {
			int diferencaEmDias = retornaDiasApartirDeDuasDatas(dataPosse,sdf.parse(dataLimite));
			resultado = (int) ((((tempoContribuicao - diferencaEmDias) * 0.4))+tempoContribuicao);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static Integer retornaBonusDezessetePorcento(int tempoContribuicao){
		int resultado = 0;
		try {
			resultado = (int) (tempoContribuicao  * (0.17));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static Integer retornaBonusVintePorcento(int tempoContribuicao){
		int resultado = 0;
		try {
			resultado = (int) (tempoContribuicao  * 0.2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	@SuppressWarnings("static-access")
	public DiaMesAno devolveDiaMesAno(Date dataInicio, Date dataFim){
		periodo = new Period(dataLocal.fromDateFields(dataInicio),dataLocal.fromDateFields(dataFim),PeriodType.yearMonthDay());
		dia = periodo.getDays();
		mes = periodo.getMonths();
		ano = periodo.getYears();
		if(dia >= 30){
			mes = mes + (dia /30);
			dia %= 30;
		}
		if(mes >=12){
			ano = ano +(mes/12);
			mes %= 12;
		}
		
		DiaMesAno dma = new DiaMesAno(dia,mes,ano);
		return dma;
	}
	
	public DiaMesAno devolveDiaMesAnoAproveitado(DiaMesAno d1, DiaMesAno d2){
		DiaMesAno dma = new DiaMesAno(d1.getDia() - d2.getDia(), d1.getMes()-d2.getMes(), d1.getAno() - d2.getAno());
		return dma;
	}
}
