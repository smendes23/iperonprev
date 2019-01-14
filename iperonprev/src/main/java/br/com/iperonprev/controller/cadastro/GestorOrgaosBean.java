package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.SelectEvent;
import br.com.iperonprev.dao.GestorOrgaosDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.GestorOrgaos;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
/*Verificar a persistencia pois o escopo era de sessão*/
@ManagedBean
public class GestorOrgaosBean implements GenericBean<GestorOrgaos>,Serializable {

	
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{orgaoBean}")
	private OrgaoBean ob;
	GestorOrgaos gestor = new GestorOrgaos();
	GenericDao<GestorOrgaos> dao = new GestorOrgaosDao();
	List<GestorOrgaos> filtroDeGestor;
	private String matricula;
	PessoasFuncionais funcionais = new PessoasFuncionais();
	
	
	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<GestorOrgaos> getFiltroDeGestor() {
		return filtroDeGestor;
	}

	public void setFiltroDeGestor(List<GestorOrgaos> filtroDeGestor) {
		this.filtroDeGestor = filtroDeGestor;
		dao.salvaObjeto(this.gestor);
	}

	public GestorOrgaos getGestor() {
		return gestor;
	}

	public void setGestor(GestorOrgaos gestor) {
		this.gestor = gestor;
	}

	public void setOb(OrgaoBean ob) {
		this.ob = ob;
	}
	
	public void buscaServidor(){
		this.funcionais = new PessoasFuncionaisDao().devolveFuncional(this.matricula);
	}
	
	@Override
	public void salvarObjeto() {
		try{
			if(ob.orgaos.getNUMG_idDoObjeto() == null){
				Message.addErrorMessage("Por faovr, selecione um órgão!");
			}else{
				this.gestor.setPessoasFuncionais(this.funcionais);
				this.gestor.setOrgaos(ob.orgaos);
				dao.salvaObjeto(this.gestor);
				novoObjeto();
//			inserir logica para verificar data do inicio do exercício
			}
			Message.addSuccessMessage("Gestor do Órgão salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Gestor do Órgão!");
		}
	}

	@Override
	public void novoObjeto() {
		this.gestor = new GestorOrgaos();
		this.funcionais = new PessoasFuncionais();
		this.matricula = "";
	}

	@Override
	public List<GestorOrgaos> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeGestorOrgaos");
	}

	@Override
	public void pegaInstanciaDialogo(GestorOrgaos obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.gestor = (GestorOrgaos)event.getObject();
		this.funcionais = this.gestor.getPessoasFuncionais();
		this.matricula = this.funcionais.getDESC_matricula();
	}
	
	public List<GestorOrgaos> getListaGestorOrgaos() {
		if(ob.orgaos.getNUMG_idDoObjeto() != null){
			return dao.listaRelacionamenoDoObjeto("GestorOrgaos", "orgaos", ob.getOrgaos().getNUMG_idDoObjeto());
		}
		return new ArrayList<GestorOrgaos>();
	}

}
