package br.com.iperonprev.services.averbacao;

import java.util.ArrayList;
import java.util.List;

public class CalculaTempoLiquidoComDeducao extends Thread{
	
	
	List<ModificadorDeAcessoDiaMesAno> listaModificador = new ArrayList<ModificadorDeAcessoDiaMesAno>();
	ModificadorDeAcessoDiaMesAno mda = new ModificadorDeAcessoDiaMesAno();
	
	public ModificadorDeAcessoDiaMesAno getMda() {
		return mda;
	}

	public CalculaTempoLiquidoComDeducao() {
	}

	public CalculaTempoLiquidoComDeducao(ModificadorDeAcessoDiaMesAno mda) {
		this.mda = mda;
	}
	
	public CalculaTempoLiquidoComDeducao(List<ModificadorDeAcessoDiaMesAno> listaModificador){
		this.listaModificador = listaModificador;
	}
	
	@Override
	public void run() {
		
			mda.setAno(listaModificador.get(0).getAno());
			mda.setMes(listaModificador.get(0).getMes());
			mda.setDia(listaModificador.get(0).getDia());
			
		for (int i = 1; i < listaModificador.size(); i++) {
			
			mda.setAno(mda.getAno() - listaModificador.get(i).getAno());
			if(mda.getDia() < listaModificador.get(i).getDia()){
				mda.setMes(mda.getMes() -1);
				mda.setDia((mda.getDia() +30) - listaModificador.get(i).getDia());
			}else{
				mda.setDia(mda.getDia() - listaModificador.get(i).getDia());
			}
			
			if(mda.getMes() < listaModificador.get(i).getMes()){
				mda.setAno(mda.getAno() - 1);
				mda.setMes((mda.getMes()+12) - listaModificador.get(i).getMes());
			}else{
				mda.setMes(mda.getMes() - listaModificador.get(i).getMes());
			}
		}
		
		
	}
	
	public String retornaCalculoTempoAproveitado(){
		return ((((mda.getDia()/30)%12)+mda.getMes())/12+mda.getAno())+" ano(s), "+((((mda.getDia()/30)%12)+mda.getMes())%12)+" mes(es) e "+(mda.getDia()%30)+" dia(s).";
	}
	
	public ModificadorDeAcessoDiaMesAno devolveModificador(){
		ModificadorDeAcessoDiaMesAno md = new ModificadorDeAcessoDiaMesAno();
		md.setAno((((mda.getDia()/30)%12)+mda.getMes())/12+mda.getAno());
		md.setMes((((mda.getDia()/30)%12)+mda.getMes())%12);
		md.setDia(mda.getDia()%30);
		return md;
	}
	
	public String retornaModificadorAnoMesDiaFormatado(){
		return mda.getAno()+" ano(s), "+mda.getMes()+" mes(es) e "+mda.getDia()+" dia(s).";
	}
}
