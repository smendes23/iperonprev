package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoDeducaoEnum;
import br.com.iperonprev.dao.DeducaoDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;

@Named
@ViewScoped
public class DeducaoBean implements GenericBean<Deducao>,Serializable{

	private static final long serialVersionUID = 1L;
	
	GenericDao<Deducao> dao = new DeducaoDao();
	Deducao deducao  = new Deducao();
	@Inject
	private FuncionalBean fb;
	private List<Deducao> filtroDeDeducao;
	PessoasFuncionais pf = new PessoasFuncionais();
	
	public List<Deducao> getFiltroDeDeducao() {
		return filtroDeDeducao;
	}

	public void setFiltroDeDeducao(List<Deducao> filtroDeDeducao) {
		this.filtroDeDeducao = filtroDeDeducao;
	}

	public Deducao getDeducao() {
		return deducao;
	}

	public void setDeducao(Deducao deducao) {
		this.deducao = deducao;
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
			this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
			deducao.setNUMR_pessoasFuncionais(pf);
			dao.salvaObjeto(deducao);
			novoObjeto();
			Message.addSuccessMessage("Dedução salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Dedução!");
		}
	}

	@Override
	public void novoObjeto() {
		this.deducao = new Deducao();
	}

	@Override
	public List<Deducao> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeducoes");
	}

	@Override
	public void pegaInstanciaDialogo(Deducao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.deducao = (Deducao)event.getObject();
	}
	
	public List<TipoDeducaoEnum> getListaTipoDeDeducoes(){
		List<TipoDeducaoEnum> lista = Arrays.asList(TipoDeducaoEnum.values()); 
		return lista;
	}
	
	public void calculaQtdDiasDeduzidos(){
		try{
			this.deducao.setNUMR_qtdDias(RetornaTempos.retornaDiasApartirDeDuasDatas(this.deducao.getDATA_inicio(),
					this.deducao.getDATA_fim())+1);
		}catch(Exception e){
			Message.addWarningMessage("Campo data obrigatório!");
		}
	}
	
	@SuppressWarnings("static-access")
	public List<Deducao> getListaDeDeducoes(){
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		List<Deducao> lista = new ArrayList<>();
		
		if(pf.getNUMG_idDoObjeto() != 0){
			lista = dao.listaRelacionamenoDoObjeto("Deducao", "NUMR_pessoasFuncionais",pf.getNUMG_idDoObjeto() ); 
		}
		return lista;
	}

	public void remover(int id){
		dao.removeObjeto(id);
	}
}
