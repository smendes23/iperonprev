package br.com.iperonprev.util.jsf;

public class Compare {

	 /**
     * Compara se um número encontra-se no intervalo entre 2 números.
     *
     * @param   
     * @return Se o valor1 encontra-se dentro do intervalo, o retorno será t0 
     * 
     */
	public static int inteiro(int valor1, int valorInicio, int valorFim){
		if(valor1 >= valorInicio && valor1 <= valorFim){
			return 0;
		}else{
			return 1;
		}
	}
}
