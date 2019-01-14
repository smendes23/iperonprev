package br.com.iperonprev.reports.cts;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsColumnFooterFrequencia implements JasperReportBuiderInterface{

	private int tempoTotalAverbacao = 0;
	
	public CtsColumnFooterFrequencia() {
		// TODO Auto-generated constructor stub
	}
	
	public CtsColumnFooterFrequencia(int tempoTotalAverbacao) {
		this.tempoTotalAverbacao = tempoTotalAverbacao;
	}
		
	@Override
	public List<Field> listaFields() {
		
		List<Field> field = new ArrayList<>();
		field.add(new Field("tempoTotal", "string"));
		field.add(new Field("certifico1", "string"));
		return field;
	}

	@SuppressWarnings("static-access")
	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("tempoTotal", "certifico1");
		
		dataSource.add(Integer.toString(this.tempoTotalAverbacao),
				new StringBuilder().append("CERTIFICO, em face do apurado, que no período acima referido, o interessado conta de efetivo exercício")
				.append(" o tempo de servíço líquido de ").append(this.tempoTotalAverbacao)
				.append(" dias, ou ").append(new RetornaTempos().retornaDiaMesAnoApartirDias(this.tempoTotalAverbacao)).toString());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_columnfooter_frequencia";
	}
	
}
