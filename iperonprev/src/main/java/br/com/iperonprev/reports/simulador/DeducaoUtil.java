package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.dao.AfastamentoLicencaDao;
import br.com.iperonprev.dao.DeducaoDao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.averbacao.DiaMesAno;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;

public class DeducaoUtil {

	private PessoasFuncionais pf;
	public DeducaoUtil(){}
	public DeducaoUtil(PessoasFuncionais pf){
		this.pf = pf;
		
	}
	
	public List<AfastamentosLicenca> getListaDeAfastamentos(){
		return new AfastamentoLicencaDao().devolveListaDeAfastamentosInteresseParticular(pf.getNUMG_idDoObjeto());
	}
	
	public List<Deducao> getListaDeFaltas(){
		return new DeducaoDao().devolveFaltas(pf.getNUMG_idDoObjeto());
	}
	
	public List<DiaMesAno> devolveListaDiaMesAnoAfastamentos(){
		List<DiaMesAno> lista = new ArrayList<>();
		try{
			for (AfastamentosLicenca afastamento : getListaDeAfastamentos()) {
				lista.add(new RetornaTemposAverbacao().devolveDiaMesAno(afastamento.getDATA_inicioLicenca(), afastamento.getDATA_fimLicenca()));
			}
		}catch(Exception e){
			System.out.println("Não foi possivel gerar lista de dia mes e ano.");
		}
		
		return lista;
	}
	
	public List<DiaMesAno> devolveListaDiaMesAnoFaltas(){
		List<DiaMesAno> lista = new ArrayList<>();
		try{
			for (Deducao deducao : getListaDeFaltas()) {
				lista.add(new RetornaTemposAverbacao().devolveDiaMesAno(deducao.getDATA_inicio(), deducao.getDATA_fim()));
			}
		}catch(Exception e){
			System.out.println("Não foi possivel modelar dia mes ano faltas");
		}
		
		return lista;
	}
	
	
}
