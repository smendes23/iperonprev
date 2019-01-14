package br.com.iperonprev.reports.funcional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class LicencasReport implements JasperReportBuiderInterface{
	
	private List<AfastamentosLicenca> listaLicencas;

	public LicencasReport() {
	}
	
	public LicencasReport(List<AfastamentosLicenca> listaLicencas) {
		this.listaLicencas = listaLicencas;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("documento", "string"));
		field.add(new Field("tipoLicenca", "string"));
		field.add(new Field("doe", "string"));
		field.add(new Field("contribuicao", "string"));
		field.add(new Field("dataInicio", "string"));
		field.add(new Field("dataFim", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}

	String contribui = "Sim";
	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("documento", "tipoLicenca","doe","contribuicao","dataInicio","dataFim","observacao");
		listaLicencas.forEach(l->{
			if(l.getFLAG_contribuicao() != 0){
				contribui = "Não";
			}
			dataSource.add(
					l.getNUMR_processo(),
					l.getNUMR_tipoLicenca().getDESC_nome(),
					l.getDESC_doe(),
					contribui,
					sdf.format(l.getDATA_inicioLicenca()),
					sdf.format(l.getDATA_fimLicenca()),
					l.getDESC_observacao()
					);
		});
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "licencas";
	}

}
