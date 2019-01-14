package br.com.iperonprev.reports.cts;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.FrequenciaCtc;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class FrequenciaDetailCts implements JasperReportBuiderInterface{

	private List<FrequenciaCtc> listaFrequencia = new ArrayList<FrequenciaCtc>();
	
	public FrequenciaDetailCts() {
		// TODO Auto-generated constructor stub
	}
	
	public FrequenciaDetailCts(List<FrequenciaCtc> listaFrequencia) {
		this.listaFrequencia = listaFrequencia;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("ano", "integer"));
		field.add(new Field("tempoBruto", "integer"));
		field.add(new Field("faltas", "integer"));
		field.add(new Field("licencas", "integer"));
		field.add(new Field("suspensoes", "integer"));
		field.add(new Field("outros", "integer"));
		field.add(new Field("soma", "integer"));
		field.add(new Field("tempoLiquido", "integer"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("ano", "tempoBruto", "faltas", "licencas",
				"suspensoes", "outros","soma", "tempoLiquido");
		
		for (FrequenciaCtc f : this.listaFrequencia) {
			
			dataSource.add(f.getAno(), f.getTempoBruto(), f.getFaltas(),
					f.getLicencas(), f.getSuspensoes(),
					f.getOutras(),f.getSoma(), f.getTempoLiquido());
		}
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_frequencia";
	}

}
