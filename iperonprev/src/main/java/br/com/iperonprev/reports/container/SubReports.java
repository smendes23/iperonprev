package br.com.iperonprev.reports.container;

import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.List;

import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


public class SubReports {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JasperReportBuilder create(List<Field> listaFields,JRDataSource dataSource,String templateDesigner){
		JasperReportBuilder report = report();
		StringBuilder sb = new StringBuilder();
		listaFields.forEach(f->{
			try {
				
				report.addField( f.getField(),(DRIDataType) type.detectType(f.getDataType()));
			} catch (Exception e) {
				System.out.println("Erro de conversão de field"+e.getMessage());
			}
		});
		try{
			report
			.setTemplateDesign(Reports.class.getResourceAsStream(sb.append(templateDesigner).append(".jrxml").toString()))
			.setDataSource(dataSource);
		}catch(DRException d){
			d.printStackTrace();
		}
		
		return report;
	}
	
}
