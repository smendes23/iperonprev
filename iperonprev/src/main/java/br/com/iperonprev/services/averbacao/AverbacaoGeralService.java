package br.com.iperonprev.services.averbacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposAverbacao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class AverbacaoGeralService implements TemposAverbacao{
	ModificadorDeAcessoDiaMesAno mda;
	FormataTemposConcomitantesThread ftc;
	FormataTemposPrevidenciariosThread ftpt;
	Averbacao a;
	List<Averbacao> l;

	@SuppressWarnings("static-access")
	@Override
	public String retornaTempoPrevidenciario(PessoasFuncionais funcional) {
		
		OutrosTemposDeduzidos ot = new OutrosTemposDeduzidos();
		
		ftpt = new FormataTemposPrevidenciariosThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftpt.run();
		ModificadorDeAcessoDiaMesAno mdaFtpt = new ModificadorDeAcessoDiaMesAno(ftpt.getDia(), ftpt.getMes(), ftpt.getAno());
		
		ftc = new FormataTemposConcomitantesThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftc.run();
		ModificadorDeAcessoDiaMesAno mdaFtc = new ModificadorDeAcessoDiaMesAno(ftc.getDia(), ftc.getMes(), ftc.getAno());
		
		Date dt = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).toDate();
		Date dt2 = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).plusDays(ot.retornaDiasDoTempoPrevidenciario(funcional)).toDate();
		FormataAnoMesDia fa = new FormataAnoMesDia(dt, dt2);
		fa.devolveAnoMesDiasFormatadosSemParametros();
		
		ModificadorDeAcessoDiaMesAno mdaOutros = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
		
		List<ModificadorDeAcessoDiaMesAno> lm = new ArrayList<ModificadorDeAcessoDiaMesAno>();
		lm.add(mdaFtpt);
		lm.add(mdaFtc);
		lm.add(mdaOutros);
		
		CalculaTempoLiquidoComDeducao ctl = new CalculaTempoLiquidoComDeducao(lm);
		ctl.run();
		
		return ctl.retornaCalculoTempoAproveitado();
	}

	@SuppressWarnings("static-access")
	@Override
	public int retornaDiasDoTempoPrevidenciario(PessoasFuncionais funcional) {
		OutrosTemposDeduzidos ot = new OutrosTemposDeduzidos();
		
		ftpt = new FormataTemposPrevidenciariosThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftpt.run();
		ModificadorDeAcessoDiaMesAno mdaFtpt = new ModificadorDeAcessoDiaMesAno(ftpt.getDia(), ftpt.getMes(), ftpt.getAno());
		
		ftc = new FormataTemposConcomitantesThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftc.run();
		ModificadorDeAcessoDiaMesAno mdaFtc = new ModificadorDeAcessoDiaMesAno(ftc.getDia(), ftc.getMes(), ftc.getAno());
		
		Date dt = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).toDate();
		Date dt2 = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).plusDays(ot.retornaDiasDoTempoPrevidenciario(funcional)).toDate();
		FormataAnoMesDia fa = new FormataAnoMesDia(dt, dt2);
		fa.devolveAnoMesDiasFormatadosSemParametros();
		
		ModificadorDeAcessoDiaMesAno mdaOutros = new ModificadorDeAcessoDiaMesAno(fa.getDia(), fa.getMes(), fa.getAno());
		
		List<ModificadorDeAcessoDiaMesAno> lm = new ArrayList<ModificadorDeAcessoDiaMesAno>();
		lm.add(mdaFtpt);
		lm.add(mdaFtc);
		lm.add(mdaOutros);
		
		CalculaTempoLiquidoComDeducao ctl = new CalculaTempoLiquidoComDeducao(lm);
		ctl.run();
		
		mdaOutros = new ModificadorDeAcessoDiaMesAno();
		mdaOutros = ctl.devolveModificador();
		return mdaOutros.getAno() * 365+mdaOutros.getMes()*30+mdaOutros.getDia();
	}

	@Override
	public String enviaTempoPrevidenciario(Date inicio, Date fim) {
		Averbacao a = new Averbacao();
		a.setDATA_admissao(inicio);
		a.setDATA_demissao(fim);
		List<Averbacao> l = new ArrayList<Averbacao>();
		l.add(a);
		ftpt = new FormataTemposPrevidenciariosThread(l);
		ftpt.run();
		return ftpt.retornaTempoPrevidenciario();
	}
	
	public int eviaDiasPrevidenciarios(Date inicio, Date fim){
		Averbacao a = new Averbacao();
		a.setDATA_admissao(inicio);
		a.setDATA_demissao(fim);
		List<Averbacao> l = new ArrayList<Averbacao>();
		l.add(a);
		ftpt = new FormataTemposPrevidenciariosThread(l);
		ftpt.run();
		return ftpt.retornaDiasPrevidenciarios();
	}

}
