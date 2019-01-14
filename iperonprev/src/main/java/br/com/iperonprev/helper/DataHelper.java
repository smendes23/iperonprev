package br.com.iperonprev.helper;

import java.util.Date;

public class DataHelper {

	public static boolean verificaDataNula(Date data){
		if(data == null){
			return true;
		}
		return false;
	}
	
}
