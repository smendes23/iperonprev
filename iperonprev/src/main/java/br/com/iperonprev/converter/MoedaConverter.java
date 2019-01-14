package br.com.iperonprev.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iperonprev.util.jsf.BigDecimalUtil;

@FacesConverter("moedaConverter")
public class MoedaConverter implements Converter  {
	
	private BigDecimalUtil df = new BigDecimalUtil();
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		BigDecimal bd = new BigDecimal("0");
		try {
			if(value != ""){
				bd =  new BigDecimal(df.parse(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bd;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return df.format(new BigDecimal(value.toString()).setScale(2, RoundingMode.FLOOR));
	}

}
