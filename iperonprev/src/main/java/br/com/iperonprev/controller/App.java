package br.com.iperonprev.controller;

import java.text.ParseException;
import java.util.List;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;

public class App {

	int resultado = 0;
	public static void main(String[] args) throws ParseException {
		PessoasFuncionais pf = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(27581);
		
		List<ContribuicoeseAliquotas> lista = new RemuneracaoDao().listaRemuneracoesContribuicoes(pf);
		lista.forEach(r->{
			System.out.println("Competência: "+r.getDESC_competencia());
			System.out.println("Valor: "+r.getVALR_contribuicaoPrevidenciaria());
			
		});
		
	}
	

}
