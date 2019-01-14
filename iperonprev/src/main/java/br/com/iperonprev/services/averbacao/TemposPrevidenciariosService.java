package br.com.iperonprev.services.averbacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.TemposAverbacao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;


public class TemposPrevidenciariosService implements TemposAverbacao{
	
	FormataTemposPrevidenciariosThread ftpt;
	
	@Override
	public String retornaTempoPrevidenciario(PessoasFuncionais funcional) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int retornaDiasDoTempoPrevidenciario(PessoasFuncionais funcional) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String enviaTempoPrevidenciario(Date inicio, Date fim) {
		// TODO Auto-generated method stub
		return null;
	}

	public String retornaTempoDeContribuicao(PessoasFuncionais funcional){
		List<Averbacao> listaAverbacao = new ArrayList<Averbacao>();
		listaAverbacao.addAll(new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", funcional.getNUMG_idDoObjeto()));
		
		Averbacao a = new Averbacao();
		a.setDATA_admissao(funcional.getDATA_efetivoExercicio());
		a.setDATA_demissao(new Date());
		listaAverbacao.add(a);
		
		ftpt = new FormataTemposPrevidenciariosThread(listaAverbacao);
		ftpt.run();
		return ftpt.retornaTempoPrevidenciario();
	}
	
	public String retornaTempoNoCargoPublico(PessoasFuncionais funcional){
		/*List<Averbacao> listaAverbacao = new ArrayList<Averbacao>();
		listaAverbacao.addAll(new RppsDao().retornaListaRpps("pessoaFuncional", funcional.getNUMG_idDoObjeto()));
		
		Averbacao a = new Averbacao();
		a.setDataAdmissao(funcional.getDATA_posse());
		a.setDataDemissao(new Date());
		listaAverbacao.add(a);
		
		ftpt = new FormataTemposPrevidenciariosThread(listaAverbacao);
		ftpt.run();
		return ftpt.retornaTempoPrevidenciario();*/
		return null;
	}
	
	public String retornaTempoNoCargo(PessoasFuncionais funcional){
		List<Averbacao> listaAverbacao = new ArrayList<Averbacao>();
		
		Averbacao a = new Averbacao();
		a.setDATA_admissao(funcional.getDATA_efetivoExercicio());
		a.setDATA_demissao(new Date());
		listaAverbacao.add(a);
		
		ftpt = new FormataTemposPrevidenciariosThread(listaAverbacao);
		ftpt.run();
		return ftpt.retornaTempoPrevidenciario();
	}
	
}
