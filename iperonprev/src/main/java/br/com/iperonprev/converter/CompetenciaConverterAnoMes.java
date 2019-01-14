package br.com.iperonprev.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("competenciaConverterAnoMes")
public class CompetenciaConverterAnoMes implements Converter {
	
	private String getCompetencia(String competencia){
		return new StringBuilder().append(competencia.substring(3,7)).append(competencia.substring(0,2)).toString(); 
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return getCompetencia(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		return getCompetencia(value.toString());
	}

}
