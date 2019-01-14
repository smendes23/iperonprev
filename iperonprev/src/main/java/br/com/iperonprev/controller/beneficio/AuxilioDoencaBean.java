package br.com.iperonprev.controller.beneficio;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.AposentadoriaInvalidezDao;
import br.com.iperonprev.dao.AuxilioDoencaDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.CidHelper;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AtestadosMedicos;
import br.com.iperonprev.models.AuxilioDoenca;
import br.com.iperonprev.models.Cid;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.MotivoCessacaoBeneficio;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;

@ManagedBean
@ViewScoped
public class AuxilioDoencaBean implements GenericBean<AuxilioDoenca>,Serializable{

	private static final long serialVersionUID = 1L;
	
	PessoasFuncionais funcionais = new PessoasFuncionais();
	AuxilioDoenca auxilio = new AuxilioDoenca();
	GenericDao<AuxilioDoenca> dao = new AuxilioDoencaDao();
	static private List<Cid> listaCid = new ArrayList<Cid>();
	Cid cid = new Cid();
	private List<AuxilioDoenca> filtroDeAuxilioDoenca;
	private boolean verificaDiretorio;
	private String nomeDoArquivo;
	CopyFile copyFile = new CopyFile();
	static List<AtestadosMedicos> listaAtestado = new ArrayList<AtestadosMedicos>();

		
	public Cid getCid() {
		return cid;
	}


	public void setCid(Cid cid) {
		this.cid = cid;
	}


	public List<Cid> getListaCid() {
		return listaCid;
	}


	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public boolean isVerificaDiretorio() {
		return verificaDiretorio;
	}

	public void setVerificaDiretorio(boolean verificaDiretorio) {
		this.verificaDiretorio = verificaDiretorio;
	}

	public String reinitCid() {
        cid = new Cid();
        return null;
    }
	static List<AuxilioDoenca> listaAux = new ArrayList<>();

	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	@PostConstruct
	public void init(){
		try{
			if(ec.getSessionMap().containsKey("funcional")){
				this.funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				listaAux = dao.listaRelacionamenoDoObjeto("AuxilioDoenca", "NUMR_pessoaFuncional", funcionais.getNUMG_idDoObjeto());
				
			}else{
				this.funcionais = new PessoasFuncionais();
			}
		}catch(Exception e){
			System.out.println("Sem funcional");
		}
	}
	

	public List<AuxilioDoenca> getFiltroDeAuxilioDoenca() {
		return filtroDeAuxilioDoenca;
	}

	public void setFiltroDeAuxilioDoenca(List<AuxilioDoenca> filtroDeAuxilioDoenca) {
		this.filtroDeAuxilioDoenca = filtroDeAuxilioDoenca;
	}


	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	public AuxilioDoenca getAuxilio() {
		return auxilio;
	}

