package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.DependentesDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Dependentes;
import br.com.iperonprev.models.GrauParentesco;
import br.com.iperonprev.models.MotivoDependencia;
import br.com.iperonprev.models.MotivoFimDependencia;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.TipoDependencia;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@SessionScoped
public class DependentesPensionistasBean implements GenericBean<Dependentes>,Serializable{

	
	private static final long serialVersionUID = 1L;
	Dependentes dependente = new Dependentes();
	Pessoas pessoa = new Pessoas();
	Pessoas pessoaDependente = new Pessoas();
	static List<Pessoas> listaPessoas = new ArrayList<Pessoas>();
	static List<Pessoas> listaPessoasDependentes = new ArrayList<Pessoas>();
	static List<Dependentes> listaDep = new ArrayList<Dependentes>();
	List<Dependentes> filtroDependentes;
	private boolean pessoaAtiva = false;
	private boolean dependenteAtivo = false;
	
	
	public boolean isDependenteAtivo() {
		return dependenteAtivo;
	}


	public boolean isPessoaAtiva() {
		return pessoaAtiva;
	}


	public List<Dependentes> getFiltroDependentes() {
		return filtroDependentes;
	}

	public void setFiltroDependentes(List<Dependentes> filtroDependentes) {
		this.filtroDependentes = filtroDependentes;
	}

	public Pessoas getPessoaDependente() {
		return pessoaDependente;
	}

	public void setPessoaDependente(Pessoas pessoaDependente) {
		this.pessoaDependente = pessoaDependente;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public Dependentes getDependente() {
		return dependente;
	}

	public void setDependente(Dependentes dependente) {
		this.dependente = dependente;
	}
	

	@Override
	public void salvarObjeto() {
		try{
			if(new DependentesDao().verificaExisteRegistro(this.pessoaDependente.getNUMG_idDoObjeto(), this.pessoa.getNUMG_idDoObjeto()) != true ||
					this.dependente.getNUMG_idDoObjeto() > 0 && new DependentesDao().verificaExisteRegistro(this.pessoaDependente.getNUMG_idDoObjeto(), this.pessoa.getNUMG_idDoObjeto()) != false){
				this.dependente.setNUMR_idDoObjetoPessoas(this.pessoa);
				this.dependente.setNUMR_idDoObjetoDependentes(this.pessoaDependente);
				new GenericPersistence<Dependentes>(Dependentes.class).salvar(this.dependente);
				novoObjeto();
				Message.addSuccessMessage("Dependente salvo com sucesso!");
			}else{
				Message.addErrorMessage("Registro já existe");
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Dependente");
		}
	}

	@Override
	public void novoObjeto() {
		this.dependente = new Dependentes();
		this.pessoa = new Pessoas();
		this.pessoaDependente = new Pessoas();
		listaPessoas = new ArrayList<Pessoas>();
		listaPessoasDependentes = new ArrayList<Pessoas>();
		listaDep = new ArrayList<>();
		this.pessoaAtiva = false;
		this.dependenteAtivo = false;
	}

	@Override
	public List<Dependentes> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaPessoasDependentes");
	}
	
	public void exibeListaDeObjetosDependentes() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDependentes");
	}
	
	public void fechaDependente(){
		RequestContext.getCurrentInstance().closeDialog("listaDependentesPensionistas");
	}
	
	private boolean actionButtonDependente = false;
	public void actionCloseDependente(){
		actionButtonDependente = true;
	}
	
	public void actionEditDependente(){
		actionButtonDependente = false;
	}
	
	public void exibeListaDependetesPensionistas(){
		new DialogsPrime().showDialog(true, true, true, false, "listaDependentesPensionistas");
	}
	
	@Override
	public void pegaInstanciaDialogo(Dependentes obj) {
	}
	
	public void pegaInstanciaPessoa(Pessoas obj){
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	public void pegaInstanciaDependente(Pessoas obj){
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	public void pegaInstanciaDependentePensionistas(Dependentes obj){
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	
	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.pessoa = (Pessoas)event.getObject();
		this.pessoaAtiva = true;
		try{
			System.out.println(this.pessoa.getNUMG_idDoObjeto());
			listaDep = new DependentesDao().listaDependentesPensionistas(this.pessoa.getNUMG_idDoObjeto()); 
		}catch(Exception e){
			System.out.println("Não foi possivel lista de Dependentes");
		}
	}

	public void selecionaObjetoDialogoDependente(SelectEvent event) {
		this.pessoaDependente = (Pessoas)event.getObject();
		this.dependenteAtivo = true;
	}
	
	public void selecionaObjetoDialogoDependentePensionista(SelectEvent event) {
		if(actionButtonDependente != true){
			this.dependente = (Dependentes)event.getObject();
			this.pessoaDependente = this.dependente.getNUMR_idDoObjetoDependentes();
			this.dependenteAtivo = true;
		}
	}
	
	public List<TipoDependencia> getListaTipoDependencia(){
		return new GenericPersistence<TipoDependencia>(TipoDependencia.class).listarTodos("TipoDependencia");
	}
	
	public List<MotivoDependencia> getListaMotivosInicioDependencia(){
		return new GenericPersistence<MotivoDependencia>(MotivoDependencia.class).listarTodos("MotivoDependencia");
	}
	
	public List<MotivoFimDependencia> getListaMotivosFimDependencia(){
		return new GenericPersistence<MotivoFimDependencia>(MotivoFimDependencia.class).listarTodos("MotivoFimDependencia");
	}
	
	public List<GrauParentesco> getListaGrauParentesco(){
		return new GenericPersistence<GrauParentesco>(GrauParentesco.class).listarTodos("GrauParentesco");
	}
	
	
	
	public List<Dependentes> getListaDependentesPensionistas(){
		return listaDep;
	}
	
	public void pegaListaDePessoas(ValueChangeEvent event){
		if(event.getNewValue().toString().length() >= 4 ){
			listaPessoas = new PessoasDao().devolveListaDePessoasComClausulaLike(event.getNewValue().toString());
		}
	}
	
	public void pegaListaDependentes(ValueChangeEvent event){
		
		if(event.getNewValue().toString().length() >= 4 ){
			listaPessoasDependentes = new PessoasDao().devolveListaDePessoasComClausulaLike(event.getNewValue().toString());
		}
	}
	
	public List<Pessoas> getListaDePessoas(){
		if(listaPessoas.isEmpty()){
			Message.addWarningMessage("Nome deve conter pelo menos 4 dígitos!");
			return null;
		}else{
			return listaPessoas;
		}
	}
	
	public List<Pessoas> getListaDependentes(){
		if(listaPessoasDependentes.isEmpty()){
			Message.addWarningMessage("Nome deve conter pelo menos 4 dígitos!");
			return null;
		}else{
			return listaPessoasDependentes;
		}
	}
	
	public void recarregaPagina(){
		listaPessoas = new ArrayList<Pessoas>();
		listaPessoasDependentes = new ArrayList<Pessoas>();
		novoObjeto();
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("dependentesPensionistasBean");
	}
}
