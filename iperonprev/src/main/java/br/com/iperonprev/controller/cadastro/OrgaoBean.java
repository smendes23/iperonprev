package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.OrgaosDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.EntesFederados;
import br.com.iperonprev.models.NaturezasJuridicas;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Poderes;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
/*Verificar a persistencia pois o escopo era de sessão*/

@ManagedBean
@javax.faces.view.ViewScoped
public class OrgaoBean implements GenericBean<Orgaos>,Serializable{

	private static final long serialVersionUID = 1L;
	
	Orgaos orgaos = new Orgaos();
	GenericDao<Orgaos> dao;
	static List<Orgaos> lista = new ArrayList<Orgaos>();
	static private List<Orgaos> filtroDeOrgaos;
	
	

	public List<Orgaos> getFiltroDeOrgaos() {
		return filtroDeOrgaos;
	}

	@SuppressWarnings("static-access")
	public void setFiltroDeOrgaos(List<Orgaos> filtroDeOrgaos) {
		this.filtroDeOrgaos = filtroDeOrgaos;
	}

	@PostConstruct
	public void init() {
		lista = new OrgaosDao().buscaTodosObjetos("Orgaos");
	}
	
	public Orgaos getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(Orgaos orgaos) {
		this.orgaos = orgaos;
	}
	
	/*Lista de dependências*/
	public List<NaturezasJuridicas> getListaDeNaturezasJuridicas(){
		List<NaturezasJuridicas> lista = new GenericPersistence<NaturezasJuridicas>(NaturezasJuridicas.class)
									.listarTodos("NaturezasJuridicas");
		return lista;
	}
	
	public List<Poderes> getListaDePoderes(){
		List<Poderes> lista = new GenericPersistence<Poderes>(Poderes.class)
							.listarTodos("Poderes");
		return lista;
	}
	
	public List<EntesFederados> getListaDeEnteFederados(){
		List<EntesFederados> lista = new GenericPersistence<EntesFederados>(EntesFederados.class)
				.listarTodos("EntesFederados");
		return lista;
	}
	
	@Override
	public void salvarObjeto() {
		try{
			dao = new OrgaosDao();
			dao.salvaObjeto(orgaos);
			this.orgaos = new Orgaos();
			Message.addSuccessMessage("Órgão salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Órgão!");
		}
	}

	@Override
	public void novoObjeto() {
		this.orgaos = new Orgaos();
		actionButtonOrgao = false;
	}

	@Override
	public List<Orgaos> listaDeObjetos() {
		return lista;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeOrgaos");
	}

	@Override
	public void pegaInstanciaDialogo(Orgaos obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	private static boolean actionButtonOrgao = false;
	
	public void fechaOrgao(){
		actionButtonOrgao = true;
		RequestContext.getCurrentInstance().closeDialog("listaDeOrgaos");
	}
	
	public void actionEditOrgao(){
		actionButtonOrgao = false;
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		try{
			if(actionButtonOrgao == false){
				this.orgaos = (Orgaos)event.getObject();
			}
			actionButtonOrgao = false;
		}catch(Exception e){
			System.out.println(actionButtonOrgao);
			
		}
	}
	
	public List<Orgaos> getListaDeOrgaos(){
		return lista;
	}
}
