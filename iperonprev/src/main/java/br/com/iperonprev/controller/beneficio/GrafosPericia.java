package br.com.iperonprev.controller.beneficio;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class GrafosPericia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PieChartModel grafico1;

	public PieChartModel getGrafico1() {
		return grafico1;
	}
	@PostConstruct
	public void init(){
		criaGrafico();
	}
	
	private void criaGrafico(){
		grafico1 = new PieChartModel();
		grafico1.set("Afastados", 350);
		grafico1.set("Dependentes Invalidos", 500);
		grafico1.set("Aposentados por invalidez", 300);
		grafico1.setTitle("Relação de Beneficiários");
		grafico1.setLegendPosition("w");
	}
}
