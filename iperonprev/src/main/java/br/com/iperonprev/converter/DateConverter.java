package br.com.iperonprev.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	
	private final SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date getDateEnviada(Date data){
		Calendar dataHoje = Calendar.getInstance();
		dataHoje.setTime(data);
		dataHoje.set(Calendar.HOUR_OF_DAY, 0);
		dataHoje.set(Calendar.MINUTE, 0);
		dataHoje.set(Calendar.SECOND, 0);
		dataHoje.set(Calendar.MILLISECOND, 0);
		return dataHoje.getTime();
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		Date dataConvertida = null;
		if(value != null && !value.equals("")){
			try {
				dataConvertida = getDateEnviada(formata.parse(value));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			dataConvertida = null;
		}
		return dataConvertida;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return formata.format(value);
	}

}
