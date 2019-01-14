package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposConcomitantesDuasAverbacoes;
import br.com.iperonprev.models.Averbacao;

public class ConcomitanciaDemissao implements TemposConcomitantesDuasAverbacoes {
	private TemposConcomitantesDuasAverbacoes proximo;

	@Override
	public boolean verificaConcomitancia(Averbacao a1,int diasPeriodo1,Averbacao a2,int diasPeriodo2) {

		try {
			
			if (diasPeriodo1 > diasPeriodo2) {
		        if (a1.getDATA_admissao().compareTo(a2.getDATA_demissao()) < 0 && 
		          a1.getDATA_demissao().compareTo(a2.getDATA_demissao()) > 0) {
		          if (a1.isFLAG_concomitado() == false)
		          {
		            Averbacao averbacao = new Averbacao();
		            a2.setDATA_inicioConcomitancia(a1.getDATA_admissao());
		            a2.setDATA_fimConcomitancia(a2.getDATA_demissao());
		            a2.setFLAG_concomitado(true);
		            averbacao = new QualificaTempoDeContribuicao().executa(a2, a2.getNUMR_deducao());
		            
		            new GenericPersistence<Averbacao>(Averbacao.class).salvar(averbacao);
		            
		            return true;
		          }
		        }
		      }

		} catch (Exception e) {
			System.out.println("Erro ao converter data");
		}

		return proximo.verificaConcomitancia(a1,diasPeriodo1, a2,diasPeriodo2);
	}

	@Override
	public void setProximaConcomitancia(TemposConcomitantesDuasAverbacoes proximo) {
		this.proximo = proximo;
	}

}
