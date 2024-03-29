package br.com.iperonprev.services.beneficio.simulador;

import br.com.iperonprev.controller.dto.SimuladorDto;
import br.com.iperonprev.interfaces.DataSourceSimuladorAposentadoria;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class DataSourceArt08I implements DataSourceSimuladorAposentadoria{

	@Override
	public JRDataSource devolveDatasource(SimuladorDto simulador) {
		DRDataSource dataSource = new DRDataSource("regraAposentadoria", 
				"tipoProventos",
				"descricaoRegra",
				"requisito1", 
				"requisito2", 
				"requisito3", 
				"requisito4", 
				"requisito5", 
				"requerido1", 
				"requerido2",
				"requerido3", 
				"requerido4", 
				"requerido5", 
				"faltante1", 
				"faltante2", 
				"faltante3", 
				"faltante4",
				"faltante5", 
				"status");
		dataSource.add(
				simulador.getTituloRegra(),
				 simulador.getTipoProventosReajustes(),
				 simulador.getDescricaoRegra(),
				 simulador.getTituloCampoCargo(),
				 simulador.getTituloCampoTempoContribuicao(),
				 simulador.getTituloCampoContribuicaoPedagio(),
				 simulador.getTituloCampoIdade(),
				 null,
				 simulador.getRequisitoTempoCargo(),
				 simulador.getRequisitoTempoContribuicao(),
				 simulador.getRequisitoContribuicaoPedagio(),
				 simulador.getRequisitoIdade(),
				 null,
				 simulador.getTempoFaltanteCargo(),
				 simulador.getTempoFaltanteContribuicao(),
				 simulador.getFaltanteTempoContribuicaoPedagio(),
				 simulador.getTempoFaltanteIdade(),
				 null,
				 simulador.getStatus());
		
		return dataSource;
	}

}
