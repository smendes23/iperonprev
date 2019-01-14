package br.com.iperonprev.reports.container;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.jasperreports.engine.JRDataSource;

@SuppressWarnings({ "deprecation", "unused" })
public class Reports implements br.com.iperonprev.interfaces.Reports{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void createReport(ReportTemplateBuilder reportTemplateBuilder,String templateDesigner,List<Column> listColumns, List<Field> listFields, JRDataSource dataSource) throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		OutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
//		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		
		StringBuilder sb = new StringBuilder();
		JasperReportBuilder report = report();
		listFields.forEach(f->{
			try {
				
				report.addField( f.getField(),(DRIDataType) type.detectType(f.getDataType()));
			} catch (Exception e) {
				System.out.println("Erro de conversão de field"+e.getMessage());
			}
		});

		if(!listColumns.isEmpty()){
			listColumns.forEach(c->{
				try {
					report.addColumn(col.column(c.getTitle(),c.getField(),(DRIDataType) type.detectType(c.getDataType())));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}		
	
		try {
			if(reportTemplateBuilder != null){
				report
				.setTemplate(reportTemplateBuilder)
				.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
				.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)),
						cmp.pageNumber().setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.RIGHT)))
				
				.setDataSource(dataSource)
//				.toHtml(responseStream);
				.toPdf(responseStream);
			}else{
				report
				.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
				.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)),cmp.pageXofY().setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.RIGHT)))
				.setDataSource(dataSource)
//				.toHtml(responseStream);
				.toPdf(responseStream);

			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();
	}

	@Override
	public JRDataSource createDataSource(List<Object> obj,List<Column> columns) {
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JasperReportBuilder giveBackReportWithOneSubReportAndFooterReport(ReportTemplateBuilder reportTemplateBuilder, String templateDesigner, ComponentBuilder<?, ?>[] listaJasper,JRDataSource dataSource, List<Field> listFields,JasperReportBuilder jrbFooter) throws IOException{
		StringBuilder sb = new StringBuilder();
		JasperReportBuilder report = report();
		listFields.forEach(f->{
			try {
				
				report.addField( f.getField(),(DRIDataType) type.detectType(f.getDataType()));
			} catch (Exception e) {
				System.out.println("Erro de conversão de field"+e.getMessage());
			}
		});
		
		try {
			
			if(reportTemplateBuilder != null){
				report
				.detail(listaJasper)
				.setTemplate(reportTemplateBuilder)
				.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
				.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)))
				.addDetailFooter(cmp.subreport(jrbFooter))
				.setDataSource(dataSource);
			}else{
				report
				.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
				.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)))
				.addDetailFooter(cmp.subreport(jrbFooter))
				.setDataSource(dataSource);

			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JasperReportBuilder giveBackReportWithOneSubReport(ReportTemplateBuilder reportTemplateBuilder, String templateDesigner, ComponentBuilder<?, ?>[] listaJasper,JRDataSource dataSource, List<Field> listFields) throws IOException{
		JasperReportBuilder report = report();
		StringBuilder sb = new StringBuilder();
		listFields.forEach(f->{
			try {
				
				report.addField( f.getField(),(DRIDataType) type.detectType(f.getDataType()));
			} catch (Exception e) {
				System.out.println("Erro de conversão de field"+e.getMessage());
			}
		});
		
		try {
			
			if(reportTemplateBuilder != null){
				report
				.detail(listaJasper)
				.setTemplate(reportTemplateBuilder)
				.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
				.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)))
				.setDataSource(dataSource);
			}else{
				report
				.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
				.addPageFooter(cmp.text("Desenvolvido pela coordenadoria de sistemas do Iperon").setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER)))
				.setDataSource(dataSource);

			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return report;
	}
	
}
