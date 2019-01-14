package br.com.iperonprev.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("competenciaConverterMesAno")
public class CompetenciaConverterMesAno implements Converter {
	
	private String getCompetencia(String competencia){
		return  competencia.replace("/", "");
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
