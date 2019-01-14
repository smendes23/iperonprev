package br.com.iperonprev.services.beneficio.simulador;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.RegrasAposentadoria;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.beneficio.Artigo02;
import br.com.iperonprev.services.beneficio.Artigo03;
import br.com.iperonprev.services.beneficio.Artigo06;
import br.com.iperonprev.services.beneficio.Artigo08I;
import br.com.iperonprev.services.beneficio.Artigo08P;
import br.com.iperonprev.services.beneficio.Artigo40A;
import br.com.iperonprev.services.beneficio.Artigo40A20;
import br.com.iperonprev.services.beneficio.Artigo40B;
import br.com.iperonprev.services.beneficio.Artigo40B20;
import br.com.iperonprev.services.beneficio.ArtigoPcI;
import br.com.iperonprev.services.beneficio.ArtigoPcP;
import br.com.iperonprev.services.beneficio.RegraAposentadoriaCompulsoria;
import br.com.iperonprev.services.beneficio.RegraAposentadoriaPorInvalidez;
import net.sf.jasperreports.engine.JRDataSource;

public class SimuladorHelper {

	PessoasFuncionais obj = new PessoasFuncionais();
	List<Simulador> listaSimulador = new ArrayList<Simulador>();
	
	public SimuladorHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public SimuladorHelper(PessoasFuncionais pessoasFuncionais) {
		this.obj = pessoasFuncionais;
	}
	
	public static String[] camposRegrasAposentadoria(){
		String[] campos= new String[]{
				"regraAposentadoria", 
				"tipoProventos",
				"descricaoRegra",
				"requisito1", 
				"requisito2", 
				"requisito3", 
				"requisito4", 
				"requisito5", 
				"requerido1", 
				"requerido2",
				"requerido3", 
				"requerido4", 
				"requerido5", 
				"faltante1", 
				"faltante2", 
				"faltante3", 
				"faltante4",
				"faltante5", 
				"status"};
		
		return campos;
	}
	
	public  SimuladorHelper devolveRegrasDeAposentadoria(){

		List<RegrasAposentadoria> listaRegras = listaRegrasAposentadoria();
		
		if(StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(), "policia")){
			listaRegras.add(new ArtigoPcI());
			listaRegras.add(new ArtigoPcP());
		}
		List<Averbacao> listaAverbacao = new ArrayList<>();
		try{
			listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", obj.getNUMG_idDoObjeto());
		}catch(Exception e){
			
		}
		
		for (RegrasAposentadoria ra : listaRegras) {
			ra.verificaRegra(obj,listaAverbacao);
			
			listaSimulador.add(ra.getSimulador());
		}
		
		return this;
	}
	
	
	private static List<RegrasAposentadoria> listaRegrasAposentadoria(){
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
	
	public List<JRDataSource> listaDataSourceRegrasAposentadoria(){
		
		List<JRDataSource> lista = new ArrayList<>();
//		lista.add(new DataSourceArt06().devolveDatasource(listaSimulador.get(0)));
		/*lista.add(new DataSourceArt02().devolveDatasource(listaSimulador.get(1)));
		lista.add(new DataSourceArt40B().devolveDatasource(listaSimulador.get(2)));
		lista.add(new DataSourceArt40A().devolveDatasource(listaSimulador.get(3)));
		lista.add(new DataSourceArt08P().devolveDatasource(listaSimulador.get(4)));
		lista.add(new DataSourceArt08I().devolveDatasource(listaSimulador.get(5)));
		lista.add(new DataSourceArt40B20().devolveDatasource(listaSimulador.get(6)));
		lista.add(new DataSourceArt40A20().devolveDatasource(listaSimulador.get(7)));
		lista.add(new DataSourceArt03().devolveDatasource(listaSimulador.get(8)));
		lista.add(new DataSourceArtInvalidez().devolveDatasource(listaSimulador.get(9)));
		lista.add(new DataSourceArtCompulsoria().devolveDatasource(listaSimulador.get(10)));
		if(StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(), "policia")){
			lista.add(new DataSourceArtPcI().devolveDatasource(listaSimulador.get(11)));
			lista.add(new DataSourceArtPcP().devolveDatasource(listaSimulador.get(12)));
		}*/
		return lista;
	}
}
