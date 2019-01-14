package br.com.iperonprev.reports.ctc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class FooterCtc implements JasperReportBuiderInterface {


	private Date dataEmissao;

	public FooterCtc() {
	}
	
	public FooterCtc(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("dataHoje", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("dataHoje");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataSource.add(
				sdf.format(this.dataEmissao).toString());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "ctc_footer";
	}

}
