package br.com.iperonprev.reports.cts;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TemplateCts implements JasperReportBuiderInterface{

	String nomeOrgao = new String();
	String siglaOrgao = new String();
	public TemplateCts() {
		// TODO Auto-generated constructor stub
	}
	
	public TemplateCts(String nomeOrgao,String siglaOrgao) {
		this.nomeOrgao = nomeOrgao;
		this.siglaOrgao = siglaOrgao;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("nomeOrgao", "string"));
		field.add(new Field("siglaOrgao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("nomeOrgao","siglaOrgao");
		dataSource.add(this.nomeOrgao,this.siglaOrgao);
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_template";
	}

}
