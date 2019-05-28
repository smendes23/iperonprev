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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate data = new LocalDate(sdf.parse("10/05/2019"));
		if(new LocalDate().now().compareTo(data) > 0) {
			System.out.println("Hoje é maior");
		}else {
				System.out.println("menor");
		}
//		System.out.println(sdf.format(data.toDate()));
	}
	
}




