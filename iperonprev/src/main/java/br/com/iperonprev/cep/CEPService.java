package br.com.iperonprev.cep;

import java.util.List;

public interface CEPService {

	
	public CEP obtemPorNumeroCEP(String numeroCEP);

	public List<CEP> obtemPorEndereco(String query);

}