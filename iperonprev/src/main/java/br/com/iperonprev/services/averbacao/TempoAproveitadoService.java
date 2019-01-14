package br.com.iperonprev.services.averbacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposAverbacao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;



public class TempoAproveitadoService implements TemposAverbacao{
	ModificadorDeAcessoDiaMesAno mda;
	FormataTemposConcomitantesThread ftc;
	FormataTemposPrevidenciariosThread ftpt;
	Averbacao a;
	List<Averbacao> l;
	
	int ano,mes,dia ;
	
	@SuppressWarnings("static-access")
	@Override
	public String retornaTempoPrevidenciario(PessoasFuncionais funcional) {
		ftpt = new FormataTemposPrevidenciariosThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftpt.run();
		ModificadorDeAcessoDiaMesAno mdaFtpt = new ModificadorDeAcessoDiaMesAno(ftpt.getDia(), ftpt.getMes(), ftpt.getAno());
		
		ftc = new FormataTemposConcomitantesThread(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		ftc.run();
		ModificadorDeAcessoDiaMesAno mdaFtc = new ModificadorDeAcessoDiaMesAno(ftc.getDia(), ftc.getMes(), ftc.getAno());
		
		Date dt = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).toDate();
		Date dt2 = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).plusDays(722).toDate();
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

	@Override
	public int retornaDiasDoTempoPrevidenciario(PessoasFuncionais funcional) {
		/*ftpt = new FormataTemposPrevidenciariosThread(new RgpsDao().retornaListaRgps("pessoaFuncional", funcional.getId()));
		ftpt.run();
		
		FormataTemposPrevidenciariosThread ft2 = new FormataTemposPrevidenciariosThread(new RppsDao().retornaListaRpps("pessoaFuncional", funcional.getId()));
		ft2.run();
		
		ftc = new FormataTemposConcomitantesThread(new GenericDao<Averbacao>(Averbacao.class).listaRelacionamento("Averbacao", "pessoa", funcional.getPessoa_id()));
		ftc.run();
		
		return (ftpt.retornaDiasPrevidenciarios() + ft2.retornaDiasPrevidenciarios()) - ftc.retornaDiasPrevidenciarios();*/
		return 0;
	}

	@Override
	public String enviaTempoPrevidenciario(Date inicio, Date fim) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@SuppressWarnings("static-access")
	public String enviaTempoAproveitadoDeduzido(int dias, Date inicio,Date fim){

		Date dt = new LocalDate().fromDateFields(inicio).toDate();
		Date dt2 = new LocalDate().fromDateFields(fim).minusDays((dias)).toDate();
		List<Averbacao> l = new ArrayList<Averbacao>();
		Averbacao a = new Averbacao();
		a.setDATA_inicioConcomitancia(dt);
		a.setDATA_fimConcomitancia(dt2);
		l.add(a);
		
		ftc = new FormataTemposConcomitantesThread(l);
		ftc.run();
		
		CalculaTempoLiquidoComDeducao ctl = new CalculaTempoLiquidoComDeducao(new ModificadorDeAcessoDiaMesAno(ftc.getDia(), ftc.getMes(), ftc.getAno()));
		return ctl.retornaCalculoTempoAproveitado();
	}
	
	@SuppressWarnings("static-access")
	public String devolveTempoAproveitado(int dias, PessoasFuncionais funcional){
		Date dt = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).toDate();
		Date dt2 = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).plusDays(dias).toDate();
		
		List<Averbacao> l = new ArrayList<Averbacao>();
		Averbacao a = new Averbacao();
		a.setDATA_inicioConcomitancia(dt);
		a.setDATA_fimConcomitancia(dt2);
		l.add(a);
		
		ftc = new FormataTemposConcomitantesThread(l);
		ftc.run();
		
		CalculaTempoLiquidoComDeducao ctl = new CalculaTempoLiquidoComDeducao(new ModificadorDeAcessoDiaMesAno(ftc.getDia(), ftc.getMes(), ftc.getAno()));
		return ctl.retornaCalculoTempoAproveitado();
	}
	

}
