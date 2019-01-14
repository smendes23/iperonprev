package br.com.iperonprev.reports.cts;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsColumnFooterAverbacao implements JasperReportBuiderInterface{

	private int somaAverbacao = 0;
	
	public CtsColumnFooterAverbacao() {
		// TODO Auto-generated constructor stub
	}
	
	public CtsColumnFooterAverbacao(int somaAverbacao) {
		this.somaAverbacao = somaAverbacao;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("tempoTotalAverbacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("tempoTotalAverbacao");
		
		dataSource.add(Integer.toString(somaAverbacao));
		
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		// TODO Auto-generated method stub
		return "cts_columnfooter_averbacao";
	}

}
