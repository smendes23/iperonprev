package br.com.iperonprev.interfaces;

import java.util.Date;

import br.com.iperonprev.models.PessoasFuncionais;

public interface TemposAverbacao {
	
	String retornaTempoPrevidenciario(PessoasFuncionais funcional);
	int retornaDiasDoTempoPrevidenciario(PessoasFuncionais funcional);
	String enviaTempoPrevidenciario(Date inicio, Date fim);
}
