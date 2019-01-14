package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.controller.dto.TempoContribuicaoSimuladorDto;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.util.averbacao.DiaMesAno;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TempoServicoSimulador implements JasperReportBuiderInterface{
	
	private Pessoas pessoa;
	private TempoServicoSimuladorService tss;
	public TempoServicoSimulador() {
	}
	
	public TempoServicoSimulador(Pessoas pessoa) {
		tss = new TempoServicoSimuladorService();
		this.pessoa = pessoa;
		tss = new TempoServicoSimuladorService(this.pessoa);
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("periodo", "quantidadeDias", "tempoExtenso",
				"totalTempoExtenso", "totalDias", "orgao", "matricula");
		
		
		List<TempoContribuicaoSimuladorDto> lista = new ArrayList<>();
		boolean verificador = false;
		try {
			lista = tss.devolveListaServico();
			verificador = true;
		} catch (Exception e) {
			System.out.println("Lista de serviço vazia");
		}
		
		if(verificador == false){
			lista.add(new TempoContribuicaoSimuladorDto("", 0, "", "", 0, "", "", new DiaMesAno(0, 0, 0)));
		}
		
		
		for (TempoContribuicaoSimuladorDto contribuicao : lista) {
			dataSource.add(
					contribuicao.getPeriodo(),
					new StringBuilder().append(contribuicao.getQuantidadeDias()).toString(),
					contribuicao.getTempoExtenso(),
					tss.devolveTotalPeriodos(),
					new StringBuilder().append(tss.devolveTotalDias()).toString(),
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
		field.add(new Field("matricula", "string"));
		return field;
	}

	@Override
	public String templateDesigner() {
		return "simulador_tempo_servico";
	}
}
