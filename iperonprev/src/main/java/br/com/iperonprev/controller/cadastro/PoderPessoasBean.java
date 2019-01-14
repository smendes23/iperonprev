package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PoderPessoasDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PoderPessoas;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
@Named
@ViewScoped
public class PoderPessoasBean implements GenericBean<PoderPessoas>,Serializable{

	private static final long serialVersionUID = 1L;
	PoderPessoas poderes = new PoderPessoas();
	private String cpfServidor;
	GenericDao<PoderPessoas> dao = new PoderPessoasDao();
	private List<PoderPessoas> filtroDePoderes;
	List<PoderPessoas> lista = new ArrayList<PoderPessoas>();
	Orgaos orgao = new Orgaos();
	Pessoas pessoas = new Pessoas();
	
	public Pessoas getPessoas() {
		return pessoas;
	}

	public Orgaos getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgaos orgao) {
		this.orgao = orgao;
	}

	@PostConstruct
	public void init(){
		lista = dao.buscaTodosObjetos("PoderPessoas");
	}

	public List<PoderPessoas> getFiltroDePoderes() {
		return filtroDePoderes;
	}


	public void setFiltroDePoderes(List<PoderPessoas> filtroDePoderes) {
		this.filtroDePoderes = filtroDePoderes;
	}


	public PoderPessoas getPoderes() {
		return poderes;
	}


	public void setPoderes(PoderPessoas poderes) {
		this.poderes = poderes;
	}


	public String getCpfServidor() {
		return cpfServidor;
	}


	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}


	@Override
	public void salvarObjeto() {
		try{
			this.poderes.setNUMR_idDoObjetoPessoa(this.pessoas);
			this.poderes.setREL_orgao(orgao);
			dao.salvaObjeto(this.poderes);
			novoObjeto();
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Poder!");
		}
	}

	@Override
	public void novoObjeto() {
		this.poderes = new PoderPessoas();
		this.pessoas = new Pessoas();
		this.orgao = new Orgaos();
	}

	@Override
	public List<PoderPessoas> listaDeObjetos() {
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDePoderPessoas");
	}
	
	private boolean actionButton = false;
	
	public void fechaListaPoderPessoas(){
		actionButton = true;
		RequestContext.getCurrentInstance().closeDialog("listaDePoderPessoas");
	}
	
	public void actionClose(){
		actionButton = false;
	}

	@Override
	public void pegaInstanciaDialogo(PoderPessoas obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		try{
			if(actionButton != true){
				this.poderes = (PoderPessoas)event.getObject();
				this.orgao = this.poderes.getREL_orgao();
				this.pessoas = this.poderes.getNUMR_idDoObjetoPessoa();
			}
		}catch(Exception e){
			
		}
	}
	
	public List<Orgaos> getListaOrgaos(){
		return new PoderPessoasDao().listaDeOrgaosGestores();
	}
	
	public void buscaServidor(){
		this.pessoas = new PessoasDao().devolvePessoa(cpfServidor);
		this.cpfServidor = "";
	}
	
	public List<PoderPessoas> getListaDePoderPessoas(){
		Comparator<PoderPessoas> listaAvaliacaoComparator =(d1,d2)->d1.getDATA_inicioMandato().compareTo(d2.getDATA_inicioMandato()); 
		lista.sort(listaAvaliacaoComparator.reversed());
		return lista;
	}
}
