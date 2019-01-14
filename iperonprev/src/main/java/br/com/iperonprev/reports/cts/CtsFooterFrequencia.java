package br.com.iperonprev.reports.cts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsFooterFrequencia implements JasperReportBuiderInterface{

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("dataHoje", "string"));
		return field;
	}

	@SuppressWarnings("static-access")
	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("dataHoje");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataSource.add(sdf.format(new LocalDate().now().toDate()));
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_footer_frequencia";
	}

}
