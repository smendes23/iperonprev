package br.com.iperonprev.controller.gerenciador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.PessoasFuncionais;
import static java.util.stream.Collectors.toCollection;
import static java.util.Comparator.*;


public class App {

	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static final int diasAno = 365;
	static final int diasMes = 30;
	
	public static void main(String[] args) throws ParseException {
		
		List<PessoasFuncionais> listaFuncional = new PessoasFuncionaisDao().devolveListaDeFuncionais("55970940763");
		listaFuncional.forEach(f->{
			System.out.println(f.getDESC_matricula());
		});
		System.out.println("****************************************************************");
		
	/*	
		@SuppressWarnings("unchecked")
		List<PessoasFuncionais> listaFuncional2 = listaFuncional
				.stream()
					.collect(collectingAndThen(toCollection(()-> new TreeSet<>(Comparator.comparing(PessoasFuncionais::getDATA_posse))),
							ArrayList::new));
		
		listaFuncional2.forEach(f2->{
			System.out.println(f2.getDESC_matricula());
		});*/
		
		
		
		/*LocalDate data1 = new LocalDate(sdf.parse("29/12/1998"));
		LocalDate data2 = data1.plusDays(244);
		
		Period periodo = new Period(data1,data2,PeriodType.yearMonthDay()).plusDays(1);
		
		Days days = Days.daysBetween(data1, data2);
		
		System.out.println(days.getDays()+1);
		int valor = days.getDays()+1;
		int ano = valor/diasAno;
		int mes = (valor-ano*diasAno)/diasMes;
		int dia = (valor-ano*diasAno)-(mes*diasMes);
		System.out.println(ano+" anos, "+mes+" meses, "+dia+" dias.");
		
		System.out.println(periodo.getYears()+" - "+periodo.getMonths()+" - "+periodo.getDays());*/
		
		
//		System.out.println(dataTempo1I.compareTo(dataTempo1F));
		/*AvisoDto aviso = new AvisoDto();
		aviso.setTitulo("Teste");
		aviso.setMensagem("asdasdasdasd");
		
		System.out.println(aviso.toJson());*/
		
		
		/*List<PegaData> lista = new ArrayList<PegaData>();
		
		Tempo 1
		
		lista.add(new PegaData(dataTempo1I, dataTempo1F,"Tempo 1"));

		
		Tempo 2
		Date dataTempo2I = sdf.parse("01/12/2000");
		Date dataTempo2F = sdf.parse("30/12/2001");
		lista.add(new PegaData(dataTempo2I, dataTempo2F,"Tempo 2"));

		Tempo 3
		Date dataTempo3I = sdf.parse("01/11/2001");
		Date dataTempo3F = sdf.parse("30/12/2002");
		lista.add(new PegaData(dataTempo3I, dataTempo3F,"Tempo 3"));
		
		Tempo 4
		Date dataTempo4I = sdf.parse("02/12/2000");
		Date dataTempo4F = sdf.parse("29/12/2001");
		lista.add(new PegaData(dataTempo4I, dataTempo4F,"Tempo 4"));
		
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).descricao+" Data verificada: "+sdf.format(lista.get(i).dataI));
			for (int j = 0; j < lista.size(); j++) {
				if(lista.get(i).dataI != lista.get(j).getDataI() && lista.get(i).getDataF() != lista.get(j).getDataF()){
					System.out.println(new App().verificaData(lista.get(i).getDataI(), lista.get(i).getDataF(), lista.get(j).getDataI(), lista.get(j).getDataF(), lista.get(j).getDescricao()));
				}
			}
			System.out.println("*********************************************************");
		}*/
	}
	
	public String convertHexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();

		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){

		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);

		      temp.append(decimal);
		  }
		  System.out.println("Decimal : " + temp.toString());

		  return sb.toString();
	  }
	
	
	public String verificaData(Date dataI1,Date dataF1, Date dataI2, Date dataF2, String descricao){
		String res = null;
		
		if(dataI1.before(dataI2) && dataF1.after(dataF2)){
			System.out.println(descricao);
			res  = "Concomitancia com a data de admissão e demissão";
		}else if(dataI1.before(dataI2) && dataF1.after(dataI2)){
			
			System.out.println(descricao+" Data Aproveitada: "+sdf.format(dataF1)+" - "+sdf.format(dataF2));
			res = "Concomitancia com a data de admissão";
		}else{
			res = "Não existe concomitancia";
		}
		return res;
	}
	
}


