package br.com.iperonprev.interfaces.temposPrevidenciarios;

import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public interface TemposAverbados {

	DiaMesAno devolvePeriodo(Averbacao a);
	void proximoPerido(TemposAverbados tempoAverbado);
}
