package br.com.iperonprev.services.averbacao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.iperonprev.models.Averbacao;


public class FormataTemposPrevidenciariosThread extends Thread{
	
	final int diasMes = 30;
	final int mesesAno = 12;
	
	private int dia;
	private int mes;
	private int ano;
	List<Averbacao> listaAverbacao = new ArrayList<Averbacao>();

	public int getDia() {
		return dia;
	}


	public int getMes() {
		return mes;
	}


	public int getAno() {
		return ano;
	}
	
	public FormataTemposPrevidenciariosThread(){}
	public FormataTemposPrevidenciariosThread(List<Averbacao> listaAverbacao){
		this.listaAverbacao = listaAverbacao;
	}


	@Override
	public void run() {
		
		List<DateTime> lI = new ArrayList<DateTime>();
		List<DateTime> lF = new ArrayList<DateTime>();
		
		
		
		int a = 0;
		int m = 0;
		int d = 0;
		
		/*Seta as datas em listas*/
		for (Averbacao averbacao : listaAverbacao) {
			
			DateTime dt = new DateTime(averbacao.getDATA_admissao());
			DateTime dt2 = new DateTime(averbacao.getDATA_demissao());
			lI.add(dt);
			lF.add(dt2);
			
		}
		
		/*Deduz o tempo entre datas*/
		for (int i = 0; i < lI.size(); i++) {
			a = lF.get(i).getYear() - lI.get(i).getYear();
			m = lF.get(i).getMonthOfYear();
			
			if(lI.get(i).getDayOfMonth() > lF.get(i).getDayOfMonth()){
				d = ((lF.get(i).getDayOfMonth()+30) - lI.get(i).getDayOfMonth());
				m -= 1;
			}else{
				d = (lF.get(i).getDayOfMonth() - lI.get(i).getDayOfMonth());
			}
			
			if(m < lI.get(i).getMonthOfYear()){
				m = (m+12) - lI.get(i).getMonthOfYear();
				a -= 1;
			}else{
				m -= lI.get(i).getMonthOfYear() ;
			}

			
			
			if(diasMes <= d){
				d =0;
				m +=1;
			}
			
			if(mesesAno <= m){
				m = 0;
				a +=1;
			}
			
			ano += a;
			mes += m;
			dia += d;
		}
		
	}
	
	public String retornaTempoPrevidenciario(){
		return (ano+(mes/mesesAno))+" ano(s), "+((mes+(dia/diasMes))%mesesAno)+" mes(es) e "+(dia%diasMes)+" dia(s).";
	}
	
	public int retornaDiasPrevidenciarios(){
//		((((dia/30)%12)+mes)/12+ano)+" ano(s), "+((((dia/30)%12)+mes)%12)+" mes(es) e "+(dia%30)
		return ((ano+(mes/mesesAno))*365)+((mes+(dia/diasMes))%mesesAno) *diasMes+(dia%diasMes);
	}
	
	public String retornaTempoPrevidenciarioComParametro(ModificadorDeAcessoDiaMesAno modificador){
		return ((((modificador.getDia()/30)%12)+modificador.getMes())/12+modificador.getAno())+" ano(s), "+((((modificador.getDia()/30)%12)+modificador.getMes())%12)+" mes(es) e "+(modificador.getDia()%30)+" dias.";
	}
}
