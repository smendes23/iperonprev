package br.com.iperonprev.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import br.com.iperonprev.dao.AfastamentoLicencaDao;
import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.inigracao.Remuneracao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.services.contribuicao.QualificaCompetencia;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class App {

	int resultado = 0;
	public static void main(String[] args) throws ParseException {
		List<Deducao> listaDeducao = new GenericPersistence<Deducao>(Deducao.class).listarRelacionamento("Deducao", "NUMR_pessoasFuncionais",71991);
		System.out.println(listaDeducao.size());
		
		
	}
	

}
