package br.com.iperonprev.controller.censo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.joda.time.LocalDate;
import org.primefaces.event.FlowEvent;

import br.com.iperonprev.cep.CEP;
import br.com.iperonprev.cep.CEPService;
import br.com.iperonprev.cep.CEPServiceFactory;
import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.controller.gerenciador.MasterBean;
import br.com.iperonprev.dao.EnderecoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.Dependentes;
import br.com.iperonprev.models.Enderecos;
import br.com.iperonprev.models.EstadoCivil;
import br.com.iperonprev.models.Estados;
import br.com.iperonprev.models.Municipios;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.TipoLogradouro;
import br.com.iperonprev.models.TipoRepresentanteLegal;
import br.com.iperonprev.models.UnidadesCenso;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@ViewScoped
public class RecadastramentoAposentadoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Pessoas pessoa = new Pessoas();
	PessoasFuncionais pf = new PessoasFuncionais();
	Enderecos endereco = new Enderecos();
	Estados estado = new Estados();
	Municipios municipio = new Municipios();
	EstadoCivil estadoCivil = new EstadoCivil();
	CensoPrevidenciario censo = new CensoPrevidenciario();
	UnidadesCenso unidade = new UnidadesCenso();
	List<Dependentes> listaDependentes = new ArrayList<>();
	List<ConvocacaoCensoDto> listaConvocacao = new ArrayList<ConvocacaoCensoDto>();
	private int idPessoa;
	
	
	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}


	private boolean skip;
	
	
	
	public boolean isSkip() {
		return skip;
	}


	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }


	public CensoPrevidenciario getCenso() {
		return censo;
	}
	DadosCenso dadosCenso = new DadosCenso();
	
	@ManagedProperty(value="#{censoPrevidenciarioBean}")
	private CensoPrevidenciarioBean censoBean;
	

	public void setCensoBean(CensoPrevidenciarioBean censoBean) {
		this.censoBean = censoBean;
	}

	@PostConstruct
	public void init(){
		System.out.println("Iniciou");
		
		try{
			
			System.out.println(censoBean.listaConvocacao.get(0).getNUMR_idPessoasFuncionais().getNUMG_idDoObjeto());
				
				this.listaConvocacao = censoBean.listaConvocacao;
				this.pf = censoBean.getPf();
				this.dadosCenso = listaConvocacao.get(0).getNUMR_idCenso();
				this.pessoa = pf.getNUMR_idDoObjetoPessoas();
				System.out.println("Nome: "+this.pessoa.getDESC_nome());
				this.estadoCivil = this.pessoa.getNUMR_estadoCivil();
				this.endereco = this.pessoa.getNUMR_idDoObjetoEndereco();
				this.municipio = this.endereco.getNUMR_idDoObjetoMunicipio();
				this.estado = this.municipio.getNUMR_idDoObjetoEstado();
				listaM = new GenericPersistence<Municipios>(Municipios.class).listarRelacionamento("Municipios", "NUMR_idDoObjetoEstado", this.estado.getNUMG_idDoObjeto());
			
				this.censoBean = new CensoPrevidenciarioBean();
		}catch(Exception e){
			System.out.println("não possui censo");
		}
	}
	
	public List<Dependentes> getListaDependentes() {
		return listaDependentes;
	}


	public UnidadesCenso getUnidade() {
		return unidade;
	}


	public void setUnidade(UnidadesCenso unidade) {
		this.unidade = unidade;
	}


	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public PessoasFuncionais getPf() {
		return pf;
	}

	public void setPf(PessoasFuncionais pf) {
		this.pf = pf;
	}

	public Enderecos getEndereco() {
		return endereco;
	}

	public void setEndereco(Enderecos endereco) {
		this.endereco = endereco;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public Municipios getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipios municipio) {
		this.municipio = municipio;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

		
	@SuppressWarnings("static-access")
	public String salvaObjeto(){
		FacesContext fc = FacesContext.getCurrentInstance();
		@SuppressWarnings("deprecation")
		MasterBean mb = (MasterBean) fc.getApplication().getVariableResolver().resolveVariable(fc, "masterBean");
		
		String res = new String();
		try{
		this.endereco.setNUMR_idDoObjetoMunicipio(municipio);
		this.pessoa.setNUMR_idDoObjetoEndereco(endereco);
		this.pessoa.setNUMR_estadoCivil(this.estadoCivil);
			if(salvaPessoa(this.pessoa) != false){
				
				for (ConvocacaoCensoDto cc : listaConvocacao) {
					CensoPrevidenciario cp = new CensoPrevidenciario();
					cp.setDESC_atendente(mb.getNomeUsuario());
					cp.setDATA_recadastramento(new LocalDate().now().toDate());
					cp.setNUMR_idCenso(this.dadosCenso);
					cp.setNUMR_unidade(unidade);
					LocalDate local = new LocalDate().now();
					cp.setNUMR_recadastramento(local.getMonthOfYear()+""+local.getYear()+this.pessoa.getNUMR_cpf());
					cp.setFLAG_pendente(false);
					cp.setTipoBeneficiario(cc.getNUMR_tipoBeneficiario());
					cp.setNUMR_pessoasFuncionais(cc.getNUMR_idPessoasFuncionais());
					new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(cp);
					
				}
			}
			novo();
			res = "/paginas/censo/atualizacaoDados.xhtml?faces-redirect=true";
		}catch(Exception e){
			Message.addErrorMessage("Erro ao realizar recadastramento!");
		}
		return res;
	}
	
	public void novo(){
		pessoa = new Pessoas();
		pf = new PessoasFuncionais();
		endereco = new Enderecos();
		estado = new Estados();
		municipio = new Municipios();
		estadoCivil = new EstadoCivil();
		censo = new CensoPrevidenciario();
		unidade = new UnidadesCenso();
		this.dadosCenso = new DadosCenso();
		recarregaPagina();
	}
	
	private boolean salvaPessoa(Pessoas pes){
		boolean res = false;
		try{
			
			if(pes.getNUMG_idDoObjeto() > 0){
				new GenericPersistence<Pessoas>(Pessoas.class).salvar(pes);
				res = true;
			}
		}catch(Exception e){
			System.out.println("errou!");
		}
		return res;
	}
	
	public List<UnidadesCenso> getListaUnidadeCenso(){
		List<UnidadesCenso> lista = new GenericPersistence<UnidadesCenso>(UnidadesCenso.class).listarTodos("UnidadesCenso");
		return lista;
	}
	
	public List<SexoEnum> getSexoEnum(){
		List<SexoEnum> lista = Arrays.asList(SexoEnum.values());
		return lista;
	}
	
	public List<EstadoCivil> getListaEstadosCivis(){
		return new GenericPersistence<EstadoCivil>(EstadoCivil.class).listarTodos("EstadoCivil");
	}
	
	public List<TipoLogradouro> getListaTipoLogradouro(){
		return new GenericPersistence<TipoLogradouro>(TipoLogradouro.class).listarTodos("TipoLogradouro");
	}
	
	static List<Municipios> listaM = new ArrayList<Municipios>();
	public void populaMunicipios(){
		listaM = new GenericPersistence<Municipios>(Municipios.class).listarRelacionamento("Municipios", "NUMR_idDoObjetoEstado", this.estado.getNUMG_idDoObjeto());
	}
	
	public List<Municipios> getListaDeMunicipios(){
		return listaM;
	}
	
	public List<Estados> getListaDeEstados(){
		List<Estados> lista = new GenericPersistence<Estados>(Estados.class).listarTodos("Estados");
		return lista;
	}
	
	public List<TipoRepresentanteLegal> getListaTipoRepresentante(){
		return new GenericPersistence<TipoRepresentanteLegal>(TipoRepresentanteLegal.class).listarTodos("TipoRepresentanteLegal");
	}
	
	public void retornaEndereco(){
		try{
			CEPService buscaCep = CEPServiceFactory.getCEPService();
			CEP cep = buscaCep.obtemPorNumeroCEP(endereco.getNUMR_cep());
			endereco.setNUMR_cep(cep.getNumero());
			endereco.setDESC_bairro(cep.getBairro());
			endereco.setDESC_logradouro(cep.getLogradouro());
			municipio=new EnderecoDao().devolveMunicipioLike(cep.getLocalidade().toString());
			estado = municipio.getNUMR_idDoObjetoEstado();
		}catch(Exception e){
			Message.addErrorMessage("Cep não encontrado!");
		}
	}
	
	public void recarregaPagina(){
		/*ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		if(!ec.getFlash().isEmpty()){
			ec.getFlash().remove("funcional");
			ec.getFlash().remove("listaConvocacao");
			ec.getFlash().clear();
		}*/
		
		/*FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("funcional");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("listaConvocacao");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();*/
		
	}
	
	
}
