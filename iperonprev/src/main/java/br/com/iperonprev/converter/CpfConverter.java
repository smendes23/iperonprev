package br.com.iperonprev.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfConverter")
public class CpfConverter implements Converter {
	
	private String getCpf(String cpf){
		String cpfRes = null;
		cpfRes = cpf.replace(".", "");
		cpfRes = cpfRes.replace("-", "");
		return cpfRes;
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return getCpf(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return getCpf(value.toString());
	}

}
