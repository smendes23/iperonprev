package br.com.iperonprev.interfaces;

import java.util.List;

import br.com.iperonprev.util.jsf.Field;
import net.sf.jasperreports.engine.JRDataSource;

public interface JasperReportBuiderInterface {

	List<Field> listaFields();
	JRDataSource dataSource();
	String templateDesigner();
}
