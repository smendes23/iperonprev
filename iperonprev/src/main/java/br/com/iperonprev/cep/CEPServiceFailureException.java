package br.com.iperonprev.cep;


public class CEPServiceFailureException extends RuntimeException {

	private static final long serialVersionUID = 1462228622695384135L;
	
	public CEPServiceFailureException(Throwable cause) {
		super(cause);
	}

}
