package br.com.iperonprev.controller.gerenciador;

import java.util.Date;

public class PegaData {
	Date dataI;
	Date dataF;
	String descricao;
	
	public PegaData(Date dataI, Date dataF, String descricao) {
		this.dataI = dataI;
		this.dataF = dataF;
		this.descricao = descricao;
	}
	
	public PegaData() {
	}

	
	public Date getDataI() {
		return dataI;
	}
	public Date getDataF() {
		return dataF;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
