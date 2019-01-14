package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.dao.CargosDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.Carreiras;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean(name="cargoBean")
public class CargoBean implements GenericBean<Cargos>,Serializable{

	private static final long serialVersionUID = 1L;
	
	Cargos cargos = new Cargos();
	GenericDao<Cargos> dao;
	static List<Cargos> listaCargos = new ArrayList<Cargos>();
	static private List<Cargos> filtroDeCargos;
	
	private String assinatura;
	
	
	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}
	
	public void assina(){
		System.out.println(assinatura);
	}

	@PostConstruct
	public void init(){
		listaCargos = new CargosDao().buscaTodosObjetos("Cargos");
	}

	public Cargos getCargos() {
		return cargos;
	}

	public void setCargos(Cargos cargos) {
		this.cargos = cargos;
	}

	public List<Cargos> getFiltroDeCargos() {
		return filtroDeCargos;
	}

	@SuppressWarnings("static-access")
	public void setFiltroDeCargos(List<Cargos> filtroDeCargos) {
		this.filtroDeCargos = filtroDeCargos;
	}

	@Override
	public void salvarObjeto() {
		try{
			dao = new CargosDao();
			dao.salvaObjeto(cargos);
			this.cargos = new Cargos();
			Message.addSuccessMessage("Cargo salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Cargo!");
		}
	}

	@Override
	public void novoObjeto() {
		this.cargos = new Cargos();
		actionButtonCargo = false;
		
	}

	@Override
	public List<Cargos> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeCargos");
	}

	@Override
	public void pegaInstanciaDialogo(Cargos obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	private static boolean actionButtonCargo = false;
	
	public void fechaCargo(){
		actionButtonCargo = true;
		RequestContext.getCurrentInstance().closeDialog("listaDeCargos");
	}
	
	public void actionEditCargo(){
		actionButtonCargo = false;
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		if(actionButtonCargo != true){
			this.cargos = (Cargos)event.getObject();
		}
		actionButtonCargo = false;
	}
	
	public List<DecisaoEnum> getListaDedicacaoExclusiva(){
		List<DecisaoEnum> lista = Arrays.asList(DecisaoEnum.values());
		return lista;
	}
	
	public List<DecisaoEnum> getListaAposentadoriaEspecial(){
		List<DecisaoEnum> lista = Arrays.asList(DecisaoEnum.values());
		return lista;
	}
	
	public List<DecisaoEnum> getListaTecnicoCientifico(){
		List<DecisaoEnum> lista = Arrays.asList(DecisaoEnum.values());
		return lista;
	}
	
	public List<AtosLegais> getListaDeAtosLegais(){
		List<AtosLegais> lista = new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
		return lista;
	}
	
	public List<Carreiras> getListaDeCarreiras(){
		List<Carreiras> lista = new GenericPersistence<Carreiras>(Carreiras.class).listarTodos("Carreiras");
		return lista;
	}
	
	public List<Orgaos> getListaDeOrgaos(){
		List<Orgaos> lista = new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
		Collections.sort(lista, new Comparator<Orgaos>() {
			@Override
			public int compare(Orgaos o1, Orgaos o2) {
				return o1.getDESC_nome().compareTo(o2.getDESC_nome());
			}
		});
		return lista;
	}
	
	public List<Cargos> getListaDeCargos(){
		return listaCargos;
	}

}
