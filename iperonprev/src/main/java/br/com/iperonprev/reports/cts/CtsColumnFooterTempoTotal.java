package br.com.iperonprev.reports.cts;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsColumnFooterTempoTotal implements JasperReportBuiderInterface{

	private int somaTotais = 0;
	
	public CtsColumnFooterTempoTotal() {
	}
	
	public CtsColumnFooterTempoTotal(int somaTotais) {
		this.somaTotais = somaTotais;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("somaTotais", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("somaTotais");
		dataSource.add(new StringBuilder().append(somaTotais).append(" ou ").append(RetornaTempos.retornaDiaMesAnoApartirDias(somaTotais)).toString());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_columnfooter_tempoTotal";
	}

}
