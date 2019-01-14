package br.com.iperonprev.controller.beneficio;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Ajax;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;
import br.com.iperonprev.dao.AposentadoriaInvalidezDao;
import br.com.iperonprev.dao.CheckListDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.helper.CidHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaPorInvalidez;
import br.com.iperonprev.models.AtestadosMedicos;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.Cid;
import br.com.iperonprev.models.DocumentosChecklist;
import br.com.iperonprev.models.MotivoCessacaoBeneficio;
import br.com.iperonprev.models.MotivoConcessaoBeneficio;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.RequisitosBeneficio;
import br.com.iperonprev.models.SituacaoPrevidenciaria;
import br.com.iperonprev.models.TituloBeneficio;
import br.com.iperonprev.models.VinculoPrevidenciario;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;

@Named
@ViewScoped
public class AposentadoriaInvalidezBean implements Serializable,GenericBean<AposentadoriaPorInvalidez>{
	private static final long serialVersionUID = 1L;
	
	AposentadoriaPorInvalidez invalidez = new AposentadoriaPorInvalidez();
	PessoasFuncionais funcionais = new PessoasFuncionais();
	private  List<Cid> listaCid = new ArrayList<Cid>();
	Cid cid = new Cid();
	GenericDao<AposentadoriaPorInvalidez> dao = new AposentadoriaInvalidezDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<AtestadosMedicos> listaAtestado = new ArrayList<AtestadosMedicos>();
	List<AtosLegais> listaDeAtos = new ArrayList<>();
	AtosLegais atosLegais = new AtosLegais();
	CopyFile copyFile = new CopyFile();

	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	@PostConstruct
	public void init(){
		try{
			if(ec.getSessionMap().containsKey("funcional")){
				this.funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				if(new AposentadoriaInvalidezDao().verificaExistenciaAposentadoria(this.funcionais.getNUMG_idDoObjeto()) == true){
					this.invalidez =new GenericPersistence<AposentadoriaPorInvalidez>(AposentadoriaPorInvalidez.class).buscaObjetoRelacionamento("AposentadoriaPorInvalidez", "NUMR_idDoObejtoPessoasFuncionais", funcionais.getNUMG_idDoObjeto());
					this.listaAtestado = invalidez.getREL_atestadoMedico();
					this.listaDeAtos = invalidez.getREL_atoLegais();
					this.listaCid = invalidez.getNUMR_cid();
					if(!new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).isEmpty()){
						this.invalidez.setNUMR_processo(new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).get(0).getDESC_numeroProcesso());
					}
				}else{
					this.invalidez.setNUMR_idade(RetornaTempos.retornaAnosApartirDeUmaData(funcionais.getNUMR_idDoObjetoPessoas().getDATA_nascimento()));
					this.invalidez.setDESC_tempoDeContruibuicao(RetornaTempos.retornaTempoDeContribuicaoFormatadoDiaMesAno(funcionais));
				}
				
			}
		}catch(Exception e){
			System.out.println("Não existe funcional");
		}
	}

	
	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public String reinit() {
        atosLegais = new AtosLegais();
        return null;
    }
	
	public String reinitCid() {
        cid = new Cid();
        return null;
    }
	

	public void setAtosLegais(AtosLegais atosLegais) {
		this.atosLegais = atosLegais;
	}

	public List<AtosLegais> getListaDeAtos() {
		return listaDeAtos;
	}

	public AtosLegais getAtosLegais() {
		return atosLegais;
	}

	public List<Cid> getListaCid() {
		return listaCid;
	}

	public AposentadoriaPorInvalidez getInvalidez() {
		return invalidez;
	}

	public void setInvalidez(AposentadoriaPorInvalidez invalidez) {
		this.invalidez = invalidez;
	}

	
	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	@Override
	public void salvarObjeto() {
		
		try{
			this.funcionais.setNUMR_vinculoPrevidenciario(new GenericPersistence<VinculoPrevidenciario>(VinculoPrevidenciario.class).buscarPorId(3));
			this.funcionais.setNUMR_situacaoPrevidenciaria(new GenericPersistence<SituacaoPrevidenciaria>(SituacaoPrevidenciaria.class).buscarPorId(2));
			
			this.funcionais.setENUM_tipoAposentadoria(TipoBeneficioEnum.INVALIDEZ);
			new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(funcionais);
			
			this.invalidez.setNUMR_idDoObejtoPessoasFuncionais(this.funcionais);
			this.invalidez.setNUMR_cid(this.listaCid);
			this.invalidez.setREL_atoLegais(this.listaDeAtos);
			this.invalidez.setREL_atestadoMedico(this.listaAtestado);
			dao.salvaObjeto(invalidez);
			novoObjeto();
			Message.addSuccessMessage("Aposentadoria salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Aposentadoria!");
		}
	}
	

	@Override
	public void novoObjeto() {
		this.invalidez = new AposentadoriaPorInvalidez();
		this.funcionais = new PessoasFuncionais();
		this.listaDeAtos = new ArrayList<>();
		this.listaAtestado = new ArrayList<>();
		this.listaCid = new ArrayList<>();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("funcional");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();
	}

	@Override
	public List<AposentadoriaPorInvalidez> listaDeObjetos() {
		return null;
	}

	
	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialogWithAndHeight(true, false, true, false, "proventosAposentadoria", 520, 840);
	}

	@Override
	public void pegaInstanciaDialogo(AposentadoriaPorInvalidez obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.invalidez = (AposentadoriaPorInvalidez)event.getObject();
	}
	
	public List<TipoProventosEnum> getListaDeProventos(){
		return new AposentadoriaInvalidezDao().devolveListaDeTiposDeProventos();
	}
	
	
	public List<Cid> listaDeCids(String query){
	
		return new CidHelper().devolveResultadoConsulta(query);
	}

	public List<MotivoConcessaoBeneficio> getListaMotivoConcessaoBeneficio(){
		return new AposentadoriaInvalidezDao().devolveListaConcessaoBeneficio();
	}
	
	public List<MotivoCessacaoBeneficio> getListaMotivoCessacaoBeneficio(){
		return new AposentadoriaInvalidezDao().devolveListaCessacaoBeneficio();
	}
	
	public List<TipoReajusteEnum> getListaDeReajustes(){
		return Arrays.asList(TipoReajusteEnum.values());
	}
	
	public void atualizaDataFimDoBeneficio(){
		this.invalidez.setDATA_proximaPericia(RetornaTempos.retornaDataFuturaComAnos(1, this.invalidez.getDATA_ultimaPericia()));
		Ajax.update("formInvalidez:proxima");
	}
	
	public void recarregaPagina(){
		if(this.funcionais.getNUMG_idDoObjeto() == 0){
			novoObjeto();
		}
		
	}
	

	StringBuilder sb = new StringBuilder();

	
	
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
		String caminho = new StringBuilder().append(this.funcionais.getDESC_matricula()).append("\\")
				.append("invalidez").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
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
	
	public List<AtosLegais> getListaAtosLegais(){
		List<AtosLegais> listaAto = new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
		return listaAto.stream().filter(a->a.getENUM_tipoBeneficio() == TipoBeneficioEnum.INVALIDEZ).collect(Collectors.toList());
	}
	
	TituloBeneficio tituloBen = new TituloBeneficio();
	
	public TituloBeneficio getTituloBen() {
		return tituloBen;
	}

	public void setTituloBen(TituloBeneficio tituloBen) {
		this.tituloBen = tituloBen;
	}
	
	public void carregaRequisito(){
		RequestContext.getCurrentInstance().execute("PF('reqDialog').show()");
	}
	
	public List<RequisitosBeneficio> getListaDeRequisitos(){
		List<RequisitosBeneficio> lista = new ArrayList<>();
		try{
			
			if(this.funcionais.getENUM_tipoAposentadoria() != null || !this.funcionais.getENUM_tipoAposentadoria().equals(null)){
				lista = new CheckListDao().listaDeRequisitos(this.funcionais.getENUM_tipoAposentadoria().toString());
				this.tituloBen = lista.get(0).getREL_tituloBeneficio();
			}
			
			if(this.funcionais.getNUMG_idDoObjeto() > 0){
				this.funcionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(this.funcionais.getNUMG_idDoObjeto());
				 lista.forEach(l->{
					 l.setFLAG_arquivo(new CheckListDao().verificaExistenciaArquivo(l.getNUMG_idDoObjeto(),this.funcionais.getNUMG_idDoObjeto()));
				 });
			}
			
			
		}catch(Exception e){
			System.out.println("Erro ao carregar lista de Beneficios.");
		}
		
		return lista;
	}
	
	public String devolveDocumento(int idRequisito){
		String res = new String();
		DocumentosChecklist doc = new DocumentosChecklist();
		try{
			
			if(!new CheckListDao().devolveListaDocumentos(idRequisito, this.funcionais.getNUMG_idDoObjeto()).isEmpty()){
				doc = new CheckListDao().devolveListaDocumentos(idRequisito, this.funcionais.getNUMG_idDoObjeto()).get(0);
				res = new StringBuilder().append(doc.getDESC_caminhoRelativo()).append("/").append(doc.getDESC_nome()).toString(); 
			}
		}catch(Exception e){
			System.out.println("Erro ao carregar documento.");
		}
		return res;
	}
	
	public void fechaRequisito(){
		RequestContext.getCurrentInstance().execute("PF('reqDialog').hide()");
	}
	
	
}
