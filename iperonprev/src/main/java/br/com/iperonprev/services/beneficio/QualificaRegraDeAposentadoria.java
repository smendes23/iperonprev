package br.com.iperonprev.services.beneficio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.com.iperonprev.constantes.RegrasAposentadoriaEnum;
import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;

public class QualificaRegraDeAposentadoria {
		
	public Map<String,Object> devolveProventosEReajuste(RegrasAposentadoriaEnum regraAposentadoria){
			
		RegrasAposentadoria artigo2 = new Artigo02();
		RegrasAposentadoria artigo3 = new Artigo03();
		RegrasAposentadoria artigo6 = new Artigo06();
		RegrasAposentadoria artigo8i = new Artigo08I();
		RegrasAposentadoria artigo8p = new Artigo08P();
		RegrasAposentadoria artigo40a = new Artigo40A();
		RegrasAposentadoria artigo40b = new Artigo40B();
		RegrasAposentadoria artigo40a20 = new Artigo40A20();
		RegrasAposentadoria artigo40b20 = new Artigo40B20();
		RegrasAposentadoria artigoPcI = new ArtigoPcI();
		RegrasAposentadoria artigoPcP = new ArtigoPcP();
		RegrasAposentadoria nulo = new ArtigoNulo();
		
		artigo2.proximoObject(artigo3);
		artigo3.proximoObject(artigo6);
		artigo6.proximoObject(artigo8i);
		artigo8i.proximoObject(artigo8p);
		artigo8p.proximoObject(artigo40a);
		artigo40a.proximoObject(artigo40b);
		artigo40b.proximoObject(artigo40a20);
		artigo40a20.proximoObject(artigo40b20);
		artigo40b20.proximoObject(artigoPcI);
		artigoPcI.proximoObject(artigoPcP);
		artigoPcP.proximoObject(nulo);
		
		return artigo2.verificaProventosEReajuste(regraAposentadoria);
	}
	
	
	public List<Simulador> devolveRegrasDeAposentadoria(PessoasFuncionais obj){
		List<Simulador> listaSimulador = new ArrayList<Simulador>();
		List<RegrasAposentadoria> listaRegras = listaRegrasAposentadoria();
		
		if(StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(), "policia")){
			listaRegras.add(new ArtigoPcI());
			listaRegras.add(new ArtigoPcP());
		}
		List<Averbacao> listaAverbacao = new ArrayList<>();
		try{
			
			listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", obj.getNUMG_idDoObjeto());
		}catch(Exception e){
			System.out.println("Não foi possível carregar lista de Averbação");
		}
		for (RegrasAposentadoria ra : listaRegras) {
			ra.verificaRegra(obj,listaAverbacao);
			listaSimulador.add(ra.getSimulador());
		}
		
		return listaSimulador;
	}
	
	
	private List<RegrasAposentadoria> listaRegrasAposentadoria(){
		List<RegrasAposentadoria> listaRegras = new ArrayList<>();
		listaRegras.add(new Artigo06());
		listaRegras.add(new Artigo02());
		listaRegras.add(new Artigo40B());
		listaRegras.add(new Artigo40A());
		listaRegras.add(new Artigo08P());
		listaRegras.add(new Artigo08I());
		listaRegras.add(new Artigo40B20());
		listaRegras.add(new Artigo40A20());
		listaRegras.add(new Artigo03());
		listaRegras.add(new RegraAposentadoriaPorInvalidez());
		listaRegras.add(new RegraAposentadoriaCompulsoria());
		return listaRegras;
	}
	
}
