package br.com.iperonprev.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BoletoBuilder {
	int[] codigoBarras = new int[44];
	List<Integer> codigoDeBarrasList = new ArrayList<>();
	
	public BoletoBuilder(){
		codigoBarras[0] = 8; //Valor fixo
		codigoBarras[1] = 5; //Valor fixo
		codigoBarras[2] = 6; //Valor fixo
		codigoBarras[17] = 2; //Valor fixo
		codigoBarras[18] = 2; //Valor fixo
		codigoBarras[19] = 7; //Valor fixo
	}
	
	public BoletoBuilder tipoContribuinte(int tpContriuinte){
		codigoBarras[43] = tpContriuinte;
		return this;
	}
	
	public BoletoBuilder dataVencimento(String dataVencimento){
		codigoBarras[20] = Integer.parseInt(dataVencimento.substring(8, 9));
		codigoBarras[21] = Integer.parseInt(dataVencimento.substring(9, 10));
		codigoBarras[25] = Integer.parseInt(dataVencimento.substring(3, 4));
		codigoBarras[26] = Integer.parseInt(dataVencimento.substring(4, 5));
		return this;
	}
	
	public BoletoBuilder valorTotal(BigDecimal vlTotal){
		String[] res = vlTotal.toString().replace(".", "").replace(",", "").split("");
		int lengthValueAmount = 14;
		
		for (int i = res.length -1; i >= 0; i--) {
			codigoBarras[lengthValueAmount] = Integer.parseInt(res[i]);
			lengthValueAmount--;
		}
		
		return this;
	}
	
	public BoletoBuilder codigoDaReceita(String codigoReceita){
		String[] codReceitaArray = codigoReceita.split("");
		devolveArrayCodigoBarras(codReceitaArray, 39, 0);
		return this;
	}
	
	public BoletoBuilder ieCpfCnpj(String numeroDocumento){
		String[] documento = numeroDocumento.replace(".", "").replace("-", "").replace("/", "").split("");
		devolveArrayCodigoBarras(documento, 27, 12);
		return this;
	}
	
	public BoletoBuilder geraDataJuliana(Date dataVencimento) throws ParseException{
//		SimpleDateFormat formatJulianYearDay = new SimpleDateFormat("yyDDD");
		SimpleDateFormat formatJulianDay = new SimpleDateFormat("DDD");
		String[] tempo = formatJulianDay.format(dataVencimento).split("");
		devolveArrayCodigoBarras(tempo, 22, 0);
		return this;
	}
	
	public BoletoBuilder geraDigitoVerificadorGeral(){
		Integer[] arrayVerificador = new Integer[this.codigoDeBarrasList.size()];
		arrayVerificador = this.codigoDeBarrasList.toArray(new Integer[this.codigoDeBarrasList.size()]);
		System.out.println("Digito verificador: "+devolveDigitoVerificador(arrayVerificador));
		this.codigoDeBarrasList.set(3, devolveDigitoVerificador(arrayVerificador));
		return this;
	}
	
	
	public BoletoBuilder geraDigitoAutoConferencia(){
		 int val = 1;
		 Integer[] arrayCodigo = new Integer[11];
		 int verificador = 0;
		 List<Integer> listaCodigo = new ArrayList<>();
		 
		 for (int i = 0; i < codigoBarras.length; i++) {
			 
			 listaCodigo.add(codigoBarras[i]);
			 arrayCodigo[verificador] = codigoBarras[i];
			 if(val == 11){
				 listaCodigo.add(devolveDigitoVerificador(arrayCodigo));
				 val = 1;
				 verificador = 0;
				 arrayCodigo = new Integer[11];
			 }else{
				 val++;
				 verificador++;
			 }
			 
		}
		 this.codigoDeBarrasList =listaCodigo; 
		 return this;
	}
	
	private int devolveDigitoVerificador(Integer[] codigo){
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		int valorTeste = 1;
		
		if(codigo.length > 11){
			for (int i = codigo.length-1; i >= 0; i--) {
				if(i != 3){
					if(valorTeste % 2 > 0){
						sb.append(codigo[i] * 2);
					}else{
						sb.append(codigo[i] * 1);
					}
					valorTeste++;
				}
			}
		}else{
			for (int i = codigo.length-1; i >= 0; i--) {
				if(valorTeste % 2 > 0){
					sb.append(codigo[i] * 2);
				}else{
					sb.append(codigo[i] * 1);
				}
				valorTeste++;
			}
		}
		
		
		res = Arrays.asList(sb.toString().split(""));
		int resultado = 0;
		for (int i = 0; i < res.size(); i++) {
			resultado += Integer.parseInt(res.get(i));
		}

		return 10 - (resultado % 10);
	}
	
	private void devolveArrayCodigoBarras(String[] codigoDeBarrasArray,int posicaoIncial,int indice){
		int valorPosicao = posicaoIncial;

		for (int i = 0; i < codigoDeBarrasArray.length; i++) {
				codigoBarras[valorPosicao] = Integer.parseInt(codigoDeBarrasArray[i]);
				valorPosicao++;
			}
	}
	
	public List<Integer> geraCodigoDeBarras(){
		return this.codigoDeBarrasList;
	}
	
	
}
