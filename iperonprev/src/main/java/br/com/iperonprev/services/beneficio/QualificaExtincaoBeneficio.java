package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.interfaces.ExtincaoDoBeneficio;
import br.com.iperonprev.models.Pensao;

public class QualificaExtincaoBeneficio {

	public Date executa(Pensao pensao){
		ExtincaoDoBeneficio emancipado = new ExtincaoEmancipado();
		ExtincaoDoBeneficio vinteEUm = new ExtincaoVinteEUmAnos();
		ExtincaoDoBeneficio dezoito = new ExtincaoDezoitoAnos();
		ExtincaoDoBeneficio invalido = new ExtincaoInvalido();
		ExtincaoDoBeneficio nulo = new ExtincaoNula();
		
		emancipado.proximaData(vinteEUm);
		vinteEUm.proximaData(dezoito);
		dezoito.proximaData(invalido);
		invalido.proximaData(nulo);
		
		return emancipado.devolveDataDeExtincao(pensao);
	}
}
