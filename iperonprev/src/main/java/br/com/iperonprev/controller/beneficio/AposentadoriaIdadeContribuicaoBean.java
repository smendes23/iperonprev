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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;
import br.com.iperonprev.dao.AposentadoriaIdadeTempoDao;
import br.com.iperonprev.dao.AposentadoriaInvalidezDao;
import br.com.iperonprev.dao.CheckListDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaIdadeTempo;
import br.com.iperonprev.models.AtestadosMedicos;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.CheckList;
import br.com.iperonprev.models.DecisoesJudiciais;
import br.com.iperonprev.models.DocumentosChecklist;
import br.com.iperonprev.models.MotivoCessacaoBeneficio;
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
public class AposentadoriaIdadeContribuicaoBean implements GenericBean<AposentadoriaIdadeTempo>,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	AposentadoriaIdadeTempo idadeEtempo = new AposentadoriaIdadeTempo();
	PessoasFuncionais funcionais = new PessoasFuncionais();
	GenericDao<AposentadoriaIdadeTempo> dao = new AposentadoriaIdadeTempoDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<DecisoesJudiciais> listaDecisao = new ArrayList<>();
	List<AtosLegais> listaAtosLegaisCollection = new ArrayList<>();
	AtosLegais atoLegal = new AtosLegais();
	CopyFile copyFile = new CopyFile();
	CheckList check = new CheckList();
	
	@PostConstruct
	public void init(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try{
			if(ec.getSessionMap().containsKey("funcional")){
				this.funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				
				if(new AposentadoriaIdadeTempoDao().verificaExistenciaAposentadoria(this.funcionais.getNUMG_idDoObjeto()) == true){
					this.idadeEtempo = new GenericPersistence<AposentadoriaIdadeTempo>(AposentadoriaIdadeTempo.class).buscaObjetoRelacionamento("AposentadoriaIdadeTempo","NUMR_idDoObejtoPessoasFuncionais",funcionais.getNUMG_idDoObjeto());
					
					if(!idadeEtempo.getREL_atoLegais().isEmpty()){
						this.listaAtosLegaisCollection = idadeEtempo.getREL_atoLegais();
					}
					
					if(!idadeEtempo.getREL_decisaoJudical().isEmpty()){
						this.listaDecisao = idadeEtempo.getREL_decisaoJudical();
					}
				}
				
				if(!new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).isEmpty()){
					this.idadeEtempo.setNUMR_processo(new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).get(0).getDESC_numeroProcesso());
				}
				
				this.idadeEtempo.setNUMR_idade(RetornaTempos.retornaAnosApartirDeUmaData(this.funcionais.getNUMR_idDoObjetoPessoas().getDATA_nascimento()));
				this.idadeEtempo.setDESC_tempoDeContruibuicao(RetornaTempos.retornaTempoDeContribuicaoFormatadoDiaMesAno(this.funcionais));
			}else{
				this.funcionais = new PessoasFuncionais();
			}
			
			
			
		}catch(Exception e){
			System.out.println("Funcional não existe para aposentadoria por Idade de Tempo de Contribuição.");
		}
	}
	
	
	public AtosLegais getAtoLegal() {
		return atoLegal;
	}


	public void setAtoLegal(AtosLegais atoLegal) {
		this.atoLegal = atoLegal;
	}


	public List<AtosLegais> getListaAtosLegaisCollection() {
		return listaAtosLegaisCollection;
	}


	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	
	public AposentadoriaIdadeTempo getIdadeEtempo() {
		return idadeEtempo;
	}

	public void setIdadeEtempo(AposentadoriaIdadeTempo idadeEtempo) {
		this.idadeEtempo = idadeEtempo;
	}
	
	public String reinit() {
        this.atoLegal = new AtosLegais();
        return null;
    }

	@Override
	public void salvarObjeto() {
		try{
			this.funcionais.setNUMR_vinculoPrevidenciario(new GenericPersistence<VinculoPrevidenciario>(VinculoPrevidenciario.class).buscarPorId(3));
			this.funcionais.setNUMR_situacaoPrevidenciaria(new GenericPersistence<SituacaoPrevidenciaria>(SituacaoPrevidenciaria.class).buscarPorId(2));
			this.funcionais.setENUM_tipoAposentadoria(TipoBeneficioEnum.IDADETEMPO);
			new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(funcionais);
			this.idadeEtempo.setREL_atoLegais(this.listaAtosLegaisCollection);
			this.idadeEtempo.setREL_decisaoJudical(this.listaDecisao);
			this.idadeEtempo.setNUMR_idDoObejtoPessoasFuncionais(this.funcionais);
			dao.salvaObjeto(idadeEtempo);
			novoObjeto();
			Message.addSuccessMessage("Aposentadoria Salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Aposentadoria!");
		}
		
		
	}

	
	@Override
	public void novoObjeto() {
		this.idadeEtempo = new AposentadoriaIdadeTempo();
		this.funcionais = new PessoasFuncionais();
		this.listaAtosLegaisCollection = new ArrayList<>();
		this.listaDecisao = new ArrayList<>();
		
	}

	@Override
	public List<AposentadoriaIdadeTempo> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	// Disabilitado
	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialogWithAndHeight(true, false, true, false, "proventosAposentadoria", 520, 840);
	}

	@Override
	public void pegaInstanciaDialogo(AposentadoriaIdadeTempo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.idadeEtempo = (AposentadoriaIdadeTempo)event.getObject();
		this.funcionais = idadeEtempo.getNUMR_idDoObejtoPessoasFuncionais();
	}
	
	public List<MotivoCessacaoBeneficio> getListaMotivoCessacaoBeneficio(){
		return new GenericPersistence<MotivoCessacaoBeneficio>(MotivoCessacaoBeneficio.class).listarTodos("MotivoCessacaoBeneficio");
	}

	public void recarregaPagina(){
		if(this.funcionais.getNUMG_idDoObjeto() == 0){
			novoObjeto();
		}
	}
	
	public List<TipoReajusteEnum> getListaDeReajustes(){
		return Arrays.asList(TipoReajusteEnum.values());
	}
	
	public List<TipoProventosEnum> getListaDeProventos(){
		return new AposentadoriaInvalidezDao().devolveListaDeTiposDeProventos();
	}
	
	public void removeArquivo(AtestadosMedicos atesta){
		try{
		this.copyFile.removeFile(atesta.getDESC_caminhoRelativo(), atesta.getDESC_nome());
			for (int i = 0; i < listaDecisao.size(); i++) {
				if(listaDecisao.get(i).getNUMG_idDoObjeto() == atesta.getNUMG_idDoObjeto()){
					listaDecisao.remove(i);
				}
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível remover o arquivo.");
		}
	}
	
	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		
		DecisoesJudiciais decisoes = new DecisoesJudiciais();
		String caminho = new StringBuilder().append(this.funcionais.getDESC_matricula()).append("\\")
				.append("idadeContribuicao").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(this.funcionais.getNUMG_idDoObjeto() > 0){
			copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
			
			decisoes.setDESC_caminhoRelativo(caminho);
			decisoes.setDESC_nome(event.getFile().getFileName());
			listaDecisao.add(decisoes);
		}else{
			Message.addErrorMessage("Funcional inexistente");
		}
		
    }
	
	public List<DecisoesJudiciais> getListaDeDecisoesJudiciais(){
		return listaDecisao;
	}
	
	public List<AtosLegais> getListaFundamentacao(){
		List<AtosLegais> lista = new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
		return lista.stream().filter(a->a.getENUM_tipoBeneficio() == TipoBeneficioEnum.IDADETEMPO).collect(Collectors.toList());
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
