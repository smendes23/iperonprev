package br.com.iperonprev.helper;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.reports.container.SubReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

public class ComponentReportBuilderHelper {
	
	List<JasperReportBuiderInterface> listaJasperBuilder = new ArrayList<>();
	
	public List<JasperReportBuiderInterface> getListaJasperBuilder() {
		return listaJasperBuilder;
	}

	public ComponentReportBuilderHelper addComponent(JasperReportBuiderInterface jasperBuilder){
		if(!jasperBuilder.listaFields().isEmpty()){
			listaJasperBuilder.add(jasperBuilder);
		}
		return this;
	}
	
	public ComponentBuilder<?, ?>[] listaSubReport() {
		ComponentBuilder<?, ?>[] listaJasper = new ComponentBuilder<?, ?>[listaJasperBuilder.size()];
		if(listaJasper.length > 0){
			for (int i = 0; i < listaJasperBuilder.size(); i++) {
				listaJasper[i] = cmp.subreport(new SubReports().create(listaJasperBuilder.get(i).listaFields(), 
						listaJasperBuilder.get(i).dataSource(), listaJasperBuilder.get(i).templateDesigner()));
			}
		}
		return listaJasper;
	}
}
