package br.com.iperonprev.cep;


public class CEPServiceFactory {

	
	public static CEPService getCEPService() {
		return new BuscaCEP();
	}
	
}
