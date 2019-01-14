package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.controller.dto.TempoContribuicaoSimuladorDto;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TempoContribuicaoSimulador implements JasperReportBuiderInterface{
	

	private Pessoas pessoa;
	
	
	public TempoContribuicaoSimulador(){}

	public TempoContribuicaoSimulador(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("periodo", "quantidadeDias", "tempoExtenso",
				"totalTempoExtenso", "totalDias", "orgao", "cargo");
		TempoContribuicaoSimuladorService tss = new TempoContribuicaoSimuladorService(this.pessoa);
		List<TempoContribuicaoSimuladorDto> lista = tss.devolveListaServico();
		
		for (TempoContribuicaoSimuladorDto contribuicao : lista) {
			dataSource.add(
						contribuicao.getPeriodo(),
						new StringBuilder().append(contribuicao.getQuantidadeDias()).toString(),
						contribuicao.getTempoExtenso(),
						contribuicao.getTotalTempoExtenso(),
						new StringBuilder().append(contribuicao.getTotalDias()).toString(),
						contribuicao.getOrgao(),
						contribuicao.getMatricula()
					);
		}
		
		return dataSource;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("periodo", "string"));
		field.add(new Field("quantidadeDias", "string"));
		field.add(new Field("tempoExtenso", "string"));
		field.add(new Field("totalTempoExtenso", "string"));
		field.add(new Field("totalDias", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("cargo", "string"));
		return field;
	}

	@Override
	public String templateDesigner() {
		return "simulador_tempo_contribuicao";
	}
}
