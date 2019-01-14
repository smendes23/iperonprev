package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposConcomitantesDuasAverbacoes;
import br.com.iperonprev.models.Averbacao;

public class ConcomitanciaDataNula implements TemposConcomitantesDuasAverbacoes{
	private TemposConcomitantesDuasAverbacoes proximo;
	@Override
	public boolean verificaConcomitancia(Averbacao a1,int diasPeriodo1,Averbacao a2,int diasPeriodo2) {
		try{
			if(a1.getDATA_inicioConcomitancia() == null
					&& a1.getDATA_fimConcomitancia() == null
					/*&& a1.getDATA_inicioTempoAproveitado() == null
					&& a1.getDATA_fimTempoAproveitado() == null*/){
				a1.setDATA_inicioTempoAproveitado(a1.getDATA_admissao());
				a1.setDATA_fimTempoAproveitado(a1.getDATA_demissao());
				Averbacao averbacao = new QualificaTempoDeContribuicao().executa(a1, a1.getNUMR_deducao());
				new GenericPersistence<Averbacao>(Averbacao.class).salvar(averbacao);
				return true;
			}
		}catch(Exception e){
			System.out.println("Data nula");
		}
		return proximo.verificaConcomitancia(a1,diasPeriodo1, a2,diasPeriodo2);
	}

	@Override
	public void setProximaConcomitancia(TemposConcomitantesDuasAverbacoes proximo) {
		this.proximo = proximo;
	}

}
