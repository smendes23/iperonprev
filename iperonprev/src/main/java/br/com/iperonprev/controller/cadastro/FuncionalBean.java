package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.iperonprev.constantes.DecenioEnum;
import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.constantes.TipoAtosDiversosEnum;
import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.constantes.TipoDeducaoEnum;
import br.com.iperonprev.constantes.TipoOnusEnum;
import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.controller.dto.FichaFinanceiraDto;
import br.com.iperonprev.dao.AfastamentoDisposicaoDao;
import br.com.iperonprev.dao.AfastamentoLicencaDao;
import br.com.iperonprev.dao.AverbacaoDao;
import br.com.iperonprev.dao.DeducaoDao;
import br.com.iperonprev.dao.FuncionaisFuncoesDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PensaoDao;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.dao.PortariaDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.dao.TempoLiquidoDao;
import br.com.iperonprev.helper.ComponentReportBuilderHelper;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.AfastamentosDisposicao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.AtosDiversos;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.ClasseFuncional;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.DocumentoFuncional;
import br.com.iperonprev.models.Enquadramento;
import br.com.iperonprev.models.Ferias;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.models.Indice;
import br.com.iperonprev.models.Iniciativa;
import br.com.iperonprev.models.NivelFuncional;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Portaria;
import br.com.iperonprev.models.Quinquenio;
import br.com.iperonprev.models.RegimesPrevidenciarios;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.models.SalarioMinimo;
import br.com.iperonprev.models.SituacaoFuncional;
import br.com.iperonprev.models.SituacaoPrevidenciaria;
import br.com.iperonprev.models.TempoLiquidoAverbacao;
import br.com.iperonprev.models.TipoLicenca;
import br.com.iperonprev.models.VinculoPrevidenciario;
import br.com.iperonprev.reports.container.BuilderReport;
import br.com.iperonprev.reports.container.GenerateReportWithSub;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.ReportsConcate;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.reports.funcional.AnotacoesDeFeriasReport;
import br.com.iperonprev.reports.funcional.DeducoesReport;
import br.com.iperonprev.reports.funcional.DisposicaoReport;
import br.com.iperonprev.reports.funcional.HeaderTitleFuncionalReport;
import br.com.iperonprev.reports.funcional.LicencasReport;
import br.com.iperonprev.reports.funcional.QuiquenioReport;
import br.com.iperonprev.reports.funcional.RegistroDeAtosDiversosReport;
import br.com.iperonprev.services.averbacao.QualificaConcomitanciaDuasAverbacoes;
import br.com.iperonprev.services.averbacao.QualificaConcomitanciaUmaAverbacao;
import br.com.iperonprev.services.beneficio.Artigo03;
import br.com.iperonprev.services.contribuicao.QualificaBaseDeCalculo;
import br.com.iperonprev.util.averbacao.DiaMesAno;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@ManagedBean
@SessionScoped
public class FuncionalBean implements GenericBean<PessoasFuncionais>, Serializable {

	private static final long serialVersionUID = 1L;

	PessoasFuncionais funcional = new PessoasFuncionais();
	Pessoas pessoas = new Pessoas();
	GenericDao<PessoasFuncionais> dao = new PessoasFuncionaisDao();
	GenericDao<FuncionaisFuncoes> daoSituacao = new FuncionaisFuncoesDao();
	List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
	List<PessoasFuncionais> filtroDeFuncionais;
	private boolean selDisposicao;
	private boolean selLicenca;
	private boolean compulsoria;
	private boolean idade;
	private boolean idadeContribuicao;
	private boolean invalidez;
	private boolean ativaTab;
	private boolean ativaTabSituacao;
	private List<Enquadramento> enquadramentoSelecionado;
	static List<Enquadramento> listaEnquadramento = new ArrayList<>();
	VinculoPrevidenciario vinculo = new VinculoPrevidenciario();
	FuncionaisFuncoes funcionaisFuncoes = new FuncionaisFuncoes();
	SituacaoPrevidenciaria situacao = new SituacaoPrevidenciaria();
	Orgaos orgao = new Orgaos();
	Orgaos orgaoPrevidenciario = new Orgaos();
	Cargos cargo = new Cargos();
	SituacaoFuncional situacaoFuncional = new SituacaoFuncional();
	Averbacao averbacao = new Averbacao();
	private List<Averbacao> filtroDeAverbacao;
	private int aproveitado;
	AverbacaoBean averbacaoBean = new AverbacaoBean(funcional, averbacao);
	GenericDao<Averbacao> daoAverbacao = new AverbacaoDao();
	AtosDiversos atosDiversos = new AtosDiversos();
	Ferias ferias = new Ferias();
	Quinquenio quiquenio = new Quinquenio();
	AfastamentosLicenca licenca = new AfastamentosLicenca();
	TipoLicenca tipoLicenca = new TipoLicenca();
	GenericDao<AfastamentosLicenca> daoLicenca = new AfastamentoLicencaDao();
	GenericDao<Deducao> daoDeducao = new DeducaoDao();
	Deducao deducao = new Deducao();
	private boolean ativaRemuneracao;
	List<TempoLiquidoAverbacao> listaTempoLiquidoAverbado = new ArrayList<>();
	JasperReportBuiderInterface funcionalReport;
	private Enquadramento enquadramento = new Enquadramento();

	
	public Enquadramento getEnquadramento() {
		return enquadramento;
	}

	public void setEnquadramento(Enquadramento enquadramento) {
		this.enquadramento = enquadramento;
	}

	@PostConstruct
	public void init(){
		this.funcional.setFLAG_abonoPermanencia(DecisaoEnum.NAO);
	}
	
	public boolean isAtivaRemuneracao() {
		return ativaRemuneracao;
	}

	public void setAtivaRemuneracao(boolean ativaRemuneracao) {
		this.ativaRemuneracao = ativaRemuneracao;
	}

	public Deducao getDeducao() {
		return deducao;
	}

	public void setDeducao(Deducao deducao) {
		this.deducao = deducao;
	}

	public AfastamentosLicenca getLicenca() {
		return licenca;
	}

	public void setLicenca(AfastamentosLicenca licenca) {
		this.licenca = licenca;
	}

	public TipoLicenca getTipoLicenca() {
		return tipoLicenca;
	}

	public void setTipoLicenca(TipoLicenca tipoLicenca) {
		this.tipoLicenca = tipoLicenca;
	}

	public Quinquenio getQuiquenio() {
		return quiquenio;
	}

	public void setQuiquenio(Quinquenio quiquenio) {
		this.quiquenio = quiquenio;
	}

	public Ferias getFerias() {
		return ferias;
	}

	public void setFerias(Ferias ferias) {
		this.ferias = ferias;
	}

	public AtosDiversos getAtosDiversos() {
		return atosDiversos;
	}

	public void setAtosDiversos(AtosDiversos atosDiversos) {
		this.atosDiversos = atosDiversos;
	}

	public Orgaos getOrgaoPrevidenciario() {
		return orgaoPrevidenciario;
	}

	public void setOrgaoPrevidenciario(Orgaos orgaoPrevidenciario) {
		this.orgaoPrevidenciario = orgaoPrevidenciario;
	}

	public AverbacaoBean getAverbacaoBean() {
		return averbacaoBean;
	}

	public Averbacao getAverbacao() {
		return averbacao;
	}

	public void setAverbacao(Averbacao averbacao) {
		this.averbacao = averbacao;
	}

	public List<Averbacao> getFiltroDeAverbacao() {
		return filtroDeAverbacao;
	}

	public void setFiltroDeAverbacao(List<Averbacao> filtroDeAverbacao) {
		this.filtroDeAverbacao = filtroDeAverbacao;
	}

	public int getAproveitado() {
		return aproveitado;
	}

	public void setAproveitado(int aproveitado) {
		this.aproveitado = aproveitado;
	}

	public SituacaoFuncional getSituacaoFuncional() {
		return situacaoFuncional;
	}

	public void setSituacaoFuncional(SituacaoFuncional situacaoFuncional) {
		this.situacaoFuncional = situacaoFuncional;
	}

