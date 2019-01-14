package br.com.iperonprev.controller.censo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;

import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.controller.dto.RelatorioPensaoDto;
import br.com.iperonprev.dao.CensoPrevidenciarioDao;
import br.com.iperonprev.dao.ConvocacaoCensoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@ManagedBean
@SessionScoped
public class RelatorioRecadastramentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String competenciaInicio;
	private Date dataInicio;
	private Date dataFim;
	private int tipoRecadastramento;
	private int tipoBeneficiario;
	DadosCenso censo = new DadosCenso();
	
	
	public DadosCenso getCenso() {
		return censo;
	}
	public void setCenso(DadosCenso censo) {
		this.censo = censo;
	}
	public String getCompetenciaInicio() {
		return competenciaInicio;
	}
	public void setCompetenciaInicio(String competenciaInicio) {
		this.competenciaInicio = competenciaInicio;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public int getTipoRecadastramento() {
		return tipoRecadastramento;
	}
	public void setTipoRecadastramento(int tipoRecadastramento) {
		this.tipoRecadastramento = tipoRecadastramento;
	}
	public int getTipoBeneficiario() {
		return tipoBeneficiario;
	}
	public void setTipoBeneficiario(int tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}
	List<CensoPrevidenciario> listaRecadastramento = new ArrayList<>();
	
	public List<CensoPrevidenciario> getListaRecadastramento() {
		return listaRecadastramento;
	}
	ConvocacaoCensoDao ccd = new ConvocacaoCensoDao();
	List<ConvocacaoCensoDto> listaConvocacao = new ArrayList<>();
	
	CensoPrevidenciarioDao cpd = new CensoPrevidenciarioDao();
	
	String tituloRelatorio = new String();
	
	public String getTituloRelatorio() {
		return tituloRelatorio;
	}
	
	public void gerarRelatorioRecadastramento(){
		tituloRelatorio = new String();
		listaConvocacao = new ArrayList<>();
		try{
			String relatorio = "relatorioRecadastramentoAposentado";
			if(tipoBeneficiario == 4){
				relatorio = "relatorioRecadastramentoPensionista";
			}
			
			listaRecadastramento = cpd.getListaDeRecadastrados(this.dataInicio, this.dataFim, this.tipoBeneficiario,this.tipoRecadastramento);
			Collections.sort(listaRecadastramento, CENSO_ORDER);
			
			if(!listaRecadastramento.isEmpty()){
				Reports report = new Reports();
				report.createReport(Templates.reportTemplate, relatorio, new ArrayList<Column>(), fields(),
						dataSourceRecadastramento(fields(), listaRecadastramento,listaRecadastramento.size()));
				
			}else{
				Message.addErrorMessage("Não existem registros para essa competência");
			}
		}catch(Exception e){
			System.out.println("Erro ao gerar relatório de recadastramento");
		}
	}
	
	public void abreRelatorio(){
		RequestContext.getCurrentInstance().execute("openReport();");
	}
	
	private static Comparator<CensoPrevidenciario> CENSO_ORDER = new Comparator<CensoPrevidenciario>() {

		@Override
		public int compare(CensoPrevidenciario o1, CensoPrevidenciario o2) {
			return o1.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()
			.compareTo(o2.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome());
		}
	};
	
	public void recarregaPagina(){
		this.dataInicio = null;
		this.dataFim = null;
		this.tipoRecadastramento = 0;
		this.tipoBeneficiario = 0;
		this.censo = new DadosCenso();
		
	}
	
	public List<DadosCenso> getListaCenso(){
		return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	}
	
	public void gerarRelatorioNaoRecadastrado(){
		String tituloRelatorio = new StringBuilder().append("RELATÓRIO DE APOSENTADOS NÃO RECADASTRADOS ").append(this.competenciaInicio).toString();
		 
		try{
			
			if(this.tipoBeneficiario == 1){
				List<ConvocacaoCensoDto> listaNaoRecadastrados = cpd.listaServidoresBeneficiariosNaoRecadastrados(this.competenciaInicio.replace("/", ""), this.censo.getNUMG_idDoObjeto());
				
				Reports report = new Reports();
				report.createReport(Templates.reportTemplate, "relatorioAposentadoNaoRecadastrado", new ArrayList<Column>(), fieldsConvocacao(),
						dataSourceConvocacao(fieldsConvocacao(), listaNaoRecadastrados, competenciaInicio, listaNaoRecadastrados.size(), tituloRelatorio));
			} else if(this.tipoBeneficiario == 3){
				tituloRelatorio = new StringBuilder().append("RELATÓRIO DE PENSIONISTAS NÃO RECADASTRADOS ").append(this.competenciaInicio).toString() ;
				List<RelatorioPensaoDto> listaPensionistas = new CensoPrevidenciarioDao().listaPensionistasNaoRecadastrados(this.competenciaInicio.replace("/", ""), this.censo.getNUMG_idDoObjeto());
				Reports report = new Reports();
				report.createReport(Templates.reportTemplate, "relatorioPensionistaNaoRecadastrado", new ArrayList<Column>(), fieldsNaoRecadastrados(),
						dataSourceNaoRecadastrados(listaPensionistas, tituloRelatorio));
			}else{
				Message.addErrorMessage("Não existem registros para essa competência");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao gerar relatório de recadastramento");
		}
	}
	
	private static Comparator<ConvocacaoCensoDto> CONVOCACAO_ORDER = new Comparator<ConvocacaoCensoDto>() {

		@Override
		public int compare(ConvocacaoCensoDto o1, ConvocacaoCensoDto o2) {
			return o1.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()
			.compareTo(o2.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome());
		}
	};
	
	private List<ConvocacaoCensoDto> listaDeConvocacao(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DadosCenso dc = new DadosCenso();
			List<DadosCenso> lista = new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
				
			for (DadosCenso d : lista) {
				try {
					if(d.getDATA_inicio().compareTo(sdf.parse(new StringBuilder().append("01/01/").append(competenciaInicio.substring(3, 7)).toString())) == 0){
						dc = d;
					}
				} catch (ParseException e) {
					System.out.println("Erro ao converter data");
				}
			}
		
		this.listaConvocacao = ccd.devolveListaDeConvocacao(this.competenciaInicio.replace("/", ""), tipoBeneficiario, dc.getNUMG_idDoObjeto()); 

		return listaConvocacao;
	}
	
	
	
	private List<Field> fields() {
		
		List<Field> field = new ArrayList<>();
		
		field.add(new Field("cpf", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("nascimento", "string"));
		field.add(new Field("dataRecadastramento", "string"));
		field.add(new Field("totalRecadastrados", "string"));
		field.add(new Field("titulo", "string"));
		return field;
	}
	
	private JRDataSource dataSourceRecadastramento(List<Field> fields, List<CensoPrevidenciario> listaCenso,int total) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("cpf","nome","matricula","nascimento","dataRecadastramento",
				"totalRecadastrados","titulo");
		this.tituloRelatorio = "Recadastramento de Aposentados - Presencial";
		
		if(tipoRecadastramento == 2){
			this.tituloRelatorio = "Recadastramento de Aposentados - Online";
		}
		
		if(this.tipoBeneficiario == 4){
			this.tituloRelatorio = "Recadastramento de Pensionista - Presencial";
			if(tipoRecadastramento == 2){
				this.tituloRelatorio = "Recadastramento de Pensionista - Online";
			}	
		}
		
		
		listaCenso.forEach(c->{
			dataSource.add(
					c.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					c.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome(),
					c.getNUMR_pessoasFuncionais().getDESC_matricula(),
					sdf.format(c.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento()),
					sdf.format(c.getDATA_recadastramento()),
					new StringBuilder().append(total).toString(),
					this.tituloRelatorio
					);
		});
		
	/*	Collections.sort(listaCenso, (p1, p2) -> p1.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()
				.compareTo(p2.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()));
		int mesI = Integer.parseInt(sdf.format(this.dataInicio).substring(3, 5));
		int mesF = Integer.parseInt(sdf.format(this.dataFim).substring(3, 5));
		res = total;
		
		if(this.tipoBeneficiario == 1 || this.tipoBeneficiario == 3){
			listaCenso.forEach(c->{
				int mesNascimento = Integer.parseInt(sdf.format(c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento()).substring(3, 5));
					if(mesI != mesNascimento && mesF != mesNascimento){
//					res--;	
						dataSource.add(
								c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
								c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome(),
								c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getDESC_matricula(),
								sdf.format(c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento()),
								sdf.format(c.getDATA_recadastramento()),
								tituloRelatorio,
								new StringBuilder().append(total).toString()
								);
					}
					
			});
		}else{
			listaCenso.forEach(c->{
					dataSource.add(
							c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
							c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome(),
							c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getDESC_matricula(),
							sdf.format(c.getNUMR_convocacao().getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento()),
							sdf.format(c.getDATA_recadastramento()),
							tituloRelatorio,
							new StringBuilder().append(total).toString()
							);
					
			});
		}
		*/
		return dataSource;
	}
	
	public void geraRelatorioConvocacao(){
		try{
			String tituloRelatorio = new String();
			tituloRelatorio = "Relatório Convocação de Aposentados "+competenciaInicio;
			if(tipoBeneficiario != 1){
				tituloRelatorio = "Relatório Convocação de Pensionistas "+competenciaInicio;
			}
			Collections.sort(listaDeConvocacao(), CONVOCACAO_ORDER);
			Reports report = new Reports();
			report.createReport(Templates.reportTemplate, "relatorioConvocacao", new ArrayList<Column>(), fieldsConvocacao(),
					dataSourceConvocacao(fieldsConvocacao(), listaDeConvocacao(), competenciaInicio, listaDeConvocacao().size(), tituloRelatorio));
		}catch(Exception e){
			
		}
	}
	
	private List<Field> fieldsConvocacao() {
		
		List<Field> field = new ArrayList<>();
		
		field.add(new Field("cpf", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("competencia", "string"));
		field.add(new Field("total", "string"));
		field.add(new Field("nascimento", "string"));
		return field;
	}
	
	private JRDataSource dataSourceConvocacao(List<Field> fields, List<ConvocacaoCensoDto> lista, String competencia, 
			int total, String tituloRelatorio) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("cpf","nome","matricula","tituloRelatorio","competencia","total","nascimento");
		Collections.sort(lista, (p1, p2) -> p1.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()
				.compareTo(p2.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()));
		lista.forEach(c->{
			dataSource.add(
					c.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					c.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome(),
					c.getNUMR_idPessoasFuncionais().getDESC_matricula(),
					tituloRelatorio,
					competenciaInicio,
					new StringBuilder().append(total).toString(),
					sdf.format(c.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento()));
		});
		
		return dataSource;
	}
	
	private List<Field> fieldsNaoRecadastrados() {
		
		List<Field> field = new ArrayList<>();
		
		field.add(new Field("cpf", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("competencia", "string"));
		field.add(new Field("total", "string"));
		field.add(new Field("nascimento", "string"));
		field.add(new Field("sequencia", "string"));
		field.add(new Field("dataEmissao", "string"));
		return field;
	}
	
	@SuppressWarnings("static-access")
	private JRDataSource dataSourceNaoRecadastrados(List<RelatorioPensaoDto> lista,String tituloRelatorio) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("cpf","nome","matricula","tituloRelatorio","competencia","total","nascimento","sequencia","dataEmissao");
		Collections.sort(lista, (p1, p2) -> p1.getNome()
				.compareTo(p2.getNome()));
		
		lista.forEach(pen->{
			dataSource.add(
					pen.getCpf(),
					pen.getNome(),
					pen.getMatricula(),
					tituloRelatorio,
					this.competenciaInicio,
					new StringBuilder().append(lista.size()).toString(),
					sdf.format(pen.getDataNascimento()),
					new StringBuilder().append(pen.getSequencia()).toString(),
					sdf.format(new LocalDate().now().toDate()));
		});
		
		return dataSource;
	}

}
