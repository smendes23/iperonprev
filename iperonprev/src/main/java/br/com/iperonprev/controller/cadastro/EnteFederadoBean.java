package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.EnteFederadoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.EntesFederados;
import br.com.iperonprev.models.Esferas;
import br.com.iperonprev.models.Estados;
import br.com.iperonprev.models.Municipios;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
@Named
@ViewScoped
public class EnteFederadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntesFederados enteFederado = new EntesFederados();
	
	static List<Municipios> listaDeMunicipios = new ArrayList<Municipios>();
	List<EntesFederados> listaDeEntesFederados = new ArrayList<EntesFederados>();
	GenericDao<EntesFederados> dao;
	private List<EntesFederados> filtroEntes;
	Estados estado = new Estados();
	Municipios municipio = new Municipios();
	Esferas esfera = new Esferas();
	
	
	public Esferas getEsfera() {
		return esfera;
	}

	public void setEsfera(Esferas esfera) {
		this.esfera = esfera;
	}

	public Municipios getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipios municipio) {
		this.municipio = municipio;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public List<EntesFederados> getFiltroEntes() {
		return filtroEntes;
	}

	public void setFiltroEntes(List<EntesFederados> filtroEntes) {
		this.filtroEntes = filtroEntes;
	}

	@PostConstruct
	public void init(){
		listaDeMunicipios = new ArrayList<>();
		listaDeEntesFederados = new EnteFederadoDao().buscaTodosObjetos("EntesFederados");
	}

	public EntesFederados getEnteFederado() {
		return enteFederado;
	}

	public void setEnteFederado(EntesFederados enteFederado) {
		this.enteFederado = enteFederado;
	}
	
	public List<Esferas> getListaEsfera(){
		return new GenericPersistence<Esferas>(Esferas.class).listarTodos("Esferas");
	}
	
	public List<Estados> getListaEstado(){
		List<Estados> listaDeEstados = new GenericPersistence<Estados>(Estados.class).listarTodos("Estados");
		return listaDeEstados;
	}
	
	public List<Municipios> getListaMunicipio(){
		return listaDeMunicipios;
	}
	
	public List<EntesFederados> getListaDeEntesFederados() {
		return listaDeEntesFederados;
	}
	
	public void salvarEnteFederado() {
		try{
			dao = new EnteFederadoDao();
			this.enteFederado.setNUMR_idDoObjetoMunicipio(this.municipio);
			this.enteFederado.setNURM_idDoObjetoEsfera(this.esfera);
			new GenericPersistence<EntesFederados>(EntesFederados.class).salvar(enteFederado);
			novoEnteFederado();
			Message.addSuccessMessage("Ente Federativo salvo com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao salvar Ente Federativo!");
		}
	}
	
	public void novoEnteFederado() {
		this.enteFederado = new EntesFederados();
		this.estado = new Estados();
		this.municipio = new Municipios();
		this.esfera = new Esferas();
		listaDeMunicipios = new ArrayList<>();
	}
	
	public void abreDialogoEnteFederado() {
		new DialogsPrime().showDialog(true, true, true, false, "listaEnteFederado");
	}
	
	public void pegaInstanciaDialogo(EntesFederados enteFederados){
		new DialogsPrime().getInstanceObjectDialog(enteFederados);
	}
	
	private static boolean actionButtonEnteFederado = false;

	public void fechaEnte(){
		actionButtonEnteFederado = true;
		RequestContext.getCurrentInstance().closeDialog("listaEnteFederado");
	}
	
	
	public void actionEditEnte(){
		actionButtonEnteFederado = false;
	}
	
	public void selecionaEnteDialogo(SelectEvent event){
		if(actionButtonEnteFederado != true){
			this.enteFederado = (EntesFederados)event.getObject();
			this.estado = this.enteFederado.getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado();
			populaMunicipios();
		}
	}
	
	public void populaMunicipios(){
		listaDeMunicipios = new GenericPersistence<Municipios>(Municipios.class).listarRelacionamento("Municipios", "NUMR_idDoObjetoEstado", estado.getNUMG_idDoObjeto());
	}
}