	public Orgaos getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgaos orgao) {
		this.orgao = orgao;
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public SituacaoPrevidenciaria getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPrevidenciaria situacao) {
		this.situacao = situacao;
	}

	public FuncionaisFuncoes getFuncionaisFuncoes() {
		return funcionaisFuncoes;
	}

	public void setFuncionaisFuncoes(FuncionaisFuncoes funcionaisFuncoes) {
		this.funcionaisFuncoes = funcionaisFuncoes;
	}

	public VinculoPrevidenciario getVinculo() {
		return vinculo;
	}

	public void setVinculo(VinculoPrevidenciario vinculo) {
		this.vinculo = vinculo;
	}

	public List<Enquadramento> getEnquadramentoSelecionado() {
		return enquadramentoSelecionado;
	}

	public void setEnquadramentoSelecionado(List<Enquadramento> enquadramentoSelecionado) {
		this.enquadramentoSelecionado = enquadramentoSelecionado;
	}
	
	

	public boolean isAtivaTabSituacao() {
		return ativaTabSituacao;
	}

	public boolean isAtivaTab() {
		return ativaTab;
	}

	public void setAtivaTab(boolean ativaTab) {
		this.ativaTab = ativaTab;
	}

	public boolean isInvalidez() {
		return invalidez;
	}

	public void setInvalidez(boolean invalidez) {
		this.invalidez = invalidez;
	}

	public boolean isIdade() {
		return idade;
	}

	public void setIdade(boolean idade) {
		this.idade = idade;
	}

	public boolean isIdadeContribuicao() {
		return idadeContribuicao;
	}

	public void setIdadeContribuicao(boolean idadeContribuicao) {
		this.idadeContribuicao = idadeContribuicao;
	}

	public boolean isCompulsoria() {
		return compulsoria;
	}

	public void setCompulsoria(boolean compulsoria) {
		this.compulsoria = compulsoria;
	}

	private String cpfServidor;
	static Map<String, Object> mapa = new HashMap<String, Object>();

	public boolean isSelDisposicao() {
		return selDisposicao;
	}

	public void setSelDisposicao(boolean selDisposicao) {
		this.selDisposicao = selDisposicao;
	}

	public boolean isSelLicenca() {
		return selLicenca;
	}

	public void setSelLicenca(boolean selLicenca) {
		this.selLicenca = selLicenca;
	}

	public List<PessoasFuncionais> getFiltroDeFuncionais() {
		return filtroDeFuncionais;
	}

	public void setFiltroDeFuncionais(List<PessoasFuncionais> filtroDeFuncionais) {
		this.filtroDeFuncionais = filtroDeFuncionais;
	}

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public String getCpfServidor() {
		return cpfServidor;
	}

	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}

	public PessoasFuncionais getFuncional() {
		return funcional;
	}

	public void setFuncional(PessoasFuncionais funcional) {
		this.funcional = funcional;
	}
	
	public List<TipoBeneficioEnum> listaTipoBeneficio(){
		return Arrays.asList(TipoBeneficioEnum.values());
	}
	
	public void redirecionaPericia(){
		if(this.cargo.getNUMG_idDoObjeto() > 0){
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getSessionMap().put("funcional", funcional);
		}else{
			Message.addErrorMessage("Órgão e Cargo são obrigatórios!");
		}
	}
	
	HtmlInputText inputEndereco = new HtmlInputText();
	
	public HtmlInputText getInputEndereco() {
		return inputEndereco;
	}

	public void setInputEndereco(HtmlInputText inputEndereco) {
		this.inputEndereco = inputEndereco;
	}
	
	public boolean avaliarPericia(){
		boolean res = false;
		try{
			if(this.funcional.getENUM_tipoAposentadoria() == TipoBeneficioEnum.INVALIDEZ ||
					this.funcional.getENUM_tipoAposentadoria() == TipoBeneficioEnum.PENSAO && 
					new PensaoDao().devolvePensao(this.funcional.getNUMG_idDoObjeto()).getENUM_invalido() == DecisaoEnum.SIM){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Não foi possivel avaliar pericia.");
		}
		return res;
	}

	public String redirecionaBeneficio(){
		
		try {
			if(this.funcional.getNUMR_idDoObjetoCargo().getNUMG_idDoObjeto() > 0){
				FacesContext fc = FacesContext.getCurrentInstance();
				fc.getExternalContext().getSessionMap().put("funcional", this.funcional);
			}else{
				Message.addErrorMessage("Órgão e Cargo são obrigatórios!");
				return "/paginas/cadastro/funcional.xhtml";
			}
			switch (this.funcional.getENUM_tipoAposentadoria()) {
			case INVALIDEZ:
				return "/paginas/beneficio/aposentadoriaInvalidez.xhtml?faces-redirect=true";
			case IDADE:
				return "/paginas/beneficio/aposentadoriaIdade.xhtml?faces-redirect=true";
			case IDADETEMPO:
				return "/paginas/beneficio/aposentadoriaIdadeContribuicao.xhtml?faces-redirect=true";
			case COMPULSORIA:
				return "/paginas/beneficio/aposentadoriaCompulsoria.xhtml?faces-redirect=true";
			case PENSAO:
				return "/paginas/beneficio/pensao.xhtml?faces-redirect=true";
			default:
				return "/paginas/cadastro/funcional.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			System.out.println("Não possivel gerar endereço da pagina: "+e.getMessage());
		}
		return null;
	}
	
	

	public void buscaServidor() {
		try {
			if(!cpfServidor.isEmpty()){
				this.pessoas = new PessoasDao().devolvePessoa(cpfServidor);
			}else{
				Message.addErrorMessage("Cpf Nulo");
			}
			
			if (this.pessoas.getNUMG_idDoObjeto() > 0) {
				this.funcional.setFLAG_abonoPermanencia(DecisaoEnum.NAO);
				this.ativaTabSituacao = true;
				this.enquadramentoSelecionado = new ArrayList<>();
				
			} else {
				Message.addErrorMessage("Servidor não encontrado!");
			}

		} catch (Exception e) {
			Message.addErrorMessage("Não foi possível carregar os dados pessoais!");
		}
		
		try {
			if (new FuncionaisFuncoesDao()
					.verificaExistenciaFuncionalFuncoes(this.pessoas.getNUMG_idDoObjeto()) == true) {
				this.funcionaisFuncoes = devolveSituacaoPrevidenciaria();
			}
			
		} catch (Exception e) {
			System.out.println("Não possui Funcional Função");
		}
	}
	
	public void limpaDadosFuncionaisServidor(){
		novoObjeto();
	}
	
	public void imprimeFichaFuncional() {
		try{
			funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithTitle");
			
			List<Quinquenio> listaQuinquenio = new GenericPersistence<Quinquenio>(Quinquenio.class).listarRelacionamento("Quinquenio", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
			List<Ferias> listaFerias = new GenericPersistence<Ferias>(Ferias.class).listarRelacionamento("Ferias", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
			List<AtosDiversos> listaAtosDiversos = new GenericPersistence<AtosDiversos>(AtosDiversos.class).listarRelacionamento("AtosDiversos", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
			List<AfastamentosLicenca> listaLicenca = new GenericPersistence<AfastamentosLicenca>(AfastamentosLicenca.class).listarRelacionamento("AfastamentosLicenca", "NUMR_idDoObjetoFuncional", funcional.getNUMG_idDoObjeto());
			List<Deducao> listaDeducao = new GenericPersistence<Deducao>(Deducao.class).listarRelacionamento("Deducao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
			List<AfastamentosDisposicao> listaDisposicao = new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).listarRelacionamento("AfastamentosDisposicao", "NUMR_idDoObjetoPessoasFuncionais", funcional.getNUMG_idDoObjeto());
			
			JasperReportBuiderInterface reportQuinquenio = new QuiquenioReport(listaQuinquenio);
			JasperReportBuiderInterface reportFerias = new AnotacoesDeFeriasReport(listaFerias);
			JasperReportBuiderInterface reportAtosDiversos = new RegistroDeAtosDiversosReport(listaAtosDiversos);
			JasperReportBuiderInterface reportLicenca = new LicencasReport(listaLicenca);
			JasperReportBuiderInterface reportDeducao = new DeducoesReport(listaDeducao);
			JasperReportBuiderInterface reportDisposicao = new DisposicaoReport(listaDisposicao);
			List<JasperReportBuiderInterface> lj = new ArrayList<>();
			lj.add(reportQuinquenio);
			lj.add(reportFerias);
			lj.add(reportAtosDiversos);
			lj.add(reportLicenca);
			lj.add(reportDeducao);
			lj.add(reportDisposicao);
			devolveComponentReport(lj);
			ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(reportAtosDiversos)
					.addComponent(reportFerias)
					.addComponent(reportDeducao)
					.addComponent(reportLicenca)
					.addComponent(reportDisposicao)
					.addComponent(reportQuinquenio);
			
			GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		}catch(Exception e){
			Message.addErrorMessage("Ficha Funcional indisponível! erro: "+e.getMessage());
		}
		
	}
	
	private ComponentReportBuilderHelper devolveComponentReport(List<JasperReportBuiderInterface> listaReportBuilderInterface){
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper();
		int count = 0;
		for (JasperReportBuiderInterface j : listaReportBuilderInterface) {
			if(j.dataSource() == null){
				count++;
			}
			crbh.addComponent(j);
		}
		
		return crbh;
	}

	@Override
	public void salvarObjeto() {
		try {
			
			this.funcional.setNUMR_idDoObjetoCargo(this.cargo);
			this.funcional.setREL_enquadramento(this.enquadramento);
			this.funcional.setNUMR_idDoObjetoPessoas(pessoas);
			this.funcional.setNUMR_situacaoFuncional(this.situacaoFuncional);
			this.funcional.setNUMR_situacaoPrevidenciaria(this.situacao);
			this.funcional.setNUMR_vinculoPrevidenciario(this.vinculo);

			if (new PessoasFuncionaisDao().verificaExistenciaFuncional(funcional) == false || this.funcional.getNUMG_idDoObjeto() != 0) {
				new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(funcional);
				novoObjeto();
				Message.addSuccessMessage("Funcional salvo com sucesso!");
			} else {
				Message.addErrorMessage("Funcional já existente!");
			}
			
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Funcional!");
		}
	}

	public void salvaSituacaoPrevidenciaria() {
		try {

			this.funcionaisFuncoes.setNUMR_idPessoas(this.pessoas);
			new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).salvar(funcionaisFuncoes);
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar situação previdenciária");
		}

	}
	
	private FuncionaisFuncoes devolveSituacaoPrevidenciaria() {
		return new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).buscaObjetoRelacionamento("FuncionaisFuncoes", "NUMR_idPessoas", this.pessoas.getNUMG_idDoObjeto());
	}

	@Override
	public void novoObjeto() {
		this.pessoas = new Pessoas();
		this.funcional = new PessoasFuncionais();
		this.funcional.setFLAG_abonoPermanencia(DecisaoEnum.NAO);
		this.enquadramentoSelecionado = new ArrayList<>();
		this.ativaTab = false;
		this.cargo = new Cargos();
		this.orgao = new Orgaos();
		this.vinculo = new VinculoPrevidenciario();
		this.situacao = new SituacaoPrevidenciaria();
		this.situacaoFuncional = new SituacaoFuncional();
	}
	
	private PessoasFuncionais devolveNovoFuncional(int idFuncional){
		return new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(idFuncional);
	}

	@Override
	public List<PessoasFuncionais> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeFuncionais");
	}
	
	public void fechaFuncional(){
		RequestContext.getCurrentInstance().closeDialog("listaDeFuncionais");
	}

	public void carregaSituacaoPrevidenciaria() {
		new DialogsPrime().showDialog(true, true, true, false, "situacaoPrevidenciaria");
	}

	@Override
	public void pegaInstanciaDialogo(PessoasFuncionais obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	private boolean botaoFechaFuncional = false; 
	
	public void actionButtonClose(){
		botaoFechaFuncional = true;
	} 
	
	public void actionButtonEdit(){
		botaoFechaFuncional = false;
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		if(botaoFechaFuncional != true){
			
			try {
				this.funcional = new PessoasFuncionais();
				this.funcional = (PessoasFuncionais) event.getObject();
				this.pessoas = this.funcional.getNUMR_idDoObjetoPessoas();
				this.enquadramento = this.funcional.getREL_enquadramento();
				this.situacao = this.funcional.getNUMR_situacaoPrevidenciaria();
				this.vinculo = this.funcional.getNUMR_vinculoPrevidenciario();
				
				if (this.funcional.getFLAG_abonoPermanencia() == null) {
					this.funcional.setFLAG_abonoPermanencia(DecisaoEnum.NAO);
				}
				
				if (this.funcional.getNUMG_idDoObjeto() > 0) {
					this.ativaTab = true;
				}
				
				if (this.funcional.getNUMR_idDoObjetoCargo().getNUMG_idDoObjeto() > 0) {
					this.cargo = this.funcional.getNUMR_idDoObjetoCargo();
					this.orgao = this.cargo.getNUMR_idDoObjetoOrgaos();
				}
				
				this.situacaoFuncional = this.funcional.getNUMR_situacaoFuncional();
				populaCargoListaDeCargos();
				carregaListaDocumentos();
			} catch (Exception e) {
				Message.addErrorMessage("Erro ao carregar dados funcionais");
			}
			
			List<Averbacao> listaAverbacao = new ArrayList<>();
			try{
				
				listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais",this.funcional.getNUMG_idDoObjeto());
			}catch(Exception e){
				System.out.println("Não foi possível carregar lista de Averbação");
			}
			
			try {
				if (new FuncionaisFuncoesDao()
						.verificaExistenciaFuncionalFuncoes(this.pessoas.getNUMG_idDoObjeto()) == true) {
				
					Artigo03 artigo = new Artigo03();
					if (artigo.verificaRegra(funcional,listaAverbacao) == RegrasAposentadoriaEnum.ARTIGO3
							
							&& this.funcional.getENUM_tipoAposentadoria() == null) {
						RequestContext.getCurrentInstance().execute("PF('dlg').show();");
					}
					
					
				}
				
			} catch (Exception e) {
				System.out.println("Não possui Funcional Função");
			}
			
			try{
				tla = new TempoLiquidoDao().devolveTempoLiquido(funcional.getNUMG_idDoObjeto());
				this.aproveitado = tla.getDiasAproveitado();
			}catch(Exception e){
				System.out.println("Erro ao listar tempo liquido averbado");
			}
			this.deducao.setENUM_compensasaoDeducao(DecisaoEnum.NAO);
			this.funcional.setFLAG_abonoPermanencia(DecisaoEnum.NAO);
		}
		
	}

	/* Lista de Objetos */
	PessoasFuncionaisDao pfDao = new PessoasFuncionaisDao();

	public List<Orgaos> getListaDeOrgaos() {
		List<Orgaos> listaOrgao = pfDao.listaDeOrgaos();
		
		Collections.sort(listaOrgao, new Comparator<Orgaos>() {
			@Override
			public int compare(Orgaos o1, Orgaos o2) {
				return o1.getDESC_nome().compareTo(o2.getDESC_nome());
			}
		});
		return listaOrgao;
	}

	List<Cargos> listaC = new ArrayList<Cargos>();

	public void populaCargoListaDeCargos() {
		listaC = new ArrayList<Cargos>();
		listaC = new GenericPersistence<Cargos>(Cargos.class).listarRelacionamento("Cargos", "NUMR_idDoObjetoOrgaos",
				this.orgao.getNUMG_idDoObjeto());
		
	}
	
	List<Enquadramento> listaE = new ArrayList<Enquadramento>();
	public void populaCargoListaDeEnquadramentos() {
		listaE = new ArrayList<Enquadramento>();
		listaE = new GenericPersistence<Enquadramento>(Enquadramento.class).listarRelacionamento("Enquadramento", "REL_cargos",
				this.cargo.getNUMG_idDoObjeto());
		
	}

	public List<Cargos> getListaDeCargos() {
		return listaC;
	}
	
	public List<Enquadramento> getListaDeEnquadramentos() {
		return listaE;
	}

	public List<ClasseFuncional> getListaClasseFuncional() {
		return pfDao.listaClasseFuncional();
	}

	public List<NivelFuncional> getListaNivelFuncional() {
		return pfDao.listaNivelFuncional();
	}

	public List<SituacaoFuncional> getListaSituacaoFuncional() {
		return new GenericPersistence<SituacaoFuncional>(SituacaoFuncional.class)
				.listarTodos("SituacaoFuncional");
	}

	public List<DecisaoEnum> getPermanencia() {
		return pfDao.listaAbonoDePermanencia();
	}

	public List<PessoasFuncionais> getListaDeFuncionais() {
		try {
			if (this.pessoas.getNUMR_cpf() != null) {
				return dao.listaRelacionamenoDoObjeto("PessoasFuncionais", "NUMR_idDoObjetoPessoas",
						this.pessoas.getNUMG_idDoObjeto());
			}
		} catch (Exception e) {
			Message.addErrorMessage("Selecione um servidor!");
		}
		return null;
	}

	public String redirecionaAposentadoria(String url) {
		try{
			if(this.cargo.getNUMG_idDoObjeto() > 0){
				FacesContext fc = FacesContext.getCurrentInstance();
				fc.getExternalContext().getSessionMap().put("funcional", funcional);
			}else{
				Message.addErrorMessage("Órgão e Cargo são obrigatórios!");
				return "/paginas/cadastro/funcional.xhtml";
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao redirecionar a página");
		}
		return url;
	}

	public String redirecionaCtc(String url) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("funcional", funcional);
		return url;
	}

	public List<VinculoPrevidenciario> getListaDeVinculosPrevidenciarios() {
		return new GenericPersistence<VinculoPrevidenciario>(VinculoPrevidenciario.class)
				.listarTodos("VinculoPrevidenciario");
	}

	public List<SituacaoPrevidenciaria> getListaSituacaoPrevidenciaria() {
		return new GenericPersistence<SituacaoPrevidenciaria>(SituacaoPrevidenciaria.class)
				.listarTodos("SituacaoPrevidenciaria");
	}

	public void pegaItem(SelectEvent event) {
		Enquadramento item = (Enquadramento) event.getObject();
		listaEnquadramento.add(item);
	}

	public void pegaItemRemovido(UnselectEvent event) {
		Enquadramento item = (Enquadramento) event.getObject();
		listaEnquadramento.remove(item);
	}

	/*
	 * Início Remuneração
	 */
	Remuneracoes remuneracao = new Remuneracoes();
	Rubricas rubrica = new Rubricas();
	ContribuicoeseAliquotas contribuicao = new ContribuicoeseAliquotas();
	private List<Remuneracoes> filtroDeRemuneracao;
	private int decimoTerceiro;
	private String competenciaInicial;
	private String competenciaFinal;
	static List<Remuneracoes> listaDefinanceiro = new ArrayList<>();
	private String competenciaPortaria;
	private boolean existePortaria;

	public Remuneracoes getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(Remuneracoes remuneracao) {
		this.remuneracao = remuneracao;
	}

	public Rubricas getRubrica() {
		return rubrica;
	}

	public void setRubrica(Rubricas rubrica) {
		this.rubrica = rubrica;
	}

	public ContribuicoeseAliquotas getContribuicao() {
		return contribuicao;
	}

	public void setContribuicao(ContribuicoeseAliquotas contribuicao) {
		this.contribuicao = contribuicao;
	}

	public List<Remuneracoes> getFiltroDeRemuneracao() {
		return filtroDeRemuneracao;
	}

	public void setFiltroDeRemuneracao(List<Remuneracoes> filtroDeRemuneracao) {
		this.filtroDeRemuneracao = filtroDeRemuneracao;
	}

	public int getDecimoTerceiro() {
		return decimoTerceiro;
	}

	public void setDecimoTerceiro(int decimoTerceiro) {
		this.decimoTerceiro = decimoTerceiro;
	}

	public String getCompetenciaInicial() {
		return competenciaInicial;
	}

	public void setCompetenciaInicial(String competenciaInicial) {
		this.competenciaInicial = competenciaInicial;
	}

	public String getCompetenciaFinal() {
		return competenciaFinal;
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
		return competenciaPortaria;
	}

	public void setCompetenciaPortaria(String competenciaPortaria) {
		this.competenciaPortaria = competenciaPortaria;
	}

	public boolean isExistePortaria() {
		return existePortaria;
	}

	public void setExistePortaria(boolean existePortaria) {
		this.existePortaria = existePortaria;
	}

	public void salvarObjetoRemuneracao() {
		try {
			this.remuneracao.setNUMR_idDoObjetoFuncional(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()) );
			this.remuneracao.setNUMR_rubrica(this.rubrica);
			this.remuneracao.setFLAG_decimoTerceiro(decimoTerceiro);
			new GenericPersistence<Remuneracoes>(Remuneracoes.class).salvar(remuneracao);
			novoObjetoRemuneracao();
			Message.addSuccessMessage("Remuneração salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Remuneração!");
		}
	}

	public void novoObjetoRemuneracao() {
		this.remuneracao = new Remuneracoes();
		this.rubrica = new Rubricas();
		this.contribuicao = new ContribuicoeseAliquotas();
		this.decimoTerceiro = 0;
	}

	public void exibeListaDeObjetosRemuneracao() {
		listaDefinanceiro = new ArrayList<>();
		new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeVerbas", 350, 930);
	}
	
	private static boolean actionButtonVerbas = false;
	
	public void fechaVerbas(){
		actionButtonVerbas = true;
		RequestContext.getCurrentInstance().closeDialog("listaDeVerbas");
	}
	
	public void actionEditVerbas(){
		actionButtonVerbas = false;
	}
	
	public void pegaInstanciaDialogoRemuneracao(Remuneracoes obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	public void selecionaObjetoDialogoRemuneracao(SelectEvent event) {
		if(actionButtonVerbas != true){
			this.remuneracao = (Remuneracoes) event.getObject();
			this.rubrica = this.remuneracao.getNUMR_rubrica();
			
		}
		actionEditVerbas();
	}
	
	@SuppressWarnings("unused")
	private static boolean actionButtonRubricas = false;
	
	public void fechaRubricas(){
		actionButtonRubricas = true;
		devolveListaDeRubricas();
		RequestContext.getCurrentInstance().closeDialog("listaDeRubricas");
	}
	
	public void actionEditRubricas(){
		actionButtonRubricas = false;
	}
	
	public void exibeListaDeObjetosRubricas() {
		new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeRubricas", 295, 930);
	}
	
	public void selecionaObjetoDialogoRubricas(SelectEvent event) {
		
		actionEditRubricas();
	}

	public void fechaContribuicao() {
		RequestContext.getCurrentInstance().closeDialog("contribuicao");
		this.contribuicao = new ContribuicoeseAliquotas();
    }

	public void selecionaObjetoDialogoContribuicao(SelectEvent event) {
		System.out.println("fechou contribuição");
	}
	
	
	private List<Rubricas> devolveListaDeRubricas(){
		return new GenericPersistence<Rubricas>(Rubricas.class).listarTodos("Rubricas");
	}
	
	
	public List<Rubricas> getListaRubricas() {
		return devolveListaDeRubricas();
	}

	public void exibeContribuicoesRemuneracao() {
		try {
			if (ContribuicaoHelper.existeContribuicao(this.funcional.getNUMG_idDoObjeto()) != true) {
				ContribuicaoHelper.criaContribuicao(this.funcional);
			}
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao popular contribuição");
		}
	}

	boolean alteracaoAliquota = false;
	
	public void calculaBaseContribuicao() {
		try{
			this.contribuicao = new QualificaBaseDeCalculo().executa(contribuicao, funcional.getDATA_efetivoExercicio());
		}catch(Exception e){
			System.out.println("Erro ao estabelecer base de contribuição.");
		}
	}
	
	
	public void modificaCalculoContribuicaoSegurado() {
		this.contribuicao.setVALR_contribuicaoPrevidenciaria(this.contribuicao.getVALR_contribSegurado()
				.divide(new BigDecimal(this.contribuicao.getNUMR_aliquotaSegurado()).divide(new BigDecimal(100)),2,RoundingMode.UP)
				.setScale(3, BigDecimal.ROUND_CEILING));
		
	}

	public void modificaCalculoContribuicaoPatronal() {
		
		if(this.contribuicao.getVALR_contribPatronal().compareTo(new BigDecimal(0)) == 0){
			this.contribuicao.setVALR_contribPatronal(this.contribuicao.getVALR_contribuicaoPrevidenciaria()
					.multiply(new BigDecimal(this.contribuicao.getNUMR_aliquotaPatronal()).divide(new BigDecimal(100)))
					.setScale(3, BigDecimal.ROUND_CEILING));
			
		}
	}
	
	public void fechaDialogoContribuicao(CloseEvent event){
		resetaContribuicao();
	}
	
	public void resetaContribuicao(){
		alteracaoAliquota = false;
		this.contribuicao = new ContribuicoeseAliquotas();
	}
	
	public void salvaContribuicoes() {
		try {
			if (new RemuneracaoDao().verificaSeExisteContribuicao(this.contribuicao.getDESC_competencia(),
					this.funcional.getNUMG_idDoObjeto()) == false || this.contribuicao.getNUMG_idDoObjeto() != 0) {

				this.contribuicao.setNUMR_idPessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
				new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class)
						.salvar(this.contribuicao);
				Message.addSuccessMessage(""
						+ "Contribuição salva com sucesso!");

			} else {
				Message.addErrorMessage("Contribuição já existe!");
			}
			novoObjetoContribuicoes();
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar contribuições!");
		}
	}
	
	public void removeContribuicao(){
		try{
			if(this.contribuicao.getNUMG_idDoObjeto() != 0){
				new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).removeObjeto(this.contribuicao);
				Message.addSuccessMessage("Contribuição removida com sucesso!");
			}else{
				Message.addWarningMessage("É necessário carregar uma contribuição!");
			}
			novoObjetoContribuicoes();
		}catch(Exception e){
			Message.addErrorMessage("Erro ao remover contribuições!");
		}
	}

	public void novoObjetoContribuicoes() {
		this.contribuicao = new ContribuicoeseAliquotas();
	}

	public void pegaRemuneracoes() {
		listaDefinanceiro = new ArrayList<>();
		try {
			listaDefinanceiro = new RemuneracaoDao().devolveListaDeRemuneracoesComBetween(competenciaInicial,
					competenciaFinal, funcional.getNUMG_idDoObjeto());
			this.competenciaInicial = new String();
		} catch (Exception e) {
			Message.addErrorMessage("Não foi possível carregar as remunerações");
		}
	}
	
	private String anoFichaFinanceira = new String();

	public String getAnoFichaFinanceira() {
		return anoFichaFinanceira;
	}

	public void setAnoFichaFinanceira(String anoFichaFinanceira) {
		this.anoFichaFinanceira = anoFichaFinanceira;
	}
	
	List<Rubricas> listaResultadoRubricas = new ArrayList<>();
	List<Remuneracoes> listaDeRemuneracoesFichaFinanceira = new ArrayList<>();
	private void devolveListaDeRemuneracoesFichaFinanceira() {
		LinkedHashSet<Rubricas> listaRubricasDistintas = new LinkedHashSet<Rubricas>();
		try {
			this.listaDeRemuneracoesFichaFinanceira = new RemuneracaoDao().devolveListaRemuneracoesContribuicao(this.funcional.getNUMG_idDoObjeto(), this.anoFichaFinanceira);
			
			for (Remuneracoes re : listaDeRemuneracoesFichaFinanceira) {
				listaRubricasDistintas.add(re.getNUMR_rubrica());
			}
		
			listaResultadoRubricas = new ArrayList<Rubricas>(listaRubricasDistintas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void geraFichaFin() {
		
		try {
			devolveListaDeRemuneracoesFichaFinanceira();
			Reports report = new Reports();
			report.createReport(Templates.reportTemplate, "fichafinanceira", new ArrayList<Column>(), fieldsFichaFinanceira(),
					dataSourceFinanceiro(this.funcional, new ArrayList<Column>(), fieldsFichaFinanceira()));
		} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Salve a Certidão de Tempo de Contribuição antes de Imprimir!");
		}
}
	
	public List<Field> fieldsFichaFinanceira() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("matricula", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("dataAdmissao", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("jan", "string"));
		field.add(new Field("fev", "string"));
		field.add(new Field("mar", "string"));
		field.add(new Field("abr", "string"));
		field.add(new Field("mai", "string"));
		field.add(new Field("jun", "string"));
		field.add(new Field("jul", "string"));
		field.add(new Field("ago", "string"));
		field.add(new Field("set", "string"));
		field.add(new Field("out", "string"));
		field.add(new Field("nov", "string"));
		field.add(new Field("dez", "string"));
		field.add(new Field("titulo", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("decimoterceiro", "string"));
		field.add(new Field("total", "string"));
		field.add(new Field("verba", "string"));
		return field;
	}
	
	private JRDataSource dataSourceFinanceiro(PessoasFuncionais obj, List<Column> columns, List<Field> fields) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("matricula", "nome","dataAdmissao","orgao","jan", "fev", 
				"mar", "abr", "mai", "jun","jul", "ago", "set", "out", "nov", "dez", "titulo", "cargo", "decimoterceiro", "total","verba");
		List<FichaFinanceiraDto> listaRrc = devolveFichaFinanceiraDto(listaResultadoRubricas, listaDeRemuneracoesFichaFinanceira);
		
		try {
			listaRrc.forEach(f -> {
				dataSource.add(
						
						obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(), 
						obj.getNUMR_idDoObjetoPessoas().getDESC_nome(), 
						sdf.format(obj.getDATA_efetivoExercicio()),
						obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
						f.getJan(), 
						f.getFev(), 
						f.getMar(), 
						f.getAbr(),
						f.getMai(), 
						f.getJun(), 
						f.getJul(), 
						f.getAgo(), 
						f.getSet(), 
						f.getOut(), 
						f.getNov(), 
						f.getDez(),
						"Ficha Financeira de "+this.anoFichaFinanceira, 
						obj.getNUMR_idDoObjetoCargo().getDESC_nome(),
						f.getDecimo(),
						f.getTotal(),
						f.getVerba());

			});
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("Erro ao gerar RRC datasource.");
		}
		return dataSource;
	}
	
	private List<FichaFinanceiraDto> devolveFichaFinanceiraDto(List<Rubricas> listaDeRubricas,
			List<Remuneracoes> listaFinanceiro) {
		
		List<FichaFinanceiraDto> lista = new ArrayList<>();
		DecimalFormatter df = new DecimalFormatter();
		try {
			listaDeRubricas.forEach(a -> {
				BigDecimal valor = new BigDecimal(0);
				FichaFinanceiraDto fr = new FichaFinanceiraDto();
				fr.setVerba(new StringBuilder().append(a.getNUMR_codigo().replaceAll("\\s", "")).append(" ").append(a.getDESC_descricao()).toString());
				for (Remuneracoes re : listaFinanceiro) {
//				fr = new QualificaFinanceiro().executa(contribuicao, a);
					
					if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 1
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setJan(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 2
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setFev(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 3
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setMar(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 4
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setAbr(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 5
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setMai(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 6
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setJun(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 7
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setJul(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 8
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setAgo(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 9
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setSet(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 10
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setOut(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 11
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setNov(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					} else if (Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 12
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()) {
						fr.setDez(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					}
					
					if(Integer.parseInt(re.getNUMR_competencia().substring(0, 2)) == 12 
							&&re.getNUMR_folha() == 13
							&& re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()){
						fr.setDecimo(df.formatterToCurrencyBrazilianWithoutSymbol(
								re.getVALR_remuneracao()));
					}
					
					if(re.getNUMR_rubrica().getNUMG_idDoObjeto() == a.getNUMG_idDoObjeto()){
						valor  = valor.add(re.getVALR_remuneracao());
					}
				}
				
				fr.setTotal(df.formatterToCurrencyBrazilianWithoutSymbol(valor));
				lista.add(fr);
				valor = new BigDecimal(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao gerar RRC");
		}
		return lista;
	}
	

	public List<Remuneracoes> getListaDeFinanceiro() {
		return listaDefinanceiro;
	}

	public void verificaSeExistePortaria() {
		
	}

	Portaria portaria = new Portaria();
	List<Indice> listaIndice = new ArrayList<>();
	private Date dataSimulacao;
	private boolean tipoCalculo = false;

	public boolean isTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(boolean tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public Date getDataSimulacao() {
		return dataSimulacao;
	}

	public void setDataSimulacao(Date dataSimulacao) {
		this.dataSimulacao = dataSimulacao;
	}

	public void geraMedias() throws IOException {
		try {
			portaria = new PortariaDao().buscaPortariaPorCompetencia(competenciaPortaria);
			listaIndice = new GenericPersistence<Indice>(Indice.class).listarRelacionamento("Indice", "portaria",
					portaria.getNUMG_idDoObjeto());
			
			ReportsConcate rep = new ReportsConcate();
			
			rep.generateReportsWithTwoBuilder(new BuilderReport().createReport(Templates.reportTemplate, "medias", new ArrayList<Column>(), fieldsRemuneracao(),
					dataSourceMedias(funcional, new ArrayList<Column>(), fieldsRemuneracao(), listaIndice)), 
					new BuilderReport().createReport(Templates.reportTemplate, "calculoProventos", new ArrayList<Column>(), fieldsProventos(),
					dataSourceProventos(funcional, new ArrayList<Column>(), fieldsProventos(), listaIndice)));
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
	
	int afastamentoInteresseParticular = 0;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@SuppressWarnings("unused")
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
		
		
		try {
			afastamentoInteresseParticular = 0;
			List<AfastamentosLicenca> al =  new AfastamentoLicencaDao().devolveListaDeAfastamentosInteresseParticular(this.funcional.getNUMG_idDoObjeto());
			al.forEach(a -> {
				afastamentoInteresseParticular += Days.daysBetween(new LocalDate(a.getDATA_inicioLicenca()),new LocalDate( a.getDATA_fimLicenca())).getDays();
			});
			System.out.println("Afastamento: "+afastamentoInteresseParticular);
			
			List<Deducao> de =  new DeducaoDao().devolveFaltas(this.funcional.getNUMG_idDoObjeto());
			de.forEach(d -> {
				afastamentoInteresseParticular += Days.daysBetween(new LocalDate(d.getDATA_inicio()),new LocalDate(d.getDATA_fim())).getDays();
			});
			System.out.println("Faltas: "+afastamentoInteresseParticular);
			
		}catch(Exception e) {
			System.out.println("Erro afastamentos");
		}
		
		@SuppressWarnings("static-access")
		Date dataSimulacaoConcessao = new Date();
		List<Averbacao> listaAverbacao = daoAverbacao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				obj.getNUMG_idDoObjeto());

		RetornaTemposAverbacao rta = new RetornaTemposAverbacao(listaAverbacao);
		
		try{
			
			if(dataSimulacao != null){
				dataSimulacaoConcessao = new LocalDate(dataSimulacao).minusDays(afastamentoInteresseParticular).toDate()  ;
			}
			
		}catch(Exception e){
			System.out.println("Erro ao converter data!");
		}
		int resComparator = 0;
		
		
		valorApurado = (resComparator = mediaAritmetica.compareTo(ultimaRemuneracao)) == -1 ? mediaAritmetica : ultimaRemuneracao;
        int somaDeducoes = new AfastamentoLicencaDao().devolveListaDiasAfastados(this.funcional.getNUMG_idDoObjeto()).stream().mapToInt(Integer::intValue).sum();
		
        if (StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(),"policia")) {
            proporcionalidade = new BigDecimal("100");
            tempoServico = "art. 1º, I LC 144/14";
            tipoProventos = "Provento Apurado pela Integralidade das Médias";
            proventoApurado = valorApurado;
        } else {
            tipoProventos = "Provento Proporcional Apurado";
            int tempo = (RetornaTempos.retornaDiasApartirDeDuasDatas(obj.getDATA_efetivoExercicio(), dataSimulacaoConcessao) + rta.devolveDiasTempoTotalAproveitado()) - somaDeducoes;
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
					sdf.format(dataSimulacao),
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

	public void fechaDialogoMedias(CloseEvent event) {
		this.competenciaPortaria = new String();
		this.dataSimulacao = null;
	}
	

	// Report Contribuições
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
		List<ContribuicaoDto> listaDeContribuicoes = new ContribuicaoHelper().listaDeContribuicoesRemuneracao(funcional);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		DRDataSource dataSource = new DRDataSource("nome", "matricula", "cpf", "orgao", "situacaoPrevidenciaria",
				"situacaoFuncional", "planoPrevidenciario", "competencia", "remuneracaoBeneficio",
				"contribuicaoSegurado", "contribuicaoPatronal", "total");
		String planoPrevidenciario = "";

		try {
			if (funcional.getDATA_efetivoExercicio().compareTo(sdf.parse("31/12/2009")) < 0) {
				planoPrevidenciario = "Conta Financeira";
			} else {
				planoPrevidenciario = "Conta Capitalizada";
			}

		} catch (Exception e) {
			System.out.println("Erro ao converter data plano previdenciario");
		}
		Collections.sort(listaDeContribuicoes, CONTRIBUICAO_ORDER);
		for (ContribuicaoDto c : listaDeContribuicoes) {
			dataSource.add(funcional.getNUMR_idDoObjetoPessoas().getDESC_nome(), funcional.getDESC_matricula(),
					funcional.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					funcional.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					funcional.getNUMR_situacaoPrevidenciaria().getDESC_descricao(),
					funcional.getNUMR_situacaoFuncional().getDESC_nome(), 
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

	static String competencia;

	public void pegaContribuicao(ValueChangeEvent event) {
		competencia = (String) event.getNewValue();
	}

	public void buscaContribuicaoAliquota() {
		try {
			if (funcional.getNUMG_idDoObjeto() != 0 && this.contribuicao.getDESC_competencia() != "") {
				this.contribuicao = new RemuneracaoDao().devolveContribuicaoeAliquota(funcional.getNUMG_idDoObjeto(),
						competencia);
			}
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao buscar contribuição!");
		}
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

	public void limpaDialogoRemuneracao(CloseEvent event) {
		this.contribuicao = new ContribuicoeseAliquotas();
		competencia = new String();
	}
	
	
	/*
	 * Fim Remuneração
	 */

	/*
	 * Início Averbação
	 */
	
	private int idAtoAverbacao;
	

	public int getIdAtoAverbacao() {
		return idAtoAverbacao;
	}

	public void setIdAtoAverbacao(int idAtoAverbacao) {
		this.idAtoAverbacao = idAtoAverbacao;
	}

	public void salvarObjetoAverbacao() {
		try {
			this.averbacao.setREL_atoLegal(new GenericPersistence<AtosLegais>(AtosLegais.class).buscarPorId(idAtoAverbacao));
			this.averbacao.setNUMR_pessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()) );
			this.averbacao.setFLAG_concomitado(false);
			new GenericPersistence<Averbacao>(Averbacao.class).salvar(this.averbacao);
			averbacao = new Averbacao();
			Message.addSuccessMessage("Averbação salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Averbação!");
		}
	}

	public void novoObjetoAverbacao() {
		this.averbacao = new Averbacao();
		this.actionEditAverbacao();
	}

	public void exibeListaDeObjetosAverbacao() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeAverbacoes");
	}

	public void pegaInstanciaDialogoAverbacao(Averbacao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	public void fechaAverbacao(){
		RequestContext.getCurrentInstance().closeDialog("listaDeAverbacoes");
	}
	
	private boolean actionButtonAverbacao = false;
	public void actionCloseAverbacao(){
		actionButtonAverbacao = true;
	}
	
	public void actionEditAverbacao(){
		actionButtonAverbacao = false;
	}
	
	public void selecionaObjetoDialogoAverbacao(SelectEvent event) {
		if(actionButtonAverbacao != true){
			this.averbacao = (Averbacao) event.getObject();
		}
		actionButtonAverbacao = false;
	}

	public List<RegimesPrevidenciarios> getListaRegimesPrevidenciarios() {
		return new GenericPersistence<RegimesPrevidenciarios>(RegimesPrevidenciarios.class)
				.listarTodos("RegimesPrevidenciarios");
	}

	public List<Iniciativa> getListaDeIniciativas() {
		return new GenericPersistence<Iniciativa>(Iniciativa.class).listarTodos("Iniciativa");
	}

	public List<TipoDeducaoEnum> getListaDeDeducaoes() {
		return Arrays.asList(TipoDeducaoEnum.values());
	}

	List<Averbacao> listaAverb = new ArrayList<>();

	private void geraListaAverbacao() {
		listaAverb = daoAverbacao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				funcional.getNUMG_idDoObjeto());
	}

	public List<Averbacao> getListaDeAverbacoes() {
		geraListaAverbacao();
		return listaAverb;
	}

	public List<Orgaos> getListaDeOrgaosPrevidenciarios() {
		return new AverbacaoDao().devolveListaDeOrgaosPrevidenciarios();
	}

	TempoLiquidoAverbacao tla = new TempoLiquidoAverbacao();
	
	public TempoLiquidoAverbacao getTla() {
		return tla;
	}

	public void setTla(TempoLiquidoAverbacao tla) {
		this.tla = tla;
	}
	
	private void verificaSeExisteTempoLiquidoAverbado(){
		try{
			TempoLiquidoAverbacao tl = new TempoLiquidoDao().devolveTempoLiquido(this.funcional.getNUMG_idDoObjeto());
			if(tl != null && aproveitado == 0){
				new GenericPersistence<TempoLiquidoAverbacao>(TempoLiquidoAverbacao.class).removeObjeto(tl);
				tla = new TempoLiquidoAverbacao();
			}
			
		}catch(Exception e){
			System.out.println("Não foi possível remover Tempo liquido aproveitado.");
		}
	}

	public void imprimeAverbacao() throws IOException {
		new AverbacaoDao().limpaAverbacao(this.funcional.getNUMG_idDoObjeto());
			try {
				if (aproveitado > 0) {
					 
					if (listaTempoLiquidoAverbado.isEmpty()
							|| aproveitado != tla.getDiasAproveitado()) {
						
						tla.setDiasAproveitado(aproveitado);
						tla.setTempoAproveitado(RetornaTempos.retornaDiaMesAnoApartirDias(aproveitado)
								.toString());
						tla.setNUMR_pessoaFuncional(funcional);
						new GenericPersistence<TempoLiquidoAverbacao>(TempoLiquidoAverbacao.class).salvar(tla);
					}
						tla = listaTempoLiquidoAverbado.get(0);
				}
				
				verificaSeExisteTempoLiquidoAverbado();
				
			} catch (Exception e) {
				Message.addErrorMessage("Erro ao gravar o Tempo Aproveitado!");
			}

		
		
		List<Averbacao> listaAverbacao = daoAverbacao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				this.funcional.getNUMG_idDoObjeto());
		
			for (Averbacao aver1 : listaAverbacao) {
				
				for (Averbacao aver2 : listaAverbacao) {
					Averbacao a = new GenericPersistence<Averbacao>(Averbacao.class)
							.buscarPorId(aver1.getNUMG_idDoObjeto());
					Averbacao ab = new GenericPersistence<Averbacao>(Averbacao.class)
							.buscarPorId(aver2.getNUMG_idDoObjeto());
					if(verificaTempoFicto(a, ab) != true) {
						
						new QualificaConcomitanciaDuasAverbacoes().executa(a, ab);
						
					}
					
				}
			}
			
			
			listaAverbacao.forEach(a->{

				if(verificaTempoFicto(a) == false) {
					new QualificaConcomitanciaUmaAverbacao()
						.executa(
								new GenericPersistence<Averbacao>(Averbacao.class)
							.buscarPorId(a.getNUMG_idDoObjeto()));
					
				}
			});
			
		Reports report = new Reports();
		report.createReport(Templates.reportTemplate, "averbacao", new ArrayList<Column>(), fieldsAverbacao(),
				dataSourceAverbacao(funcional, new ArrayList<Column>(), fieldsAverbacao(), tla));
	}
	
	private boolean verificaTempoFicto(Averbacao a1, Averbacao a2) {
		boolean res = false;
		if(a1.isFLAG_tempoFicto() != false || a2.isFLAG_tempoFicto() != false) {
			res = true;
		}
		return res;
	}
	
	private boolean verificaTempoFicto(Averbacao a1) {
		return a1.isFLAG_tempoFicto();
		
	}

	private List<Field> fieldsAverbacao() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("cargo", "string"));
		field.add(new Field("certificamos", "string"));
		field.add(new Field("cnpj", "string"));
		field.add(new Field("contribuicaoPrivada", "string"));
		field.add(new Field("contribuicaoPublica", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("dataAdmissaoEDemissao", "string"));
		field.add(new Field("dataNascimento", "string"));
		field.add(new Field("dataPosse", "string"));
		field.add(new Field("empresaOrgao", "string"));
		field.add(new Field("estadoCivil", "string"));
		field.add(new Field("funcao", "string"));
		field.add(new Field("iniciativa", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("numProcesso", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("orgaoPrevidenciario", "string"));
		field.add(new Field("outrasDeducoes", "string"));
		field.add(new Field("periodos", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("tempoAproveitado", "string"));
		field.add(new Field("tempoCargo", "string"));
		field.add(new Field("tempoConcomitante", "string"));
		field.add(new Field("tempoContribuicao", "string"));
		field.add(new Field("certificamos2", "string"));
		field.add(new Field("localData", "string"));
		return field;
	}

	@SuppressWarnings("static-access")
	private JRDataSource dataSourceAverbacao(PessoasFuncionais obj, List<Column> columns, List<Field> fields, TempoLiquidoAverbacao tempoLiquidoAverbacao) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Averbacao> listaAverbacao = daoAverbacao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				obj.getNUMG_idDoObjeto());

		RetornaTemposAverbacao rta = new RetornaTemposAverbacao(listaAverbacao);
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao);
		DRDataSource dataSource = new DRDataSource("cargo", "certificamos", "cnpj", "contribuicaoPrivada",
				"contribuicaoPublica", "cpf", "dataAdmissaoEDemissao", "dataNascimento", "dataPosse", "empresaOrgao",
				"estadoCivil", "funcao", "iniciativa", "matricula", "nome", "numProcesso", "orgao",
				"orgaoPrevidenciario", "outrasDeducoes", "periodos", "sexo", "tempoAproveitado", "tempoCargo",
				"tempoConcomitante", "tempoContribuicao", "certificamos2","localData");
		int qtdAverbacoes = 0;
		String certificamos = new String();
		for (Averbacao averbacao : listaAverbacao) {
			DiaMesAno d1 = rta.devolveDiaMesAno(averbacao.getDATA_admissao(), averbacao.getDATA_demissao());
			qtdAverbacoes++;
			
			if (tempoLiquidoAverbacao.getDiasAproveitado() > 0) {
				certificamos = new StringBuilder().append("A pedido do requente foi aproveitado(s) ")
						.append(tempoLiquidoAverbacao.getDiasAproveitado()).append(" dia(s),")
						.append("correspondente a ").append(tempoLiquidoAverbacao.getTempoAproveitado())
						.append("Conforme Instrução Normativa INSS/PRES N°77, artigo 439 §2° , de 21 de Janeiro de 2015.")
						.toString();
			}else{
				certificamos = new StringBuilder().append("A pedido do requente foi aproveitado(s) ")
						.append(rta.devolveDiasTempoTotalAproveitado()).append(" dia(s),").append("correspondente a ")
						.append(rta.devolveDiaMesAnoTempoTotalAproveitado())
						.append("Conforme Instrução Normativa INSS/PRES N°77, artigo 439 §2° , de 21 de Janeiro de 2015.")
						.toString();
			}

			dataSource.add(obj.getNUMR_idDoObjetoCargo().getDESC_nome(),
					new StringBuilder().append("Certificamos que consta de Tempo de Contribuição averbado, o total de ")
							.append(rta.devolveDiasTempoTotalAproveitado()).append(" dia(s), correspondentes a ")
							.append(rta.devolveDiaMesAnoTempoTotalAproveitado()).toString(),
					averbacao.getNUMR_cnpj(), 
					rta.devolveDiaMesAnoIniciativa(2), 
					rta.devolveDiaMesAnoIniciativa(1),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					new StringBuilder().append(sdf.format(averbacao.getDATA_admissao())).append(" a ")
							.append(sdf.format(averbacao.getDATA_demissao())).toString(),
					sdf.format(obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento()), 
					sdf.format(obj.getDATA_efetivoExercicio()),
					averbacao.getDESC_empregador(),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_estadoCivil().getDESC_nome(), 
					averbacao.getDESC_funcao(),
					averbacao.getNUMR_iniciativa().getDESC_nome(), 
					obj.getDESC_matricula(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_nome(), 
					averbacao.getNUMR_processo(),
					obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					new StringBuilder().append(averbacao.getNUMR_orgaoPrevidenciario().getDESC_sigla()).append(" - ")
							.append(averbacao.getNUMR_orgaoPrevidenciario().getDESC_nome()).toString(),
					tp.retornaAnoMesDiaOutrasDedudoes(),
					new StringBuilder().append(qtdAverbacoes).append(" período(s)").toString(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome(),
					rta.devolveDiaMesAnoTempoAproveitado(averbacao),
					//Tempo Cargo
					devolveTempoLiquidoCargo(),
					rta.devolveDiaMesAnoTotalConcomitancia(), 
					rta.formata(d1),
					certificamos,
					new StringBuilder().append("Porto Velho, ").append(sdf.format(new LocalDate().now().toDate())).toString());
		}
		return dataSource;
	}
	
	int diasAfastamento = 0;
	@SuppressWarnings("static-access")
	private String devolveTempoLiquidoCargo() {
		diasAfastamento = 0;
		List<AfastamentosLicenca> lista = new AfastamentoLicencaDao().devolveListaDeAfastamentosInteresseParticular(this.funcional.getNUMG_idDoObjeto());
		String res = new String();
		if(lista.size() > 0) {
			
			lista.forEach(l->{
				diasAfastamento += Days.daysBetween(new LocalDate(l.getDATA_inicioLicenca()),new LocalDate(l.getDATA_fimLicenca())).getDays();
			});
			res = RetornaTempos.retornaDiaMesAno(this.funcional.getDATA_efetivoExercicio(), new LocalDate().now().minusDays(diasAfastamento)
					.toDate()).toString();
		}else {
			res = RetornaTempos.retornaDiaMesAno(this.funcional.getDATA_efetivoExercicio(), new LocalDate().now().toDate()).toString();
		}
		return res;
	}

	public void exibeImpressaoAverbacao() {
		new DialogsPrime().showDialogWithAndHeight(false, false, false, false, "impressaoAverbacao", 120, 540);
	}

	public void removeAverbacao(Averbacao averb) {
		daoAverbacao.removeObjeto(averb.getNUMG_idDoObjeto());
		geraListaAverbacao();
	}
	
	public AtosLegais getAtoLegalLicencaPremio(){
		return new GenericPersistence<AtosLegais>(AtosLegais.class).buscarPorId(9);
	}
	
	public void fechaImpressaoAverbacao(){
		RequestContext.getCurrentInstance().closeDialog("impressaoAverbacao");
	}

	/*
	 * Fim Averbação
	 */

	/*
	 * Início Disposições
	 */
	AfastamentosDisposicao disposicao = new AfastamentosDisposicao();
	GenericDao<AfastamentosDisposicao> daoDisposicao = new AfastamentoDisposicaoDao();
	private List<AfastamentosDisposicao> filtroDeDisposicoes;

	public AfastamentosDisposicao getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(AfastamentosDisposicao disposicao) {
		this.disposicao = disposicao;
	}

	public List<AfastamentosDisposicao> getFiltroDeDisposicoes() {
		return filtroDeDisposicoes;
	}

	public void setFiltroDeDisposicoes(List<AfastamentosDisposicao> filtroDeDisposicoes) {
		this.filtroDeDisposicoes = filtroDeDisposicoes;
	}

	public void salvarObjetoDisposicao() {
		try {
			this.disposicao.setNUMR_idDoObjetoPessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
			daoDisposicao.salvaObjeto(this.disposicao);
			novoObjetoDisposicao();
			Message.addSuccessMessage("Disposição salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Disposição!");
		}
	}

	public void novoObjetoDisposicao() {
		this.actionEditDisposicoes();
		this.disposicao = new AfastamentosDisposicao();
	}

	public void exibeListaDeObjetosDisposicao() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeDisposicoes");
	}
	
	public void fechaDisposicoes(){
		RequestContext.getCurrentInstance().closeDialog("listaDeDisposicoes");
	}
	
	public void pegaInstanciaDialogoDisposicao(AfastamentosDisposicao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	private boolean actionButtonDisposicoes = false;
	public void actionCloseDisposicoes(){
		actionButtonDisposicoes = true;
	}
	
	public void actionEditDisposicoes(){
		actionButtonDisposicoes = false;
	}

	public void selecionaObjetoDialogoDisposicao(SelectEvent event) {
		if(actionButtonDisposicoes != true){
			this.disposicao = (AfastamentosDisposicao) event.getObject();
		}
		actionButtonDisposicoes = false;
	}

	public List<Orgaos> getListaDeOrgaosDisposicao() {
		return new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
	}

	public List<TipoOnusEnum> getListaDeOnus() {
		return Arrays.asList(TipoOnusEnum.values());
	}

	public List<AfastamentosDisposicao> getListaDeDisposicoes() {
		List<AfastamentosDisposicao> lista = new ArrayList<>();
		lista = daoDisposicao.listaRelacionamenoDoObjeto("AfastamentosDisposicao", "NUMR_idDoObjetoPessoasFuncionais",
				this.funcional.getNUMG_idDoObjeto());

		return lista;
	}

	public void excluirObjetoDisposicao(int id) {
		new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).remover("AfastamentosDisposicao",
				"NUMG_idDoObjeto", id);
	}
	
	public void imprimeDisposicoes() throws IOException{
		funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithoutTitle");
		List<AfastamentosDisposicao> lista = new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).listarRelacionamento("AfastamentosDisposicao", "NUMR_idDoObjetoPessoasFuncionais", funcional.getNUMG_idDoObjeto());
		JasperReportBuiderInterface disposicaoReport = new DisposicaoReport(lista);
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(disposicaoReport);
		GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		
	}

	/*
	 * Fim Disposição
	 */

	/* Inicio Licenças */

	public void salvarObjetoLicenca() {
		try {
			this.licenca.setNUMR_tipoLicenca(this.tipoLicenca);
			this.licenca.setNUMR_idDoObjetoFuncional(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
			daoLicenca.salvaObjeto(this.licenca);
			novoObjetoLicenca();
			Message.addSuccessMessage("Licença salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Licença!");
		}
	}

	public void novoObjetoLicenca() {
		this.licenca = new AfastamentosLicenca();
		this.tipoLicenca = new TipoLicenca();
		this.actionEditLicencas();
	}

	public void exibeListaDeObjetosLicenca() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeLicencas");
	}
	
	public void fechaLicencas(){
		RequestContext.getCurrentInstance().closeDialog("listaDeLicencas");
	}
	
	private boolean actionButtonLicenca = false;
	public void actionCloseLicencas(){
		actionButtonLicenca = true;
	}
	
	public void actionEditLicencas(){
		actionButtonLicenca = false;
	}

	public void pegaInstanciaDialogoLicenca(AfastamentosLicenca obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	public void selecionaObjetoDialogoLicenca(SelectEvent event) {
		if(actionButtonLicenca != true){
			this.licenca = (AfastamentosLicenca) event.getObject();
			this.tipoLicenca = this.licenca.getNUMR_tipoLicenca();
		}
		actionButtonLicenca = false;
	}

	public List<TipoLicenca> getListaTiposDeLicencas() {
		return new GenericPersistence<TipoLicenca>(TipoLicenca.class).listarTodos("TipoLicenca");
	}

	public List<DecisaoEnum> getListaContribuicao() {
		return Arrays.asList(DecisaoEnum.values());
	}

	public List<AfastamentosLicenca> getListaDeLicencas() {

		List<AfastamentosLicenca> lista = new ArrayList<>();

		if (this.funcional.getNUMG_idDoObjeto() != 0) {
			lista = daoLicenca.listaRelacionamenoDoObjeto("AfastamentosLicenca", "NUMR_idDoObjetoFuncional",
					this.funcional.getNUMG_idDoObjeto());
		}
		return lista;
	}

	public void removeLicenca(int idLicenca) {
		new GenericPersistence<AfastamentosLicenca>(AfastamentosLicenca.class).remover("AfastamentosLicenca",
				"NUMG_idDoObjeto", idLicenca);
	}
	
	public void imprimeLicencas() throws IOException{
		funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithoutTitle");
		List<AfastamentosLicenca> lista = new GenericPersistence<AfastamentosLicenca>(AfastamentosLicenca.class).listarRelacionamento("AfastamentosLicenca", "NUMR_idDoObjetoFuncional", funcional.getNUMG_idDoObjeto());
		JasperReportBuiderInterface licencaReport = new LicencasReport(lista);
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(licencaReport);
		GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		
	}
	
	public void calculaDataFimLicenca() {
		try {
			this.licenca.setDATA_fimLicenca(RetornaTempos.retornaDataFutura(this.licenca.getNUMR_qtdDias(), new LocalDate(this.licenca.getDATA_inicioLicenca()).minusDays(1).toDate()));
			
		} catch (Exception e) {
			Message.addWarningMessage("Campo data Inicio e quantidade de dias obrigatório!");
		}
	}

	/* Fim Licenças */

	/* Inicio Deduções */
	public void salvarObjetoDeducao() {
		try {
			deducao.setNUMR_pessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
			new GenericPersistence<Deducao>(Deducao.class).salvar(this.deducao);
			novoObjetoDeducao();
			Message.addSuccessMessage("Dedução salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Dedução!");
		}
	}

	public void novoObjetoDeducao() {
		this.actionEditDeducao();
		this.deducao = new Deducao();
	}

	public void fechaDeducoes(){
		RequestContext.getCurrentInstance().closeDialog("listaDeducoes");
	}
	
	private boolean actionButtonDeducao = false;
	public void actionCloseDeducao(){
		actionButtonDeducao = true;
	}
	
	public void actionEditDeducao(){
		actionButtonDeducao = false;
	}
	
	public void exibeListaDeObjetosDeducao() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeducoes");
	}

	public void pegaInstanciaDialogoDeducao(Deducao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	public void selecionaObjetoDialogoDeducao(SelectEvent event) {
		if(actionButtonDeducao != true){
			this.deducao = (Deducao) event.getObject();
		}
		actionButtonDeducao = false;
	}

	public List<TipoDeducaoEnum> getListaTipoDeDeducoes() {
		List<TipoDeducaoEnum> lista = Arrays.asList(TipoDeducaoEnum.values());
		return lista;
	}
	
	public List<DecisaoEnum> getListaCompensaoDeducao(){
		return Arrays.asList(DecisaoEnum.values());
	}

	public void calculaQtdDiasDeduzidos() {
		try {
			this.deducao.setNUMR_qtdDias(RetornaTempos.retornaDiasApartirDeDuasDatas(this.deducao.getDATA_inicio(),
					this.deducao.getDATA_fim()) + 1);
		} catch (Exception e) {
			Message.addWarningMessage("Campo data obrigatório!");
		}
	}
	
	public void calculaDataFimDeducao() {
		try {
			this.deducao.setDATA_fim(RetornaTempos.retornaDataFutura(this.deducao.getNUMR_qtdDias(), new LocalDate(this.deducao.getDATA_inicio()).minusDays(1).toDate()));
			
		} catch (Exception e) {
			Message.addWarningMessage("Campo data Inicio e quantidade de dias obrigatório!");
		}
	}

	public List<Deducao> getListaDeDeducoes() {
		List<Deducao> lista = new ArrayList<>();

		if (this.funcional.getNUMG_idDoObjeto() != 0) {
			lista = daoDeducao.listaRelacionamenoDoObjeto("Deducao", "NUMR_pessoasFuncionais",
					this.funcional.getNUMG_idDoObjeto());
		}
		return lista;
	}

	public void removerDeducao(int id) {
		daoDeducao.removeObjeto(id);
	}
	
	public void imprimeDeducoes() throws IOException{
		funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithoutTitle");
		List<Deducao> lista = new GenericPersistence<Deducao>(Deducao.class).listarRelacionamento("Deducao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
		JasperReportBuiderInterface deducaoReport = new DeducoesReport(lista);
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(deducaoReport);
		GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		
	}
	/* Fim Deduções */

	public void recarregaPagina() {
		tipoCalculo = false;
		mapa = new HashMap<>();
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("funcionalBean");
		this.funcional = new PessoasFuncionais();
		this.averbacao = new Averbacao();
		this.pessoas = new Pessoas();
		this.cargo = new Cargos();
		this.funcionaisFuncoes = new FuncionaisFuncoes();
		this.situacao = new SituacaoPrevidenciaria();
		this.situacaoFuncional = new SituacaoFuncional();
		this.vinculo = new VinculoPrevidenciario();
		this.orgao = new Orgaos();
		ativaTab = false;
		this.remuneracao = new Remuneracoes();
		this.rubrica = new Rubricas();
		this.contribuicao = new ContribuicoeseAliquotas();
		this.competenciaInicial = new String();
		this.competenciaFinal = new String();
		this.atosDiversos = new AtosDiversos();
		this.ferias = new Ferias();
		this.quiquenio = new Quinquenio();
		this.disposicao = new AfastamentosDisposicao();
		this.licenca = new AfastamentosLicenca();
		this.deducao = new Deducao();
		this.tipoLicenca = new TipoLicenca();
		listaTempoLiquidoAverbado = new ArrayList<>();
		this.actionEditAverbacao();
		this.actionEditDeducao();
		this.actionEditDisposicoes();
		this.actionEditFerias();
		this.actionEditLicencaPremio();
		this.actionEditLicencas();
		this.actionEditRad();
		this.actionButtonEdit();
		this.aproveitado = 0;
		this.tla = new TempoLiquidoAverbacao();
		this.cpfServidor = new String();
		
	}

	public void handleClose(CloseEvent event) {
		Message.addWarningMessage("Fechado");
	}

	/* Atos Diversos */

	public List<TipoAtosDiversosEnum> getListaTipoAtosDiversos() {
		return Arrays.asList(TipoAtosDiversosEnum.values());
	}

	public void salvaAtosDiversos() {
		try {
			this.atosDiversos.setNUMR_pessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
			new GenericPersistence<AtosDiversos>(AtosDiversos.class).salvar(atosDiversos);
			novoAtosDiversos();
			Message.addSuccessMessage("Ato salvo com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Ato.");
		}
	}

	public void novoAtosDiversos() {
		this.atosDiversos = new AtosDiversos();
		this.actionEditRad();
	}

	public void exibeListaDeObjetosAtosDiversos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaAtosDiversos");
	}

	public void pegaInstanciaDialogoAtosDiversos(AtosDiversos obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	public void fechaRad(){
		RequestContext.getCurrentInstance().closeDialog("listaAtosDiversos");
	}
	
	private boolean actionButtonRad = false;
	public void actionCloseRad(){
		actionButtonRad = true;
	}
	
	public void actionEditRad(){
		actionButtonRad = false;
	}

	public void selecionaObjetoDialogoAtosDiversos(SelectEvent event) {
		if(actionButtonRad != true){
			this.atosDiversos = (AtosDiversos) event.getObject();
		}
		actionButtonRad = false;
	}

	List<AtosDiversos> listaAtos = new ArrayList<>();

	public void geraListaAtosDiversos() {
		listaAtos = new GenericPersistence<AtosDiversos>(AtosDiversos.class).listarRelacionamento("AtosDiversos",
				"NUMR_pessoasFuncionais", this.funcional.getNUMG_idDoObjeto());
	}

	public List<AtosDiversos> getListaDeAtosDiversos() {
		return listaAtos;
	}

	public void removeAtoDiversos(int idAto) {
		new GenericPersistence<AtosDiversos>(AtosDiversos.class).remover("AtosDiversos", "NUMG_idDoObjeto", idAto);
		geraListaAtosDiversos();
	}
	
	public void imprimeRad() throws IOException{
		funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithoutTitle");
		List<AtosDiversos> lista = new GenericPersistence<AtosDiversos>(AtosDiversos.class).listarRelacionamento("AtosDiversos", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
	
		JasperReportBuiderInterface atosDiversos = new RegistroDeAtosDiversosReport(lista);
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(atosDiversos);
		GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		
	}

	/* Férias */

	public void salvarFerias() {
		try {
			this.ferias.setNUMR_pessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
			new GenericPersistence<Ferias>(Ferias.class).salvar(ferias);
			novoObjetoFerias();
			Message.addSuccessMessage("Férias salvas com sucesso");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar férias.");
		}
	}

	public void novoObjetoFerias() {
		this.ferias = new Ferias();
		this.actionEditFerias();
	}

	public void exibeListaDeObjetosFerias() {
		new DialogsPrime().showDialog(true, true, true, false, "listaFerias");
	}

	public void pegaInstanciaDialogoFerias(Ferias obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	public void fechaFerias(){
		RequestContext.getCurrentInstance().closeDialog("listaFerias");
	}
	
	private boolean actionButtonFerias = false;
	public void actionCloseFerias(){
		actionButtonFerias = true;
	}
	
	public void actionEditFerias(){
		actionButtonFerias = false;
	}

	public void selecionaObjetoDialogoFerias(SelectEvent event) {
		if(actionButtonFerias != true){
			this.ferias = (Ferias) event.getObject();
		}
		actionButtonFerias = false;
	}

	List<Ferias> listaFerias = new ArrayList<>();

	private void geraListaFerias() {
		listaFerias = new GenericPersistence<Ferias>(Ferias.class).listarRelacionamento("Ferias",
				"NUMR_pessoasFuncionais", this.funcional.getNUMG_idDoObjeto());
	}

	public List<Ferias> getListaDeFerias() {
		geraListaFerias();
		return listaFerias;
	}

	public void removeFerias(int idFerias) {
		new GenericPersistence<Ferias>(Ferias.class).remover("Ferias", "NUMG_idDoObjeto", idFerias);
		geraListaFerias();
	}
	
	public void imprimeFerias() throws IOException{
		funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithoutTitle");
		List<Ferias> lista = new GenericPersistence<Ferias>(Ferias.class).listarRelacionamento("Ferias", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
		JasperReportBuiderInterface reportFerias = new AnotacoesDeFeriasReport(lista);
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(reportFerias);
		GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		
	}

	/* Quiquenio */

	public void salvarQuiquenio() {
		try {
			this.quiquenio.setNUMR_pessoasFuncionais(devolveNovoFuncional(this.funcional.getNUMG_idDoObjeto()));
			new GenericPersistence<Quinquenio>(Quinquenio.class).salvar(this.quiquenio);
			novoObjetoQuinquenio();
			Message.addSuccessMessage("Quiquênio salvo com sucesso");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Quinquênio.");
		}
	}

	public void novoObjetoQuinquenio() {
		this.quiquenio = new Quinquenio();
		this.actionEditLicencaPremio();
	}

	public void exibeListaDeObjetosQuinquenio() {
		new DialogsPrime().showDialog(true, true, true, false, "listaQuinquenio");
	}

	public void pegaInstanciaDialogoQuinquenio(Quinquenio obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	public void fechaLicencaPremio(){
		RequestContext.getCurrentInstance().closeDialog("listaQuinquenio");
	}
	
	private boolean actionButtonLicencaPremio = false;
	public void actionCloseLicencaPremio(){
		actionButtonLicencaPremio = true;
	}
	
	public void actionEditLicencaPremio(){
		actionButtonLicencaPremio = false;
	}

	public void selecionaObjetoDialogoQuinquenio(SelectEvent event) {
		if(actionButtonLicencaPremio != true){
			this.quiquenio = (Quinquenio) event.getObject();
		}
		actionButtonLicencaPremio = false;
	}

	List<Quinquenio> listaQuinquenio = new ArrayList<>();

	public void geraListaQuinquenio() {

		listaQuinquenio = new GenericPersistence<Quinquenio>(Quinquenio.class).listarRelacionamento("Quinquenio",
				"NUMR_pessoasFuncionais", this.funcional.getNUMG_idDoObjeto());
	}

	public List<Quinquenio> getListaDeQuinquenios() {
		return listaQuinquenio;
	}

	public void removeQuinquenio(int idQuinquenio) {
		new GenericPersistence<Quinquenio>(Quinquenio.class).remover("Quinquenio", "NUMG_idDoObjeto", idQuinquenio);
		geraListaFerias();
	}

	List<DecenioEnum> novaListaDecenio = new ArrayList<>();

	public List<DecenioEnum> getListaDecenio() {
		return Arrays.asList(DecenioEnum.values());
	}

	@SuppressWarnings({ "static-access", "unused" })
	private List<DecenioEnum> devolveDecenioDisponivel() {
		List<DecenioEnum> listaDecenio = Arrays.asList(DecenioEnum.values());
		List<DecenioEnum> novaListaDecenio = new ArrayList<>();
		int tamanhoDaLista = 0;
		try {
			tamanhoDaLista = (RetornaTempos.retornaDiasApartirDeDuasDatas(this.funcional.getDATA_efetivoExercicio(),
					new LocalDate().now().toDate()) / 365) / 5;
			for (int i = 0; i < tamanhoDaLista; i++) {
				if (verificaExistenciaDecenio(listaDecenio.get(i)) != true) {
					novaListaDecenio.add(listaDecenio.get(i));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível listar Decenio");
		}
		return novaListaDecenio;
	}

	private boolean verificaExistenciaDecenio(DecenioEnum decenio) {
		boolean res = false;
		List<Quinquenio> lista = new GenericPersistence<Quinquenio>(Quinquenio.class).listarRelacionamento("Quinquenio",
				"NUMR_pessoasFuncionais", this.funcional.getNUMG_idDoObjeto());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getENUM_decenio() == decenio) {
				res = true;
			}
		}
		return res;
	}
	
	public void imprimeQuinquenio() throws IOException{
		funcionalReport = new HeaderTitleFuncionalReport(this.funcional,"templateReportWithoutTitle");
		List<Quinquenio> lista = new GenericPersistence<Quinquenio>(Quinquenio.class).listarRelacionamento("Quinquenio", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
		JasperReportBuiderInterface reportQuinquenio = new QuiquenioReport(lista);
		ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(reportQuinquenio);
		GenerateReportWithSub.create(crbh.listaSubReport(), funcionalReport);
		
	}
	
	/*Documentos*/
	CopyFile copyFile = new CopyFile();
	List<DocumentoFuncional> listaDocumentos = new ArrayList<DocumentoFuncional>();
	
	
	
	public List<DocumentoFuncional> getListaDocumentos() {
		return listaDocumentos;
	}

	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		DocumentoFuncional df = new DocumentoFuncional();
		String caminho = new StringBuilder().append(this.funcional.getNUMR_idDoObjetoPessoas().getNUMR_cpf()).append("\\")
				.append(this.funcional.getDESC_matricula()).append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(this.funcional.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto() > 0){
			copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
			df.setDESC_caminhoRelativo(caminho);
			df.setDESC_nome(event.getFile().getFileName());
			df.setREL_funcional(this.funcional);
			new GenericPersistence<DocumentoFuncional>(DocumentoFuncional.class).salvar(df);
			carregaListaDocumentos();
		}else{
			Message.addErrorMessage("Funcional inexistente");
		}
		
    }
	
	private void carregaListaDocumentos(){
		try{
			listaDocumentos = new PessoasFuncionaisDao().devolveListaDocumentos(this.funcional.getNUMG_idDoObjeto());
		}catch(Exception e){
			
		}
	}
	
	public void removeArquivo(DocumentoFuncional df){
		try{
			new GenericPersistence<DocumentoFuncional>(DocumentoFuncional.class)
			.remover("DocumentoFuncional", "NUMG_idDoObjeto", df.getNUMG_idDoObjeto());
		this.copyFile.removeFile(df.getDESC_caminhoRelativo(), df.getDESC_nome());
			for (int i = 0; i < listaDocumentos.size(); i++) {
				if(listaDocumentos.get(i).getNUMG_idDoObjeto() == df.getNUMG_idDoObjeto()){
					listaDocumentos.remove(i);
				}
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível remover o arquivo.");
		}
	}

}
