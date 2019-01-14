package br.com.iperonprev.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("truncateTextConverter")
public class TruncateTextConverter  implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value.toString().length() < 80){
			return value.toString();
		}else{
			return value.toString().substring(0, 80)+"...";
		}
	}

}