	public void setAuxilio(AuxilioDoenca auxilio) {
		this.auxilio = auxilio;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	static List<AtestadosMedicos> listaAtestados = new ArrayList<>();

	@Override
	public void salvarObjeto() {
		try{
			this.auxilio.setNUMR_pessoaFuncional(this.funcionais);
			this.auxilio.setNUMR_cid(listaCid);
			this.auxilio.setREL_atestadoMedico(listaAtestado);
			new GenericPersistence<AuxilioDoenca>(AuxilioDoenca.class).salvar(this.auxilio);
			novoObjeto();
			Message.addSuccessMessage("Auxilio salvo com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar auxílio");
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public void novoObjeto() {
		this.auxilio = new AuxilioDoenca();
		this.listaCid = new ArrayList<>();
		this.listaAtestados = new ArrayList<>();
//		this.funcionais = new PessoasFuncionais();
		this.listaAtestado = new ArrayList<>();
		actionButton = false;
	}

	@Override
	public List<AuxilioDoenca> listaDeObjetos() {
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaAuxilioDoenca");
	}
	
	private static boolean actionButton = false;
	
	public void fechaListaPessoas(){
		actionButton = true;
		RequestContext.getCurrentInstance().closeDialog("listaAuxilioDoenca");
	}

	@Override
	public void pegaInstanciaDialogo(AuxilioDoenca obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		if(actionButton != true){
			AuxilioDoenca aux =(AuxilioDoenca)event.getObject(); 
			this.auxilio = new GenericPersistence<AuxilioDoenca>(AuxilioDoenca.class).buscarPorId(aux.getNUMG_idDoObjeto());
			this.funcionais = this.auxilio.getNUMR_pessoaFuncional();
			listaCid= this.auxilio.getNUMR_cid();
			listaAtestado = this.auxilio.getREL_atestadoMedico();
		}
		System.out.println(event.getPhaseId());
		actionButton = false;
	}
	
	public List<Cid> listaDeCids(String query){
		
		return new CidHelper().devolveResultadoConsulta(query);
	}
	
	public List<MotivoCessacaoBeneficio> getListaMotivoCessacaoBeneficio(){
		return new AposentadoriaInvalidezDao().devolveListaCessacaoBeneficio();
	}
	
	StringBuilder sb = new StringBuilder();
	
	public void removeArquivo(String nomeArquivo){
		try{
			new CopyFile().removeFile(funcionais.getDESC_matricula(),nomeArquivo);
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível remover o arquivo!");
		}
	}
	
	public void recarregaPagina(){
//		ec.getSessionMap().remove("funcional");
		if(this.funcionais.getNUMG_idDoObjeto() == 0){
			novoObjeto();
		}
	}

	
	public List<AuxilioDoenca> getListaDeAuxilioDoenca(){
		return listaAux;
	}
	
	@SuppressWarnings("static-access")
	public String getTempoTotalAuxilio(){
		listaAux = dao.listaRelacionamenoDoObjeto("AuxilioDoenca", "NUMR_pessoaFuncional", funcionais.getNUMG_idDoObjeto());
		int total = 0;
		for (AuxilioDoenca a : listaAux) {
			if(a.getDATA_inicioBeneficio() != null && a.getDATA_fimBeneficio() != null){
				total += RetornaTempos.retornaDiasApartirDeDuasDatas(a.getDATA_inicioBeneficio(), a.getDATA_fimBeneficio());
			}
		}
		return RetornaTempos.retornaDiaMesAno(new LocalDate().now().toDate(), new LocalDate().now().plusDays(total).toDate()).toString();
	}
	
	@SuppressWarnings("static-access")
	public void calculaProventos(){
		try{
			Period periodo = new Period(new LocalDate().fromDateFields(this.auxilio.getDATA_inicioBeneficio()),
					new LocalDate().fromDateFields(this.auxilio.getDATA_fimBeneficio()).minusDays(15),PeriodType.days());

			if(periodo.getDays() > 0){
				if (ContribuicaoHelper.existeContribuicao(this.funcionais.getNUMG_idDoObjeto()) != true) {
					ContribuicoeseAliquotas ca = new RemuneracaoDao().devolveUltimaContribuicao(this.funcionais.getDESC_matricula());
					System.out.println(ca.getVALR_contribuicaoPrevidenciaria());
					/*this.auxilio.setNUMR_qtdDias(periodo.getDays());
					BigDecimal bd = ca.getVALR_contribuicaoPrevidenciaria().divide(new BigDecimal(30));
					this.auxilio.setVALR_calculo(bd.multiply(new BigDecimal(this.auxilio.getNUMR_qtdDias())));*/
				}else{
					Message.addErrorMessage("Servidor não possui contribuição!");
				}
			}
			
			this.funcionais = new PessoasFuncionaisDao().devolveFuncional(this.funcionais.getDESC_matricula());
		}catch(Exception e){
//			Message.addErrorMessage("Erro ao calcular proventos!");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		AtestadosMedicos atestado = new AtestadosMedicos();
		String caminho = new StringBuilder().append(this.funcionais.getDESC_matricula()).append("\\")
				.append("auxilioDoenca").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(this.funcionais.getNUMG_idDoObjeto() > 0){
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
	
}


