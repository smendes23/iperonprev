package br.com.iperonprev.reports.container;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;

import br.com.iperonprev.interfaces.Reports;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class SisobiReport implements Reports{

	@Override
	public void createReport(ReportTemplateBuilder reportTemplateBuilder,String templateDesigner,List<Column> listColumns, List<Field> listFields, JRDataSource dataSource) throws IOException {
				
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		OutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
		
		FieldBuilder<String> mat = field("mat", type.stringType());
		FieldBuilder<String> tituloRelatorio = field("tituloRelatorio",  type.stringType());
		FieldBuilder<String> serv = field("serv", type.stringType());
		FieldBuilder<String> cpf = field("cpf", type.stringType());
		FieldBuilder<String> sexo = field("sexo", type.stringType());
		FieldBuilder<Date> nasc = field("dataNascimento", type.dateType());
		FieldBuilder<String> estadoCivil = field("estadoCivil", type.stringType());
		FieldBuilder<String> cargo = field("cargo", type.stringType());
		FieldBuilder<Date> dataAdmissao = field("dataAdmissao", type.dateType());
		FieldBuilder<String> orgao = field("orgao", type.stringType());
		FieldBuilder<String> item = field("item", type.stringType());
		FieldBuilder<Integer> quantity = field("quantity", type.integerType());
		FieldBuilder<BigDecimal> unitprice = field("unitprice", type.bigDecimalType());
		
		try {
			
				report()
					.fields(mat,serv,item,quantity,unitprice,cpf,sexo,nasc,estadoCivil,cargo,dataAdmissao,orgao,tituloRelatorio)
					.setTemplate(Templates.reportTemplate)
					.setTemplateDesign(SisobiReport.class.getResourceAsStream("templateIperon.jrxml"))
					.columns(
							col.column("Item", item),
							col.column("Quantity", quantity),
							col.column("Unit price", unitprice))
					.setDataSource(createDataSource(new ArrayList<>(),new ArrayList<>()))
					.toPdf(responseStream);
		} catch (DRException e) {
			e.printStackTrace();
		}
		responseStream.flush();

		responseStream.close();

		context.renderResponse();

		context.responseComplete();
	}

	@SuppressWarnings("static-access")
	@Override
	public JRDataSource createDataSource(List<Object> obj,List<Column> columns) {
		LocalDate data = new LocalDate();
		DRDataSource dataSource = new DRDataSource("tituloRelatorio","orgao","dataAdmissao","cargo","estadoCivil","dataNascimento","mat","serv","cpf","sexo","item", "quantity", "unitprice");
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","Notebook", 1, new BigDecimal(500));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","DVD", 5, new BigDecimal(30));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","DVD", 1, new BigDecimal(28));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","DVD", 5, new BigDecimal(32));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","Book", 3, new BigDecimal(11));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","Book", 1, new BigDecimal(15));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","Book", 5, new BigDecimal(10));
		dataSource.add("Relatório de Médias","Secretaria de Educação do Estado de Rondônia",data.now().toDate(),"Técnico Educacional N1","casado",data.now().toDate() ,"300021070","Maria Auxiliadora Conrado do Nascimento","421.371.872-72","F","Book", 8, new BigDecimal(9));
		return dataSource;
	}
	

}
