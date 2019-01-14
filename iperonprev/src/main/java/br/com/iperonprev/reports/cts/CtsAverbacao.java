package br.com.iperonprev.reports.cts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.CertidaoTempoServico;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsAverbacao implements JasperReportBuiderInterface{

	private CertidaoTempoServico cts = new CertidaoTempoServico();
	
	public CtsAverbacao() {
	}
	
	public CtsAverbacao(CertidaoTempoServico cts) {
		this.cts = cts;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("orgao", "string"));
		field.add(new Field("periodo", "string"));
		field.add(new Field("regime", "string"));
		field.add(new Field("tempoEmDias", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Averbacao> listaAverbacao = cts.getLIST_Averbacao();
		DRDataSource dataSource = new DRDataSource("orgao", "periodo", "regime", "tempoEmDias");
		
		for (Averbacao a : listaAverbacao) {
			dataSource.add(
					a.getDESC_empregador(),
					new StringBuilder().append(sdf.format(a.getDATA_admissao())).append(" a ").append(sdf.format(a.getDATA_demissao())).toString(),
					a.getNUMR_regime().getDESC_nome(),
					Integer.toString(new RetornaTemposAverbacao().devolveDiasAproveitados(a)));
		}
		
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_averbacao";
	}

}
