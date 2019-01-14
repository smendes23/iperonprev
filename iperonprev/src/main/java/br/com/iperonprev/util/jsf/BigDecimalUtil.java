package br.com.iperonprev.util.jsf;

import java.math.BigDecimal;

public class BigDecimalUtil {

	public String format(BigDecimal valor) {
		String res = new String();
		res = valor.toString().replace(".", "_").replace(",", "-");
		res = res.replace("_", ",").replace("-", ".");
		return res;
	}
	
	public String parse(String valor) {
		return valor.replace(".", "").replace(",", ".");
	}
}
