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
import br.com.iperonprev.dao.AfastamentoLicencaDao;
import br.com.iperonprev.dao.DeducaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.PessoasFuncionais;

public class App {
	static int total = 0;
	public static void main(String[] args) throws ParseException {
		//72752
		
		List<Deducao> de =  new DeducaoDao().devolveFaltas(72752);
		de.forEach(d -> {
			total += Days.daysBetween(new LocalDate(d.getDATA_inicio()),new LocalDate(d.getDATA_fim())).getDays();
		});
			
		System.out.println(total);

	}
	
}




