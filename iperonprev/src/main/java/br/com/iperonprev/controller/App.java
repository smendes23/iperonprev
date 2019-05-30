package br.com.iperonprev.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;

public class App {
	
	public static void main(String[] args) throws ParseException {
		
		List<ContribuicaoDto> lista =  new RemuneracaoDao().listaRemuneracoesContribuicoes(new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(81851));
		
		lista.forEach(r->{
			System.out.println(r.getVALR_contribuicaoPrevidenciaria());
		});

	}
	
}




