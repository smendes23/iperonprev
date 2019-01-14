package br.com.iperonprev.services.averbacao;

import org.joda.time.LocalDate;

import br.com.iperonprev.interfaces.TempoDeContribuicao;
import br.com.iperonprev.models.Averbacao;

public class TempoSemConcomitancia implements TempoDeContribuicao{
	TempoDeContribuicao tempoDeContribuicao;
	LocalDate dataLocal = new LocalDate();
	@Override
	public Averbacao devolvePeriodo(Averbacao a,int outrasDeducoes) {
		if(a.getDATA_inicioConcomitancia() == null && a.getDATA_fimConcomitancia() == null && a.getNUMR_deducao() == 0){
			
			AgrupaTemposPrevidenciarios atp = new AgrupaTemposPrevidenciarios(a.getNUMR_pessoasFuncionais());
			a.setNUMR_diasContribuicao(atp.enviaDiasAverbados(a.getDATA_admissao(), a.getDATA_demissao()));
			a.setDESC_tempoContribuicao(atp.enviaAverbacao(a.getDATA_admissao(),new LocalDate(a.getDATA_demissao()).plusDays(1).toDate()));
			a.setDESC_tempoAproveitado(atp.enviaAverbacao(a.getDATA_admissao(),new LocalDate( a.getDATA_demissao()).plusDays(1).toDate()));
			return a;
		}
		return tempoDeContribuicao.devolvePeriodo(a,outrasDeducoes);
	}

	@Override
	public void proximoPerido(TempoDeContribuicao tempoDeContribuicao) {
		this.tempoDeContribuicao = tempoDeContribuicao;
	}

}
