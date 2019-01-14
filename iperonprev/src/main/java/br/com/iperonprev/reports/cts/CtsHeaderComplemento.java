package br.com.iperonprev.reports.cts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.CertidaoTempoServico;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsHeaderComplemento implements JasperReportBuiderInterface{

	private CertidaoTempoServico cts = new CertidaoTempoServico();
	
	public CtsHeaderComplemento() {
	}
	
	public CtsHeaderComplemento(CertidaoTempoServico cts) {
		this.cts = cts;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("periodoContribuicao", "string"));
		field.add(new Field("fonteInformacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("periodoContribuicao","fonteInformacao");
		
		dataSource.add(new StringBuilder().append(sdf.format(this.cts.getDATA_inicioPeriodoApuracao())).append(" a ")
				.append(sdf.format(this.cts.getDATA_fimPeriodoApuracao())).toString(),
				this.cts.getDESC_fonteInformacao());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_header_complemento";
	}

}
