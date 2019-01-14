package br.com.iperonprev.reports.cts;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsSumari implements JasperReportBuiderInterface{

	private String anotacoes = new String();
	
	public CtsSumari() {
	}
	
	public CtsSumari(String anotacoes) {
		this.anotacoes = anotacoes;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("descricaoRad", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("descricaoRad");
		dataSource.add(this.anotacoes);
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_sumari";
	}

}
