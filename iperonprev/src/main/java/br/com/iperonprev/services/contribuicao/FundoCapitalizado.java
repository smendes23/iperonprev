package br.com.iperonprev.services.contribuicao;

import java.text.SimpleDateFormat;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.FundoPrevidenciarioInterface;
import br.com.iperonprev.models.FundoPrevidenciario;
import br.com.iperonprev.models.PessoasFuncionais;

public class FundoCapitalizado implements FundoPrevidenciarioInterface {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public PessoasFuncionais calcula(PessoasFuncionais funcional) {
		
		try{
			if(funcional.getDATA_efetivoExercicio().after(sdf.parse("20/03/2016")) && funcional.getNUMR_situacaoPrevidenciaria().getNUMR_codigo() == 0 && funcional.getNUMR_vinculoPrevidenciario().getNUMR_codigo() == 0){
				funcional.setNUMR_fundoPrevidenciario(new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).buscarPorId(1));
			}
			
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public void setProximoCalculo(FundoPrevidenciarioInterface proximo) {
		// TODO Auto-generated method stub
		
	}

}
