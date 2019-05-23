package br.com.iperonprev.helper;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import br.com.iperonprev.controller.dto.ContribuicaoDto;
import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.services.contribuicao.QualificaCalculoContribuicao;
import br.com.iperonprev.util.jsf.Message;

public class ContribuicaoHelper {

	public static void criaContribuicao(PessoasFuncionais pesf){
		RemuneracaoDao rem = new RemuneracaoDao(); 
		List<Remuneracoes> listaRemuneracoes = new ArrayList<>();
		List<Remuneracoes> listaRemuneracoesSemDuplicidade = new ArrayList<>();
		
		try{
			
			if(rem.verificaSeExisteRemuneracoes(pesf.getNUMG_idDoObjeto()) == true){
				if(rem.verificaSeExisteRegistroNaTabela(pesf.getNUMG_idDoObjeto()) == false){
				
					
					listaRemuneracoes = new RemuneracaoDao().devolveListaRemuneracoesContribuicao(pesf.getNUMG_idDoObjeto());
					
					listaRemuneracoesSemDuplicidade = listaRemuneracoes.stream()
							.collect(collectingAndThen(toCollection(() -> new TreeSet<Remuneracoes>(new Comparator<Remuneracoes>() {

								@Override
								public int compare(Remuneracoes o1, Remuneracoes o2) {
									return o1.getNUMR_competencia().compareTo(o2.getNUMR_competencia());
								}
							})),
                                    ArrayList::new));

					for (Remuneracoes r : listaRemuneracoesSemDuplicidade) {
							ContribuicoeseAliquotas ca = new ContribuicoeseAliquotas();
							ca.setDESC_competencia(r.getNUMR_competencia());
							ca.setNUMR_idPessoasFuncionais(r.getNUMR_idDoObjetoFuncional());
//							ca = new QualificaCalculoContribuicao().executa(ca,r.getNUMR_idDoObjetoFuncional().getDATA_efetivoExercicio(),r.getVALR_remuneracao(),false);
							new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).salvar(ca);
					}
				}
			}else{
				Message.addErrorMessage("Não existem remunerações para este funcional");
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao Gerar Contribuições");
		}
	}
	
	public static boolean existeContribuicao(int idFuncional){
		boolean res = false;
		res = new ContribuicaoDao().verificaExistenciaContribuicao(idFuncional);
		return res;
	}
	
	public List<ContribuicaoDto> listaDeContribuicoesRemuneracao(PessoasFuncionais pf){
		List<ContribuicaoDto> lista = new ArrayList<>();
		List<ContribuicaoDto> listaRes = new ArrayList<>();
		try {
			lista = new RemuneracaoDao().listaRemuneracoesContribuicoes(pf);
			
			for (ContribuicaoDto c : lista) {
				
				ContribuicaoDto contrib = new ContribuicaoDto();
				contrib  =new QualificaCalculoContribuicao()
				.executa(c,pf.getDATA_efetivoExercicio(),c.getVALR_contribSegurado(),true);
				
				listaRes.add(contrib);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaRes;
	}
	
}

/*
 * Lógica para agrupa
 * int count = 0;
Remuneracoes remuneracao2 = new Remuneracoes();
for (Remuneracoes remuneracao : listaRemuneracoes) {
	try{
		if(!new StringUtils().contains(remuneracao2.getNUMR_competencia(), remuneracao.getNUMR_competencia())){
			listaRemuneracoesSemDuplicidade.add(count, remuneracao);
		}else{
			System.out.println("Sim");
			Remuneracoes remuneracao3 = new Remuneracoes();
			remuneracao3 = remuneracao2;
			remuneracao3.setVALR_remuneracao(remuneracao.getVALR_remuneracao().add(remuneracao2.getVALR_remuneracao()));
			listaRemuneracoesSemDuplicidade.set(count-1, remuneracao3);
			count = count -1;
		}
		remuneracao2 = remuneracao;
		count ++;
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	
}*/
