package br.com.iperonprev.controller.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;

import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.helper.BoletoBuilder;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.FundoPrevidenciario;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Remessa;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.reports.container.BuilderReport;
import br.com.iperonprev.reports.container.ReportsConcate;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@Named
@ViewScoped
public class ArrecadacaoPfBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pessoas pessoas = new Pessoas();
	ContribuicoeseAliquotas contribuicao = new ContribuicoeseAliquotas();
	Remuneracoes remuneracao = new Remuneracoes();
	PessoasFuncionais funcionais = new PessoasFuncionais();
	Remessa remessaSegurado = new Remessa();
	Remessa remessaPatronal = new Remessa();
	private String cpf;
	private String competencia;
	String vencimento = null;
	String anoJuliano = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<PessoasFuncionais> listaFuncionais = new ArrayList<>();
	
	
	public ContribuicoeseAliquotas getContribuicao() {
		return contribuicao;
	}
	public void setContribuicao(ContribuicoeseAliquotas contribuicao) {
		this.contribuicao = contribuicao;
	}
	public Remessa getRemessaSegurado() {
		return remessaSegurado;
	}
	public void setRemessaSegurado(Remessa remessaSegurado) {
		this.remessaSegurado = remessaSegurado;
	}
	public Remessa getRemessaPatronal() {
		return remessaPatronal;
	}
	public void setRemessaPatronal(Remessa remessaPatronal) {
		this.remessaPatronal = remessaPatronal;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public Pessoas getPessoas() {
		return pessoas;
	}
	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}
	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}
	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}
	
	public Remuneracoes getRemuneracao() {
		return remuneracao;
	}
	public void setRemuneracao(Remuneracoes remuneracao) {
		this.remuneracao = remuneracao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
		
	public void buscaServidor(){
		this.pessoas = new PessoasDao().devolvePessoa(cpf);
		listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", this.pessoas.getNUMG_idDoObjeto()); 
		this.cpf = new String();
		this.competencia = new String();
		this.remuneracao = new Remuneracoes();
	}
	
	public List<PessoasFuncionais> getListaDeFuncionais(){
		return listaFuncionais;
	}
	
	public void buscaFuncional(ValueChangeEvent event){
		this.funcionais = (PessoasFuncionais)event.getNewValue();
	}
	
	@SuppressWarnings("static-access")
	public void buscaContribuicao(){
		int ano = Integer.parseInt(competencia.substring(3, 7));
		int mes = Integer.parseInt(competencia.substring(0, 2));
		
				
		vencimento = sdf.format(new LocalDate(ano,mes, 20).toDate());
		

		this.contribuicao = new ContribuicaoDao().devolveContribuicao(competencia,this.funcionais.getNUMG_idDoObjeto());
		if(this.funcionais.getNUMG_idDoObjeto() != 0){
			this.contribuicao.setVALR_multaSegurado(contribuicao.getVALR_contribSegurado().multiply(retornaAliquotaMulta(competencia)).setScale(2, RoundingMode.FLOOR));
			this.contribuicao.setVALR_multaPatronal(contribuicao.getVALR_contribPatronal().multiply(retornaAliquotaMulta(competencia)).setScale(2, RoundingMode.FLOOR));
		}else{
			Message.addErrorMessage("Campo servidor obrigatório!");
		}
		
		try {
			if(new LocalDate().now().toDate().after(sdf.parse(vencimento))){
				DateTime agora = DateTime.now();
				Date vencimentoAtual = new LocalDate(new LocalDate().now().getYear(),new LocalDate().now().getMonthOfYear(),agora.dayOfMonth().getMaximumValue()).toDate();
			vencimento = sdf.format(vencimentoAtual);
			}
		} catch (ParseException e) {
			System.out.println("Erro ao converter data de vencimento atual");
		}
	}
	
	@SuppressWarnings("static-access")
	public BigDecimal retornaAliquotaMulta(String competencia){
		BigDecimal res = new BigDecimal(0);
		
		try {
			if(new LocalDate().now().toDate().after(sdf.parse(this.vencimento))){
				int diasPassados = RetornaTempos.retornaDiasApartirDeDuasDatas(sdf.parse(this.vencimento),new LocalDate().now().toDate());
				if(diasPassados>= 60){
					res = new BigDecimal(0.198);
				}else{
					res = new BigDecimal((diasPassados*0.0033));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res.setScale(6,RoundingMode.CEILING);
	}
	
	@SuppressWarnings({ "static-access", "unused" })
	private List<String> devolveListaAliquotas(List<String> listaVigencia){
		return null;
	}
	
	public void novoObjeto(){
		this.competencia = null;
		this.cpf = null;
		this.contribuicao = new ContribuicoeseAliquotas();
		this.funcionais = new PessoasFuncionais();
		this.pessoas = new Pessoas();
		this.listaFuncionais = new ArrayList<>();
	}
	
	public void geraArrecadacao(){
		try{
			if(salvaArrecadacao() == true){
				ReportsConcate rc = new ReportsConcate();
				rc.generateReportsWithTwoBuilder(new BuilderReport().createReport(
						null, 
						"dareSegurado", 
						new ArrayList<Column>(), 
						fields(),
						dataSourceSegurado(this.funcionais, 
								fields())), 
								 new BuilderReport().createReport(null, 
										 "dareSegurado", 
										 new ArrayList<Column>(), 
										 fields(),
										 dataSourcePatronal(this.funcionais, fields())));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao gerar o DARE!");
		}
	}
	
	FundoPrevidenciario fundoPrevidenciarioSegurado = new FundoPrevidenciario();
	FundoPrevidenciario fundoPrevidenciarioPatronal = new FundoPrevidenciario();
	
	private boolean salvaArrecadacao(){
		
	contribuicao.setDESC_competencia(this.competencia);
		
		boolean res = false;
		try{
			this.contribuicao.setBOL_contribuicaoInvalida(true);
			/*if(this.contribuicao.getENUM_fundoPrevidenciario() == FundoPrevidenciarioEnum.CAPITALIZADO){
				fundoPrevidenciarioSegurado = new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).buscarPorId(9262);
				fundoPrevidenciarioPatronal = new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).buscarPorId(9284);
			}else{
				fundoPrevidenciarioSegurado = new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).buscarPorId(9213);
				fundoPrevidenciarioPatronal = new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).buscarPorId(9235);
			}*/
			new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).salvar(contribuicao);
			
			BoletoBuilder bbSegurado = new BoletoBuilder();
			bbSegurado.tipoContribuinte(2)
			.dataVencimento(vencimento)
				.valorTotal(this.contribuicao.getVALR_contribSegurado().add(this.contribuicao.getVALR_jurosSegurado())
					.add(this.contribuicao.getVALR_multaSegurado()).setScale(2, RoundingMode.FLOOR))
					.codigoDaReceita(new StringBuilder().append(fundoPrevidenciarioSegurado.getNUMG_codigo()).toString())
						.ieCpfCnpj(new StringBuilder().append(this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf()).append("0").toString())
							.geraDataJuliana(sdf.parse(vencimento))
								.geraDigitoAutoConferencia()
									.geraDigitoVerificadorGeral();
			
			this.remessaSegurado.setDESC_codigoBarras(codigoDeBarrasNaoFormatado(bbSegurado.geraCodigoDeBarras()).toString());
			this.remessaSegurado.setDESC_codigoBarrasFormatado(codigoDeBarrasFormatado(bbSegurado.geraCodigoDeBarras()).toString());
			this.remessaSegurado.setContribuicao(this.contribuicao);
			
			BoletoBuilder bbPatronal = new BoletoBuilder();
			bbPatronal.tipoContribuinte(2)
			.dataVencimento(vencimento)
			.valorTotal(this.contribuicao.getVALR_contribPatronal().add(this.contribuicao.getVALR_jurosPatronal())
					.add(this.contribuicao.getVALR_multaPatronal()).setScale(2, RoundingMode.FLOOR)).codigoDaReceita(new StringBuilder().append(fundoPrevidenciarioPatronal.getNUMG_codigo()).toString())
			.codigoDaReceita(new StringBuilder().append(fundoPrevidenciarioPatronal.getNUMG_codigo()).toString())
				.ieCpfCnpj(new StringBuilder().append(this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf()).append("0").toString())
					.geraDataJuliana(sdf.parse(vencimento))
						.geraDigitoAutoConferencia()
							.geraDigitoVerificadorGeral();
			
			this.remessaPatronal.setDESC_codigoBarras(codigoDeBarrasNaoFormatado(bbPatronal.geraCodigoDeBarras()).toString());
			this.remessaPatronal.setDESC_codigoBarrasFormatado(codigoDeBarrasFormatado(bbPatronal.geraCodigoDeBarras()).toString());
			this.remessaPatronal.setContribuicao(this.contribuicao);
			List<Remessa> listaRemessa = new ArrayList<Remessa>();
			listaRemessa.add(remessaSegurado);
			listaRemessa.add(remessaPatronal);
			
			listaRemessa.forEach(r->{
				new GenericPersistence<Remessa>(Remessa.class).salvar(r);
			});
			
			res = true;
		}catch(Exception e){
			System.out.println("Erro ao salvar arrecadação!");
			e.printStackTrace();
//			new GenericPersistence<Financeiro>(Financeiro.class).remover(financeiro.getNUMG_idDoObjeto());
			
		}
		
		return res;
	}
	
	
	private List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("documento", "string"));
		field.add(new Field("ieCpfCnpj", "string"));
		field.add(new Field("compIdentificacao", "string"));
		field.add(new Field("competencia", "string"));
		field.add(new Field("vencimento", "string"));
		field.add(new Field("contribuinte", "string"));
		field.add(new Field("endereco", "string"));
		field.add(new Field("municipio", "string"));
		field.add(new Field("uf", "string"));
		field.add(new Field("cep", "string"));
		field.add(new Field("codigoBarras", "string"));
		field.add(new Field("textoCodigoBarras", "string"));
		field.add(new Field("informacoesComplementares", "string"));
		field.add(new Field("codigoReceita", "string"));
		field.add(new Field("numeroParcela", "string"));
		field.add(new Field("codigoMunicipio", "string"));
		field.add(new Field("valorPrincipal", "string"));
		field.add(new Field("valorMulta", "string"));
		field.add(new Field("valorJuros", "string"));
		field.add(new Field("acrescimos", "string"));
		field.add(new Field("valorTotal", "string"));
		return field;
	}
	
	private JRDataSource dataSourceSegurado(PessoasFuncionais obj, List<Field> fields) throws Exception {
		DRDataSource dataSource = new DRDataSource("documento","ieCpfCnpj","compIdentificacao","competencia","vencimento",
				"contribuinte","endereco","municipio","uf","cep","codigoBarras","textoCodigoBarras","informacoesComplementares",
				"codigoReceita","numeroParcela","codigoMunicipio","valorPrincipal","valorMulta","valorJuros","acrescimos","valorTotal");
		Pessoas pessoa = new PessoasDao().devolvePessoa(this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf());
		 
		dataSource.add("1",
				this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
				"-",
				this.competencia,
				this.vencimento,
				this.funcionais.getNUMR_idDoObjetoPessoas().getDESC_nome(),
				new StringBuilder().append(pessoa.getNUMR_idDoObjetoEndereco().getNUMR_tipoLogradouro().getDESC_nome()).append(": ")
				.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_logradouro())
				.append(", n° ")
				.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_numero()).append(", bairro: ").append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_bairro()).toString(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_idDoObjetoMunicipio().getDESC_nome(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado().getDESC_sigla(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_cep(),
				this.remessaSegurado.getDESC_codigoBarras(),
				this.remessaSegurado.getDESC_codigoBarrasFormatado(),
				"",
				new StringBuilder().append(fundoPrevidenciarioSegurado.getNUMG_codigo()).toString(),
				"1",
				"1",
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(this.contribuicao.getVALR_contribSegurado()),
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(this.contribuicao.getVALR_multaSegurado()),
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(contribuicao.getVALR_jurosSegurado()),
				"-",
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(this.contribuicao.getVALR_contribSegurado().add(this.contribuicao.getVALR_jurosSegurado())
						.add(this.contribuicao.getVALR_multaSegurado())));
		
		return dataSource;
	}
	
	private JRDataSource dataSourcePatronal(PessoasFuncionais obj, List<Field> fields) throws Exception {
		DRDataSource dataSource = new DRDataSource("documento","ieCpfCnpj","compIdentificacao","competencia","vencimento",
				"contribuinte","endereco","municipio","uf","cep","codigoBarras","textoCodigoBarras","informacoesComplementares",
				"codigoReceita","numeroParcela","codigoMunicipio","valorPrincipal","valorMulta","valorJuros","acrescimos","valorTotal");
//		Enderecos enderecos = this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_idDoObjetoEndereco();
		 Pessoas pessoa = new PessoasDao().devolvePessoa(this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf());
		dataSource.add("1",
				this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
				"-",
				this.competencia,
				this.vencimento,
				this.funcionais.getNUMR_idDoObjetoPessoas().getDESC_nome(),
				new StringBuilder()
				.append(pessoa.getNUMR_idDoObjetoEndereco().getNUMR_tipoLogradouro())
				.append(": ")
				.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_logradouro())
				.append(", n° ")
				.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_numero())
				.append(", bairro: ")
				.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_bairro()).toString(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_idDoObjetoMunicipio().getDESC_nome(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado().getDESC_sigla(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_cep(),
				this.remessaPatronal.getDESC_codigoBarras(),
				this.remessaPatronal.getDESC_codigoBarrasFormatado(),
				"",
				new StringBuilder()
				.append(fundoPrevidenciarioPatronal.getNUMG_codigo())
				.toString(),
				"1",
				"1",
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(this.contribuicao.getVALR_contribPatronal())
				,new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(this.contribuicao.getVALR_multaPatronal()),
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(contribuicao.getVALR_jurosPatronal()),
				"-",
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(this.contribuicao.getVALR_contribPatronal().add(this.contribuicao.getVALR_jurosPatronal())
						.add(this.contribuicao.getVALR_multaPatronal())));
		
		return dataSource;
	}
	
	public StringBuilder codigoDeBarrasFormatado( List<Integer> codigoDeBarrasList){
		StringBuilder sb = new StringBuilder();
		int posicao = 0;
		for (int i = 0; i < codigoDeBarrasList.size(); i++) {
			if(i == 11){
				posicao = i;
				sb.append("-").append(codigoDeBarrasList.get(i)).append(" ");
				
			}else if(posicao+12 == i){
				sb.append("-").append(codigoDeBarrasList.get(i)).append(" ");
				posicao = i;
			}else{
				sb.append(codigoDeBarrasList.get(i));
			}
			
		}
		return sb;
	}
	
	public StringBuilder codigoDeBarrasNaoFormatado( List<Integer> codigoDeBarrasList){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < codigoDeBarrasList.size(); i++) {
			sb.append(codigoDeBarrasList.get(i));
		}
		return sb;
	}
	
}
