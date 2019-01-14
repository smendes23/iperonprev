package br.com.iperonprev.interfaces;

import br.com.iperonprev.controller.dto.SimuladorDto;
import net.sf.jasperreports.engine.JRDataSource;

public interface DataSourceSimuladorAposentadoria {

	JRDataSource devolveDatasource(SimuladorDto simulador);
}
