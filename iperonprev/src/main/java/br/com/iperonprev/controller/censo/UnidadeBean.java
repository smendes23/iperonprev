package br.com.iperonprev.controller.censo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import org.omnifaces.util.Ajax;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.Enderecos;
import br.com.iperonprev.models.Estados;
import br.com.iperonprev.models.Municipios;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.TipoLogradouro;
import br.com.iperonprev.models.UnidadesCenso;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class UnidadeBean implements GenericBean<UnidadesCenso>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UnidadesCenso unidade = new UnidadesCenso();
	Enderecos endereco = new Enderecos();
	Estados estados = new Estados();
	Municipios municipio = new Municipios();
	Pessoas pessoa = new Pessoas();
	

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public UnidadesCenso getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadesCenso unidade) {
		this.unidade = unidade;
	}

	public Enderecos getEndereco() {
		return endereco;
	}

	public void setEndereco(Enderecos endereco) {
		this.endereco = endereco;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	@Override
	public void salvarObjeto() {
		try{
			this.unidade.setNUMR_endereco(this.endereco);
			this.unidade.setNUMR_idDoObjetoPessoa(this.pessoa);
			new GenericPersistence<UnidadesCenso>(UnidadesCenso.class).salvar(this.unidade);
			novoObjeto();
			Message.addSuccessMessage("Unidade salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Unidade!");
		}
	}

	@Override
	public void novoObjeto() {
		this.unidade = new UnidadesCenso();
		this.pessoa = new Pessoas();
		this.endereco = new Enderecos();
	}

	@Override
	public List<UnidadesCenso> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaUnidadeCenso");
	}

	@Override
	public void pegaInstanciaDialogo(UnidadesCenso obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.unidade = (UnidadesCenso)event.getObject();
		this.endereco = this.unidade.getNUMR_endereco();
		this.pessoa = this.unidade.getNUMR_idDoObjetoPessoa();
		this.estados = this.endereco.getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado();
		populaMunicipios();
	}
	
	public List<TipoLogradouro> getListaTipoLogradouro(){
		return new GenericPersistence<TipoLogradouro>(TipoLogradouro.class).listarTodos("TipoLogradouro");
	}
	
	public List<Estados> getListaEstados(){
		return new GenericPersistence<Estados>(Estados.class).listarTodos("Estados");
	}
	
	public List<DadosCenso> getListaCenso(){
		return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	}
	
	static List<Municipios> listaM = new ArrayList<Municipios>();
	public void populaMunicipios(){
		listaM = new GenericPersistence<Municipios>(Municipios.class).listarRelacionamento("Municipios", "NUMR_idDoObjetoEstado", this.estados.getNUMG_idDoObjeto());
	}
	
	public List<Municipios> getListaDeMunicipios(){
		return listaM;
	}
	
	
	public List<UnidadesCenso> getListaDeUnidadesCenso(){
		return new GenericPersistence<UnidadesCenso>(UnidadesCenso.class).listarTodos("UnidadesCenso");
	}
	
	public void exibeListaDePessoas() {
		new DialogsPrime().showDialog(true, true, true, false, "listaResponsavelCenso");
		
	}
	
	public void pegaInstanciaDialogoPessoa(Pessoas obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
		
	}


	public void selecionaDialogoPessoa(SelectEvent event) {
		this.pessoa = (Pessoas)event.getObject();
	}
	
	static List<Pessoas> listaP = new ArrayList<>();
	public List<Pessoas> getListaDePessoas(){
		if(listaP.isEmpty()){
			Message.addWarningMessage("Nome deve conter pelo menos 4 dígitos!");
			Ajax.updateAll();
			return null;
		}else{
			return listaP;
		}
	}
	
	public void recarregaPagina(){
		listaP = new ArrayList<Pessoas>();
		novoObjeto();
	}
	
	public void pegaListaPessoas(ValueChangeEvent event){
		
		if(event.getNewValue().toString().length() >= 4 ){
			listaP = new PessoasDao().devolveListaDePessoasComClausulaLike(event.getNewValue().toString());
		}
	}
}
