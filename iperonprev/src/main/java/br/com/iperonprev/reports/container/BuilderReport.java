package br.com.iperonprev.reports.container;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.List;

import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.jasperreports.engine.JRDataSource;


@SuppressWarnings("deprecation")
public class BuilderReport {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized JasperReportBuilder createReport(ReportTemplateBuilder reportTemplateBuilder,String templateDesigner,List<Column> listColumns, List<Field> listFields, JRDataSource dataSource){
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
