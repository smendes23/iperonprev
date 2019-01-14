package br.com.iperonprev.reports.funcional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Quinquenio;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class QuiquenioReport implements JasperReportBuiderInterface{
	
	private List<Quinquenio> listaQuinquenio;

	public QuiquenioReport() {
		// TODO Auto-generated constructor stub
	}
	
	public QuiquenioReport(List<Quinquenio> listaQuinquenio) {
		this.listaQuinquenio = listaQuinquenio;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("processo", "string"));
		field.add(new Field("decenio", "string"));
		field.add(new Field("dataInicio", "string"));
		field.add(new Field("dataFim", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("processo", 
				"decenio",
				"dataInicio",
				"dataFim",
				"observacao");
		listaQuinquenio.forEach(q->{
			dataSource.add(
					q.getDESC_processo(),
					q.getENUM_decenio().getNome(),
					sdf.format(q.getDATA_inicio()),
					sdf.format(q.getDATA_fim()),
					q.getDESC_observacao()
					);
		});
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "quinquenio";
	}

}
