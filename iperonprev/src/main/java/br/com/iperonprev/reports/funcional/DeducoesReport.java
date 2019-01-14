package br.com.iperonprev.reports.funcional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class DeducoesReport implements JasperReportBuiderInterface {

	private List<Deducao> listaDeducao;

	public DeducoesReport() {
	}

	public DeducoesReport(List<Deducao> listaDeducao) {
		this.listaDeducao = listaDeducao;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("tipo", "string"));
		field.add(new Field("dataInicio", "string"));
		field.add(new Field("dataFim", "string"));
		field.add(new Field("dias", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("tipo", "dataInicio", "dataFim", "dias", "observacao");
		listaDeducao.forEach(d -> {
			dataSource.add(d.getENUM_tipoDeducao().getNome(), sdf.format(d.getDATA_inicio()),
					sdf.format(d.getDATA_fim()), new StringBuilder().append(d.getNUMR_qtdDias()).toString(),
					d.getDESC_observacoes());
		});
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "deducoes";
	}

}
