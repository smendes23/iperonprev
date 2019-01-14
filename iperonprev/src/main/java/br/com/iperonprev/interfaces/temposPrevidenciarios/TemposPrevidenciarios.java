package br.com.iperonprev.interfaces.temposPrevidenciarios;

import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public interface TemposPrevidenciarios {

	DiaMesAno devolve(Averbacao averbacao);
}
