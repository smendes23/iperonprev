package br.com.iperonprev.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cnpjConverter")
public class CnpjConverter implements Converter {
	
	private String getCnpj(String cnpj){
		String cpnjRes = null;
		cpnjRes = cnpj.replace(".", "");
		cpnjRes = cpnjRes.replace("-", "");
		cpnjRes = cpnjRes.replace("/", "");
		return cpnjRes;
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return getCnpj(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return getCnpj(value.toString());
	}

}
