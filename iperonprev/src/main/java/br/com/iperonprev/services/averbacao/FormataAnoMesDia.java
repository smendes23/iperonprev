package br.com.iperonprev.services.averbacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class FormataAnoMesDia{
	DateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
	final int diasMes = 30;
	final int mesesAno = 12;
	
	int ano = 0;
	int mes = 0;
	int dia = 0;
	
	DateTime dtI = new DateTime();
	DateTime dtF = new DateTime();

	
	public int getAno() {
		return ano;
	}

	public int getMes() {
		return mes;
	}

	public int getDia() {
		return dia;
	}

	public FormataAnoMesDia(){}

	public FormataAnoMesDia(Date inicio, Date fim){
		dtI = new DateTime(inicio);
		dtF = new DateTime(fim);
	}
	
	
	
	public String devolveAnoMesDiasFormatados(int diaI, int mesI, int anoI, int diaII, int mesII, int anoII){
			
		
			if(mesI < mesII || mesI == 0){
				mes = (mesI+mesesAno) - mesII;
				ano = (anoI - 1) - anoII;
			}else{
				mes = mesI - mesII;
				ano = anoI - anoII;
			}
			
			if(diaI < diaII || diaI == 0){
				dia = (diaI+diasMes) - diaII;
				mes -= 1;
			}else{
				dia = diaI - diaII;
			}
		
		return ((((dia/30)%12)+mes)/12+ano)+" ano(s), "+((((dia/30)%12)+mes)%12)+" mes(es) e "+(dia%30)+" dias.";
		
	}
	
	public String devolveAnoMesDiasFormatadosSemParametros(){

		ano = dtF.getYear() - dtI.getYear();
		mes = dtF.getMonthOfYear();
		
		if(dtF.getDayOfMonth() < dtI.getDayOfMonth()){
			mes -= 1;
			dia = (dtF.getDayOfMonth() + 30) - dtI.getDayOfMonth();
		}else{
			dia = dtF.getDayOfMonth() - dtI.getDayOfMonth();
		}
		
		if (mes < dtI.getMonthOfYear()){
			ano -= 1;
			mes = (mes + 12) - dtI.getMonthOfYear();
		}else{
			mes -= dtI.getMonthOfYear();
		}
		

		return ano+" ano(s), "+mes+" mes(es) e "+dia+" dias.";
	}
	

}
