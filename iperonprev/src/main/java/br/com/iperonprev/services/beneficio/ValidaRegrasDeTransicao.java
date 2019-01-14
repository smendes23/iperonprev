
package br.com.iperonprev.services.beneficio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class ValidaRegrasDeTransicao {
	
	public  List<RegrasAposentadoriaEnum> devolveListaDeRegrasAposentadoria(PessoasFuncionais funcionais, List<RegrasAposentadoria> listaDeRegras){
		List<RegrasAposentadoriaEnum> lista = new ArrayList<RegrasAposentadoriaEnum>();
		List<Averbacao> listaAverbacao = new ArrayList<>();
		try{
			
			listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcionais.getNUMG_idDoObjeto());
		}catch(Exception e){
			System.out.println("Não foi possível carregar lista de Averbação");
		}
		
		for (RegrasAposentadoria l : listaDeRegras) {
			lista.add(l.verificaRegra(funcionais,listaAverbacao));
		}
		
		lista.removeAll(Collections.singleton(null));
		return lista;
	}
	
	public List<RegrasAposentadoria> listaRegrasIdadeEContribuicao(){
		List<RegrasAposentadoria> listaRegrasDeAposentadoria = new ArrayList<>();
		listaRegrasDeAposentadoria.add(new Artigo02());
		listaRegrasDeAposentadoria.add(new Artigo03());
		listaRegrasDeAposentadoria.add(new Artigo06());
		listaRegrasDeAposentadoria.add(new Artigo08I());
		listaRegrasDeAposentadoria.add(new Artigo08P());
		listaRegrasDeAposentadoria.add(new Artigo40A());
		listaRegrasDeAposentadoria.add(new Artigo40A20());
		listaRegrasDeAposentadoria.add(new Artigo40B20());
		return listaRegrasDeAposentadoria;
	}
	
	public List<RegrasAposentadoria> listaRegrasIdade(){
		List<RegrasAposentadoria> listaRegrasDeAposentadoria = new ArrayList<>();
		listaRegrasDeAposentadoria.add(new Artigo40B());
		return listaRegrasDeAposentadoria;
	}

	public List<RegrasAposentadoriaEnum> devolveListaDeRegrasDisponiveis(){
		List<RegrasAposentadoriaEnum> lista= new ArrayList<>();
		lista.add(RegrasAposentadoriaEnum.ARTIGO2);
		lista.add(RegrasAposentadoriaEnum.ARTIGO3);
		lista.add(RegrasAposentadoriaEnum.ARTIGO6);
		lista.add(RegrasAposentadoriaEnum.ARTIGO8P);
		lista.add(RegrasAposentadoriaEnum.ARTIGO8I);
		lista.add(RegrasAposentadoriaEnum.ARTIGO40A20);
		lista.add(RegrasAposentadoriaEnum.ARTIGO40B20);
		lista.add(RegrasAposentadoriaEnum.ARTIGO40A);
		return lista;
	}
}
