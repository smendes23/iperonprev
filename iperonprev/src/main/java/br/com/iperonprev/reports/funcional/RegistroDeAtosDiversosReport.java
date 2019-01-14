package br.com.iperonprev.reports.funcional;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.AtosDiversos;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class RegistroDeAtosDiversosReport implements JasperReportBuiderInterface{
	List<AtosDiversos> listaAtosDiversos = new ArrayList<>();
	
	public RegistroDeAtosDiversosReport() {
	}
	
	public RegistroDeAtosDiversosReport(List<AtosDiversos> listaAtosDiversos) {
		this.listaAtosDiversos = listaAtosDiversos;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("tipo", "string"));
		field.add(new Field("descricaoRad", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("tipo", "descricaoRad");
		listaAtosDiversos.forEach(ad->{
			dataSource.add(
					ad.getENUM_tipoAtosDiversos().getNome().toString(),
					ad.getDESC_informacao()
					);
		});
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "rad";
	}

}
