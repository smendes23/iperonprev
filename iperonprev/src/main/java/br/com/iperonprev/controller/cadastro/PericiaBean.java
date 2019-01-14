package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.joda.time.LocalDate;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.StatusPericiaEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PericiaDao;
import br.com.iperonprev.helper.CidHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.AtestadosMedicos;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.Cid;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pericia;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class PericiaBean implements GenericBean<Pericia>,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PessoasFuncionais funcional = new PessoasFuncionais();
	Pessoas pessoa = new Pessoas();
	Orgaos orgao = new Orgaos();
	Cargos cargo = new Cargos();
	Pericia pericia = new Pericia();
	private static List<Cid> listaCid = new ArrayList<Cid>();
	Cid cid = new Cid();
	static List<AtestadosMedicos> listaAtestado = new ArrayList<AtestadosMedicos>();
	CopyFile copyFile = new CopyFile();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static List<Pericia> listaPericia = new ArrayList<>();
	List<Pericia> listaPericiaBuscaDao = new ArrayList<>();
	
	
	public PessoasFuncionais getFuncional() {
		return funcional;
	}

	public void setFuncional(PessoasFuncionais funcional) {
		this.funcional = funcional;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public Orgaos getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgaos orgao) {
		this.orgao = orgao;
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}
	

	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}
	
	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public List<Cid> getListaCid() {
		return listaCid;
	}
	
	public String reinitCid() {
        cid = new Cid();
        return null;
    }

	@PostConstruct
	public void init(){
		novoObjeto();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		boolean possui = false;
		try{
			if(ec.getSessionMap().containsKey("funcional")){
				this.funcional = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				this.pessoa = funcional.getNUMR_idDoObjetoPessoas();
				this.cargo = funcional.getNUMR_idDoObjetoCargo();
				this.orgao = this.cargo.getNUMR_idDoObjetoOrgaos();
				possui = true;
				
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possivel carregar os dados funcionais");
		}
		
		if(possui != false){
			try{
				List<Pericia> listaP = new GenericPersistence<Pericia>(Pericia.class).listarRelacionamento("Pericia", "NUMR_idDoObejtoPessoasFuncionais", this.funcional.getNUMG_idDoObjeto());
				this.pericia.setENUM_statusPericia(StatusPericiaEnum.SEMPERICIA);
				if(!listaP.isEmpty()){
					listaPericiaBuscaDao = new PericiaDao().listaPorIdFuncional(this.funcional.getNUMG_idDoObjeto());
					if(!listaPericiaBuscaDao.isEmpty()){
						Pericia p = new PericiaDao().listaPorIdFuncional(this.funcional.getNUMG_idDoObjeto()).get(0);
						this.pericia.setENUM_statusPericia(devolveStatusPericia(p));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				Message.addErrorMessage("Erro ao carregar lista de Pericias!");
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private StatusPericiaEnum devolveStatusPericia(Pericia p){
		if(p.isFLAG_naoPericia() == false){
			if(new LocalDate(p.getDATA_proximaPericia()).minusMonths(3).isBefore(new LocalDate().now()) ||
					new LocalDate(p.getDATA_proximaPericia()).compareTo(new LocalDate(p.getDATA_proximaPericia()).minusMonths(3)) == 0 ){
				return StatusPericiaEnum.EXPIRANDO;	
			}else if(new LocalDate(p.getDATA_proximaPericia()).isBefore(new LocalDate().now())){
				return StatusPericiaEnum.EXPIRADO;
			
			}else{
				return StatusPericiaEnum.ANDAMENTO;
				
			}
		}else{
			return StatusPericiaEnum.NAOPERICIA;
		}
		
	}
	
	public List<Cid> listaDeCids(String query){
		
		return new CidHelper().devolveResultadoConsulta(query);
	}
	
	public void removeArquivo(AtestadosMedicos atesta){
		try{
		this.copyFile.removeFile(atesta.getDESC_caminhoRelativo(), atesta.getDESC_nome());
			for (int i = 0; i < listaAtestado.size(); i++) {
				if(listaAtestado.get(i).getNUMG_idDoObjeto() == atesta.getNUMG_idDoObjeto()){
					listaAtestado.remove(i);
				}
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível remover o arquivo.");
		}
	}
	
	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		AtestadosMedicos atestado = new AtestadosMedicos();
		String caminho = new StringBuilder().append(this.funcional.getDESC_matricula()).append("\\")
				.append("pericia").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(this.funcional.getNUMG_idDoObjeto() > 0){
			copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
			
			atestado.setDESC_caminhoRelativo(caminho);
			atestado.setDESC_nome(event.getFile().getFileName());
			listaAtestado.add(atestado);
		}else{
			Message.addErrorMessage("Funcional inexistente");
		}
		
    }
	
	public List<AtestadosMedicos> getListaDeAtestados(){
		return listaAtestado;
	}

	@Override
	public void salvarObjeto() {
		try{
			this.pericia.setNUMR_cid(listaCid);
			this.pericia.setREL_atestadoMedico(listaAtestado);
			this.pericia.setNUMR_idDoObejtoPessoasFuncionais(this.funcional);
			boolean periciar = true;
			if(!listaPericiaBuscaDao.isEmpty()){
				if(listaPericiaBuscaDao.get(0).isFLAG_naoPericia() == true){
					periciar =false;
					Message.addWarningMessage("Este servidor não pode mais ser reavaliado! ");
				}
			}
			
			if(periciar == true){
				new GenericPersistence<Pericia>(Pericia.class).salvar(this.pericia);
				Message.addSuccessMessage("Perícia salva com sucesso!");
			}
			
			novoObjeto();
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao salvar perícia.");
		}
	}

	@Override
	public void novoObjeto() {
		listaCid = new ArrayList<>();
		listaAtestado = new ArrayList<>();
		this.cid = new Cid();
		this.pericia = new Pericia();
	}

	@Override
	public List<Pericia> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialogWithAndHeight(true, false, true, false, "listaPericia", 520, 840);
	}

	@Override
	public void pegaInstanciaDialogo(Pericia obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		listaCid = this.pericia.getNUMR_cid();
		listaAtestado = this.pericia.getREL_atestadoMedico();
	}
	
	public List<Pericia> listaPericia(){
		List<Pericia> listaPer =new GenericPersistence<Pericia>(Pericia.class).listarRelacionamento("Pericia", "NUMR_idDoObejtoPessoasFuncionais", this.funcional.getNUMG_idDoObjeto()); 
		listaPer.sort(new Comparator<Pericia>() {

			@Override
			public int compare(Pericia o1, Pericia o2) {
				// TODO Auto-generated method stub
				return o2.getNUMG_idDoObjeto() - o1.getNUMG_idDoObjeto();
			}
		});
		return  listaPer;
	}
}
