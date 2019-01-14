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
import br.com.iperonprev.dao.AposentadoriaIdadeDao;
import br.com.iperonprev.dao.AposentadoriaInvalidezDao;
import br.com.iperonprev.dao.CheckListDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaIdade;
import br.com.iperonprev.models.AtestadosMedicos;
import br.com.iperonprev.models.AtosLegais;
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
public class AposentadoriaIdadeBean implements GenericBean<AposentadoriaIdade>,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	AposentadoriaIdade idade = new AposentadoriaIdade();
	PessoasFuncionais funcionais = new PessoasFuncionais();
	GenericDao<AposentadoriaIdade> dao = new AposentadoriaIdadeDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<DecisoesJudiciais> listaDecisao = new ArrayList<>();
	List<AtosLegais> listaAtosLegaisCollection = new ArrayList<>();
	AtosLegais atoLegal = new AtosLegais();
	CopyFile copyFile = new CopyFile();

	
	public AtosLegais getAtoLegal() {
		return atoLegal;
	}

	public void setAtoLegal(AtosLegais atoLegal) {
		this.atoLegal = atoLegal;
	}

	public List<AtosLegais> getListaAtosLegaisCollection() {
		return listaAtosLegaisCollection;
	}


	@PostConstruct
	public void init(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try{
			if(ec.getSessionMap().containsKey("funcional")){
				
				this.funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				
				if(new AposentadoriaIdadeDao().verificaExistenciaAposentadoria(this.funcionais.getNUMG_idDoObjeto())){
					this.idade = new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class).buscaObjetoRelacionamento("AposentadoriaIdade", "NUMR_idDoObejtoPessoasFuncionais", this.funcionais.getNUMG_idDoObjeto());
					if(!this.idade.getREL_atoLegais().isEmpty()){
						this.listaAtosLegaisCollection = this.idade.getREL_atoLegais();
					}
					if(!this.idade.getREL_decisaoJudical().isEmpty()){
						this.listaDecisao = this.idade.getREL_decisaoJudical();
					}
				}
				
				if(!new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).isEmpty()){
					this.idade.setNUMR_processo(new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).get(0).getDESC_numeroProcesso());
				}
				
				this.idade.setNUMR_idade(RetornaTempos.retornaAnosApartirDeUmaData(funcionais.getNUMR_idDoObjetoPessoas().getDATA_nascimento()));
				this.idade.setDESC_tempoDeContruibuicao(RetornaTempos.retornaTempoDeContribuicaoFormatadoDiaMesAno(funcionais));
			}
		}catch(Exception e){
			System.out.println("Não existe Funcional");
		}
		
	}
	
	public String reinit() {
        this.atoLegal = new AtosLegais();
        return null;
    }
	
	
	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	public AposentadoriaIdade getIdade() {
		return idade;
	}

	public void setIdade(AposentadoriaIdade idade) {
		this.idade = idade;
	}

	@Override
	public void salvarObjeto() {
		try{
			this.funcionais.setNUMR_vinculoPrevidenciario(new GenericPersistence<VinculoPrevidenciario>(VinculoPrevidenciario.class).buscarPorId(3));
			this.funcionais.setNUMR_situacaoPrevidenciaria(new GenericPersistence<SituacaoPrevidenciaria>(SituacaoPrevidenciaria.class).buscarPorId(2));
			this.funcionais.setENUM_tipoAposentadoria(TipoBeneficioEnum.IDADE);
			new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(funcionais);
			
			this.idade.setNUMR_idDoObejtoPessoasFuncionais(this.funcionais);
			this.idade.setREL_atoLegais(this.listaAtosLegaisCollection);
			this.idade.setREL_decisaoJudical(this.listaDecisao);
			dao.salvaObjeto(idade);
			novoObjeto();
			Message.addSuccessMessage("Aposentadoria salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Aposentadoria");
		}
		
	}
	
	@Override
	public void novoObjeto() {
		this.idade = new AposentadoriaIdade();
		this.funcionais = new PessoasFuncionais();
		this.listaAtosLegaisCollection = new ArrayList<>();
		this.listaDecisao = new ArrayList<>();
	}

	@Override
	public List<AposentadoriaIdade> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialogWithAndHeight(true, false, true, false, "proventosAposentadoria", 520, 840);
	}

	@Override
	public void pegaInstanciaDialogo(AposentadoriaIdade obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.idade = (AposentadoriaIdade)event.getObject();
		this.funcionais = idade.getNUMR_idDoObejtoPessoasFuncionais();
	}
	
	public List<TipoBeneficioEnum> getListaTipoAposentadoria(){
		return Arrays.asList(TipoBeneficioEnum.values());
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

	StringBuilder sb = new StringBuilder();

	
	
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
				.append("aposentadoriaIdade").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
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
		return lista.stream().filter(a->a.getENUM_tipoBeneficio() == TipoBeneficioEnum.IDADE).collect(Collectors.toList());
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
