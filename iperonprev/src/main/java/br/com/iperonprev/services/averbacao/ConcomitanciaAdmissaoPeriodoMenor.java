package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.helper.DataHelper;
import br.com.iperonprev.interfaces.TemposConcomitantesDuasAverbacoes;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class ConcomitanciaAdmissaoPeriodoMenor implements TemposConcomitantesDuasAverbacoes{
	private TemposConcomitantesDuasAverbacoes proximo;
	@Override
	public boolean verificaConcomitancia(Averbacao a1,int diasPeriodo1,Averbacao a2,int diasPeriodo2) {
		
		try {
			
			if(RetornaTempos.retornaDiasApartirDeDuasDatas(a1.getDATA_admissao(), a1.getDATA_demissao()) <
					RetornaTempos.retornaAnosApartirDeDuasDatas(a2.getDATA_admissao(), a2.getDATA_demissao())

					&& a1.getDATA_admissao().before(a2.getDATA_admissao())
					&& a1.getDATA_demissao().after(a2.getDATA_admissao())
					&& DataHelper.verificaDataNula(a2.getDATA_inicioConcomitancia())  == false){
				
					a1.setDATA_inicioConcomitancia(a2.getDATA_admissao());
					a1.setDATA_fimConcomitancia(a1.getDATA_demissao());
					
					a1.setDATA_inicioTempoAproveitado(a1.getDATA_demissao());
					a1.setDATA_fimTempoAproveitado(a2.getDATA_demissao());
					
					Averbacao averbacao = new QualificaTempoDeContribuicao().executa(a1, a1.getNUMR_deducao());
					new GenericPersistence<Averbacao>(Averbacao.class).salvar(averbacao);
					return true;
					
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
