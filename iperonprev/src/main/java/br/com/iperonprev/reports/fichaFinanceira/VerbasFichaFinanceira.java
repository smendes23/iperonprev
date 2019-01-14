package br.com.iperonprev.reports.fichaFinanceira;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class VerbasFichaFinanceira implements JasperReportBuiderInterface{

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("verbas", "string"));
		field.add(new Field("jan", "string"));
		field.add(new Field("fev", "string"));
		field.add(new Field("mar", "string"));
		field.add(new Field("abr", "string"));
		field.add(new Field("mai", "string"));
		field.add(new Field("jun", "string"));
		field.add(new Field("jul", "string"));
		field.add(new Field("ago", "string"));
		field.add(new Field("set", "string"));
		field.add(new Field("out", "string"));
		field.add(new Field("nov", "string"));
		field.add(new Field("dez", "string"));
		field.add(new Field("decimoTerceiro", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource ds = new DRDataSource("verbas","jan","fev","mar","abr","mai","jun","jul","ago","set",
				"out","nov","dez","decimoTerceiro");
		return ds;
	}

	@Override
	public String templateDesigner() {
		// TODO Auto-generated method stub
		return "ff_verbas";
	}

}
