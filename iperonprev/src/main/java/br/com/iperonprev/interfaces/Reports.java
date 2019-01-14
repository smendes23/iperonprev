package br.com.iperonprev.interfaces;

import java.io.IOException;
import java.util.List;

import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.jasperreports.engine.JRDataSource;

public interface Reports {
	void createReport(ReportTemplateBuilder reportTemplateBuilder,String templateDesigner,List<Column> listColumns, List<Field> listFields, JRDataSource dataSource) throws IOException;
	JRDataSource createDataSource(List<Object> obj,List<Column> columns);
}
