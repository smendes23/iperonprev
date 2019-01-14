package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
public class RubricasBean implements GenericBean<Rubricas>,Serializable{

	private static final long serialVersionUID = 1L;
	Rubricas rubrica = new Rubricas();
	private int tipoRubrica;
	private int incideContribuicao;
	private int contribuicaoPrevidenciaria;
	
	@PostConstruct
	public void init(){
		this.contribuicaoPrevidenciaria = 0;
		this.incideContribuicao = 0;
		this.tipoRubrica = 1;
	}
	
	public int getTipoRubrica() {
		return tipoRubrica;
	}

	public void setTipoRubrica(int tipoRubrica) {
		this.tipoRubrica = tipoRubrica;
	}

	
	public int getIncideContribuicao() {
		return incideContribuicao;
	}

	public void setIncideContribuicao(int incideContribuicao) {
		this.incideContribuicao = incideContribuicao;
	}

	public int getContribuicaoPrevidenciaria() {
		return contribuicaoPrevidenciaria;
	}

	public void setContribuicaoPrevidenciaria(int contribuicaoPrevidenciaria) {
		this.contribuicaoPrevidenciaria = contribuicaoPrevidenciaria;
	}

	public Rubricas getRubrica() {
		return rubrica;
	}

	public void setRubrica(Rubricas rubrica) {
		this.rubrica = rubrica;
	}

	@Override
	public void salvarObjeto() {
		try{
			this.rubrica.setFLAG_contribuicaoPrevidenciaria(this.contribuicaoPrevidenciaria);
			this.rubrica.setFLAG_incidenciaContribuicao(incideContribuicao);
			this.rubrica.setNUMR_tipoRubrica(tipoRubrica);
			new GenericPersistence<Rubricas>(Rubricas.class).salvar(rubrica);
			novoObjeto();
			Message.addSuccessMessage("Rubrica salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Rúbrica!");
		}
	}

	@Override
	public void novoObjeto() {
		this.rubrica = new Rubricas();
		this.contribuicaoPrevidenciaria = 0;
		this.incideContribuicao = 0;
	}

	@Override
	public List<Rubricas> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pegaInstanciaDialogo(Rubricas obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}

}
