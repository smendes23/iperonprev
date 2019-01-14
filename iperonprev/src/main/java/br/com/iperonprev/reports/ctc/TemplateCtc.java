package br.com.iperonprev.reports.ctc;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TemplateCtc implements JasperReportBuiderInterface{

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("portaria", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("portaria");
		dataSource.add("teste");
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		// TODO Auto-generated method stub
		return "ctc_template";
	}

}
