package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.dao.AfastamentoLicencaDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.TipoLicenca;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class LicencaBean implements GenericBean<AfastamentosLicenca>,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionalBean fb;
	AfastamentosLicenca licenca = new AfastamentosLicenca();
	GenericDao<AfastamentosLicenca> dao = new AfastamentoLicencaDao();
	private List<AfastamentosLicenca> filtroDeLicenca;
	PessoasFuncionais pf = new PessoasFuncionais();
	TipoLicenca tipoLicenca = new TipoLicenca();
	
	public TipoLicenca getTipoLicenca() {
		return tipoLicenca;
	}

	public void setTipoLicenca(TipoLicenca tipoLicenca) {
		this.tipoLicenca = tipoLicenca;
	}

	public AfastamentosLicenca getLicenca() {
		return licenca;
	}

	public void setLicenca(AfastamentosLicenca licenca) {
		this.licenca = licenca;
	}

	public List<AfastamentosLicenca> getFiltroDeLicenca() {
		return filtroDeLicenca;
	}

	public void setFiltroDeLicenca(List<AfastamentosLicenca> filtroDeLicenca) {
		this.filtroDeLicenca = filtroDeLicenca;
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
			this.licenca.setNUMR_tipoLicenca(this.tipoLicenca);
			this.licenca.setNUMR_idDoObjetoFuncional(pf);
			dao.salvaObjeto(this.licenca);
			novoObjeto();
			Message.addSuccessMessage("Licença salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Licença!");
		}
	}

	@Override
	public void novoObjeto() {
		this.licenca = new AfastamentosLicenca();
	}

	@Override
	public List<AfastamentosLicenca> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeLicencas");
	}

	@Override
	public void pegaInstanciaDialogo(AfastamentosLicenca obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.licenca = (AfastamentosLicenca)event.getObject();
		this.tipoLicenca = this.licenca.getNUMR_tipoLicenca();
	}
	
	public List<TipoLicenca> getListaTiposDeLicencas() {
		return new GenericPersistence<TipoLicenca>(TipoLicenca.class).listarTodos("TipoLicenca");
	}
	
	public List<DecisaoEnum> getListaContribuicao() {
		return Arrays.asList(DecisaoEnum.values());
	}
	
	@SuppressWarnings("static-access")
	public List<AfastamentosLicenca> getListaDeLicencas(){
		
		this.pf = (PessoasFuncionais)fb.mapa.get("funcional");
		List<AfastamentosLicenca> lista = new ArrayList<>();
		
		if(pf.getNUMG_idDoObjeto() != 0){
			lista = dao.listaRelacionamenoDoObjeto("AfastamentosLicenca", "NUMR_idDoObjetoFuncional", pf.getNUMG_idDoObjeto()); 
		}
		return lista;
	}
	
}
