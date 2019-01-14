package br.com.iperonprev.services.averbacao;

import org.joda.time.LocalDate;

import br.com.iperonprev.interfaces.TempoDeContribuicao;
import br.com.iperonprev.models.Averbacao;

public class TempoComDeducao implements TempoDeContribuicao{
	TempoDeContribuicao tempoDeContribuicao;
	LocalDate dataLocal = new LocalDate();
	@Override
	public Averbacao devolvePeriodo(Averbacao a,int outrasDeducoes) {
		if(a.getNUMR_deducao() >= 0 && a.getDATA_inicioConcomitancia() == null && a.getDATA_fimConcomitancia() == null){
			
			AgrupaTemposPrevidenciarios atp = new AgrupaTemposPrevidenciarios(a.getNUMR_pessoasFuncionais());
			a.setNUMR_diasContribuicao(atp.enviaDiasAverbados(a.getDATA_admissao(), a.getDATA_demissao()));
			a.setDESC_tempoContribuicao(atp.enviaAverbacao(a.getDATA_admissao(), a.getDATA_demissao()));
			a.setDESC_tempoAproveitado(atp.enviaTempoAproveitadoComDeducao(a.getNUMR_deducao(), a.getDATA_admissao(), a.getDATA_demissao()));
			a.setDATA_inicioTempoAproveitado(a.getDATA_admissao());
			a.setDATA_fimTempoAproveitado(new LocalDate(a.getDATA_admissao()).plusDays(a.getNUMR_diasContribuicao() - a.getNUMR_deducao()).toDate());
			return a;
		}
		return tempoDeContribuicao.devolvePeriodo(a,outrasDeducoes);
	}

	@Override
	public void proximoPerido(TempoDeContribuicao tempoDeContribuicao) {
		this.tempoDeContribuicao = tempoDeContribuicao;
	}

}
