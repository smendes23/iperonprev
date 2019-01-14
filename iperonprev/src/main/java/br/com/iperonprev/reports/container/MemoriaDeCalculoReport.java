package br.com.iperonprev.reports.container;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.iperonprev.models.ProventosBeneficio;
import br.com.iperonprev.models.TipoMemoriaCalculo;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class MemoriaDeCalculoReport {
	private List<ProventosBeneficio> listaProvento;
	private ReportTemplateBuilder reportTemplateBuilder;
	private String templateDesigner;
	private String subTemplateDesigner;

	public MemoriaDeCalculoReport() {
	}

	public MemoriaDeCalculoReport(List<ProventosBeneficio> listaProvento, ReportTemplateBuilder reportTemplateBuilder,
			String templateDesigner, String subTemplateDesigner) {
		this.listaProvento = listaProvento;
		this.reportTemplateBuilder = reportTemplateBuilder;
		this.templateDesigner = templateDesigner;
		this.subTemplateDesigner = subTemplateDesigner;
		// listaJasper = new ComponentBuilder<?,?>[3];
	}

	private List<Field> fieldsFooter() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("valorAposentadoria", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}
	
	private List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("tituloRelatorio", "string"));
		return field;
	}

	private List<Field> fieldsSub() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("descricao", "string"));
		field.add(new Field("valor", "string"));
		field.add(new Field("titulo", "string"));
		field.add(new Field("total", "string"));
		return field;
	}

	/* Verifica se existe registros duplicados */
	private Set<TipoMemoriaCalculo> findDuplicates(List<TipoMemoriaCalculo> lista) {
		final Set<TipoMemoriaCalculo> retorno = new HashSet<>();
		final Set<TipoMemoriaCalculo> analise = new HashSet<>();

		for (TipoMemoriaCalculo p : lista) {
			if (!analise.add(p)) {
				retorno.add(p);
			}
		}
		return retorno;
	}
	
	private JRDataSource createDataSourceFooter() {
		DRDataSource dataSource = new DRDataSource("valorAposentadoria","observacao");
		dataSource.add(new DecimalFormatter().formatterToCurrencyBrazilian(new BigDecimal("849")),
				"1) Para o cálculo dos proventos com base na média aritmética simples de 80% das maiores contribuições");
		return dataSource;
	}

	private JRDataSource createDataSource(String titulo) {
		DRDataSource dataSource = new DRDataSource("tituloRelatorio");
		dataSource.add(titulo);
		return dataSource;
	}

	private JRDataSource createDataSourceSub(int indice) {
		DRDataSource dataSource = new DRDataSource("descricao", "valor", "titulo", "total");
		BigDecimal bd = new BigDecimal(0);
		/*for (ProventosBeneficio p : listaProvento) {
			if (p.getNUMR_tipoMemoriaCalculo().getNUMG_idDoObjeto() == indice) {
				bd = bd.add(p.getVALR_remuneracao());
				dataSource.add(p.getNUMR_rubricas().getDESC_descricao(),
						new DecimalFormatter().formatterToCurrencyBrazilian(p.getVALR_remuneracao()),
						p.getNUMR_tipoMemoriaCalculo().getDESC_nome(),
						new DecimalFormatter().formatterToCurrencyBrazilian(bd));
			}
		}*/

		return dataSource;
	}

	private ComponentBuilder<?, ?>[] listaSubReport() {
		List<TipoMemoriaCalculo> listaMemoriaAnalise = new ArrayList<TipoMemoriaCalculo>();
		List<TipoMemoriaCalculo> listaMemoriaRetorno = new ArrayList<TipoMemoriaCalculo>();

		listaProvento.forEach(p -> {
//			listaMemoriaAnalise.add(p.getNUMR_tipoMemoriaCalculo());
		});

		listaMemoriaRetorno.addAll(findDuplicates(listaMemoriaAnalise));
		ComponentBuilder<?, ?>[] listaJasper = new ComponentBuilder<?, ?>[listaMemoriaRetorno.size()];
		
		for (int i = 0; i < listaMemoriaRetorno.size(); i++) {
			listaJasper[i] = cmp.subreport(new SubReports().create(fieldsSub(),
					createDataSourceSub(listaMemoriaRetorno.get(i).getNUMG_idDoObjeto()), subTemplateDesigner));
		}
		return listaJasper;
	}

	public JasperReportBuilder reportGenerate() {
		JasperReportBuilder report = new JasperReportBuilder();
		try {
			report = new Reports().giveBackReportWithOneSubReportAndFooterReport(reportTemplateBuilder, templateDesigner, listaSubReport(),
					createDataSource("Relatório de Memoria de Cálculo"), fields(),
					new SubReports().create(fieldsFooter(), createDataSourceFooter(), "memoriaDeCalculo_footer"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return report;
	}

}
