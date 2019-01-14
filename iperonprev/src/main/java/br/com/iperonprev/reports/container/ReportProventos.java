package br.com.iperonprev.reports.container;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.ProventosBeneficio;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class ReportProventos {

	private PessoasFuncionais pf;
	private List<ProventosBeneficio> listaProvento;

	public ReportProventos() {
	}

	public ReportProventos(PessoasFuncionais pf, List<ProventosBeneficio> listaProvento) {
		super();
		this.pf = pf;
		this.listaProvento = listaProvento;
	}

	private List<Field> fieldsReport() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("mat", "string"));
		field.add(new Field("serv", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("tipoProvento", "string"));
		field.add(new Field("tipoAposentadoria", "string"));
		field.add(new Field("enquadramento", "string"));
		field.add(new Field("dataBeneficio", "string"));
		field.add(new Field("proporcionalidade", "string"));
		field.add(new Field("competencia", "string"));
		return field;
	}

	private List<Field> fieldsSub() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("codigo", "string"));
		field.add(new Field("descricao", "string"));
		field.add(new Field("percentual", "string"));
		field.add(new Field("fundamentacao", "string"));
		field.add(new Field("valor", "string"));
		field.add(new Field("total", "string"));
		return field;
	}

	private List<Field> fieldsSub2() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("descricao", "string"));
		return field;
	}

	private JRDataSource dataReport() {
		DRDataSource dataSource = new DRDataSource("mat", "serv", "cpf", "cargo", "orgao", "tituloRelatorio",
				"tipoProvento", "tipoAposentadoria", "enquadramento", "dataBeneficio", "proporcionalidade",
				"competencia");

/*		List<Enquadramento> listaEnq = this.pf.getREL_enquadramento();
		List<Enquadramento> listaEnq = new ArrayList<>();
		StringBuilder sb = new StringBuilder().append(this.pf.getREL_enquadramento());
		for (int i = 0; i < listaEnq.size(); i++) {

			sb.append(listaEnq.get(i).getDESC_descricao());
			if (i < listaEnq.size() - 1) {
				sb.append(" - ");
			}
		}*/

		StringBuilder sb = new StringBuilder().append(this.pf.getREL_enquadramento());
		DateTime dt = new DateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		dataSource
				.add(this.pf.getDESC_matricula(), this.pf.getNUMR_idDoObjetoPessoas().getDESC_nome(),
						this.pf.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
						this.pf.getNUMR_idDoObjetoCargo().getDESC_nome(),
						this.pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
						"Proventos para Aposentadoria - (Servidor Civil)", "Proporcionais sem Paridade", "Compulsória",
						sb.toString(), sdf.format(dt.toDate()),
						new StringBuilder().append("(")
								.append(RetornaTempos.retornaDiasApartirDeDuasDatas(this.pf.getDATA_efetivoExercicio(),
										dt.toDate()))
								.append(" / ").append("10950 = ")
								.append(RetornaTempos.retornaDiasApartirDeDuasDatas(this.pf.getDATA_efetivoExercicio(),
										dt.toDate()) * 100 / 10950)
								.append("%)").toString(),
						new StringBuilder().append(dt.monthOfYear().getAsText()).append("/").append(dt.getYear())
								.toString());

		return dataSource;

	}

	private JRDataSource subreportDataSource() {
		listaProvento = new GenericPersistence<ProventosBeneficio>(ProventosBeneficio.class)
				.listarRelacionamento("ProventosBeneficio", "NUMR_idDoObjetoFuncionais", pf.getNUMG_idDoObjeto());

		DRDataSource dataSource = new DRDataSource("codigo", "descricao", "percentual", "fundamentacao", "valor",
				"total");

		BigDecimal bd = new BigDecimal(0);
		for (ProventosBeneficio p : listaProvento) {
			DecimalFormatter formata = new DecimalFormatter();
			/*if (p.isFLAG_demonstrativo() != false) {
				bd = bd.add(p.getVALR_remuneracao());
				dataSource.add(p.getNUMR_rubricas().getNUMR_codigo(), p.getNUMR_rubricas().getDESC_descricao(),
						p.getVALR_percentual().toString(), p.getNUMR_atosLegais().getDESC_tituloEmenta(),
						formata.formatterToCurrencyBrazilian(p.getVALR_remuneracao()),
						formata.formatterToCurrencyBrazilian(bd));
			}*/
		}
		return dataSource;
	}

	private JRDataSource subreportDataSource2() {

		DRDataSource dataSource = new DRDataSource("descricao");
		int count = 1;
		for (int i = 0; i < listaProvento.size(); i++) {
			/*if (listaProvento.get(i).isFLAG_resumoMemoria() != false) {
				dataSource.add(new StringBuilder().append(count).append(") ")
						.append(listaProvento.get(i).getNUMR_tipoMemoriaCalculo().getDESC_nome()).toString());
				count++;
			}*/
		}

		return dataSource;
	}
	
	private ComponentBuilder<?, ?>[] listaSubReport() {
		ComponentBuilder<?, ?>[] listaJasper = new ComponentBuilder<?, ?>[2];
		listaJasper[0] = cmp.subreport(new SubReports().create(fieldsSub(), subreportDataSource(), "proventos_demonstrativo"));
		listaJasper[1] = cmp.subreport(new SubReports().create(fieldsSub2(), subreportDataSource2(), "proventos_resumo"));
		return listaJasper;
	}

	public JasperReportBuilder reportGenerate() {
		
		JasperReportBuilder report = new JasperReportBuilder();
		try {
			report = new Reports().giveBackReportWithOneSubReport(Templates.reportTemplate, 
					"proventos", listaSubReport(), dataReport(), fieldsReport());
			} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Erro ao imprimir o relatório!");
		}
		return report;
	}
}
