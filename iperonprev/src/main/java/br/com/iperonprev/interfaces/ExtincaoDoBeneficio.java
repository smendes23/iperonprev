package br.com.iperonprev.interfaces;

import java.util.Date;

import br.com.iperonprev.models.Pensao;

public interface ExtincaoDoBeneficio {

	Date devolveDataDeExtincao(Pensao pensao);
	void proximaData(ExtincaoDoBeneficio extincaoDoBeneficio);
}
