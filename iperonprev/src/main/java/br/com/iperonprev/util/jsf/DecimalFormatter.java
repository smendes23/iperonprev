package br.com.iperonprev.util.jsf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@SuppressWarnings("serial")
public class DecimalFormatter implements Serializable {
	DecimalFormat formatterCurrency = new DecimalFormat("'R$ ' #,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
	DecimalFormat formatterCurrencyTwoDecimal = new DecimalFormat("'R$ ' #,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
	DecimalFormat formatterCurrencyWithoutSymbol = new DecimalFormat("#,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
	DecimalFormat formatterWithSix = new DecimalFormat("#,###,##0.000000", new DecimalFormatSymbols (new Locale ("pt", "BR")));
	DecimalFormat formatterThousand = new DecimalFormat("#,###,##0", new DecimalFormatSymbols (new Locale ("pt", "BR")));
	
	public String formatterToCurrencyBrazilian(BigDecimal value){
		return formatterCurrency.format(value);
	}
	
	public String formatterToCurrencyBrazilianWithTwoDecimal(BigDecimal value){
		return formatterCurrencyTwoDecimal.format(value.setScale(2, RoundingMode.HALF_EVEN));
	}
	
	public String formatterToCurrencyBrazilianWithoutSymbol(BigDecimal value){
		return formatterCurrencyWithoutSymbol.format(value.setScale(2, RoundingMode.HALF_EVEN));
	}
	
	public String formatterToFraction(BigDecimal value){
		return formatterWithSix.format(value.setScale(6, RoundingMode.HALF_EVEN));
	}
	
	public String formatterToThousand(BigDecimal value){
		return formatterThousand.format(value);
	}
	
	
}
