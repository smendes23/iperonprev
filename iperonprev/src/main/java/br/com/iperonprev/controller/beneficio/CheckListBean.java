package br.com.iperonprev.controller.beneficio;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.CheckListDao;
import br.com.iperonprev.dao.DependentesDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.CheckList;
import br.com.iperonprev.models.Dependentes;
import br.com.iperonprev.models.DocumentosChecklist;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.RequisitosBeneficio;
import br.com.iperonprev.models.SituacaoPrevidenciaria;
import br.com.iperonprev.models.TituloBeneficio;
import br.com.iperonprev.models.VinculoPrevidenciario;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@SessionScoped
public class CheckListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	PessoasFuncionais pf = new PessoasFuncionais();
	Pessoas pessoas = new Pessoas();
	private String cpfServidor;
	List<PessoasFuncionais> listaFuncionais = new ArrayList<PessoasFuncionais>();
	boolean objetoExistente = false;
	
	private TipoBeneficioEnum tipoBeneficio;
	private int tipoRecadastramento = 0;
	
	
	
	public int getTipoRecadastramento() {
		return tipoRecadastramento;
	}

	public void setTipoRecadastramento(int tipoRecadastramento) {
		this.tipoRecadastramento = tipoRecadastramento;
	}

	public TipoBeneficioEnum getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(TipoBeneficioEnum tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	List<String> listaRegras = new ArrayList<>();
	
	public List<String> getListaRegras() {
		return listaRegras;
	}

	private int idPf;
	
	public int getIdPf() {
		return idPf;
	}

	public void setIdPf(int idPf) {
		this.idPf = idPf;
	}


	public PessoasFuncionais getPf() {
		return pf;
	}

	public void setPf(PessoasFuncionais pf) {
		this.pf = pf;
	}

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	
	public String getCpfServidor() {
		return cpfServidor;
	}

	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}

	public void buscaServidor(){
		this.pessoas = new PessoasDao().devolvePessoa(this.cpfServidor);
		
		Dependentes dependente = new Dependentes();
		if(verificaExistenciaDependente(this.pessoas.getNUMG_idDoObjeto()) == true &&
				verificaExistenciaFuncional(this.pessoas.getNUMG_idDoObjeto()) == false){
			dependente = new DependentesDao().listaDependentes(this.pessoas.getNUMG_idDoObjeto()).get(0);
			this.listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(dependente.getNUMR_idDoObjetoPessoas().getNUMR_cpf());
		}else{
			this.listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais","NUMR_idDoObjetoPessoas", this.pessoas.getNUMG_idDoObjeto());
		}
		
		
	}
	
	private boolean verificaExistenciaDependente(int idPessoa){
		boolean res = false;
		if(!new DependentesDao().listaDependentes(this.pessoas.getNUMG_idDoObjeto()).isEmpty()){
			res = true;
		}
		
		return res;
	}
	
	private boolean verificaExistenciaFuncional(int idPessoa){
		boolean res = false;
		if(!new PessoasFuncionaisDao().devolveListaDeFuncionais(this.pessoas.getNUMR_cpf()).isEmpty()){
			res = true;
		}
		
		return res;
	}
	
	public boolean verificaFuncionalPessoa(){
		boolean res = false;
		try {
			if(this.pf.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto().intValue() == this.pessoas.getNUMG_idDoObjeto().intValue()){
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Não foi possivel verificar funcional");
		}
		return res;
	}
	
	
	
	public void buscaFuncional(ValueChangeEvent event){
		int  idFuncional = (int)event.getNewValue();
		try{
			
				this.pf = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(idFuncional);
				
				if(this.pessoas.getNUMG_idDoObjeto().intValue() == this.pf.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto().intValue()){
					if(!new CheckListDao().devolveListaCheckList(this.pf.getNUMG_idDoObjeto(),this.pf.getENUM_tipoAposentadoria().toString()).isEmpty()){
						check = new CheckListDao().devolveListaCheckList(this.pf.getNUMG_idDoObjeto(),this.pf.getENUM_tipoAposentadoria().toString()).get(0);
					}
					
					if(pf.getENUM_tipoAposentadoria() != null || !pf.getENUM_tipoAposentadoria().equals(null)){
						this.tipoBeneficio = pf.getENUM_tipoAposentadoria();
					}
				}
			
		}catch(Exception e){
			System.out.println("Vazio");
		}
	}
	
	public List<TipoBeneficioEnum> getListaDeBeneficio(){
		return Arrays.asList(TipoBeneficioEnum.values());
	}
	
	public List<PessoasFuncionais> getListaFuncionais() {
		return listaFuncionais;
	}
	
	public void verificaStatus(ValueChangeEvent event){
		System.out.println((boolean)event.getNewValue()); 
	}

		
	public List<TituloBeneficio> getListaTituloBeneficio(){
		return  new CheckListDao().devolveListaTituloLimitada();
	}
	
	public List<TipoBeneficioEnum> getListaDeBeneficioRequisitos(){
		return Arrays.asList(TipoBeneficioEnum.values());
	}
	
	RequisitosBeneficio requisitos = new RequisitosBeneficio();
	TituloBeneficio tituloBeneficio = new TituloBeneficio();

	public RequisitosBeneficio getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(RequisitosBeneficio requisitos) {
		this.requisitos = requisitos;
	}

	public TituloBeneficio getTituloBeneficio() {
		return tituloBeneficio;
	}

	public void setTituloBeneficio(TituloBeneficio tituloBeneficio) {
		this.tituloBeneficio = tituloBeneficio;
	}
	TituloBeneficio tituloBen = new TituloBeneficio();
	
	public TituloBeneficio getTituloBen() {
		return tituloBen;
	}

	public void setTituloBen(TituloBeneficio tituloBen) {
		this.tituloBen = tituloBen;
	}
	
	public void exibeListaDeObjetos() {
		this.requisitos = new RequisitosBeneficio();
		new DialogsPrime().showDialogWithAndHeight(true, false, false, false, "requisitos", 280, 900); ;
	}

	public void pegaInstanciaDialogo(RequisitosBeneficio obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	public void selecionaObjetoDialogo(SelectEvent event) {
		getListaDeRequisitos();
	}
	
	public List<RequisitosBeneficio> getListaDeRequisitos(){
		List<RequisitosBeneficio> lista = new ArrayList<>();
		try{
			
			if(this.tipoBeneficio.toString() != null || !this.tipoBeneficio.toString().equals(null)){
				lista = new CheckListDao().listaDeRequisitos(this.tipoBeneficio.toString());
				this.tituloBen = lista.get(0).getREL_tituloBeneficio();
			}
			
			if(this.idPf > 0){
				this.pf = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(this.idPf);
				 lista.forEach(l->{
					 l.setFLAG_arquivo(new CheckListDao().verificaExistenciaArquivo(l.getNUMG_idDoObjeto(),this.pf.getNUMG_idDoObjeto()));
				 });
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao carregar lista de Beneficios.");
		}
		
		return lista;
	}
	
	public void salvaRequisito(){
		try{
			new GenericPersistence<RequisitosBeneficio>(RequisitosBeneficio.class).salvar(this.requisitos);
			this.requisitos = new RequisitosBeneficio();
			Message.addSuccessMessage("Requisistos salvos com sucesso.");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar requisitos");
		}
	}
	
	public void novoRequisito(){
		this.requisitos = new RequisitosBeneficio();
	}
	
	public void novo(){
		idPf = 0;
		this.cpfServidor = new String();
		this.pessoas = new Pessoas();
		this.listaRegras = new ArrayList<>();
		this.pf = new PessoasFuncionais();
		this.tipoBeneficio = null;
		this.tituloBen = new TituloBeneficio();
		check = new CheckList();
	}
	
	public void recarregaPagina() {
		idPf = 0;
		this.cpfServidor = new String();
		this.pessoas = new Pessoas();
		this.listaRegras = new ArrayList<>();
		this.pf = new PessoasFuncionais();
		this.tipoBeneficio = null;
		this.tituloBen = new TituloBeneficio();
		check = new CheckList();
	}
	
	CopyFile copyFile = new CopyFile();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		DocumentosChecklist docCheck = new DocumentosChecklist();
		this.pf = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(this.idPf);
		
		String caminho = new StringBuilder().append(this.pessoas.getNUMR_cpf()).append("\\")
				.append(this.requisitos.getENUM_tipoBeneficio()).append("\\").append(this.pf.getDESC_matricula())
				.append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(pessoas.getNUMG_idDoObjeto() > 0){
			copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
			docCheck.setDESC_caminhoRelativo(caminho);
			docCheck.setDESC_nome(event.getFile().getFileName());
			docCheck.setREL_requisitos(this.requisitos);
			docCheck.setREL_pessoasFuncionais(this.pf);
			new GenericPersistence<DocumentosChecklist>(DocumentosChecklist.class).salvar(docCheck);
			this.requisitos.setFLAG_arquivo(true);
			
		}else{
			Message.addErrorMessage("Funcional inexistente");
		}
		
    }
	
	public void removeArquivo(DocumentosChecklist doc){
		try{
			if(!this.listaDeDocumentos.isEmpty()){
				this.copyFile.removeFile(doc.getDESC_caminhoRelativo(), doc.getDESC_nome());
				new CheckListDao().removeDocumento(doc.getNUMG_idDoObjeto());
				devolveListaDocumentos();
				this.requisitos.setFLAG_arquivo(false);
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível remover o arquivo.Erro: "+e.getMessage());
		}
	}
	
	
	public void carregaRequisito(){
		RequestContext.getCurrentInstance().execute("PF('carDialog').show()");
	}
	
	public void fechaRequisito(){
		RequestContext.getCurrentInstance().execute("PF('carDialog').hide()");
	}

	List<DocumentosChecklist> listaDeDocumentos = new ArrayList<>();
	
	public List<DocumentosChecklist> devolveListaDocumentos(){
		listaDeDocumentos = new ArrayList<>();
		try{
			if(this.requisitos.isFLAG_arquivo() != false){
				listaDeDocumentos = new CheckListDao().devolveListaDocumentos(this.requisitos.getNUMG_idDoObjeto(), this.pf.getNUMG_idDoObjeto());
			}
		}catch(Exception e){
			System.out.println("Erro ao gerar lista de documentos.");
		}
		return listaDeDocumentos;
	}
	
	CheckList check = new CheckList();
	
	
	public CheckList getCheck() {
		return check;
	}

	public void setCheck(CheckList check) {
		this.check = check;
	}

	public void salvarCheckList(){
		try {
			if(verificaExistenciaDependente(this.pessoas.getNUMG_idDoObjeto()) == true &&
					new PessoasFuncionaisDao().verificaExistenciaDoFuncional(this.pessoas.getNUMG_idDoObjeto()) == false){
				PessoasFuncionais pessoaFuncional = new PessoasFuncionais();
				pessoaFuncional.setDESC_matricula(this.pf.getDESC_matricula());
				pessoaFuncional.setDATA_efetivoExercicio(this.pf.getDATA_efetivoExercicio());
				pessoaFuncional.setNUMR_idDoObjetoCargo(this.pf.getNUMR_idDoObjetoCargo());
				pessoaFuncional.setNUMR_situacaoFuncional(this.pf.getNUMR_situacaoFuncional());
				pessoaFuncional.setNUMR_idDoObjetoPessoas(this.pessoas);
				pessoaFuncional.setNUMR_fundoPrevidenciario(this.pf.getNUMR_fundoPrevidenciario());
				pessoaFuncional.setENUM_tipoAposentadoria(tipoBeneficio);
				pessoaFuncional.setNUMR_idDoObjetoCargo(new GenericPersistence<Cargos>(Cargos.class).buscarPorId(4808));
				new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(pessoaFuncional);
				this.pf = new PessoasFuncionaisDao().devolveFuncionalCpfMatricula(this.pessoas.getNUMR_cpf(), pessoaFuncional.getDESC_matricula());
				this.idPf = this.pf.getNUMG_idDoObjeto();
				this.pf.setNUMR_vinculoPrevidenciario(new GenericPersistence<VinculoPrevidenciario>(VinculoPrevidenciario.class).buscarPorId(3));
				this.pf.setNUMR_situacaoPrevidenciaria(new GenericPersistence<SituacaoPrevidenciaria>(SituacaoPrevidenciaria.class).buscarPorId(2));
				this.listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais","NUMR_idDoObjetoPessoas", this.pessoas.getNUMG_idDoObjeto());
				pessoaFuncional = new PessoasFuncionais();
			}else{
				this.pf.setENUM_tipoAposentadoria(tipoBeneficio);
				new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(this.pf);
			}
			verificaFuncionalPessoa();
			Message.addSuccessMessage("Beneficio incluido no funcional com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar alteração funcional.");
		}
		
		try {
			this.check.setENuM_tipoBeneficio(tipoBeneficio);
			this.check.setREL_pessoasFuncionais(this.pf);
			new GenericPersistence<CheckList>(CheckList.class).salvar(this.check);
			Message.addSuccessMessage("CheckList salvo com sucesso");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar CheckList");
		}
		
		
	}
	
	
	public void abreRelatorio(){
		RequestContext.getCurrentInstance().execute("openReport();");
	}
}
