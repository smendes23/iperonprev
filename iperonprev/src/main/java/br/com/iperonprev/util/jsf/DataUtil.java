package br.com.iperonprev.util.jsf;

import java.util.Date;

public class DataUtil {

	public boolean verificaDataNula(Date data1){
		boolean res = true;
		
		try {
			if(data1 != null){
				res = false;
			}
		} catch (Exception e) {
			res = true;
		}
		
		return res;
	}
}
