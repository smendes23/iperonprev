package br.com.iperonprev.services.contribuicao;

public class ContadorTempo {

	public static long tempInicial;
    public static long tempFinal;
    public static void comecar(){
        tempInicial = System.currentTimeMillis();  
    }
    public static void parar(){
        tempFinal = System.currentTimeMillis();  
        long dif = (tempFinal - tempInicial);     
        System.out.println(String.format("Tempo de execução: %02d segundos  e %02d milisegundos", (dif/1000)/60, dif%1000));
    }
}
