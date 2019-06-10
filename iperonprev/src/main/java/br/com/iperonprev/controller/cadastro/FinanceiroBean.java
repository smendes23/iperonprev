package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.dao.AfastamentoLicencaDao;
import br.com.iperonprev.dao.AverbacaoDao;
import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.dao.PortariaDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.DemonstrativoFinanceiro;
import br.com.iperonprev.models.Indice;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Portaria;
import br.com.iperonprev.models.ProventosBeneficio;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.models.SalarioMinimo;
import br.com.iperonprev.reports.container.BuilderReport;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.ReportsConcate;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.services.beneficio.Aposentadorias.QualificaProporcionalidade;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoAposentadoriaImpl;
import br.com.iperonprev.services.contribuicao.QualificaCalculoContribuicao;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@ManagedBean
@SessionScoped
//@javax.faces.view.ViewScoped
public class FinanceiroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	PessoasFuncionais pf = new PessoasFuncionais();
	Remuneracoes remuneracao = new Remuneracoes();
	Rubricas rubrica = new Rubricas();
	ContribuicaoDto contribuicao = new ContribuicaoDto();
	private List<Remuneracoes> filtroDeRemuneracao;
	private int decimoTerceiro;
	private String competenciaInicial;
	private String competenciaFinal;
	static List<Remuneracoes> listaDefinanceiro = new ArrayList<Remuneracoes>();
	private String competenciaPortaria;
	private boolean existePortaria;
	private List<PessoasFuncionais> listaFuncionais = new ArrayList<PessoasFuncionais>();
	private boolean ativaTab = false;
	private String ano;
	List<ContribuicaoDto> listaDecontribuicoes = new ArrayList<>();
	private BigDecimal baseContribuicao;
	private Date dataSimulacao;
	private boolean tipoCalculo = false;
	Portaria portaria = new Portaria();
	List<Indice> listaIndice = new ArrayList<>();
	GenericDao<Averbacao> daoAverbacao = new AverbacaoDao();

	public BigDecimal getBaseContribuicao() {
		return baseContribuicao;
	}

	public void setBaseContribuicao(BigDecimal baseContribuicao) {
		this.baseContribuicao = baseContribuicao;
	}

	public List<ContribuicaoDto> getListaDecontribuicoes() {
		return listaDecontribuicoes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public boolean isAtivaTab() {
		return this.ativaTab;
	}

	public void setAtivaTab(boolean ativaTab) {
		this.ativaTab = ativaTab;
	}

	public List<PessoasFuncionais> getListaFuncionais() {
		return this.listaFuncionais;
	}

	public Remuneracoes getRemuneracao() {
		return this.remuneracao;
	}

	public void setRemuneracao(Remuneracoes remuneracao) {
		this.remuneracao = remuneracao;
	}

	public Rubricas getRubrica() {
		return this.rubrica;
	}

	public void setRubrica(Rubricas rubrica) {
		this.rubrica = rubrica;
	}

	public ContribuicaoDto getContribuicao() {
		return this.contribuicao;
	}

	public void setContribuicao(ContribuicaoDto contribuicao) {
		this.contribuicao = contribuicao;
	}

	public List<Remuneracoes> getFiltroDeRemuneracao() {
		return this.filtroDeRemuneracao;
	}

	public void setFiltroDeRemuneracao(List<Remuneracoes> filtroDeRemuneracao) {
		this.filtroDeRemuneracao = filtroDeRemuneracao;
	}

	public int getDecimoTerceiro() {
		return this.decimoTerceiro;
	}

	public void setDecimoTerceiro(int decimoTerceiro) {
		this.decimoTerceiro = decimoTerceiro;
	}

	public String getCompetenciaInicial() {
		return this.competenciaInicial;
	}

	public void setCompetenciaInicial(String competenciaInicial) {
		this.competenciaInicial = competenciaInicial;
	}

	public String getCompetenciaFinal() {
		return this.competenciaFinal;
	}

	public void setCompetenciaFinal(String competenciaFinal) {
		this.competenciaFinal = competenciaFinal;
	}

	public static List<Remuneracoes> getListaDefinanceiro() {
		return listaDefinanceiro;
	}

	public static void setListaDefinanceiro(List<Remuneracoes> listaDefinanceiro) {
		FuncionalBean.listaDefinanceiro = listaDefinanceiro;
	}

	public String getCompetenciaPortaria() {
		return this.competenciaPortaria;
	}

	public void setCompetenciaPortaria(String competenciaPortaria) {
		this.competenciaPortaria = competenciaPortaria;
	}

	public boolean isExistePortaria() {
		return this.existePortaria;
	}

	public void setExistePortaria(boolean existePortaria) {
		this.existePortaria = existePortaria;
	}

	public PessoasFuncionais getPf() {
		return this.pf;
	}

	public void setPf(PessoasFuncionais pf) {
		this.pf = pf;
	}

	private String cpfServidor = new String();

	public String getCpfServidor() {
		return this.cpfServidor;
	}

	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}

	Pessoas pessoas = new Pessoas();

	public Pessoas getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}
	
	

	public Date getDataSimulacao() {
		return dataSimulacao;
	}

	public void setDataSimulacao(Date dataSimulacao) {
		this.dataSimulacao = dataSimulacao;
	}

	public boolean isTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(boolean tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public void buscaServidor() {
		try {
			if (!this.cpfServidor.isEmpty()) {
				this.pessoas = new PessoasDao().devolvePessoa(this.cpfServidor);
				this.listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(this.pessoas.getNUMR_cpf());
			} else {
				Message.addErrorMessage("Cpf Nulo");
			}
		} catch (Exception e) {
			Message.addErrorMessage("Não foi possível carregar os dados pessoais!");
		}
	}

	Cargos cargo = new Cargos();

	public Cargos getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	private int idFuncional = 0;

	public int getIdFuncional() {
		return this.idFuncional;
	}

	public void setIdFuncional(int idFuncional) {
		this.idFuncional = idFuncional;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void salvaContribuicaoPorPeriodo() {
		try {
			Date dataFim = sdf.parse(new StringBuilder().append("01/").append(this.competenciaFinal).toString());
			
			if(sdf.parse(new StringBuilder().append("01/").append(this.competenciaFinal).toString()).compareTo(new LocalDate().now().toDate()) < 0) {
				
				/*new QualificaCompetencia().executa(new DateTime(dataInicio), new DateTime(sdf.parse(new StringBuilder().append("01/").append(this.competenciaFinal).toString())),
						new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(this.pf.getNUMG_idDoObjeto()),
						this.baseContribuicao);*/
				new RemuneracaoDao().saveAnyFinance(new StringBuilder().append("01/").append(this.competenciaInicial).toString(),
						new StringBuilder().append("01/").append(this.competenciaFinal).toString(), this.idFuncional, this.baseContribuicao);
				
				Message.addSuccessMessage("Contribuições criadas com sucesso!");
			}else {
				Message.addErrorMessage("Competência anterior a data de posse, ou competência final maior que data atual!");
			}
			
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao criar contribuições!");
		}
	}
	
	private boolean habilitaCompetenciaFim(Date dataFim) {
		boolean res = false;
		try {
			if(new LocalDate(dataFim).getYear() <= new LocalDate().now().getYear() &&
					new LocalDate(dataFim).getMonthOfYear() <= new LocalDate().now().getMonthOfYear()) {
				res = true;
			}
		}catch(Exception e) {
			res = false;
		}
		return res;
	}

	public void buscaFuncional(ValueChangeEvent event) {
		try {
			this.pf = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class)
					.buscarPorId((Integer) event.getNewValue());
			this.cargo = this.pf.getNUMR_idDoObjetoCargo();
			this.ativaTab = true;
		} catch (Exception e) {
			System.out.println("Funcional nulo");
		}
	}

	public void limpaDadosFuncionaisServidor() {
		novoObjetoRemuneracao();
	}

	public void salvarObjetoRemuneracao() {
		try {
			this.remuneracao.setNUMR_idDoObjetoFuncional(devolveNovoFuncional(this.pf.getNUMG_idDoObjeto()));
			this.remuneracao.setNUMR_rubrica(this.rubrica);
			this.remuneracao.setFLAG_decimoTerceiro(this.decimoTerceiro);
			new GenericPersistence<Remuneracoes>(Remuneracoes.class).salvar(this.remuneracao);
			novoObjetoRemuneracao();
			Message.addSuccessMessage("Remuneração salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Remuneração!");
		}
	}

	private PessoasFuncionais devolveNovoFuncional(int idFuncional) {
		return new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(idFuncional);
	}

	public void novoObjetoRemuneracao() {
		this.remuneracao = new Remuneracoes();
		this.rubrica = new Rubricas();
		this.contribuicao = new ContribuicaoDto();
		this.decimoTerceiro = 0;
		this.pessoas = new Pessoas();
		this.pf = new PessoasFuncionais();
		validaComp = false;
	}

	public void exibeListaDeObjetosRemuneracao() {
		listaDefinanceiro = new ArrayList<Remuneracoes>();
		new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeVerbas", 350, 930);
	}

	private static boolean actionButtonVerbas = false;

	public void fechaVerbas() {
		actionButtonVerbas = true;
		RequestContext.getCurrentInstance().closeDialog("listaDeVerbas");
	}

	public void actionEditVerbas() {
		actionButtonVerbas = false;
	}

	public void pegaInstanciaDialogoRemuneracao(Remuneracoes obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	public void selecionaObjetoDialogoRemuneracao(SelectEvent event) {
		if (!actionButtonVerbas) {
			this.remuneracao = ((Remuneracoes) event.getObject());
			this.rubrica = this.remuneracao.getNUMR_rubrica();
		}
		actionEditVerbas();
	}

	private boolean actionButtonRubricas = false;

	public void fechaRubricas() {
		actionButtonRubricas = true;
		devolveListaDeRubricas();
		RequestContext.getCurrentInstance().closeDialog("listaDeRubricas");
	}

	public void actionEditRubricas() {
		actionButtonRubricas = false;
	}

	public void exibeListaDeObjetosRubricas() {
		new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeRubricas", 295, 930);
	}

	public void selecionaObjetoDialogoRubricas(SelectEvent event) {
		actionEditRubricas();
	}

	private List<Rubricas> devolveListaDeRubricas() {
		return new GenericPersistence<Rubricas>(Rubricas.class).listarTodos("Rubricas");
	}

	public List<Rubricas> getListaRubricas() {
		return devolveListaDeRubricas();
	}

	/*
	 * public void onCellEdit(CellEditEvent event) { Object oldValue =
	 * event.getOldValue(); Object newValue = event.getNewValue();
	 * 
	 * if(newValue != null && !newValue.equals(oldValue)) { FacesMessage msg = new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue +
	 * ", New:" + newValue); FacesContext.getCurrentInstance().addMessage(null,
	 * msg); }
	 * 
	 * }
	 */

	public void processaLista() {
		RemuneracaoDao dao = new RemuneracaoDao();
		this.listaDecontribuicoes = new ArrayList<>();
		List<ContribuicaoDto> listaContrib = new ArrayList<>();

		try {

//			populaListaDeContribuicoesComCompetencia();
			
			listaContrib = dao.listaRemuneracoesPorAno(this.ano, this.idFuncional);
			for (ContribuicaoDto c : listaContrib) {
				
				ContribuicaoDto contrib = new ContribuicaoDto();
				contrib  =new QualificaCalculoContribuicao()
				.executa(c,pf.getDATA_efetivoExercicio(),c.getVALR_contribSegurado(),true);
				
				listaDecontribuicoes.add(contrib);
			}

//			listaDecontribuicoes= dao.listaRemuneracoesPorAno(this.ano, this.idFuncional);
			Collections.sort(listaDecontribuicoes, CONTRIBUICAO_ORDER) ;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populaListaDeContribuicoesComCompetencia() {
		for (int i = 0; i < 12; i++) {
			this.listaDecontribuicoes.add(new ContribuicaoDto(
					new StringBuilder().append(i <= 8 ? "0" + (i + 1) : (i + 1)).append(this.ano).toString(),
					new BigDecimal("0.00"),
					new Double(0.00),
					new BigDecimal("0.00"),
					new BigDecimal("0.00"),
					new Double(0.00),
					new BigDecimal("0.00"), 
					new BigDecimal("0.00")));
		}

	}

	public void onCellEdit(CellEditEvent event) {

		this.pf = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(this.idFuncional);
		ContribuicaoDto contrib = new ContribuicaoDto();
		contrib.setDESC_competencia(listaDecontribuicoes.get(event.getRowIndex()).getDESC_competencia());
		
		
		
		switch (event.getColumn().getField()) {
		case "baseCalculo":
			contrib.setVALR_contribuicaoPrevidenciaria((BigDecimal) event.getNewValue());
			contrib = new QualificaCalculoContribuicao().executa(contrib, this.pf.getDATA_efetivoExercicio(),contrib.getVALR_contribuicaoPrevidenciaria(),true);
			Remuneracoes rem = new RemuneracaoDao().devolveRemuneracoesContribuicaoPorCompetencia(this.idFuncional, 7602, listaDecontribuicoes.get(event.getRowIndex()).getDESC_competencia());
			rem.setVALR_remuneracao((BigDecimal) event.getNewValue());
			new GenericPersistence<Remuneracoes>(Remuneracoes.class).salvar(rem);
			
			break;
		case "aliquotaSegurado":
			contrib.setVALR_contribuicaoPrevidenciaria(
					listaDecontribuicoes.get(event.getRowIndex()).getVALR_contribuicaoPrevidenciaria());
			contrib.setNUMR_aliquotaPatronal(listaDecontribuicoes.get(event.getRowIndex()).getNUMR_aliquotaPatronal());
			contrib.setVALR_contribPatronal(listaDecontribuicoes.get(event.getRowIndex()).getVALR_contribPatronal());
			contrib.setNUMR_aliquotaSegurado((double) event.getNewValue());
			contrib.setVALR_contribSegurado(contrib.getVALR_contribuicaoPrevidenciaria()
					.multiply(new BigDecimal(contrib.getNUMR_aliquotaSegurado())
							.divide(new BigDecimal(100), 2, RoundingMode.UP).setScale(3, BigDecimal.ROUND_CEILING)));
			break;
		case "contribuicaoSegurado":
			contrib.setVALR_contribSegurado((BigDecimal) event.getNewValue());
			contrib = new QualificaCalculoContribuicao().executa(contrib, this.pf.getDATA_efetivoExercicio(),
					contrib.getVALR_contribSegurado(),false);
			break;
		case "aliquotaPatronal":
			contrib.setVALR_contribuicaoPrevidenciaria(
					listaDecontribuicoes.get(event.getRowIndex()).getVALR_contribuicaoPrevidenciaria());
			contrib.setNUMR_aliquotaSegurado(listaDecontribuicoes.get(event.getRowIndex()).getNUMR_aliquotaSegurado());
			contrib.setVALR_contribSegurado(listaDecontribuicoes.get(event.getRowIndex()).getVALR_contribSegurado());
			contrib.setNUMR_aliquotaPatronal((double) event.getNewValue());
			contrib.setVALR_contribPatronal(contrib.getVALR_contribuicaoPrevidenciaria()
					.multiply(new BigDecimal(contrib.getNUMR_aliquotaPatronal())
							.divide(new BigDecimal(100), 2, RoundingMode.UP).setScale(3, BigDecimal.ROUND_CEILING)));
			break;
		case "contribuicaoPatronal":
			contrib.setVALR_contribPatronal((BigDecimal) event.getNewValue());
			contrib = new QualificaCalculoContribuicao().executa(contrib, this.pf.getDATA_efetivoExercicio(),
					contrib.getVALR_contribPatronal(),false);
			break;
		default:
			break;
		}
		listaDecontribuicoes.set(event.getRowIndex(), contrib);

	}

	List<DemonstrativoFinanceiro> listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();
	TipoAposentadoriaImpl impl = new TipoAposentadoriaImpl();
	private String competencia = new String();
	DemonstrativoFinanceiro demonstrativo = new DemonstrativoFinanceiro();
	ProventosBeneficio pb = new ProventosBeneficio();

	public void onTabChange(TabChangeEvent event) {
		listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();
		this.listaDecontribuicoes = new ArrayList<>();
		this.ano = new String();
		if (event.getTab().getTitle().equals("Proventos")) {
			if ((verificaDataBeneficioValida(this.pf)) && (verificaSeExisteBeneficio(this.pf))) {
				this.impl = new QualificaProporcionalidade().devolveProporcionalidade(this.pf);
				try {
					this.pb = new GenericPersistence<ProventosBeneficio>(ProventosBeneficio.class)
							.buscaObjetoRelacionamento("ProventosBeneficio", "NUMR_idDoObjetoFuncionais",
									this.pf.getNUMG_idDoObjeto());
				} catch (Exception e) {
					System.out.println("Não foi possível carregar proventos beneficio.");
				}
			} else if (!verificaDataBeneficioValida(this.pf)) {
				Message.addErrorMessage("Data do Beneficio Nula!");
			} else if (!verificaSeExisteBeneficio(this.pf)) {
				Message.addErrorMessage("Não existe benefício para este funcional!");
			} else {
				Message.addErrorMessage("Existem pendencias para este funcional!");
			}
		}

		this.competencia = new String();
		this.demonstrativo = new DemonstrativoFinanceiro();
		this.pb = new ProventosBeneficio();
		this.impl = new TipoAposentadoriaImpl();
		this.pessoas = pf.getNUMR_idDoObjetoPessoas();
	}

	Remuneracoes rem = new Remuneracoes();

	private void devolveRemuneracao() {
		try {
			this.rem = new RemuneracaoDao().devolveRemuneracoesContribuicaoPorCompetencia(
					this.pf.getNUMG_idDoObjeto().intValue(), this.demonstrativo.getNUMR_rubricas().getNUMG_idDoObjeto(),
					this.competencia);
		} catch (Exception e) {
			System.out.println("N�o foi possivel carregar remunera�oes");
		}
	}

	private boolean verificaSeExisteBeneficio(PessoasFuncionais funcional) {
		boolean res = false;
		try {
			if (!funcional.getENUM_tipoAposentadoria().equals(null)) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("N�o existe beneficio");
		}
		return res;
	}

	private boolean verificaDataBeneficioValida(PessoasFuncionais funcional) {
		boolean res = false;
		try {
			if (funcional.getDATA_Beneficio() != null) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Data Beneficio NUla");
		}
		return res;
	}

	public void recarregaPagina() {
		this.pf = new PessoasFuncionais();
		this.remuneracao = new Remuneracoes();
		this.rubrica = new Rubricas();
		this.contribuicao = new ContribuicaoDto();
		this.filtroDeRemuneracao = new ArrayList<>();
		this.decimoTerceiro = 0;
		this.competenciaInicial = new String();
		this.competenciaFinal = new String();
		this.listaDefinanceiro = new ArrayList<Remuneracoes>();
		this.competenciaPortaria = new String();
		this.existePortaria = false;
		this.listaFuncionais = new ArrayList<PessoasFuncionais>();
		this.ativaTab = false;
		this.ano = new String();
		this.listaDecontribuicoes = new ArrayList<>();
		this.pessoas = new Pessoas();
		this.cpfServidor = new String();
		this.cargo = new Cargos();
		this.idFuncional = 0;
		this.competenciaInicial = new String();
		this.competenciaFinal = new String();
		this.baseContribuicao = BigDecimal.ZERO;
		validaComp = false;
		
	}

	public void salvaContribuicoes() {
		try {

			Message.addSuccessMessage("Contribuicoes salvas com sucesso");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Contribuições");
		}
	}
	
	public void geraFichaFinanceira() {
		
		Reports report = new Reports();
		try {
			report.createReport(Templates.reportTemplate, "fichaContribuicoes", new ArrayList<>(), fieldsFinanceiro(),
					dataSourceFichaFinanceira());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível imprimir o relatório de ficha financeira");
		}
	}
	
	private List<Field> fieldsFinanceiro() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("nome", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("situacaoPrevidenciaria", "string"));
		field.add(new Field("situacaoFuncional", "string"));
		field.add(new Field("planoPrevidenciario", "string"));
		field.add(new Field("competencia", "string"));
		field.add(new Field("remuneracaoBeneficio", "string"));
		field.add(new Field("contribuicaoSegurado", "string"));
		field.add(new Field("contribuicaoPatronal", "string"));
		field.add(new Field("total", "string"));
		return field;
	}

	private JRDataSource dataSourceFichaFinanceira() {
		List<ContribuicaoDto> listaDeContribuicoes = new ContribuicaoHelper().listaDeContribuicoesRemuneracao(this.pf);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		DRDataSource dataSource = new DRDataSource("nome", "matricula", "cpf", "orgao", "situacaoPrevidenciaria",
				"situacaoFuncional", "planoPrevidenciario", "competencia", "remuneracaoBeneficio",
				"contribuicaoSegurado", "contribuicaoPatronal", "total");
		String planoPrevidenciario = "";

		try {
			if (this.pf.getDATA_efetivoExercicio().compareTo(sdf.parse("31/12/2009")) < 0) {
				planoPrevidenciario = "Conta Financeira";
			} else {
				planoPrevidenciario = "Conta Capitalizada";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao converter data plano previdenciario");
		}
		Collections.sort(listaDeContribuicoes, CONTRIBUICAO_ORDER);
		for (ContribuicaoDto c : listaDeContribuicoes) {
			dataSource.add(this.pf.getNUMR_idDoObjetoPessoas().getDESC_nome(), this.pf.getDESC_matricula(),
					this.pf.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					this.pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					this.pf.getNUMR_situacaoPrevidenciaria().getDESC_descricao(),
					this.pf.getNUMR_situacaoFuncional().getDESC_nome(), 
					planoPrevidenciario,
					new StringBuilder().append(c.getDESC_competencia().substring(0, 2)).append("/")
							.append(c.getDESC_competencia().substring(2, 6)).toString(),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribuicaoPrevidenciaria()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribSegurado()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribPatronal()),
					new DecimalFormatter().formatterToCurrencyBrazilian(
							c.getVALR_contribSegurado().add(c.getVALR_contribPatronal())));
		}
		return dataSource;
	}
	
	private static Comparator<ContribuicaoDto> CONTRIBUICAO_ORDER = new Comparator<ContribuicaoDto>() {

		@Override
		public int compare(ContribuicaoDto o1, ContribuicaoDto o2) {
			Integer valor1 = Integer.parseInt(new StringBuilder().append(o1.getDESC_competencia().substring(2, 6))
					.append(o1.getDESC_competencia().substring(0, 2)).toString());
			Integer valor2 = Integer.parseInt(new StringBuilder().append(o2.getDESC_competencia().substring(2, 6))
					.append(o2.getDESC_competencia().substring(0, 2)).toString());
			return valor1.compareTo(valor2);
		}
	};
	
	
	public void geraMedias() throws IOException {
		try {
			portaria = new PortariaDao().buscaPortariaPorCompetencia(competenciaPortaria);
			listaIndice = new GenericPersistence<Indice>(Indice.class).listarRelacionamento("Indice", "portaria",
					portaria.getNUMG_idDoObjeto());
			
			/*listaIndice = new GenericPersistence<Indice>(Indice.class).listarRelacionamento("Indice", "portaria",
					portaria.getNUMG_idDoObjeto());*/
			ReportsConcate rep = new ReportsConcate();
			
			rep.generateReportsWithTwoBuilder(new BuilderReport().createReport(Templates.reportTemplate, "medias", new ArrayList<Column>(), fieldsRemuneracao(),
					dataSourceMedias(this.pf, new ArrayList<Column>(), fieldsRemuneracao(), listaIndice)), 
					new BuilderReport().createReport(Templates.reportTemplate, "calculoProventos", new ArrayList<Column>(), fieldsProventos(),
					dataSourceProventos(this.pf, new ArrayList<Column>(), fieldsProventos(), listaIndice)));
		} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Não foi possível gerar médias.");
		}
	}

	private List<Field> fieldsRemuneracao() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("mat", "string"));
		field.add(new Field("serv", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("dataNascimento", "date"));
		field.add(new Field("estadoCivil", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("dataAdmissao", "date"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("fatores", "string"));
		field.add(new Field("competencia", "string"));
		field.add(new Field("base", "string"));
		field.add(new Field("indice", "string"));
		field.add(new Field("remuneracao", "string"));
		field.add(new Field("oitenta", "string"));
		field.add(new Field("total", "string"));
		return field;
	}

	private List<Field> fieldsProventos() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("mat", "string"));
		field.add(new Field("serv", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("dataNascimento", "date"));
		field.add(new Field("estadoCivil", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("dataAdmissao", "date"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("fatores", "string"));
		field.add(new Field("dataSimulacao", "string"));
		field.add(new Field("totalRemuneracao", "string"));
		field.add(new Field("totalOitentaMaiores", "string"));
		field.add(new Field("ultimaRemuneracao", "string"));
		field.add(new Field("somaOitentaMaiores", "string"));
		field.add(new Field("mediaOitentaMaiores", "string"));
		field.add(new Field("valorApurado", "string"));
		field.add(new Field("tempoServico", "string"));
		field.add(new Field("salarioMinimo", "string"));
		field.add(new Field("proporcionalidade", "string"));
		field.add(new Field("proventoIntegralidade", "string"));
		field.add(new Field("totalProventos", "string"));
		field.add(new Field("tipoProventos", "string"));
		field.add(new Field("tipoCalculo", "string"));
		return field;
	}

	private JRDataSource dataSourceProventos(PessoasFuncionais obj, List<Column> columns, List<Field> fields,
			List<Indice> listaDeIndice) {

		DRDataSource dataSource = new DRDataSource("mat", "serv", "cpf", "sexo", "dataNascimento", "estadoCivil",
				"cargo", "dataAdmissao", "orgao", "tituloRelatorio", "fatores", "dataSimulacao", "totalRemuneracao",
				"totalOitentaMaiores", "ultimaRemuneracao", "somaOitentaMaiores", "mediaOitentaMaiores", "valorApurado",
				"tempoServico", "salarioMinimo", "proporcionalidade", "proventoIntegralidade", "totalProventos","tipoProventos","tipoCalculo");
		
		
		List<ContribuicaoDto> listaContribuicoes = devolveContribuicoes(listaDeIndice, obj);
		
		System.out.println("Tamanho da Lista: "+listaContribuicoes.size());
		
		

		int qtdOitenta = (int) listaContribuicoes.stream()
				.filter(c -> !c.getVALR_oitentaMaiores().equals(new BigDecimal("0"))).count();
		int qtdRemuneracao = (int) listaContribuicoes.stream()
				.filter(c -> c.getVALR_contribuicaoPrevidenciaria() != BigDecimal.ZERO).count();

		BigDecimal somaOitentaMaiores =listaContribuicoes.stream().map(b -> b.getVALR_oitentaMaiores())
				.filter(c -> !c.equals(new BigDecimal("0"))).reduce(BigDecimal.ZERO, BigDecimal::add)
				.setScale(3, RoundingMode.CEILING);
		
		BigDecimal ultimaRemuneracao = listaContribuicoes.get(listaContribuicoes.size()-1).getVALR_contribuicaoPrevidenciaria();
		BigDecimal mediaAritmetica = somaOitentaMaiores.divide(new BigDecimal(qtdOitenta), RoundingMode.CEILING);
		BigDecimal valorApurado = BigDecimal.ZERO;
		String tempoServico = "";
		BigDecimal proporcionalidade = BigDecimal.ZERO;
		String tipoProventos = null;
		BigDecimal proventoApurado = BigDecimal.ZERO;
		
		int afastamentoInteresseParticular = 0;
		AfastamentosLicenca al = new AfastamentosLicenca();
		try {
			al = new AfastamentoLicencaDao().devolveListaDeAfastamentosInteresseParticular(obj.getNUMG_idDoObjeto()).get(0);
		}catch(Exception e) {
			System.out.println("Erro afastamentos");
		}
		afastamentoInteresseParticular = Days.daysBetween(new LocalDate(al.getDATA_inicioLicenca()),new LocalDate( al.getDATA_fimLicenca())).getDays();
		
		@SuppressWarnings("static-access")
		Date dataSimulacaoConcessao = new LocalDate().minusDays(afastamentoInteresseParticular).now().toDate();
		List<Averbacao> listaAverbacao = daoAverbacao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				obj.getNUMG_idDoObjeto());

		RetornaTemposAverbacao rta = new RetornaTemposAverbacao(listaAverbacao);
		
		try{
			
			if(dataSimulacao != null){
				dataSimulacaoConcessao = dataSimulacao;
			}
			
		}catch(Exception e){
			System.out.println("Erro ao converter data!");
		}
		int resComparator = 0;
		
		
		valorApurado = (resComparator = mediaAritmetica.compareTo(ultimaRemuneracao)) == -1 ? mediaAritmetica : ultimaRemuneracao;
        int somaDeducoes = new AfastamentoLicencaDao().devolveListaDiasAfastados(obj.getNUMG_idDoObjeto()).stream().mapToInt(Integer::intValue).sum();
		
        if (StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(),"policia")) {
            proporcionalidade = new BigDecimal("100");
            tempoServico = "art. 1º, I LC 144/14";
            tipoProventos = "Provento Apurado pela Integralidade das M\u00e9dias";
            proventoApurado = valorApurado;
        } else {
            tipoProventos = "Provento Proporcional Apurado";
            int tempo = RetornaTempos.retornaDiasApartirDeDuasDatas(obj.getDATA_efetivoExercicio(), dataSimulacaoConcessao) + rta.devolveDiasTempoTotalAproveitado() - somaDeducoes;
            if (obj.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.F) {
                if (tempo > 10950) {
                    tempo = 10950;
                }
                tempoServico = this.df.formatterToThousand(new BigDecimal(tempo)) + "/" + "10.950";
                proporcionalidade = new BigDecimal(tempo).multiply(new BigDecimal(100)).divide(new BigDecimal(10950), 2, RoundingMode.FLOOR);
                proventoApurado = valorApurado.multiply(new BigDecimal(tempo)).divide(new BigDecimal(10950), 2, RoundingMode.FLOOR);
            } else {
                if (tempo > 12775) {
                    tempo = 12775;
                }
                tempoServico = this.df.formatterToThousand(new BigDecimal(tempo)) + "/" + "12.775";
                proporcionalidade = new BigDecimal(tempo).multiply(new BigDecimal(100)).divide(new BigDecimal(12775), 2, RoundingMode.FLOOR);
                proventoApurado = valorApurado.multiply(new BigDecimal(tempo)).divide(new BigDecimal(12775), 2, RoundingMode.FLOOR);
            }
        }
		
		
		String tipoCalc = "Proporcionalidade";
		if(tipoCalculo == true){
			tipoProventos = "Provento Integral Apurado";
			proporcionalidade = new BigDecimal(100);
			tipoCalc = "Integralidade";
			proventoApurado = mediaAritmetica.compareTo(ultimaRemuneracao) == 1 ?ultimaRemuneracao :mediaAritmetica;
		}
		
		List<SalarioMinimo> listaSalario = new GenericPersistence<SalarioMinimo>(SalarioMinimo.class).listarTodos("SalarioMinimo");
		
		Optional<SalarioMinimo> salarioMinimo = listaSalario.stream()
				.filter(s->s.getNUMR_ano() == Integer.parseInt(portaria.getDESC_competencia().substring(2, 6)))
				.findFirst();
		
		
		dataSource.add(obj.getDESC_matricula(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_nome(),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome().toString(),
					obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento(),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_estadoCivil().getDESC_nome(),
					obj.getNUMR_idDoObjetoCargo().getDESC_nome(), 
					obj.getDATA_efetivoExercicio(),
					obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(), 
					"Planilha de Cálculo de Proventos",
					portaria.getDESC_descricao(),
					sdf.format(dataSimulacaoConcessao),
					String.valueOf(qtdRemuneracao),
					String.valueOf(qtdOitenta),
					df.formatterToCurrencyBrazilian(ultimaRemuneracao),
					df.formatterToCurrencyBrazilian(somaOitentaMaiores),
					df.formatterToCurrencyBrazilian(mediaAritmetica),
					df.formatterToCurrencyBrazilian(valorApurado),
//					devolveTempoLiquidoCargo(),
					tempoServico,
					df.formatterToCurrencyBrazilian(salarioMinimo.get().getNUMR_valor()),
					new StringBuilder().append(proporcionalidade).append("%").toString(),
					df.formatterToCurrencyBrazilian(proventoApurado),
					df.formatterToCurrencyBrazilian(proventoApurado),
					tipoProventos,
					tipoCalc
					);

		return dataSource;
	}


	DecimalFormatter df = new DecimalFormatter();

	private JRDataSource dataSourceMedias(PessoasFuncionais obj, List<Column> columns, List<Field> fields,
			List<Indice> listaDeIndice) {

		DRDataSource dataSource = new DRDataSource("mat", "serv", "cpf", "sexo", "dataNascimento", "estadoCivil",
				"cargo", "dataAdmissao", "orgao", "tituloRelatorio", "fatores", "competencia", "base", "indice",
				"remuneracao", "oitenta", "total");

		List<ContribuicaoDto> listaContribuicoes = devolveContribuicoes(listaDeIndice, obj);

		BigDecimal totalOitentas = listaContribuicoes.stream().map(b -> b.getVALR_oitentaMaiores())
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		for (ContribuicaoDto lC : listaContribuicoes) {
			dataSource.add(obj.getDESC_matricula(), obj.getNUMR_idDoObjetoPessoas().getDESC_nome(),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome().toString(),
					obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento(),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_estadoCivil().getDESC_nome(),
					obj.getNUMR_idDoObjetoCargo().getDESC_nome(), obj.getDATA_efetivoExercicio(),
					obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(), "Relatório de Médias",
					portaria.getDESC_descricao(), lC.getDESC_competencia(),
					df.formatterToCurrencyBrazilian(lC.getVALR_contribuicaoPrevidenciaria()),
					df.formatterToFraction(lC.getVALR_indice()),
					df.formatterToCurrencyBrazilian(lC.getVALR_remuneracaoMedia()),
					df.formatterToCurrencyBrazilian(lC.getVALR_oitentaMaiores()),
					df.formatterToCurrencyBrazilian(totalOitentas));

		}

		return dataSource;
	}
	
	private List<BigDecimal> devolveOitentaMaiores(List<Indice> listaI, List<ContribuicaoDto> listaC) {
		List<BigDecimal> lista = new ArrayList<>();
		listaC.forEach(i -> {
			listaI.forEach(f -> {
				if (i.getDESC_competencia().equals(f.getNUMR_mesAno())) {
					lista.add(i.getVALR_contribuicaoPrevidenciaria().multiply(f.getVALR_indice()));
				}
			});
		});
		return lista;
	}


	
	public List<ContribuicaoDto> devolveContribuicoes(List<Indice> listaI,
			PessoasFuncionais pf) {
		
		List<ContribuicaoDto> listaC = new ContribuicaoHelper().listaDeContribuicoesRemuneracao(pf);
		
		List<ContribuicaoDto> lista = new ArrayList<>();
		listaC.forEach(i -> {
			listaI.forEach(f -> {
				if (i.getDESC_competencia().equals(f.getNUMR_mesAno())) {
					i.setVALR_remuneracaoMedia(i.getVALR_contribuicaoPrevidenciaria().multiply(f.getVALR_indice()));
					lista.add(i);
				}
			});
		});

		Collections.sort(lista, CONTRIBUICAO_ORDER);

		List<BigDecimal> listaOitenta = devolveOitentaMaiores(listaI, lista);

		Collections.sort(listaOitenta, Collections.reverseOrder());

		int tamanhoOitentaMaiores = listaOitenta.size() * 80 / 100;

		for (int i = 0; i < lista.size(); i++) {
			if (i < tamanhoOitentaMaiores && !listaOitenta.get(i).equals(null)) {
				lista.get(i).setVALR_oitentaMaiores(listaOitenta.get(i));
			} else {
				lista.get(i).setVALR_oitentaMaiores(new BigDecimal("0"));
			}
		}
		List<ContribuicaoDto> listaContribuicoes = new ArrayList<>();

		listaI.forEach(i -> {
			lista.forEach(f -> {
				if (f.getDESC_competencia().equals(i.getNUMR_mesAno())) {
					ContribuicaoDto ca = new ContribuicaoDto();
					ca.setDESC_competencia(f.getDESC_competencia());
					ca.setVALR_contribuicaoPrevidenciaria(f.getVALR_contribuicaoPrevidenciaria());
					ca.setVALR_indice(i.getVALR_indice());
					ca.setVALR_remuneracaoMedia(f.getVALR_contribuicaoPrevidenciaria().multiply(i.getVALR_indice()));
					ca.setVALR_oitentaMaiores(f.getVALR_oitentaMaiores());
					ca.getVALR_oitentaMaiores();
					listaContribuicoes.add(ca);
				}
			});
		});

		return listaContribuicoes;
	}
	boolean validaComp = false;
	
	@SuppressWarnings("static-access")
	public void validaCompetencia() {
		
		try {

			Date dataAtual  = sdf.parse(new StringBuilder().append("01/").append(this.competenciaFinal).toString());
			
			if(dataAtual.compareTo(new LocalDate().now().toDate()) > 0 ) {
				validaComp = true;
				Message.addWarningMessage("Competencia fora do intervalo.");
				
			}
			
		}catch(Exception e) {
		}
	}
	
	
	public void removeContribuicoes() {
		
		try {
			new ContribuicaoDao().excluirContribuicoes(this.idFuncional, new StringBuffer().append("01/").append(this.competenciaInicial).toString()
					, new StringBuffer().append("01/").append(this.competenciaFinal).toString());
			processaLista();
			Message.addSuccessMessage("Contribuição excluída com sucesso!");
		} catch (Exception e) {
			System.out.println("Não foi possivel excluir as contribuições");
		}
	}
}
