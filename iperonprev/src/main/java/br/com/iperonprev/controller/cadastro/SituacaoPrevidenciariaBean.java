package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.FuncionaisFuncoesDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.models.FuncionaisFuncoes;

@Named
@RequestScoped
public class SituacaoPrevidenciariaBean implements GenericBean<FuncionaisFuncoes>,Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionalBean fb;
	PessoasFuncionais pf = new PessoasFuncionais();
	FuncionaisFuncoes situacao = new FuncionaisFuncoes();
	static Map<String, Object> mapa = new HashMap<String, Object>();
	GenericDao<FuncionaisFuncoes> dao = new FuncionaisFuncoesDao();
	public FuncionaisFuncoes getSituacao() {
		return situacao;
	}

	public void setSituacao(FuncionaisFuncoes situacao) {
		this.situacao = situacao;
	}

	public FuncionalBean getFb() {
		return fb;
	}

	public void setFb(FuncionalBean fb) {
		this.fb = fb;
	}

	@SuppressWarnings("static-access")
	@Override
	public void salvarObjeto() {
		try{
			Message.addSuccessMessage("Situação Previdenciária salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Situação Previdenciária!");
		}
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		this.situacao.setNUMR_idPessoas(this.pf.getNUMR_idDoObjetoPessoas());
		dao.salvaObjeto(situacao);
		novoObjeto();
	}

	@Override
	public void novoObjeto() {
		this.situacao = new FuncionaisFuncoes();
	}

	@Override
	public List<FuncionaisFuncoes> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pegaInstanciaDialogo(FuncionaisFuncoes obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	

}
