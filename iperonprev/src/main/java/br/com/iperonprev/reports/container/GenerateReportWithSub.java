package br.com.iperonprev.reports.container;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

@SuppressWarnings("deprecation")
public class GenerateReportWithSub {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void create(ComponentBuilder<?, ?>[] subReport, JasperReportBuiderInterface jasperInterface) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		OutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
		StringBuilder sb = new StringBuilder();
		JasperReportBuiderInterface jrbi = jasperInterface;
		JasperReportBuilder report = report();

		jrbi.listaFields().forEach(f -> {
			try {
				report.addField(f.getField(), (DRIDataType) type.detectType(f.getDataType()));
			} catch (Exception e) {
				System.out.println("Erro de conversão de field" + e.getMessage());
			}
		});

		try {
			report()
			.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(jrbi.templateDesigner()).append(".jrxml").toString()))
			.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)),
					cmp.pageNumber().setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.RIGHT)))
			.detail(subReport)
			.setDataSource(jrbi.dataSource())
			.toPdf(responseStream);
			
		} catch (DRException e) {
			e.printStackTrace();
		}

		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();
	}
}
