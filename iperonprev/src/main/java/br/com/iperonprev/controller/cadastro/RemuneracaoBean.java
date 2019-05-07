package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PortariaDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Indice;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Portaria;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.services.contribuicao.QualificaCalculoContribuicao;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@Named
@ViewScoped
public class RemuneracaoBean implements GenericBean<Remuneracoes>,Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionalBean fb;
	PessoasFuncionais pf = new PessoasFuncionais();
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
	@PostConstruct
	public void init(){
		this.decimoTerceiro = 0;
	}
		
	
	public boolean isExistePortaria() {
		return existePortaria;
	}


	public void setExistePortaria(boolean existePortaria) {
		this.existePortaria = existePortaria;
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

	public List<Remuneracoes> getFiltroDeRemuneracao() {
		return filtroDeRemuneracao;
	}

	public void setFiltroDeRemuneracao(List<Remuneracoes> filtroDeRemuneracao) {
		this.filtroDeRemuneracao = filtroDeRemuneracao;
	}

	public ContribuicoeseAliquotas getContribuicao() {
		return contribuicao;
	}

	public void setContribuicao(ContribuicoeseAliquotas contribuicao) {
		this.contribuicao = contribuicao;
	}

	public int getDecimoTerceiro() {
		return decimoTerceiro;
	}

	public void setDecimoTerceiro(int decimoTerceiro) {
		this.decimoTerceiro = decimoTerceiro;
	}

	public Rubricas getRubrica() {
		return rubrica;
	}

	public void setRubrica(Rubricas rubrica) {
		this.rubrica = rubrica;
	}

	public FuncionalBean getFb() {
		return fb;
	}

	public void setFb(FuncionalBean fb) {
		this.fb = fb;
	}

	public PessoasFuncionais getPf() {
		return pf;
	}

	public void setPf(PessoasFuncionais pf) {
		this.pf = pf;
	}

	public Remuneracoes getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(Remuneracoes remuneracao) {
		this.remuneracao = remuneracao;
	}

	
	public String getCompetenciaPortaria() {
		return competenciaPortaria;
	}


	public void setCompetenciaPortaria(String competenciaPortaria) {
		this.competenciaPortaria = competenciaPortaria;
	}


	@SuppressWarnings("static-access")
	@Override
	public void salvarObjeto() {
		try{
			this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
			this.remuneracao.setNUMR_idDoObjetoFuncional(pf);
			this.remuneracao.setNUMR_rubrica(rubrica);
			new GenericPersistence<Remuneracoes>(Remuneracoes.class).salvar(remuneracao);
			novoObjeto();
			Message.addSuccessMessage("Remuneração salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Remuneração!");
		}
	}

	@Override
	public void novoObjeto() {
		this.remuneracao = new Remuneracoes();
		this.rubrica = new Rubricas();
		this.contribuicao = new ContribuicoeseAliquotas();
	}

	@Override
	public List<Remuneracoes> listaDeObjetos() {
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		listaDefinanceiro = new ArrayList<>();
		new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeVerbas", 350, 930);
	}

	@Override
	public void pegaInstanciaDialogo(Remuneracoes obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.remuneracao = (Remuneracoes)event.getObject();
		this.rubrica = this.remuneracao.getNUMR_rubrica();
	}
	
	public List<Rubricas> getListaRubricas(){
		return new GenericPersistence<Rubricas>(Rubricas.class).listarTodos("Rubricas");
	}
	
	@SuppressWarnings("static-access")
	public void exibeContribuicoes(){
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		try{
			ContribuicaoHelper.criaContribuicao(this.pf);
		}catch(Exception e){
			System.out.println("Contribuição não existe!");
		}finally {
			RequestContext.getCurrentInstance().execute("PF('contribuicao').show();");
		}
	}
	
		
	@SuppressWarnings("static-access")
	public void calculaContribuicao(){
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		this.contribuicao = new QualificaCalculoContribuicao().executa(this.contribuicao,pf.getDATA_efetivoExercicio(),this.remuneracao.getVALR_remuneracao(),false);
	}
	
	public void modificaCalculoContribuicaoSegurado(){
		this.contribuicao.setVALR_contribSegurado(this.contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(this.contribuicao.getNUMR_aliquotaSegurado()).divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_FLOOR));
	}
	
	public void modificaCalculoContribuicaoPatronal(){
		this.contribuicao.setVALR_contribPatronal(this.contribuicao.getVALR_contribuicaoPrevidenciaria().multiply(new BigDecimal(this.contribuicao.getNUMR_aliquotaPatronal()).divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_FLOOR));
	}
	
	public void salvaContribuicoes(){
		new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).salvar(contribuicao);
		novoObjeto();
	}
	
	@SuppressWarnings("static-access")
	public void pegaRemuneracoes(){
		listaDefinanceiro = new ArrayList<>();
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		
		try{
			if(pf.getNUMG_idDoObjeto() != 0){
				listaDefinanceiro = new RemuneracaoDao().devolveListaDeRemuneracoesComBetween(competenciaInicial,competenciaFinal,this.pf.getNUMG_idDoObjeto());
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível carregar as remunerações");
		}
	}
	
	public List<Remuneracoes> getListaDeFinanceiro(){
		return listaDefinanceiro;
	}
	
	public void verificaSeExistePortaria(){
		if(validaPortariaIndice(pf).getNUMG_idDoObjeto() != 0){
			existePortaria = true;
		}
	}
	
	Portaria portaria = new Portaria();
	List<Indice> listaIndice = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	public void geraMedias() throws IOException{
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		try{
			listaIndice = new GenericPersistence<Indice>(Indice.class)
					.listarRelacionamento("Indice", "portaria",portaria.getNUMG_idDoObjeto());
			if(existePortaria != false){
				portaria = validaPortariaIndice(pf);
			}else{
				portaria = new PortariaDao().buscaPortariaPorCompetencia(competenciaPortaria);
			}
			
			listaIndice = new GenericPersistence<Indice>(Indice.class)
					.listarRelacionamento("Indice", "portaria",portaria.getNUMG_idDoObjeto());
			Reports report = new Reports();
			report.createReport(Templates.reportTemplate, "medias",new ArrayList<Column>(), fields(), 
					dataSourceMedias(this.pf,new ArrayList<Column>(),fields(),listaIndice));
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Não foi possível gerar médias.");
		}
	}
	
	private List<Field> fields(){
		List<Field> field = new ArrayList<>();
		field.add(new Field("mat","string"));
		field.add(new Field("serv","string"));
		field.add(new Field("cpf","string"));
		field.add(new Field("sexo","string"));
		field.add(new Field("dataNascimento","date"));
		field.add(new Field("estadoCivil","string"));
		field.add(new Field("cargo","string"));
		field.add(new Field("dataAdmissao","date"));
		field.add(new Field("orgao","string"));
		field.add(new Field("tituloRelatorio","string"));
		field.add(new Field("fatores","string"));
		field.add(new Field("competencia","string"));
		field.add(new Field("base","string"));
		field.add(new Field("indice","string"));
		field.add(new Field("remuneracao","string"));
		field.add(new Field("oitenta","string"));
		return field;
	}
	
	private Portaria validaPortariaIndice(PessoasFuncionais obj){
		Portaria port = new Portaria();
		/*switch (obj.getENUM_tipoAposentadoria().getNome()) {
		case "Compulsoria":
			port = new GenericPersistence<AposentadoriaCompulsoria>(AposentadoriaCompulsoria.class)
						.buscaObjetoRelacionamento("AposentadoriaCompulsoria", "NUMR_idDoObejtoPessoasFuncionais", obj.getNUMG_idDoObjeto()).getNUMR_idDoObjetoPortaria();
			break;
		case "Idade":
			port = new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class)
					.buscaObjetoRelacionamento("AposentadoriaIdade", "NUMR_idDoObejtoPessoasFuncionais", obj.getNUMG_idDoObjeto()).getNUMR_idDoObjetoPortaria();
			break;
		case "Invalidez":
			port = new GenericPersistence<AposentadoriaPorInvalidez>(AposentadoriaPorInvalidez.class)
					.buscaObjetoRelacionamento("AposentadoriaPorInvalidez", "NUMR_idDoObejtoPessoasFuncionais", obj.getNUMG_idDoObjeto()).getNUMR_idDoObjetoPortaria();
			break;
		case "Idade e Tempo de Contribuição":
			port = new GenericPersistence<AposentadoriaIdadeTempo>(AposentadoriaIdadeTempo.class)
				.buscaObjetoRelacionamento("AposentadoriaIdadeTempo", "NUMR_idDoObejtoPessoasFuncionais", obj.getNUMG_idDoObjeto()).getNUMR_idDoObjetoPortaria();
			break;
		default:
			break;
		}*/
		return port;
	}
	static int count = 1;
	private JRDataSource dataSourceMedias(PessoasFuncionais obj,List<Column> columns,List<Field> fields,List<Indice> listaDeIndice) {
		DecimalFormatter df = new DecimalFormatter();
		
		DRDataSource dataSource = new DRDataSource("mat",
				"serv",
				"cpf",
				"sexo",
				"dataNascimento",
				"estadoCivil",
				"cargo",
				"dataAdmissao",
				"orgao",
				"tituloRelatorio",
				"fatores",
				"competencia",
				"base",
				"indice",
				"remuneracao",
				"oitenta");
		List<ContribuicoeseAliquotas> listaDefinanceiro = devolveContribuicoes(listaIndice, new RemuneracaoDao().devolveContribuicoesComPortaria(portaria.getNUMG_idDoObjeto(),obj.getNUMG_idDoObjeto())) ;
		
		Collections.sort(listaDefinanceiro,CONTRIBUICAO_ORDER);
		
		List<BigDecimal> listaOitenta = devolveOitentaMaiores(listaIndice, listaDefinanceiro);
		
		Collections.sort(listaOitenta, Collections.reverseOrder());
		
		int tamanhoOitentaMaiores = listaOitenta.size() * 80/100;
		
		for (int i = 0; i < listaDefinanceiro.size(); i++) {
			if(i < tamanhoOitentaMaiores && !listaOitenta.get(i).equals(null)){
				listaDefinanceiro.get(i).setVALR_oitentaMaiores(listaOitenta.get(i));
			}else{
				listaDefinanceiro.get(i).setVALR_oitentaMaiores(new BigDecimal("0"));
			}
		}
		
		listaDeIndice.forEach(i->{
			listaDefinanceiro.forEach(f->{
				if(f.getDESC_competencia().equals(i.getNUMR_mesAno())){
					
					dataSource.add(obj.getDESC_matricula(),
							obj.getNUMR_idDoObjetoPessoas().getDESC_nome(),
							obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
							obj.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome().toString(),
							obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento(),
							obj.getNUMR_idDoObjetoPessoas().getNUMR_estadoCivil().getDESC_nome(),
							obj.getNUMR_idDoObjetoCargo().getDESC_nome(),
							obj.getDATA_efetivoExercicio(),
							obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
							"Relatório de Médias",portaria.getDESC_descricao(),
							f.getDESC_competencia(),
							df.formatterToCurrencyBrazilian(f.getVALR_contribuicaoPrevidenciaria()),
							df.formatterToFraction(i.getVALR_indice()),
							df.formatterToCurrencyBrazilian(f.getVALR_contribuicaoPrevidenciaria().multiply(i.getVALR_indice())),
							df.formatterToCurrencyBrazilian(f.getVALR_oitentaMaiores()));
				}
			});
		});
		
		return dataSource;
	}
	
	private List<BigDecimal> devolveOitentaMaiores(List<Indice> listaI, List<ContribuicoeseAliquotas> listaC){
		List<BigDecimal> lista = new ArrayList<>();
		listaC.forEach(i->{
			listaI.forEach(f->{
				if(i.getDESC_competencia().equals(f.getNUMR_mesAno())){
					lista.add(i.getVALR_contribuicaoPrevidenciaria().multiply(f.getVALR_indice()));
				}
			});
		});
		return lista;
	}
	
	public List<ContribuicoeseAliquotas> devolveContribuicoes(List<Indice> listaI, List<ContribuicoeseAliquotas> listaC){
		List<ContribuicoeseAliquotas> lista = new ArrayList<>();
		listaC.forEach(i->{
			listaI.forEach(f->{
				if(i.getDESC_competencia().equals(f.getNUMR_mesAno())){
					i.setVALR_remuneracaoMedia(i.getVALR_contribuicaoPrevidenciaria().multiply(f.getVALR_indice()));
					lista.add(i);
				}
			});
		});
		return lista;
	} 
	
		
	//Report Financeiro
	private List<Field> fieldsFinanceiro(){
		List<Field> field = new ArrayList<>();
		field.add(new Field("nome","string"));
		field.add(new Field("matricula","string"));
		field.add(new Field("cpf","string"));
		field.add(new Field("orgao","string"));
		field.add(new Field("situacaoPrevidenciaria","string"));
		field.add(new Field("situacaoFuncional","string"));
		field.add(new Field("planoPrevidenciario","string"));
		field.add(new Field("competencia","string"));
		field.add(new Field("remuneracaoBeneficio","string"));
		field.add(new Field("contribuicaoSegurado","string"));
		field.add(new Field("contribuicaoPatronal","string"));
		field.add(new Field("devolucao","string"));
		return field;
	}
	
	@SuppressWarnings("static-access")
	private JRDataSource dataSourceFichaFinanceira() {
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		List<ContribuicoeseAliquotas> listaDeContribuicoes = new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).listarRelacionamento("ContribuicoeseAliquotas","NUMR_idPessoasFuncionais", pf.getNUMG_idDoObjeto());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
//		FuncionaisFuncoes funcoes = new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).buscaObjetoRelacionamento("FuncionaisFuncoes", "NUMR_idPessoas", pf.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
		DRDataSource dataSource = new DRDataSource("nome","matricula","cpf","orgao","situacaoPrevidenciaria","situacaoFuncional",
				"planoPrevidenciario","competencia","remuneracaoBeneficio","contribuicaoSegurado","contribuicaoPatronal","devolucao");
		String planoPrevidenciario = "";
		
		try{
			if(pf.getDATA_efetivoExercicio().before(sdf.parse("31/12/2009"))){
				planoPrevidenciario = "Conta Financeira";
			}else{
				planoPrevidenciario = "Conta Capitalizada";
			}
			
		}catch(Exception e){
			System.out.println("Erro ao converter data plano previdenciario");
		}
		Collections.sort(listaDeContribuicoes,CONTRIBUICAO_ORDER);
		for (ContribuicoeseAliquotas c : listaDeContribuicoes) {
			dataSource.add(
					pf.getNUMR_idDoObjetoPessoas().getDESC_nome(),
					pf.getDESC_matricula(),
					pf.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					pf.getNUMR_situacaoPrevidenciaria().getDESC_descricao(),
					pf.getNUMR_situacaoFuncional().getDESC_nome(),
					planoPrevidenciario,
					new StringBuilder().append(c.getDESC_competencia().substring(0, 2)).append("/").append(c.getDESC_competencia().substring(2, 6)).toString(),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribuicaoPrevidenciaria()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribSegurado()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribPatronal()),
					new DecimalFormatter().formatterToCurrencyBrazilian(new BigDecimal("0")));
		}
		return dataSource;
	}
	
	public void geraFichaFinanceira(){
		Reports report = new Reports();
		try{
			report.createReport(Templates.reportTemplate, "fichaFinanceira", new ArrayList<>(), fieldsFinanceiro(), dataSourceFichaFinanceira());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Não foi possível imprimir o relatório de ficha financeira");
		}
	}
	static String competencia;
	
	public void pegaContribuicao(ValueChangeEvent event){
		competencia = (String)event.getNewValue();
	}
	
	@SuppressWarnings("static-access")
	public void buscaContribuicaoAliquota(){
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		try{
			if(pf.getNUMG_idDoObjeto() != 0 && this.contribuicao.getDESC_competencia() != ""){
				this.contribuicao = new RemuneracaoDao().devolveContribuicaoeAliquota(pf.getNUMG_idDoObjeto(), competencia);
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao buscar contribuição!");
		}
	}
	
	private static Comparator<ContribuicoeseAliquotas> CONTRIBUICAO_ORDER = new Comparator<ContribuicoeseAliquotas>() {

		@Override
		public int compare(ContribuicoeseAliquotas o1, ContribuicoeseAliquotas o2) {
			Integer valor1 = Integer.parseInt(new StringBuilder().append(o1.getDESC_competencia().substring(2,6)).append(o1.getDESC_competencia().substring(0,2)).toString());
			Integer valor2 = Integer.parseInt(new StringBuilder().append(o2.getDESC_competencia().substring(2,6)).append(o2.getDESC_competencia().substring(0,2)).toString());
			return valor1.compareTo(valor2);
		}
	};
	
	public void limpaDialogo(CloseEvent event){
		this.contribuicao = new ContribuicoeseAliquotas();
		competencia = new String();
	}
	
	}
