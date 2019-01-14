package br.com.iperonprev.reports.container;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class FichaFinanceiraReport {

	private PessoasFuncionais funcionais;
	private List<ContribuicoeseAliquotas> listaDeContribuicoes;
	
	public FichaFinanceiraReport(){}
	public FichaFinanceiraReport(PessoasFuncionais funcionais, List<ContribuicoeseAliquotas> listaDeContribuicoes) {
		this.funcionais = funcionais;
		this.listaDeContribuicoes = listaDeContribuicoes;
	}

	private List<Field> fieldsFinanceiro(){
		List<Field> field = new ArrayList<>();
		field.add(new Field("nome","string"));
		field.add(new Field("matricula","string"));
		field.add(new Field("cpf","string"));
		field.add(new Field("orgao","string"));
		field.add(new Field("situacaoPrevidenciaria","string"));
		field.add(new Field("vinculoPrevidenciario","string"));
		field.add(new Field("planoPrevidenciario","string"));
		field.add(new Field("competencia","string"));
		field.add(new Field("remuneracaoBeneficio","string"));
		field.add(new Field("contribuicaoSegurado","string"));
		field.add(new Field("contribuicaoPatronal","string"));
		field.add(new Field("devolucao","string"));
		return field;
	}
	
	private JRDataSource dataSourceFichaFinanceira() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		DRDataSource dataSource = new DRDataSource("nome","matricula","cpf","orgao","situacaoPrevidenciaria","vinculoPrevidenciario",
				"planoPrevidenciario","competencia","remuneracaoBeneficio","contribuicaoSegurado","contribuicaoPatronal","devolucao");
		String planoPrevidenciario = "";
		
		try{
			if(funcionais.getDATA_efetivoExercicio().before(sdf.parse("31/12/2009"))){
				planoPrevidenciario = "Conta Financeira";
			}else{
				planoPrevidenciario = "Conta Capitalizada";
			}
			
		}catch(Exception e){
			
		}
		for (ContribuicoeseAliquotas c : listaDeContribuicoes) {
			dataSource.add(
					funcionais.getNUMR_idDoObjetoPessoas().getDESC_nome(),
					funcionais.getDESC_matricula(),
					funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					funcionais.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					funcionais.getNUMR_situacaoPrevidenciaria().getDESC_descricao(),
					funcionais.getNUMR_vinculoPrevidenciario().getDESC_descricao(),
					planoPrevidenciario,
					new StringBuilder().append(c.getDESC_competencia().substring(0, 2)).append("/").append(c.getDESC_competencia().substring(2, 6)).toString(),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribuicaoPrevidenciaria()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribSegurado()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribPatronal()),
					new DecimalFormatter().formatterToCurrencyBrazilian(c.getVALR_contribSegurado())
					);
		}
		return dataSource;
	}
	
	public void createReport(){
		Reports report = new Reports();
		try{
			report.createReport(Templates.reportTemplate, "fichaFinanceira", new ArrayList<>(), fieldsFinanceiro(), dataSourceFichaFinanceira());
		}catch(Exception e){
			System.out.println("Não foi possível imprimir o relatório de ficha financeira");
		}
	}
}
