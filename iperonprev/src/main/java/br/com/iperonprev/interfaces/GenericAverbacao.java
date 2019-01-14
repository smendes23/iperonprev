package br.com.iperonprev.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;


public interface GenericAverbacao {
	
	List<Averbacao> devolveAverbacao(int id)throws ParseException;
	String retornaTempoTotalAverbado(PessoasFuncionais funcional);
	String retornaAverbacao(Date dtInicio, Date dtFim);
	int retornaDias(PessoasFuncionais funcional);
	List<Averbacao> listaDeAverbacoes(PessoasFuncionais funcional);
	
}
