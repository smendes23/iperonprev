package br.com.iperonprev.reports.funcional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Ferias;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class AnotacoesDeFeriasReport implements JasperReportBuiderInterface{

	private List<Ferias> listaFerias;

	public AnotacoesDeFeriasReport() {
	}
	
	public AnotacoesDeFeriasReport(List<Ferias> listaFerias) {
		this.listaFerias = listaFerias;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("periodo", "string"));
		field.add(new Field("dataInicio", "string"));
		field.add(new Field("dataFim", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("periodo", "dataInicio","dataFim","observacao");
		listaFerias.forEach(f->{
			dataSource.add(
						new StringBuilder().append(f.getNUMR_periodoAquisitivo()).toString(),
						sdf.format(f.getDATA_inicio()),
						sdf.format(f.getDATA_fim()),
						f.getDESC_observacao()
					);
		});
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "anotacoesFerias";
	}

}
