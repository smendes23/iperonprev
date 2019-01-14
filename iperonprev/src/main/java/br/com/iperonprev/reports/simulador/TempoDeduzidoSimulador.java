package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.controller.dto.TemposDeduzidosDto;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TempoDeduzidoSimulador implements JasperReportBuiderInterface{
	

	private List<TemposDeduzidosDto> lista = new ArrayList<>();
	private Pessoas pessoa;
	

	public TempoDeduzidoSimulador() {
	}
	
	public TempoDeduzidoSimulador(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("tipoDeducao", "quantidade","totalDias","periodo");
		TempoDeduzidoSimuladorService tds = new TempoDeduzidoSimuladorService(this.pessoa);

		boolean verificador = false;
		try {
			lista = tds.devolveListaTemposDeduzidos();
			verificador = true;
		} catch (Exception e) {
			System.out.println("lista de tempos deduzidos");
		} 
		
		if(verificador == false){
			lista.add(new TemposDeduzidosDto());
		}
		
		
		for (TemposDeduzidosDto deduzido : lista) {
			dataSource.add(
					deduzido.getTipoDeducao(),
					new StringBuilder().append(deduzido.getQuantidade()).toString(),
					new StringBuilder().append(tds.devolveTotalDias()).toString(),
					deduzido.getPeriodo()
					);
		}
		
		return dataSource;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("tipoDeducao", "string"));
		field.add(new Field("quantidade", "string"));
		field.add(new Field("totalDias", "string"));
		field.add(new Field("periodo", "string"));
		return field;
	}

	@Override
	public String templateDesigner() {
		return "simulador_tempo_deduzido";
	}
}
