package br.com.iperonprev.services.averbacao;

import org.joda.time.LocalDate;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposConcomitanteUmaAverbacao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class ConcomitanciaDataExoneracaoComDataPosse implements TemposConcomitanteUmaAverbacao{

	private TemposConcomitanteUmaAverbacao proximo;
	
	@Override
	public boolean verificaConcomitancia(Averbacao a1) {
		try{
			Averbacao av = new GenericPersistence<Averbacao>(Averbacao.class).buscarPorId(a1.getNUMG_idDoObjeto());

		 if(av.getNUMR_pessoasFuncionais().getDATA_efetivoExercicio().compareTo(av.getDATA_demissao()) < 0 ){
				 
				 if(av.isFLAG_concomitado() == true && 
						 RetornaTempos.retornaDiasApartirDeDuasDatas(av.getDATA_inicioConcomitancia(),av.getDATA_fimConcomitancia()) < RetornaTempos.retornaDiasApartirDeDuasDatas(av.getDATA_admissao(),av.getDATA_demissao())) {
					 
					 av.setDATA_fimConcomitancia(new LocalDate(av.getDATA_fimConcomitancia())
								.plusDays(RetornaTempos.retornaDiasApartirDeDuasDatas(av.getNUMR_pessoasFuncionais().getDATA_efetivoExercicio(), av.getDATA_demissao())).toDate());
					 System.out.println("Concomitância: "+ RetornaTempos.retornaDiasApartirDeDuasDatas(av.getDATA_inicioConcomitancia(),av.getDATA_fimConcomitancia()));
					 System.out.println("Nova: "+RetornaTempos.retornaDiasApartirDeDuasDatas(av.getNUMR_pessoasFuncionais().getDATA_efetivoExercicio(), av.getDATA_demissao()));
				 }else {
					 av.setDATA_inicioConcomitancia(av.getNUMR_pessoasFuncionais().getDATA_efetivoExercicio());
					 av.setDATA_fimConcomitancia(av.getDATA_demissao());
					 
				 }
				 av.setFLAG_concomitado(true);
				 Averbacao averbacao = new QualificaTempoDeContribuicao().executa(av, av.getNUMR_deducao());
				 new GenericPersistence<Averbacao>(Averbacao.class).salvar(averbacao);
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
