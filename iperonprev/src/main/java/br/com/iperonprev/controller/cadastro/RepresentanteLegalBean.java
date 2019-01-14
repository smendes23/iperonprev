package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.RepresentanteLegal;
import br.com.iperonprev.models.TipoRepresentanteLegal;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class RepresentanteLegalBean implements GenericBean<RepresentanteLegal>,Serializable{

	
	private static final long serialVersionUID = 1L;
	RepresentanteLegal representanteLegal = new RepresentanteLegal();
	Pessoas pessoa = new Pessoas();
	Pessoas representante = new Pessoas();
	static List<Pessoas> listaRepresentante = new ArrayList<>();
	static List<Pessoas> listaPessoa= new ArrayList<>();
	TipoRepresentanteLegal tipoRepresentante = new TipoRepresentanteLegal();
	static List<RepresentanteLegal> listaTutelados = new ArrayList<>();
	public TipoRepresentanteLegal getTipoRepresentante() {
		return tipoRepresentante;
	}
	public void setTipoRepresentante(TipoRepresentanteLegal tipoRepresentante) {
		this.tipoRepresentante = tipoRepresentante;
	}
	public RepresentanteLegal getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	public Pessoas getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}
	public Pessoas getRepresentante() {
		return representante;
	}
	public void setRepresentante(Pessoas representante) {
		this.representante = representante;
	}
	@Override
	public void salvarObjeto() {
		try {
			Pessoas p =new GenericPersistence<Pessoas>(Pessoas.class).buscarPorId(this.pessoa.getNUMG_idDoObjeto());
			Pessoas r = new GenericPersistence<Pessoas>(Pessoas.class).buscarPorId(this.representante.getNUMG_idDoObjeto());
			this.representanteLegal.setNUMR_pessoa(p);
			this.representanteLegal.setNUMR_representante(r);
			this.representanteLegal.setNUMR_tipoRepresentante(this.tipoRepresentante);
			new GenericPersistence<RepresentanteLegal>(RepresentanteLegal.class).salvar(representanteLegal);
			novoObjeto();
			Message.addSuccessMessage("Representante legal salvo com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar representante legal");
			e.printStackTrace();
		}
	}
	@Override
	public void novoObjeto() {
		this.pessoa = new Pessoas();
		this.representante = new Pessoas();
		this.tipoRepresentante = new TipoRepresentanteLegal();
	}
	@Override
	public List<RepresentanteLegal> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaRepresentanteLegal");
	}
	@Override
	public void pegaInstanciaDialogo(RepresentanteLegal obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}
	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.representanteLegal = (RepresentanteLegal)event.getObject();
		this.pessoa = this.representanteLegal.getNUMR_pessoa();
		this.representante = this.representanteLegal.getNUMR_representante();
		this.tipoRepresentante = this.representanteLegal.getNUMR_tipoRepresentante();
	}
	
	public List<TipoRepresentanteLegal> getListaTipoRepresentante(){
		return new GenericPersistence<TipoRepresentanteLegal>(TipoRepresentanteLegal.class).listarTodos("TipoRepresentanteLegal");
	}
	
	public void exibeListaDeObjetosRepresentante() {
		new DialogsPrime().showDialog(true, true, true, false, "listaRepresentante");
		
	}
	
	public void selecionaObjetoDialogoPessoa(SelectEvent event) {
		this.pessoa = (Pessoas)event.getObject();
	}
	
	
	
	public void pegaListaPessoa(ValueChangeEvent event){
		if(event.getNewValue().toString().length() >= 4 ){
			listaPessoa = new PessoasDao().devolveListaDePessoasComClausulaLike(event.getNewValue().toString());
		}
	}
	
	public void exibeListaDeObjetosPessoa() {
		new DialogsPrime().showDialog(true, true, true, false, "listaPessoaRepresentante");
		
	}
	
	public void selecionaObjetoDialogoRepresentante(SelectEvent event) {
		this.representante = (Pessoas)event.getObject();
		System.out.println(this.representante.getNUMG_idDoObjeto());
		try {
			listaTutelados = new GenericPersistence<RepresentanteLegal>(RepresentanteLegal.class).listarRelacionamento("RepresentanteLegal","NUMR_pessoa", this.representante.getNUMG_idDoObjeto());
		} catch (Exception e) {
			System.out.println("Erro ao carregar lista de Tutelados");
		}
	}
	
	public List<RepresentanteLegal> getListaRepresentantesLegais(){
		return listaTutelados;
	}
	
	public void pegaInstanciaDialogoPessoa(Pessoas obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
		
	}
	
	public void pegaInstanciaDialogoRepresentante(Pessoas obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
		
	}
	
	public List<Pessoas> getListaDePessoas(){
		if(listaPessoa.isEmpty()){
			Message.addWarningMessage("Nome deve conter pelo menos 4 dígitos!");
			return null;
		}else{
			return listaPessoa;
		}
	}
	
	public List<Pessoas> getListaDeRepresentantes(){
		if(listaRepresentante.isEmpty()){
			Message.addWarningMessage("Nome deve conter pelo menos 4 dígitos!");
			return null;
		}else{
			return listaRepresentante;
		}
	}
	
	public void pegaListaRepresentante(ValueChangeEvent event){
		if(event.getNewValue().toString().length() >= 4 ){
			listaRepresentante = new PessoasDao().devolveListaDePessoasComClausulaLike(event.getNewValue().toString());
		}
	}
	
	/*public List<RepresentanteLegal> listaDeRepresentantesLegais(String query){
	List<RepresentanteLegal> listaRepresentante =  new GenericPersistence<RepresentanteLegal>(RepresentanteLegal.class).listarTodos("RepresentanteLegal");
	List<RepresentanteLegal> lista = new ArrayList<>();
	listaRepresentante.forEach(r->{
		if(r.getDESC_nome().startsWith(query)){
			lista.add(r);
		}
	});
		return lista;
	}*/
	
}
