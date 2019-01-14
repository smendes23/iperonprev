package br.com.iperonprev.services.averbacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposAverbacao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;



public class ConcomitanciaService implements TemposAverbacao {
	FormataTemposConcomitantesThread ftc;
	FormataTemposPrevidenciariosThread ftpt;
	
	Averbacao a;
	List<Averbacao> l;
	
	@Override
	public String retornaTempoPrevidenciario(PessoasFuncionais funcional) {
		ftc = new FormataTemposConcomitantesThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftc.run();
		return ftc.retornaTempoPrevidenciario();
	}

	@Override
	public int retornaDiasDoTempoPrevidenciario(PessoasFuncionais funcional) {
		ftc = new FormataTemposConcomitantesThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftc.run();
		return ftc.retornaDiasPrevidenciarios();
	}

	@Override
	public String enviaTempoPrevidenciario(Date inicio, Date fim) {
		Averbacao a = new Averbacao();
		a.setDATA_admissao(inicio);
		a.setDATA_demissao(fim);
		List<Averbacao> l = new ArrayList<Averbacao>();
		l.add(a);
		ftc = new FormataTemposConcomitantesThread(l);
		ftc.run();
		return ftc.retornaTempoPrevidenciario();
	}

	public String enviaTempoPrevidenciarioConcomitante(Date inicio, Date fim, Date deducaoI, Date deducaoF){
	
		/*Seta o periodo normal*/
		a = new Averbacao();
		a.setDATA_admissao(inicio);
		a.setDATA_demissao(fim);
		l = new ArrayList<Averbacao>();
		l.add(a);
		ftpt = new FormataTemposPrevidenciariosThread(l);
		ftpt.run();
		
		/*Seta o per�odo concomitante*/
		a = new Averbacao();
		a.setDATA_inicioConcomitancia(deducaoI);
		a.setDATA_fimConcomitancia(deducaoF);
		l = new ArrayList<Averbacao>();
		l.add(a);
		ftc = new FormataTemposConcomitantesThread(l);
		ftc.run();
		
		return new FormataAnoMesDia().devolveAnoMesDiasFormatados(ftpt.getDia(), ftpt.getMes(), ftpt.getAno(), 
				ftc.getDia(), ftc.getMes(), ftc.getAno());
	}
	

}
