package br.com.iperonprev.reports.ctc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.CertidaoTempoContribuicao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class HeaderCtcAd implements JasperReportBuiderInterface {

	private CertidaoTempoContribuicao ctc;

	public HeaderCtcAd() {
		// TODO Auto-generated constructor stub
	}

	public HeaderCtcAd(CertidaoTempoContribuicao ctc) {
		this.ctc = ctc;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("dataAdmissao", "string"));
		field.add(new Field("dataDemissao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("dataAdmissao", "dataDemissao");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		List<PessoasFuncionais> listaFuncional = this.ctc.getREL_listaDeFuncionais();
		
		for (PessoasFuncionais pf : listaFuncional) {
			dataSource.add(sdf.format(pf.getDATA_efetivoExercicio()),sdf.format(pf.getDATA_exoneracao()));
		}
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "ctc_header_ad";
	}

}
