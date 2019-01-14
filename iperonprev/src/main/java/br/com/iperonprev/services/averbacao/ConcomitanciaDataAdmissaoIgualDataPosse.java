package br.com.iperonprev.services.averbacao;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposConcomitanteUmaAverbacao;
import br.com.iperonprev.models.Averbacao;

public class ConcomitanciaDataAdmissaoIgualDataPosse implements TemposConcomitanteUmaAverbacao{

	private TemposConcomitanteUmaAverbacao proximo;
	
	@Override
	public boolean verificaConcomitancia(Averbacao a1) {
		try{
			if(a1.getNUMR_pessoasFuncionais().getDATA_efetivoExercicio().compareTo(a1.getDATA_admissao()) == 0 ){
				
				a1.setDATA_inicioConcomitancia(a1.getDATA_admissao());
				a1.setDATA_fimConcomitancia(a1.getDATA_demissao());
				a1.setFLAG_concomitado(true);
				Averbacao averb = new QualificaTempoDeContribuicao().executa(a1, a1.getNUMR_deducao());
				averb.setNUMR_diasContribuicao(0);
				averb.setDESC_tempoAproveitado("0 ano(s), 0 mes(es), 0 dia(s).");
				new GenericPersistence<Averbacao>(Averbacao.class).salvar(averb);
				return true;
				
			}
		}catch(Exception e){
			System.out.println("Não foi possível gerar Concomitância com a Data de Ingresso!");
		}
		return proximo.verificaConcomitancia(a1);
	}
	
	@SuppressWarnings("unused")
	private boolean verificaDataNula(Averbacao av){
		boolean res = true;
		try{
			if(av.getDATA_demissao().compareTo(av.getDATA_fimConcomitancia()) != 0){
				res = false;
			}
		}catch(Exception e){
			res = false;
		}
		return res;
	}

	@Override
	public void setProximaConcomitancia(TemposConcomitanteUmaAverbacao proximo) {
		this.proximo = proximo;
	}

}
