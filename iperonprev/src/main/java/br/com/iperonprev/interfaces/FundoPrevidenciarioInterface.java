package br.com.iperonprev.interfaces;

import br.com.iperonprev.models.PessoasFuncionais;

public interface FundoPrevidenciarioInterface {
	PessoasFuncionais calcula(PessoasFuncionais funcional);
	void setProximoCalculo(FundoPrevidenciarioInterface proximo);
}
