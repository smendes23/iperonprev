package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoOnusEnum;
import br.com.iperonprev.dao.AfastamentoDisposicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AfastamentosDisposicao;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class DisposicaoBean implements GenericBean<AfastamentosDisposicao>,Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionalBean fb;
	AfastamentosDisposicao disposicao = new AfastamentosDisposicao();
	GenericDao<AfastamentosDisposicao> dao = new AfastamentoDisposicaoDao();
	private List<AfastamentosDisposicao> filtroDeDisposicoes;
	PessoasFuncionais pf = new PessoasFuncionais();
	
	
	public List<AfastamentosDisposicao> getFiltroDeDisposicoes() {
		return filtroDeDisposicoes;
	}

	public void setFiltroDeDisposicoes(List<AfastamentosDisposicao> filtroDeDisposicoes) {
		this.filtroDeDisposicoes = filtroDeDisposicoes;
	}

	public AfastamentosDisposicao getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(AfastamentosDisposicao disposicao) {
		this.disposicao = disposicao;
	}

	
	public void setFb(FuncionalBean fb) {
		this.fb = fb;
	}
	
	public FuncionalBean getFb() {
		return fb;
	}

	@SuppressWarnings("static-access")
	@Override
	public void salvarObjeto() {
		try{
			this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
			this.disposicao.setNUMR_idDoObjetoPessoasFuncionais(this.pf);
			dao.salvaObjeto(this.disposicao);
			novoObjeto();
			Message.addSuccessMessage("Disposição salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Disposição!");
		}
	}

	@Override
	public void novoObjeto() {
		this.disposicao = new AfastamentosDisposicao();
	}

	@Override
	public List<AfastamentosDisposicao> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeDisposicoes");
	}

	@Override
	public void pegaInstanciaDialogo(AfastamentosDisposicao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.disposicao = (AfastamentosDisposicao)event.getObject();
	}
	
	public List<Orgaos> getListaDeOrgaos() {
		return fb.getListaDeOrgaos();
	}
	
	public List<TipoOnusEnum> getListaDeOnus(){
		return Arrays.asList(TipoOnusEnum.values());
	}
	
	@SuppressWarnings("static-access")
	public List<AfastamentosDisposicao> getListaDeDisposicoes(){
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		List<AfastamentosDisposicao> lista = new ArrayList<>();
		
		if(pf.getNUMG_idDoObjeto() != 0){
			lista = dao.listaRelacionamenoDoObjeto("AfastamentosDisposicao", "NUMR_idDoObjetoPessoasFuncionais", pf.getNUMG_idDoObjeto()); 
		}
		return lista;
	}
	
	public void excluirObjeto(int id){
		new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).remover("AfastamentosDisposicao", "NUMG_idDoObjeto", id);
	}

}
