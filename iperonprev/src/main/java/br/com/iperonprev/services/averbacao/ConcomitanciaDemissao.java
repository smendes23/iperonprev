package br.com.iperonprev.services.averbacao;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposConcomitantesDuasAverbacoes;
import br.com.iperonprev.models.Averbacao;

public class ConcomitanciaDemissao implements TemposConcomitantesDuasAverbacoes {
	private TemposConcomitantesDuasAverbacoes proximo;

	@Override
	public boolean verificaConcomitancia(Averbacao a1,int diasPeriodo1,Averbacao a2,int diasPeriodo2) {

		try {
			
			Averbacao averbacao = new Averbacao();
			
			
		        if (a1.getDATA_admissao().compareTo(a2.getDATA_demissao()) < 0 && 
		          a1.getDATA_demissao().compareTo(a2.getDATA_demissao()) > 0) {
		        	
			          if (a1.isFLAG_concomitado() == false)
			          {
			            a2.setDATA_inicioConcomitancia(a1.getDATA_admissao());
			            a2.setDATA_fimConcomitancia(a2.getDATA_demissao());
			            a2.setFLAG_concomitado(true);
			            averbacao = new QualificaTempoDeContribuicao().executa(a2, a2.getNUMR_deducao());
			            
			            new GenericPersistence<Averbacao>(Averbacao.class).salvar(averbacao);
			            
			            return true;
			          }/*else {
			        	  
			        	  if(Days.daysBetween(new LocalDate(a2.getDATA_admissao()), new LocalDate(a2.getDATA_demissao())).getDays() > 
							Days.daysBetween(new LocalDate(a2.getDATA_inicioConcomitancia()), new LocalDate(a2.getDATA_fimConcomitancia())).getDays()) {
			        		  
			        		  a2.setDATA_inicioConcomitancia(a1.getDATA_admissao());
			        		  averbacao = new QualificaTempoDeContribuicao().executa(a2, a2.getNUMR_deducao());
					            
					            new GenericPersistence<Averbacao>(Averbacao.class).salvar(averbacao);
					            return true;
			        		  
			        	  }
			          }*/
		          
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
