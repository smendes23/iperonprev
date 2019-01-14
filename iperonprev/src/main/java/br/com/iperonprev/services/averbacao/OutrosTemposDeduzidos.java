package br.com.iperonprev.services.averbacao;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposAverbacao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class OutrosTemposDeduzidos implements TemposAverbacao{

	@Override
	public String retornaTempoPrevidenciario(PessoasFuncionais funcional) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int retornaDiasDoTempoPrevidenciario(PessoasFuncionais funcional) {
		int dia = 0;
		List<Averbacao> la = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto());
		for (Averbacao averbacao : la) {
			dia += averbacao.getNUMR_deducao();
		}
		return dia;
	}

	@Override
	public String enviaTempoPrevidenciario(Date inicio, Date fim) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("static-access")
	public String devolveOutrasDeducoes(PessoasFuncionais funcional){
		
		Date dt = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).toDate();
		Date dt2 = new LocalDate().fromDateFields(funcional.getDATA_efetivoExercicio()).plusDays(retornaDiasDoTempoPrevidenciario(funcional)).toDate();
		FormataAnoMesDia fa = new FormataAnoMesDia(dt, dt2);
		return fa.devolveAnoMesDiasFormatadosSemParametros();
	}

}
