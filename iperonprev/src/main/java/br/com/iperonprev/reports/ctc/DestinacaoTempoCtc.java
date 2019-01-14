package br.com.iperonprev.reports.ctc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.DestinacaoCtc;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class DestinacaoTempoCtc implements JasperReportBuiderInterface{
	
	private List<DestinacaoCtc> listaDestinacao = new ArrayList<>();

	public DestinacaoTempoCtc() {
		// TODO Auto-generated constructor stub
	}
	
	public DestinacaoTempoCtc(List<DestinacaoCtc> listaDestinacao) {
		this.listaDestinacao = listaDestinacao;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("destinacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource ds = new DRDataSource("destinacao");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (DestinacaoCtc d : listaDestinacao) {
			ds.add(
				new StringBuilder().append("De ").append(sdf.format(d.getDATA_inicio())).append(" à ").append(sdf.format(d.getDATA_fim()))
				.append("  para aproveitamento no(a) ").append(d.getREL_orgaos().getDESC_nome()).toString()
				);
		}
		return ds;
	}

	@Override
	public String templateDesigner() {
		return "ctc_destinacaoTempo";
	}

}
