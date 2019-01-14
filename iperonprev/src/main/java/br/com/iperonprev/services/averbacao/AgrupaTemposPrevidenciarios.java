package br.com.iperonprev.services.averbacao;

import java.util.Date;

import br.com.iperonprev.interfaces.TemposAverbacao;
import br.com.iperonprev.models.PessoasFuncionais;


public class AgrupaTemposPrevidenciarios {

	TemposAverbacao tp;
	PessoasFuncionais funcional = new PessoasFuncionais();
	
	public AgrupaTemposPrevidenciarios(){}
	
	public AgrupaTemposPrevidenciarios(PessoasFuncionais funcional){
		this.funcional = funcional;
	}
	
	public String devolveAverbacaoGeral(){
		tp = new AverbacaoGeralService();
		return tp.retornaTempoPrevidenciario(funcional);
	}
	
	public String enviaAverbacao(Date inicio, Date fim){
		tp = new AverbacaoGeralService();
		return tp.enviaTempoPrevidenciario(inicio, fim);
	}
	
	public int devolveTotalDiasAverbacaoGeral(){
		tp = new AverbacaoGeralService();
		return tp.retornaDiasDoTempoPrevidenciario(funcional);
	}
	
	public int enviaDiasAverbados(Date inicio, Date fim){
		AverbacaoGeralService ag = new AverbacaoGeralService();
		return ag.eviaDiasPrevidenciarios(inicio, fim);
	}
	
		
	public String devolveTempoAproveitado(){
		tp = new TempoAproveitadoService();
		return tp.retornaTempoPrevidenciario(funcional);
	}
	
	public int devolveDiasAproveitados(){
		tp = new TempoAproveitadoService();
		return tp.retornaDiasDoTempoPrevidenciario(funcional);
	}

	public String enviaTempoConcomitante(Date inicio, Date fim, Date deducaoI, Date deducaoF){
		ConcomitanciaService cs = new ConcomitanciaService();
		return cs.enviaTempoPrevidenciarioConcomitante(inicio, fim, deducaoI, deducaoF);
	}
	
	public String enviaTempoAproveitadoComDeducao(int dias, Date inicio, Date fim){
		TempoAproveitadoService tas = new TempoAproveitadoService();
		return tas.enviaTempoAproveitadoDeduzido(dias, inicio, fim);
	}
	
	public String devolveTempoConcomitante(){
		tp = new ConcomitanciaService();
		return tp.retornaTempoPrevidenciario(funcional);
	}
	
	public int devolveDiasConcomitantes(){
		tp = new ConcomitanciaService();
		return tp.retornaDiasDoTempoPrevidenciario(funcional);
	}
}
