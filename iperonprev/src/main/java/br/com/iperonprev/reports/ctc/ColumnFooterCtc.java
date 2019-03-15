package br.com.iperonprev.reports.ctc;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.FrequenciaCtc;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class ColumnFooterCtc implements JasperReportBuiderInterface {

	private List<FrequenciaCtc> listaFrequencia;

	public ColumnFooterCtc() {
	}

	public ColumnFooterCtc(List<FrequenciaCtc> listaFrequencia) {
		this.listaFrequencia = listaFrequencia;

	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("certifico1", "string"));
		field.add(new Field("tempoTotal", "integer"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("certifico1","tempoTotal");
		/*DiaMesAno dma = new RetornaTempos().devolveDiaMesAno(new LocalDate().now().toDate(),
				RetornaTempos.retornaDataFutura(RetornaTempos.retornaTotalDiasFrequenciaCtc(listaFrequencia),
						new LocalDate().now().toDate()));*/
		int totalDias = RetornaTempos.retornaTotalDiasFrequenciaCtc(listaFrequencia);
		dataSource.add(
				new StringBuilder()
						.append("CERTIFICO, em face do apurado, que o(a) interessado(a) conta, de efetivo exercício prestado neste órgão, o tempo de contribuição de ")
						.append(RetornaTempos.retornaTotalDiasFrequenciaCtc(listaFrequencia))
						.append(" dias correspondente a ").append(totalDias/365).append(" ano(s), ").append((totalDias%365)/30)
						.append(" mes(es), ").append((totalDias%365)%30).append(" dia(s).")
						.append("\n")
						.append("CERTIFICO que a Lei n°432, de 13/03/2008, assegura aos servidores do Estado de RONDÔNIA aposentadorias voluntárias, por invalidez e compulsória, e pensão por morte, ")
						.append("com aproveitamento de tempo de contribuição para o Regime Geral de Previdência Social ou para outro Regime Próprio de "
								+ "Previdência Social, na forma da contagem recíproca, conforme Lei Federal n° 6.226, de 14/07/1975, com alteração data pela ")
						.append(" Lei Federal n° 6.864, de 01/12/1980.").toString(),
				this.listaFrequencia.stream().mapToInt(f -> f.getTempoLiquido()).sum());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "ctc_columnfooter";
	}

}
