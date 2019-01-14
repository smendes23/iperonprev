package br.com.iperonprev.helper;

import br.com.iperonprev.controller.dto.Simulador;

public class ValidaRegrasAposentadoria {

	public boolean verificaRegraDisponivel(Simulador simulador){
		try{
			if(simulador.getFaltante1().equals("")
					&& simulador.getFaltante2().equals("")
					&& simulador.getFaltante3().equals("")
					&& simulador.getFaltante5().equals("")
					|| simulador.getFaltante1().equals(null)
					&& simulador.getFaltante2().equals(null)
					&& simulador.getFaltante3().equals(null)
					&& simulador.getFaltante5().equals(null)
					){
				return true;
				
			}
			
		}catch(Exception e){
			
		}
		return false;
	}
	
	public boolean verificaRegraDisponivelComQuatroParametros(Simulador simulador){
		if(simulador.getFaltante1().substring(0,1).equals("-")
			&& simulador.getFaltante2().substring(0,1).equals("-")
			&& simulador.getFaltante3().substring(0,1).equals("-")
			){
			return true;
			
		}
		return false;
	}
	
	public boolean verificaRegraDisponivelComTresParametros(Simulador simulador){
		if(simulador.getFaltante1().substring(0,1).equals("-")
			&& simulador.getFaltante2().substring(0,1).equals("-")
			&& simulador.getFaltante3().substring(0,1).equals("-")
			){
			return true;
			
		}
		return false;
	}
}
