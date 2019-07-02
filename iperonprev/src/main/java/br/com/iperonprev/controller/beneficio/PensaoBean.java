package br.com.iperonprev.controller.beneficio;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.constantes.TipoPensaoEnum;
import br.com.iperonprev.dao.CheckListDao;
import br.com.iperonprev.dao.DependentesDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PensaoDao;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.RepresentanteDao;
import br.com.iperonprev.helper.CidHelper;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AtestadosMedicos;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.AvaliacaoPensao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.Cid;
import br.com.iperonprev.models.Dependentes;
import br.com.iperonprev.models.DocumentosChecklist;
import br.com.iperonprev.models.FundoPrevidenciario;
import br.com.iperonprev.models.Pensao;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.RepresentanteLegal;
import br.com.iperonprev.models.RequisitosBeneficio;
import br.com.iperonprev.models.SituacaoFuncional;
import br.com.iperonprev.models.SituacaoPrevidenciaria;
import br.com.iperonprev.models.TipoRepresentanteLegal;
import br.com.iperonprev.models.TituloBeneficio;
import br.com.iperonprev.models.VinculoPrevidenciario;
import br.com.iperonprev.services.beneficio.QualificaStatus;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;

@Named
@ViewScoped
public class PensaoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Pensao pensao = new Pensao();
	Pessoas instituidor = new Pessoas();
	Pessoas dependente = new Pessoas();
	Dependentes dependentes = new Dependentes();
	private boolean maioridade;
	private HtmlInputText atualizacaoDocumento;
	GenericDao<Pensao> dao = new PensaoDao();
	List<Pensao> filtroDePensoes; 
	PessoasFuncionais funcionais = new PessoasFuncionais();
	private  List<Cid> listaCid = new ArrayList<Cid>();
	Cid cid = new Cid();
	List<AtestadosMedicos> listaAtestado = new ArrayList<AtestadosMedicos>();
	CopyFile copyFile = new CopyFile();
	AvaliacaoPensao avaliacaoPericial = new AvaliacaoPensao();
	PessoasFuncionais funcionalPensionista =new PessoasFuncionais();
	static Map<String,Object> mapa = new HashMap<String,Object>();
	AtosLegais atosLegais = new AtosLegais();
	RepresentanteLegal representanteLegal = new RepresentanteLegal();
	TipoRepresentanteLegal tipoRepresentante = new TipoRepresentanteLegal();
	List<Dependentes> listaDep = new ArrayList<Dependentes>();
	List<Pensao> listaPensao = new ArrayList<>();

	
	public List<Pensao> getListaPensao() {
		return listaPensao;
	}

	public void setListaPensao(List<Pensao> listaPensao) {
		this.listaPensao = listaPensao;
	}

	public Pessoas getDependente() {
		return dependente;
	}

	public void setDependente(Pessoas dependente) {
		this.dependente = dependente;
	}

	public List<Dependentes> getListaDep() {
		return listaDep;
	}

	public void setListaDep(List<Dependentes> listaDep) {
		this.listaDep = listaDep;
	}

	public Pessoas getInstituidor() {
		return instituidor;
	}

	public void setInstituidor(Pessoas instituidor) {
		this.instituidor = instituidor;
	}

	public TipoRepresentanteLegal getTipoRepresentante() {
		return tipoRepresentante;
	}

	public void setTipoRepresentante(TipoRepresentanteLegal tipoRepresentante) {
		this.tipoRepresentante = tipoRepresentante;
	}

	public RepresentanteLegal getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public AtosLegais getAtosLegais() {
		return atosLegais;
	}

	public void setAtosLegais(AtosLegais atosLegais) {
		this.atosLegais = atosLegais;
	}

	public PessoasFuncionais getFuncionalPensionista() {
		return funcionalPensionista;
	}

	public void setFuncionalPensionista(PessoasFuncionais funcionalPensionista) {
		this.funcionalPensionista = funcionalPensionista;
	}

	public AvaliacaoPensao getAvaliacaoPericial() {
		return avaliacaoPericial;
	}

	public void setAvaliacaoPericial(AvaliacaoPensao avaliacaoPericial) {
		this.avaliacaoPericial = avaliacaoPericial;
	}

	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}
	
	public String reinitCid() {
        cid = new Cid();
        return null;
    }
	
	public List<Cid> getListaCid() {
		return listaCid;
	}
	
	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	public List<Pensao> getFiltroDePensoes() {
		return filtroDePensoes;
	}

	public void setFiltroDePensoes(List<Pensao> filtroDePensoes) {
		this.filtroDePensoes = filtroDePensoes;
	}

	public HtmlInputText getAtualizacaoDocumento() {
		return atualizacaoDocumento;
	}

	public void setAtualizacaoDocumento(HtmlInputText atualizacaoDocumento) {
		this.atualizacaoDocumento = atualizacaoDocumento;
	}

	public boolean isMaioridade() {
		return maioridade;
	}

	public void setMaioridade(boolean maioridade) {
		this.maioridade = maioridade;
	}

	public Dependentes getDependentes() {
		return dependentes;
	}

	public void setDependentes(Dependentes dependentes) {
		this.dependentes = dependentes;
	}

	public Pensao getPensao() {
		return pensao;
	}

	public void setPensao(Pensao pensao) {
		this.pensao = pensao;
	}
	
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	@PostConstruct
	public void init(){
		parametrosDefault();
		try{
			if(ec.getSessionMap().containsKey("funcional")){
				funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				if(!new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).isEmpty()){
					this.pensao.setNUMR_processo(new CheckListDao().devolveListaCheckList(this.funcionais.getNUMG_idDoObjeto(),this.funcionais.getENUM_tipoAposentadoria().toString()).get(0).getDESC_numeroProcesso());
				}
				
				if(new PensaoDao().verificaExistenciaPensao(funcionais.getNUMG_idDoObjeto()) == true){
					this.pensao = new PensaoDao().devolvePensao(funcionais.getNUMG_idDoObjeto());
				}
			}
			
		}catch(Exception e){
			System.out.println("Não existe funcional");
		}
	}
	
	
	
	

	public void salvarObjeto() {
		try{
			
			/*Funcionais*/
			
			System.out.println(this.dependente.getDESC_nome());
			this.funcionalPensionista.setNUMR_idDoObjetoPessoas(this.dependente);
			this.funcionalPensionista.setDATA_Beneficio(this.pensao.getDATA_inicioDoBeneficio());
			this.funcionalPensionista.setDATA_efetivoExercicio(this.pensao.getDATA_inicioDoBeneficio());
			this.funcionalPensionista.setDATA_posse(this.pensao.getDATA_inicioDoBeneficio());
			this.funcionalPensionista.setNUMR_fundoPrevidenciario(new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).buscarPorId(3));
			this.funcionalPensionista.setNUMR_idDoObjetoCargo(new GenericPersistence<Cargos>(Cargos.class).buscarPorId(4808));
			this.funcionalPensionista.setNUMR_situacaoFuncional(new GenericPersistence<SituacaoFuncional>(SituacaoFuncional.class).buscarPorId(1));
			this.funcionalPensionista.setNUMR_situacaoPrevidenciaria(new GenericPersistence<SituacaoPrevidenciaria>(SituacaoPrevidenciaria.class).buscarPorId(4));
			this.funcionalPensionista.setNUMR_vinculoPrevidenciario(new GenericPersistence<VinculoPrevidenciario>(VinculoPrevidenciario.class).buscarPorId(4));
//			new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).salvar(this.funcionalPensionista);
			this.pensao.setREL_pessoasFuncionais(this.funcionalPensionista);
			
			if(!listaCid.isEmpty()){
				this.pensao.setNUMR_idDoObjetoCid(listaCid);
			}
			this.pensao.setREL_atoLegais(atosLegais);
			new GenericPersistence<Pensao>(Pensao.class).salvar(this.pensao);
			novoObjeto();
			carregaListaDePensoes();
			Message.addSuccessMessage("Pensão salva com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao salvar Pensão");
		}
	}
	
	public void novoObjeto() {
			this.dependentes = new Dependentes();
			this.funcionalPensionista = new PessoasFuncionais();
			this.listaCid = new ArrayList<>();
			this.atosLegais = new AtosLegais();
			this.dependente = new Pessoas();
			pensao = new Pensao();
			parametrosDefault();
			if(this.instituidor.getNUMG_idDoObjeto() == 0 || this.instituidor.getNUMG_idDoObjeto().equals(null)) {
				this.listaPensao = new ArrayList<>();
				
			}
	}

	public List<Pensao> listaDeObjetos() {
		return null;
	}

	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDePensoes");
	}
	
	public void exibeProvento(){
		new DialogsPrime().showDialogWithAndHeight(true, false, true, false, "proventosAposentadoria", 520, 840);
	}

	public void pegaInstanciaDialogo(AvaliacaoPensao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	public void selecionaObjetoDialogo(SelectEvent event) {
		try{
			if(actionButton != true){
				this.avaliacaoPericial =(AvaliacaoPensao)event.getObject();
				this.listaAtestado = this.avaliacaoPericial.getREL_atestado();
			}
		}catch(Exception e){
			
		}
	}
	
	public List<DecisaoEnum> getDecisaoInvalido() {
		return Arrays.asList(DecisaoEnum.values());
	}
	
	public List<TipoPensaoEnum> getTipoPensao(){
		return Arrays.asList(TipoPensaoEnum.values());
	}
	
	public List<DecisaoEnum> getDecisaoEstudante() {
		return Arrays.asList(DecisaoEnum.values());
	}
	
	public List<DecisaoEnum> getDecisaoEmancipado() {
		return Arrays.asList(DecisaoEnum.values());
	}
	
	public List<Cid> listaDeCids(String query){
		return new CidHelper().devolveResultadoConsulta(query);
	}
	
	public List<Pensao> getListaDePensoes(){
		List<Pensao> lista = new ArrayList<>();
		 lista = dao.buscaTodosObjetos("Pensao");
		lista.forEach(p ->{
			p.setENUM_status(new QualificaStatus().executa(p));
		});
		return lista;
	}

	public void devolveProximaDataPericia(){
		this.avaliacaoPericial.setDATA_fim(RetornaTempos.retornaDataFuturaComAnos(1, this.avaliacaoPericial.getDATA_inicio()));
	}
	
	public void recarregaPagina(){
		if(this.funcionais.getNUMG_idDoObjeto() == 0){
			this.pensao = new Pensao();
			this.dependentes = new Dependentes();
			this.avaliacaoPericial = new AvaliacaoPensao();
			this.cid = new Cid();
			listaAvaliacao = new ArrayList<>();
			this.listaAtestado = new ArrayList<>();
			this.listaCid = new ArrayList<>();
			this.funcionais = new PessoasFuncionais();
			this.atosLegais = new AtosLegais();
			mapa.remove("pensao");
			mapa.clear();
			
		}
		
	}
	
	static List<AvaliacaoPensao> listaAvaliacao = new ArrayList<>();
	
	public void buscaPensao(ValueChangeEvent event){
		try{
			PensaoDao pensaoDao = new PensaoDao();
			novaAvaliacao();
			if(!mapa.isEmpty()){
				mapa.remove("pensao");
				mapa.clear();
			}
			this.dependentes = (Dependentes) event.getNewValue();
			if(pensaoDao.verificaExistenciaPensao(this.dependentes.getNUMG_idDoObjeto()) != false){
				this.pensao = new PensaoDao().devolvePensao(this.dependentes.getNUMG_idDoObjeto());
				listaAtestado = this.avaliacaoPericial.getREL_atestado();
				mapa.put("pensao", this.pensao);
				this.pensao = new GenericPersistence<Pensao>(Pensao.class).buscarPorId(this.pensao.getNUMG_idDoObjeto());
				listaCid = pensao.getNUMR_idDoObjetoCid();
				this.funcionalPensionista = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class)
						.buscaObjetoRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", this.dependentes.getNUMR_idDoObjetoDependentes().getNUMG_idDoObjeto());
				this.atosLegais = this.pensao.getREL_atoLegais();
			}
		}catch(Exception e){
			System.out.println("Não existe pensão");
		}
	}
	
	public List<AvaliacaoPensao> getListaAvaliacaoPensao(){
		List<AvaliacaoPensao> lista = new ArrayList<>();
		try{
			Pensao p = (Pensao)mapa.get("pensao");
			return ListaAvaliacaoReversa(p); 
		}catch(Exception e){
			System.out.println("Lista de Avaliação de pensão limpa");
		}
		return lista;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		AtestadosMedicos atestado = new AtestadosMedicos();
		String caminho = new StringBuilder().append(this.funcionalPensionista.getDESC_matricula()).append("\\")
				.append("pensaoInvalidez").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(this.funcionalPensionista.getNUMG_idDoObjeto() > 0){
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
	
	public void salvarAvaliacao(){
		try{
			Pensao pen = new GenericPersistence<Pensao>(Pensao.class).buscarPorId(this.pensao.getNUMG_idDoObjeto());
			this.avaliacaoPericial.setREL_pensao(pen);
			if(!listaAtestado.isEmpty()){
				this.avaliacaoPericial.setREL_atestado(listaAtestado);
			}
			
			new GenericPersistence<AvaliacaoPensao>(AvaliacaoPensao.class).salvar(this.avaliacaoPericial);
			novaAvaliacao();
			Message.addSuccessMessage("Avalição salva com sucesso");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar avaliação");
		}
	}
	
	public void novaAvaliacao(){
		this.avaliacaoPericial = new AvaliacaoPensao();
		listaAtestado = new ArrayList<>();
	}
	
	public void pegaInstanciaAvaliacao(AvaliacaoPensao avaliacao){
		this.avaliacaoPericial = avaliacao;
	}
	
	 private List<AvaliacaoPensao> ListaAvaliacaoReversa(Pensao pen){
		try{
			listaAvaliacao = new GenericPersistence<AvaliacaoPensao>(AvaliacaoPensao.class).listarRelacionamento("AvaliacaoPensao","REL_pensao",pen.getNUMG_idDoObjeto());
			Comparator<AvaliacaoPensao> listaAvaliacaoComparator =(d1,d2)->d1.getDATA_inicio().compareTo(d2.getDATA_inicio()); 
			listaAvaliacao.sort(listaAvaliacaoComparator.reversed());
		}catch(Exception e){
			System.out.println("Erro listaAvaliação");
		}
		return listaAvaliacao;
		
	}
	
	private void parametrosDefault(){
		this.pensao.setENUM_invalido(DecisaoEnum.NAO);
		this.pensao.setENUM_estudante(DecisaoEnum.NAO);
		this.pensao.setENUM_emancipado(DecisaoEnum.NAO);
		this.pensao.setENUM_tipoPensao(TipoPensaoEnum.T);
	}
	
	private boolean actionButton = false;
	
	public void fechaListaAvaliacaoPensao(){
		actionButton = true;
		RequestContext.getCurrentInstance().closeDialog("listaDePensoes");
	}
	
	public void actionClose(){
		actionButton = false;
	}
	
	public void setaProximaData(){
		avaliacaoPericial.setDATA_fim(new LocalDate(avaliacaoPericial.getDATA_inicio()).plusYears(1).toDate());
	}
	
	public void removerAvaliacao(AvaliacaoPensao avaliacao){
		new GenericPersistence<Averbacao>(Averbacao.class).remover("AvaliacaoPensao", "NUMG_idDoObjeto", avaliacao.getNUMG_idDoObjeto());
	}
	
	public List<AtosLegais> getListaAtosLegais(){
		List<AtosLegais> listaAto = new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
		return listaAto.stream().filter(a->a.getENUM_tipoBeneficio() == TipoBeneficioEnum.PENSAO).collect(Collectors.toList());
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
			
			if(this.funcionais.getENUM_tipoAposentadoria().toString() != null || !this.funcionais.getENUM_tipoAposentadoria().toString().equals(null)){
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
	
	public List<TipoRepresentanteLegal> getListaTipoRepresentante(){
		return new GenericPersistence<TipoRepresentanteLegal>(TipoRepresentanteLegal.class).listarTodos("TipoRepresentanteLegal");
	}
	private String cpfServidor;
	Pessoas pessoaRepresentante = new Pessoas();
	
	
	public String getCpfServidor() {
		return cpfServidor;
	}

	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}

	public Pessoas getPessoaRepresentante() {
		return pessoaRepresentante;
	}

	public void setPessoaRepresentante(Pessoas pessoaRepresentante) {
		this.pessoaRepresentante = pessoaRepresentante;
	}

	public void salvarRepresentanteLegal(){
		try {
			this.representanteLegal.setNUMR_pessoa(this.funcionais.getNUMR_idDoObjetoPessoas());
			this.representanteLegal.setNUMR_representante(this.pessoaRepresentante);
			this.representanteLegal.setNUMR_tipoRepresentante(this.tipoRepresentante);
			Message.addSuccessMessage("Representante legal salvo com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar representante legal.");
		}
	}
	
	public void buscarInstituidor(){
		try {
			
			if(new PessoasDao().devolvePessoa(this.cpfServidor).getDATA_obito() != null) {
				
				this.instituidor = new PessoasDao().devolvePessoa(this.cpfServidor);
				
				if (new DependentesDao().existeDependente(this.instituidor.getNUMG_idDoObjeto())) {
					this.listaDep = new DependentesDao().listaDependentesPensionistas(this.instituidor.getNUMG_idDoObjeto());
				}
				carregaListaDePensoes();
			}else {
				Message.addErrorMessage("Servidor está vivo!");
			}
			/*this.pessoaRepresentante = new PessoasDao().devolvePessoa(this.cpfServidor);
			if(new RepresentanteDao().devolveRepresentanteLegal(this.pessoaRepresentante.getNUMG_idDoObjeto()).isEmpty()){
				this.representanteLegal = new RepresentanteDao().devolveRepresentanteLegal(this.pessoaRepresentante.getNUMG_idDoObjeto()).get(0);
			}*/
		} catch (Exception e) {
			Message.addErrorMessage("Não foi possível carregar o representante legal.");
		}
		
				
	}
	
	public void carregaPensao(Pensao pensao) {
		this.pensao = pensao;
		this.funcionalPensionista = pensao.getREL_pessoasFuncionais();
		this.dependente = this.funcionalPensionista.getNUMR_idDoObjetoPessoas();
		try {
			this.atosLegais = pensao.getREL_atoLegais();
		}catch(Exception e) {
			Message.addErrorMessage("Não foi possível carregar ato legal");
		}
		
		try {
			this.listaCid = pensao.getNUMR_idDoObjetoCid();
		}catch(Exception e) {
			System.out.println("Não foi possivel carregar o CID");
		}
	}
	
	public void carregaListaDePensoes() {
		this.listaPensao = new PensaoDao().listaDePensoes(this.instituidor.getNUMR_cpf());
	}
}
