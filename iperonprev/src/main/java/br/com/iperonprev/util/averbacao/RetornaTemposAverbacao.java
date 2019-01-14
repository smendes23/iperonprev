package br.com.iperonprev.util.averbacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class RetornaTemposAverbacao {

	static LocalDate dataLocal;
	static int dia;
	static int mes;
	static int ano;
	static Period periodo;
	final int diasMes = 30;
	final int mesesAno = 12;

	private List<Averbacao> listaAverbacacao = new ArrayList<>();
	private PessoasFuncionais pf = new PessoasFuncionais();

	public RetornaTemposAverbacao() {
	}

	public RetornaTemposAverbacao(List<Averbacao> listaAverbacao) {
		this.listaAverbacacao = listaAverbacao;
	}

	public RetornaTemposAverbacao(PessoasFuncionais pf) {
		this.pf = pf;
		this.listaAverbacacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao",
				"NUMR_pessoasFuncionais", pf.getNUMG_idDoObjeto());
	}

	@SuppressWarnings("static-access")
	public DiaMesAno devolveDiaMesAno(Date dataInicio, Date dataFim) {
		int bissextos = devolveBissextos(dataInicio, dataFim);
		
		periodo = new Period(dataLocal.fromDateFields(dataInicio), dataLocal.fromDateFields(dataFim),
				PeriodType.yearMonthDay()).plusDays(bissextos);
		
		dia = periodo.getDays();
		mes = periodo.getMonths();
		ano = periodo.getYears();
		return formataPeriodo(new DiaMesAno(dia, mes, ano));
	}

	@SuppressWarnings("static-access")
	public DiaMesAno devolveDiaMesAnoSimulacao(Date dataInicio, Date dataFim) {
		periodo = new Period(dataLocal.fromDateFields(dataInicio), dataLocal.fromDateFields(dataFim),
				PeriodType.yearMonthDay());
		
		dia = periodo.getDays();
		mes = periodo.getMonths();
		ano = periodo.getYears();
		return formataPeriodo(new DiaMesAno(dia, mes, ano));
	}

	public String devolveDiaMesAnoTempoAproveitado(Averbacao averbacao) {
		DiaMesAno res = new DiaMesAno();
		DiaMesAno d1 = new DiaMesAno();
		
		try {
			if (averbacao.getDATA_inicioConcomitancia() != null) {

				d1 = devolveDiaMesAno(averbacao.getDATA_inicioConcomitancia(),
						new LocalDate(averbacao.getDATA_fimConcomitancia()).plusDays(averbacao.getNUMR_deducao()).toDate());
				res = subtraiDoisPeriodos(devolveDiaMesAno(averbacao.getDATA_admissao(), averbacao.getDATA_demissao()), d1);

			} else {
				res = devolveDiaMesAno(averbacao.getDATA_admissao(), new LocalDate(averbacao.getDATA_demissao()).toDate());
				if (averbacao.getNUMR_deducao() != 0) {
					d1 = devolveDiaMesAno(averbacao.getDATA_admissao(),
							new LocalDate(averbacao.getDATA_admissao()).plusDays(averbacao.getNUMR_deducao()).toDate());
					res = subtraiDoisPeriodos(devolveDiaMesAno(averbacao.getDATA_admissao(), averbacao.getDATA_demissao()),
							d1);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar tempo aproveitado.");
		}
		
		return formata(res);
	}

	public String devolveDiaMesAnoIniciativa(int tipoIniciativa) {
		int diaIniciativa = 0;
		int mesIniciativa = 0;
		int anoIniciativa = 0;
		DiaMesAno d1 = new DiaMesAno();
		
		System.out.println("Iniciativa: "+tipoIniciativa);

		List<DiaMesAno> listaPeriodo = new ArrayList<>();
		try {
			for (Averbacao a : listaAverbacacao) {
				if (a.getNUMR_iniciativa().getNUMG_idDoObejto() == tipoIniciativa) {
					if (a.getDATA_inicioConcomitancia() != null) {

						d1 = devolveDiaMesAno(a.getDATA_inicioConcomitancia(),
								new LocalDate(a.getDATA_fimConcomitancia()).plusDays(a.getNUMR_deducao()).toDate());
						listaPeriodo.add(
								subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));

					} else {

						if (a.getNUMR_deducao() != 0) {
							d1 = devolveDiaMesAno(a.getDATA_admissao(),
									new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_deducao()).toDate());
							listaPeriodo.add(subtraiDoisPeriodos(
									devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));
						} else {
							listaPeriodo.add(devolveDiaMesAno(a.getDATA_admissao(),
									new LocalDate(a.getDATA_demissao()).toDate()));
						}
					}
				}

			}

			for (DiaMesAno p : listaPeriodo) {
				diaIniciativa += p.getDia();
				mesIniciativa += p.getMes();
				anoIniciativa += p.getAno();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return formata(formataPeriodo(new DiaMesAno(diaIniciativa, mesIniciativa, anoIniciativa)));
	}
	
	public int devolveDiasIniciativa(int tipoIniciativa) {
		int diaIniciativa = 0;

		List<Integer> listaPeriodo = new ArrayList<>();
		try {
			for (Averbacao a : listaAverbacacao) {
				if (a.getNUMR_iniciativa().getNUMG_idDoObejto() == tipoIniciativa) {
					if (a.getDATA_inicioConcomitancia() != null) {
						listaPeriodo.add(new Period(new LocalDate(a.getDATA_admissao()),new LocalDate(a.getDATA_demissao())
									.minusDays(Days.daysBetween(new LocalDate(a.getDATA_inicioConcomitancia()), new LocalDate(a.getDATA_fimConcomitancia())).getDays()),PeriodType.days()).getDays()); 
					} else {

						if (a.getNUMR_deducao() != 0) {
							listaPeriodo.add(new Period(new LocalDate(a.getDATA_admissao()),new LocalDate(a.getDATA_demissao())
									.minusDays(a.getNUMR_deducao()),PeriodType.days()).getDays());
						} else {
							listaPeriodo.add(new Period(new LocalDate(a.getDATA_admissao()),new LocalDate(a.getDATA_demissao()),PeriodType.days()).getDays());
						}
					}
				}

			}

			for (Integer i : listaPeriodo) {
				diaIniciativa += i;
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return diaIniciativa;
	}

	public String devolveDiaMesAnoTotalConcomitancia() {
		int diaIniciativa = 0;
		int mesIniciativa = 0;
		int anoIniciativa = 0;
		List<DiaMesAno> listaPeriodo = new ArrayList<>();
		try {
			for (Averbacao a : listaAverbacacao) {
				if (a.getDATA_inicioConcomitancia() != null) {
					listaPeriodo.add(devolveDiaMesAno(a.getDATA_inicioConcomitancia(),
							new LocalDate(a.getDATA_fimConcomitancia()).toDate()));
				}
			}

			for (DiaMesAno p : listaPeriodo) {
				diaIniciativa += p.getDia();
				mesIniciativa += p.getMes();
				anoIniciativa += p.getAno();
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return formata(formataPeriodo(new DiaMesAno(diaIniciativa, mesIniciativa, anoIniciativa)));
	}

	public DiaMesAno devolveDiaMesAnoOutros(DiaMesAno d1, DiaMesAno d2) {
		DiaMesAno dma = subtraiDoisPeriodos(d1, d2);
		return dma;
	}

	@SuppressWarnings("unused")
	private List<DiaMesAno> devolveListaDePeriodos(List<Averbacao> listaAverbacao) {
		List<DiaMesAno> listaPeriodo = new ArrayList<>();
		DiaMesAno d1 = new DiaMesAno();
		try {
			for (Averbacao a : listaAverbacacao) {

				if (a.getDATA_inicioConcomitancia() != null) {

					d1 = devolveDiaMesAno(a.getDATA_inicioConcomitancia(),
							new LocalDate(a.getDATA_fimConcomitancia()).plusDays(a.getNUMR_deducao()).toDate());
					listaPeriodo
							.add(subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));

				} else {
					if (a.getNUMR_deducao() != 0) {
						d1 = devolveDiaMesAno(a.getDATA_admissao(),
								new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_deducao()).toDate());
						listaPeriodo.add(
								subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));
					} else {
						listaPeriodo.add(
								devolveDiaMesAno(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).toDate()));
					}
				}

			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return listaPeriodo;
	}

	public DiaMesAno devolveDiaMesAno(List<DiaMesAno> listaPeriodo) {
		int diaIniciativa = 0;
		int mesIniciativa = 0;
		int anoIniciativa = 0;

		for (DiaMesAno p : listaPeriodo) {
			diaIniciativa += p.getDia();
			mesIniciativa += p.getMes();
			anoIniciativa += p.getAno();
		}
		DiaMesAno d1 = new DiaMesAno(diaIniciativa, mesIniciativa, anoIniciativa);

		return formataPeriodo(d1);
	}

	public String devolveDiaMesAnoTempoTotalAproveitado() {
		int diaIniciativa = 0;
		int mesIniciativa = 0;
		int anoIniciativa = 0;
		DiaMesAno d1 = new DiaMesAno();
		List<DiaMesAno> listaPeriodo = new ArrayList<>();
		try {
			for (Averbacao a : listaAverbacacao) {

				if (a.getDATA_inicioConcomitancia() != null) {

					d1 = devolveDiaMesAno(a.getDATA_inicioConcomitancia(),
							new LocalDate(a.getDATA_fimConcomitancia()).plusDays(a.getNUMR_deducao()).toDate());
					listaPeriodo
							.add(subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));

				} else {
					if (a.getNUMR_deducao() != 0) {
						d1 = devolveDiaMesAno(a.getDATA_admissao(),
								new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_deducao()).toDate());
						listaPeriodo.add(
								subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));
					} else {
						listaPeriodo.add(
								devolveDiaMesAno(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).toDate()));
					}
				}

			}

			for (DiaMesAno p : listaPeriodo) {
				diaIniciativa += p.getDia();
				mesIniciativa += p.getMes();
				anoIniciativa += p.getAno();
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return formata(formataPeriodo(new DiaMesAno(diaIniciativa, mesIniciativa, anoIniciativa)));
	}
	
	@SuppressWarnings("static-access")
	public String devolveDiaMesAnoTempoTotalContribuicao() {
		int diaIniciativa = 0;
		int mesIniciativa = 0;
		int anoIniciativa = 0;
		DiaMesAno d1 = new DiaMesAno();
		List<DiaMesAno> listaPeriodo = new ArrayList<>();
		try {
			for (Averbacao a : listaAverbacacao) {

				if (a.getDATA_inicioConcomitancia() != null) {

					d1 = devolveDiaMesAno(a.getDATA_inicioConcomitancia(),
							new LocalDate(a.getDATA_fimConcomitancia()).plusDays(a.getNUMR_deducao()).toDate());
					listaPeriodo
							.add(subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));

				} else {
					if (a.getNUMR_deducao() != 0) {
						d1 = devolveDiaMesAno(a.getDATA_admissao(),
								new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_deducao()).toDate());
						listaPeriodo.add(
								subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));
					} else {
						listaPeriodo.add(
								devolveDiaMesAno(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).toDate()));
					}
				}

			}
			listaPeriodo.add(devolveDiaMesAno(listaAverbacacao.get(0).getNUMR_pessoasFuncionais().getDATA_efetivoExercicio(), new LocalDate().now().toDate()));

			for (DiaMesAno p : listaPeriodo) {
				diaIniciativa += p.getDia();
				mesIniciativa += p.getMes();
				anoIniciativa += p.getAno();
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return formata(formataPeriodo(new DiaMesAno(diaIniciativa, mesIniciativa, anoIniciativa)));
	}

	@SuppressWarnings("static-access")
	public int devolveDiasTempoTotalAproveitado() {
		int dias = 0;
		List<Period> listaPeriodo = new ArrayList<>();

		try {
			for (Averbacao a : listaAverbacacao) {

				if (a.getDATA_inicioConcomitancia() != null) {

					listaPeriodo.add(new Period(dataLocal.fromDateFields(a.getDATA_admissao()),
							dataLocal.fromDateFields(a.getDATA_demissao()), PeriodType.days())
									.minus(new Period(dataLocal.fromDateFields(a.getDATA_inicioConcomitancia()),
											dataLocal.fromDateFields(a.getDATA_fimConcomitancia()), PeriodType.days())
													.plusDays(a.getNUMR_deducao())));

				} else {
					listaPeriodo.add(periodo = new Period(dataLocal.fromDateFields(a.getDATA_admissao()),
							dataLocal.fromDateFields(a.getDATA_demissao()), PeriodType.days())
									.minusDays(a.getNUMR_deducao()));
				}
			}

			for (Period p : listaPeriodo) {
				dias += p.getDays();
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return dias;
	}
	
	@SuppressWarnings("static-access")
	public int devolveDiasAproveitados(Averbacao a) {
		int dias = 0;
		List<Period> listaPeriodo = new ArrayList<>();

		try {

				if (a.getDATA_inicioConcomitancia() != null) {

					listaPeriodo.add(new Period(dataLocal.fromDateFields(a.getDATA_admissao()),
							dataLocal.fromDateFields(a.getDATA_demissao()), PeriodType.days())
									.minus(new Period(dataLocal.fromDateFields(a.getDATA_inicioConcomitancia()),
											dataLocal.fromDateFields(a.getDATA_fimConcomitancia()), PeriodType.days())
													.plusDays(a.getNUMR_deducao())));

				} else {
					listaPeriodo.add(periodo = new Period(dataLocal.fromDateFields(a.getDATA_admissao()),
							dataLocal.fromDateFields(a.getDATA_demissao()), PeriodType.days())
									.minusDays(a.getNUMR_deducao()));
				}

			for (Period p : listaPeriodo) {
				dias += p.getDays();
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return dias;
	}

	@SuppressWarnings("static-access")
	public int devolveAnosTempoPrevidenciario() {
		int anos = 0;
		DiaMesAno d1 = new DiaMesAno();
		List<DiaMesAno> listaPeriodo = new ArrayList<>();

		try {
			for (Averbacao a : listaAverbacacao) {

				if (a.getDATA_inicioConcomitancia() != null) {

					d1 = devolveDiaMesAno(a.getDATA_inicioConcomitancia(),new LocalDate(a.getDATA_fimConcomitancia()).plusDays(a.getNUMR_deducao()).toDate());
					listaPeriodo
							.add(subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));

				} else {
					if (a.getNUMR_deducao() != 0) {
						d1 = devolveDiaMesAno(a.getDATA_admissao(),new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_deducao()).toDate());
						listaPeriodo.add(subtraiDoisPeriodos(devolveDiaMesAno(a.getDATA_admissao(), a.getDATA_demissao()), d1));
					} else {
						listaPeriodo.add(
								devolveDiaMesAno(a.getDATA_admissao(), new LocalDate(a.getDATA_demissao()).toDate()));
					}
				}
			}

			listaPeriodo.add(devolveDiaMesAno(this.pf.getDATA_efetivoExercicio(), new LocalDate().now().toDate()));
			for (DiaMesAno p : listaPeriodo) {
				anos += p.getAno();
			}
			System.out.println(anos);
		} catch (Exception e) {
			System.out.println("Não foi possivel devolver a Iniciativa");
		}

		return anos;
	}

	private DiaMesAno formataPeriodo(DiaMesAno dma) {

		dia = dma.getDia();
		/*int mesTemp = 0;
		if (dia == 31) {
			dia = 0;
			mesTemp = 1;
		}*/

		mes = dma.getMes();
		ano = dma.getAno();

		if (dia >= 30) {
			mes += (dia / 30);
			dia %= 30;
		}

		if (mes >= 12) {
			ano += (mes / 12);
			mes %= 12;
		}
		DiaMesAno d = new DiaMesAno(dia, mes, ano);

		return d;
	}

	public DiaMesAno subtraiDoisPeriodos(DiaMesAno periodo1, DiaMesAno periodo2) {
		
		dia = periodo1.getDia();
		mes = periodo1.getMes();
		ano = periodo1.getAno();
		
		System.out.println("1°)"+ano+" - "+mes+" - "+dia);
		
		if (periodo2.getDia() > dia) {
			dia += 30;
			mes -= 1;
		} else {
			dia = periodo1.getDia();
		}
		
		System.out.println("2°)"+ano+" - "+mes+" - "+dia);
		
		if (periodo2.getMes() > mes) {
			mes += 12;
			ano -= 1;
		} 
		
		System.out.println("3°)"+ano+" - "+mes+" - "+dia);
		
		dia -= periodo2.getDia();
		mes -= periodo2.getMes();
		ano -= periodo2.getAno();
		
		System.out.println("4°)"+ano+" - "+mes+" - "+dia);
		
		if(dia >= 30){
			dia %= 30;
			mes++;
		}
		
		if(mes >= 12){
			mes %= 12;
			ano++;
		}
		
		return new DiaMesAno(dia, mes,ano );
	}

	@SuppressWarnings("static-access")
	public String devolveTempoFaltanteRegraPrevidencia(int qtdAnoRegra) {
		return formata(subtraiDoisPeriodosRegraPrevidencia(new DiaMesAno(0, 0, qtdAnoRegra),
				devolveDiaMesAnoSimulacao(
						new LocalDate(pf.getDATA_efetivoExercicio()).minusDays(devolveDiasTempoTotalAproveitado()).toDate(),
						new LocalDate().now().toDate())));
	}

	@SuppressWarnings("static-access")
	public int devolveAnosRegraPrevidencia(int qtdAnoRegra) {
		DiaMesAno dma = subtraiDoisPeriodosRegraPrevidencia(new DiaMesAno(0, 0, qtdAnoRegra),
				devolveDiaMesAnoSimulacao(
						new LocalDate(pf.getDATA_efetivoExercicio()).minusDays(devolveDiasTempoTotalAproveitado()).toDate(),
						new LocalDate().now().toDate()));
		int res = 0;
		if (dma.getAno() < 0) {
			res = dma.getAno() * (-1);
		}
		return res;
	}

	private DiaMesAno subtraiDoisPeriodosRegraPrevidencia(DiaMesAno periodo1, DiaMesAno periodo2) {
		dia = periodo1.getDia() - periodo2.getDia();
		mes = periodo1.getMes() - periodo2.getMes();
		ano = periodo1.getAno() - periodo2.getAno();

		if (periodo1.getAno() > periodo2.getAno() && periodo1.getMes() < periodo2.getMes()
				&& periodo1.getDia() < periodo2.getDia()) {

			ano = (periodo1.getAno() - 1) - periodo2.getAno();
			mes = (periodo1.getMes() + 11) - periodo2.getMes();
			dia = (periodo1.getDia() + 30) - periodo2.getDia();

		} else if (periodo1.getMes() > periodo2.getMes() && periodo1.getDia() < periodo2.getDia()) {
			mes = (periodo1.getMes() - 1) - periodo2.getMes();
			dia = (periodo1.getDia() + 30) - periodo2.getDia();
		}

		return new DiaMesAno(dia, mes, ano);
	}

	public String formata(DiaMesAno diaMesAno) {
		return diaMesAno.getAno() + " ano(s), " + diaMesAno.getMes() + " mes(es) e " + diaMesAno.getDia() + " dia(s).";
	}

	private int devolveBissextos(Date data1, Date data2) {

		int bissextos = 0;
		for (int i = new LocalDate(data1).getYear(); i < new LocalDate(data2).getYear(); i++) {
			if (new DateTime().withYear(i).year().isLeap() == true) {
				bissextos++;
			}
		}

		return bissextos;
	}

}
